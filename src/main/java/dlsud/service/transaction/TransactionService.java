package dlsud.service.transaction;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.joda.time.Hours;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.twilio.Twilio;
import com.twilio.exception.TwilioException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import dlsud.entity.Transaction;
import dlsud.entity.TransactionEquipment;
import dlsud.entity.TransactionPersonnel;
import dlsud.entity.User;
import dlsud.repository.TransactionEquipmentRepository;
import dlsud.repository.TransactionPersonnelRepository;
import dlsud.repository.TransactionRepository;
import dlsud.repository.UserRepository;
import dlsud.request.model.Transactions;
import dlsud.request.transaction.TransactionRequest;
import dlsud.response.transaction.TransactionResponse;
import dlsud.utilities.AbstractService;
import dlsud.utilities.MessageUtils;
import dlsud.utilities.Response;

@Service
public class TransactionService extends AbstractService<TransactionRequest, TransactionResponse> {

	private Log log;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private TransactionPersonnelRepository transactionPersonnelRepository;
	
	@Autowired
	private TransactionEquipmentRepository transactionEquipmentRepository;
	
	@Override
	public Log getLog() {
		return log;
	}
	
	public TransactionService() {
		log = LogFactory.getLog(TransactionService.class);
	}

	@Override
	public void validateRequest(TransactionRequest request) throws Exception {
		if (request.getEventName()==null && request.getEventName().isEmpty()) {
			throw new Exception("Event Name must be filled");
		}
		if (request.getActivityStartTime()==null && request.getActivityStartTime()==""){
			throw new Exception("Please specify start time");
		}
		if (request.getActivityEndTime()==null && request.getActivityEndTime()==""){
			throw new Exception("Please specify end time");
		}
	}
	
	public TransactionResponse doRequest(TransactionRequest request, int operation) throws Exception{
		TransactionResponse transactionResponse = new TransactionResponse();
		switch(operation){
			case 1: transactionResponse = handleRequest(request);
					break;
			case 2: transactionResponse = approveRequest(request);
					break;
			case 3: transactionResponse = disapproveRequest(request);
					break;
			case 4: transactionResponse = deleteRequest(request);
					break;
			case 5: transactionResponse = viewRequest(request);
					break;
			case 6: transactionResponse = viewUserRequest(request);
					break;
			case 7: transactionResponse = editRequest(request);
					break;
		}
		return transactionResponse;
	}
	
	private TransactionResponse editRequest(TransactionRequest request) {
		TransactionResponse transactionResponse = new TransactionResponse();
		
		try{
			DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
			DateTime startTime = dateTimeFormatter.parseDateTime(request.getActivityStartTime());
			DateTime endTime = dateTimeFormatter.parseDateTime(request.getActivityEndTime());
			Hours hours = Hours.hoursBetween(startTime, endTime);
			
			transactionRepository.editTransaction(request.getActivityStartTime(), request.getActivityEndTime(), hours.getHours()+"",request.getReferenceNumber());
			
			transactionResponse.setCode(TransactionResponse.CODE_SUCCESS);
			transactionResponse.setMessage(MessageUtils.TRANSACTION_PROCESSING_SUCCESS);
		}catch(Exception e){
			log.error(e.getMessage());
			transactionResponse.setCode(Response.CODE_FAILED);
			transactionResponse.setMessage(MessageUtils.TRANSACTION_PROCESSING_FAIL);
			e.printStackTrace();
		}
		
		return transactionResponse;
	}

	private TransactionResponse viewUserRequest(TransactionRequest request) {
		TransactionResponse transactionResponse = new TransactionResponse();
		try{
			List<Transaction> transaction = transactionRepository.
					findByActivityStartTimeAndFacilityIdAndUserIdOrderByActivityStartTimeAsc(request.getRequestDate(), request.getFacilityId(), request.getUserId());
			List<Transactions> transactionList = new ArrayList<>();
			for(Transaction data:transaction){
				Transactions transactions = new Transactions();
				List<TransactionPersonnel> transactionPersonnelList = transactionPersonnelRepository.findByTransactionId(data.getId());
				List<TransactionEquipment> transactionEquipmentList = transactionEquipmentRepository.findByTransactionId(data.getId());
				
				transactions.setTransactionPersonnelList(transactionPersonnelList);
				transactions.setTransactionEquipmentList(transactionEquipmentList);
				transactions.setReferenceNumber(data.getReferenceNumber());
				transactions.setEventName(data.getEventName());
				transactions.setActivityStartTime(data.getActivityStartTime());
				transactions.setActivityEndTime(data.getActivityEndTime());
				transactions.setIsApproved(data.getIsApproved());
				transactions.setIsPaid(data.getIsPaid());
				transactions.setFacilityPayment(data.getFacilityPayment());
				transactions.setTotalPayment(data.getTotalPayment());
				transactions.setEquipmentPayment(transactionEquipmentList.remove(0).getTotalAmount());
				transactions.setPersonnelPayment(transactionPersonnelList.remove(0).getTotalAmount());
				
				transactionList.add(transactions);
			}
			
			if(transactionList.size()==0){
				transactionList = null;
			}
			
			transactionResponse.setTransactions(transactionList);
			
			transactionResponse.setCode(TransactionResponse.CODE_SUCCESS);
		}catch(Exception e){
			log.error(e.getMessage());
			transactionResponse.setCode(Response.CODE_FAILED);
			transactionResponse.setMessage(MessageUtils.TRANSACTION_PROCESSING_FAIL);
			e.printStackTrace();
		}
		return transactionResponse;
	}

	private TransactionResponse viewRequest(TransactionRequest request) {
		
		TransactionResponse transactionResponse = new TransactionResponse();
		try{
			List<Transaction> transaction = transactionRepository.
					findByActivityStartTimeAndFacilityIdAndIsApprovedOrderByActivityStartTimeAsc(request.getRequestDate(), request.getFacilityId(), request.getStatus());
			List<Transactions> transactionList = new ArrayList<>();
			for(Transaction data:transaction){
				Transactions transactions = new Transactions();
				List<TransactionPersonnel> transactionPersonnelList = transactionPersonnelRepository.findByTransactionId(data.getId());
				List<TransactionEquipment> transactionEquipmentList = transactionEquipmentRepository.findByTransactionId(data.getId());
				
				transactions.setTransactionPersonnelList(transactionPersonnelList);
				transactions.setTransactionEquipmentList(transactionEquipmentList);
				transactions.setReferenceNumber(data.getReferenceNumber());
				transactions.setEventName(data.getEventName());
				transactions.setActivityStartTime(data.getActivityStartTime());
				transactions.setActivityEndTime(data.getActivityEndTime());
				transactions.setIsApproved(data.getIsApproved());
				transactions.setIsPaid(data.getIsPaid());
				transactions.setFacilityPayment(data.getFacilityPayment());
				transactions.setTotalPayment(data.getTotalPayment());
				transactions.setEquipmentPayment(transactionEquipmentList.remove(0).getTotalAmount());
				transactions.setPersonnelPayment(transactionPersonnelList.remove(0).getTotalAmount());
				
				transactionList.add(transactions);
			}
			
			if(transactionList.size()==0){
				transactionList = null;
			}
			
			transactionResponse.setTransactions(transactionList);
			
			transactionResponse.setCode(TransactionResponse.CODE_SUCCESS);
		}catch(Exception e){
			log.error(e.getMessage());
			transactionResponse.setCode(Response.CODE_FAILED);
			transactionResponse.setMessage(MessageUtils.TRANSACTION_PROCESSING_FAIL);
			e.printStackTrace();
		}
		return transactionResponse;
	}

	private TransactionResponse deleteRequest(TransactionRequest request) {
		
		TransactionResponse transactionResponse = new TransactionResponse();
		
		try{
			Transaction transaction = transactionRepository.findByReferenceNumber(request.getReferenceNumber());
			transactionPersonnelRepository.deleteRequest(transaction.getId());
			transactionEquipmentRepository.deleteRequest(transaction.getId());
			transactionRepository.deleteRequest(request.getReferenceNumber());
			transactionResponse.setCode(TransactionResponse.CODE_SUCCESS);
			transactionResponse.setMessage(MessageUtils.TRANSACTION_PROCESSING_SUCCESS);
		}catch(Exception e){
			log.error(e.getMessage());
			transactionResponse.setCode(Response.CODE_FAILED);
			transactionResponse.setMessage(MessageUtils.TRANSACTION_PROCESSING_FAIL);
			e.printStackTrace();
		}
		return transactionResponse;
	}

	private TransactionResponse disapproveRequest(TransactionRequest request) throws Exception {

		TransactionResponse transactionResponse = new TransactionResponse();
		User user = userRepository.findById(request.getUserId());
		
		try{
			transactionRepository.disapproveTransaction(request.getReferenceNumber());
			transactionResponse.setCode(TransactionResponse.CODE_SUCCESS);
			transactionResponse.setMessage(MessageUtils.TRANSACTION_PROCESSING_SUCCESS);
			String message = buildMessage(MessageUtils.APPROVE_TRANSACTION,request.getReferenceNumber());
			sendSms(message,user);
		}catch(TwilioException e){
			log.error(e.getMessage());
			transactionResponse.setMessage("Sending of SMS failed");
		}catch(Exception e){
			log.error(e.getMessage());
			transactionResponse.setCode(Response.CODE_FAILED);
			transactionResponse.setMessage(MessageUtils.TRANSACTION_PROCESSING_FAIL);
			e.printStackTrace();
		}
		return transactionResponse;
	}

	private TransactionResponse approveRequest(TransactionRequest request) throws Exception {
		
		TransactionResponse transactionResponse = new TransactionResponse();
		User user = userRepository.findById(request.getUserId());
		
		try{
			transactionRepository.approveTransaction(request.getReferenceNumber());
			transactionResponse.setCode(TransactionResponse.CODE_SUCCESS);
			transactionResponse.setMessage(MessageUtils.TRANSACTION_PROCESSING_SUCCESS);
			String message = buildMessage(MessageUtils.APPROVE_TRANSACTION,request.getReferenceNumber());
			sendSms(message,user);
		}catch(TwilioException e){
			log.error(e.getMessage());
			transactionResponse.setMessage("Sending of SMS failed");
		}catch(Exception e){
			log.error(e.getMessage());
			transactionResponse.setCode(Response.CODE_FAILED);
			transactionResponse.setMessage(MessageUtils.TRANSACTION_PROCESSING_FAIL);
			e.printStackTrace();
		}
		return transactionResponse;
	}

	public TransactionResponse handleRequest(TransactionRequest request)
			throws Exception {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String date = format.format(new Date());
		long referenceNumber = System.currentTimeMillis();
		TransactionResponse transactionResponse = new TransactionResponse();
		
		try{
			validateRequest(request);
			User user = userRepository.findById(request.getUserId());
			String reference = date + referenceNumber;
			Transaction transaction = new Transaction();
			DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
			DateTime startTime = dateTimeFormatter.parseDateTime(request.getActivityStartTime());
			DateTime endTime = dateTimeFormatter.parseDateTime(request.getActivityEndTime());
			Hours hours = Hours.hoursBetween(startTime, endTime);
			
			transaction.setReferenceNumber(reference);
			transaction.setEventName(request.getEventName());
			transaction.setFacilityId(request.getFacilityId());
			transaction.setActivityId(request.getActivityId());
			transaction.setUserId(request.getUserId());
			transaction.setActivityStartTime(request.getActivityStartTime());
			transaction.setActivityEndTime(request.getActivityEndTime());
			transaction.setActivityTotalTime(hours.getHours()+"");
			if(user.getTypeId()==3){
				transaction.setIsApproved(1);
			}else{
				transaction.setIsApproved(2);
			}
			transaction.setFacilityPayment(request.getFacilityPayment());
			transaction.setTotalPayment(request.getTotalPayment());
			transaction.setIsPaid(0);
			transaction = transactionRepository.save(transaction);
			
			List<TransactionPersonnel> transactionPersonnelList = new ArrayList<>();
			
			for(TransactionPersonnel transactionPersonnelData:request.getPersonnelList()){
				TransactionPersonnel transactionPersonnel = new TransactionPersonnel();
				transactionPersonnel.setPersonnelId(transactionPersonnelData.getPersonnelId());
				transactionPersonnel.setPersonnelPayment(transactionPersonnelData.getPersonnelPayment());
				transactionPersonnel.setTotalAmount(request.getPersonnelTotalPayment());
				transactionPersonnel.setTransactionId(transaction.getId());
				transactionPersonnelList.add(transactionPersonnel);
			}
			
			transactionPersonnelRepository.save(transactionPersonnelList);
			
			List<TransactionEquipment> transactionEquipmentList = new ArrayList<>();
			
			for(TransactionEquipment transactionEquipmentData:request.getEquipmentList()){
				TransactionEquipment transactionEquipment = new TransactionEquipment();
				transactionEquipment.setEquipmentId(transactionEquipmentData.getEquipmentId());
				transactionEquipment.setEquipmentPayment(transactionEquipmentData.getEquipmentPayment());
				transactionEquipment.setTotalAmount(request.getEquipmentTotalPayment());
				transactionEquipment.setTransactionId(transaction.getId());
				transactionEquipmentList.add(transactionEquipment);
			}
			
			transactionEquipmentRepository.save(transactionEquipmentList);
			
			transactionResponse.setReferenceNumber(reference);
			transactionResponse.setCode(TransactionResponse.CODE_SUCCESS);
			transactionResponse.setMessage(MessageUtils.PROCESS_TRANSACTION_SMS+reference);
			String message = buildMessage(MessageUtils.PROCESS_TRANSACTION,reference);
			sendSms(message,user);
		
			
		}catch(TwilioException e){
			log.error(e.getMessage());
			transactionResponse.setMessage("Sending of SMS failed");
		}
		catch(Exception e){
			log.error(e.getMessage());
			transactionResponse.setCode(Response.CODE_FAILED);
			transactionResponse.setMessage(MessageUtils.TRANSACTION_FAIL);
			e.printStackTrace();
		}
		
		return transactionResponse;
	}
	
	@Override
	public TransactionResponse createResponse(String requestId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	protected void handleRequest(String requestId, TransactionRequest request, TransactionResponse response)
			throws Exception {
		
	}
	
	
	
private void sendSms(String messageDtl,User user){
		
		PhoneNumber TWILIO_PHONE_NUMBER = new PhoneNumber("+18589142588");
		
		List<String> userContactNumber = new ArrayList<String>();
		if(!StringUtils.isEmpty(user.getContactNumber1())){
			String formattedNumber = formatContactNumberForTwilio(user.getContactNumber1());
			userContactNumber.add(formattedNumber);
		}
		if(!StringUtils.isEmpty(user.getContactNumber2())){
			String formattedNumber = formatContactNumberForTwilio(user.getContactNumber2());
			userContactNumber.add(formattedNumber);
		}
		if(!StringUtils.isEmpty(user.getContactNumber3())){
			String formattedNumber = formatContactNumberForTwilio(user.getContactNumber3());
			userContactNumber.add(formattedNumber);
		}
		
		try{
			initializeTwilio();
		}catch(TwilioException te){
			log.error(te.getMessage());
			te.printStackTrace();
		}
		
		for(String number :userContactNumber){
			//Use Twilio Api to Send
			Message.creator(new PhoneNumber(number),TWILIO_PHONE_NUMBER,messageDtl).create();
		}
	 
	}
	
	private String formatContactNumberForTwilio(String contactNumber) {
		StringBuffer formattedNumber = new StringBuffer(contactNumber);
		String PH = "+63";
		formattedNumber.replace(0, 1, PH);
		return formattedNumber.toString();
	}
	
	private String buildMessage(Integer transactionType,String refNo) {
		
		StringBuffer message = new StringBuffer();
		
		if(transactionType.equals(MessageUtils.PROCESS_TRANSACTION)){
			message.append(MessageUtils.PROCESS_TRANSACTION_SMS+refNo);
		}else if(transactionType.equals(MessageUtils.APPROVE_TRANSACTION)){
			message.append(MessageUtils.APPROVE_TRANSACTION_SMS+refNo);
		}else if(transactionType.equals(MessageUtils.REJECT_TRANSACTION)){
			message.append(MessageUtils.REJECT_TRANSACTION_SMS+refNo);
		}
		return message.toString();
	}

	private void initializeTwilio() throws TwilioException{
		//Your Twilio Account
		String ACCOUNT_SID ="AC9ca5dc3b282fd214a2e0ae2a00e82ed0";
	   	String AUTH_TOKEN = "32bbcf2cae9c813702c2f780d6ff822e";
	   	Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
	}


}
