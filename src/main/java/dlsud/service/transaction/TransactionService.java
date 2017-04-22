package dlsud.service.transaction;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.type.IntegerType;
import org.joda.time.DateTime;
import org.joda.time.Hours;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import dlsud.entity.Personnel;
import dlsud.entity.Rate;
import dlsud.entity.Transaction;
import dlsud.entity.TransactionDetails;
import dlsud.entity.User;
import dlsud.repository.PersonnelRepository;
import dlsud.repository.RateRepository;
import dlsud.repository.TransactionDetailsRepository;
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
	private PersonnelRepository personnelRepository;
	
	@Autowired
	private RateRepository rateRepository;
	
	@Autowired
	private TransactionDetailsRepository transactionDetailsRepository;
	
	@Override
	public Log getLog() {
		return log;
	}
	
	public TransactionService() {
		log = LogFactory.getLog(TransactionService.class);
	}

	@Override
	public void validateRequest(TransactionRequest request) throws Exception {
		if (request.getReservedCapacity()==null && request.getReservedCapacity()==0) {
			throw new Exception("Reserved capacity must be filled");
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
		}
		return transactionResponse;
	}
	
	private TransactionResponse viewRequest(TransactionRequest request) {
		
		TransactionResponse transactionResponse = new TransactionResponse();
		try{
			List<Transaction> transaction = transactionRepository.findByActivityStartTimeAndFacilityId(request.getRequestDate(), request.getFacilityId());
			List<Transactions> transactionList = new ArrayList<>();
			System.out.println(transaction.size());
			for(Transaction data:transaction){
				Transactions transactions = new Transactions();
				transactions.setReferenceNumber(data.getReferenceNumber());
				transactions.setEventName(data.getEventName());
				transactions.setActivityStartTime(data.getActivityStartTime());
				transactions.setActivityEndTime(data.getActivityEndTime());
				User user = userRepository.findById(data.getUserId());
				transactions.setUserName(user.getName());
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
			transactionDetailsRepository.deleteRequest(transaction.getId());
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
		
		try{
			transactionRepository.disapproveTransaction(request.getReferenceNumber());
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

	private TransactionResponse approveRequest(TransactionRequest request) throws Exception {
		
		TransactionResponse transactionResponse = new TransactionResponse();
		
		try{
			transactionRepository.approveTransaction(request.getReferenceNumber());
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
			transaction.setUserId(request.getUserId());
			transaction.setActivityEndTime(request.getActivityEndTime());
			transaction.setActivityStartTime(request.getActivityStartTime());
			transaction.setActivityTotalTime(hours.getHours()+"");
			transaction.setFacilityId(request.getFacilityId());
			transaction.setIsApproved(2);
			transaction.setReservedCapacity(request.getReservedCapacity());
			transaction.setActivityId(request.getActivityId());
			transaction.setEventName(request.getEventName());
			transaction = transactionRepository.save(transaction);
			
			List<TransactionDetails> transactionDetailList = new ArrayList<>();
			
			Double personnelPayment = 0.00;
			Double facilityPayment = 0.00;
			Double totalPayment = 0.00;
			
			for(Integer personnelId:request.getPersonnelHiredList()){
				Personnel personnel = personnelRepository.findById(personnelId);
				personnelPayment += personnel.getRate()*hours.getHours();
			}
			Rate rate = rateRepository.findByFacilityIdAndUserTypeId(request.getFacilityId(), user.getTypeId());
			facilityPayment = rate.getRatePerHour()*hours.getHours();
			totalPayment = personnelPayment+facilityPayment;
			
			for(Integer personnelId:request.getPersonnelHiredList()){
				TransactionDetails transactionDetails = new TransactionDetails();
				transactionDetails.setIsPaid(0);
				transactionDetails.setPersonnelRateId(personnelId);
				transactionDetails.setTransactionId(transaction.getId());
				transactionDetails.setTransactionPayment(totalPayment);
				transactionDetails.setTransactionRateId(rate.getId());
				transactionDetailList.add(transactionDetails);
			}
			
			transactionDetailsRepository.save(transactionDetailList);
			
			transactionResponse.setCode(TransactionResponse.CODE_SUCCESS);
			transactionResponse.setMessage(MessageUtils.PROCESS_TRANSACTION_SMS+reference);
			
		}catch(Exception e){
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
	
	/*public void SendSms(){
		String ACCOUNT_SID ="AC9ca5dc3b282fd214a2e0ae2a00e82ed0";
	   	String AUTH_TOKEN = "32bbcf2cae9c813702c2f780d6ff822e";
	   	Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
	   	Message message = Message.creator(new PhoneNumber("+639750646891"),new PhoneNumber("+18589142588"), "Hello lels").create();
	   	System.out.println(	message.getDateSent());
	   	System.out.println(message.getSid());
		System.out.println(message.getStatus());
	}
	
	private String buildMessage(int transactionType,Transaction transaction){
		String message = null;
		if(transactionType == MessageUtils.PROCESS_TRANSACTION){
			message = MessageUtils.PROCESS_TRANSACTION_SMS;
			message = message.replace("{0}", transaction.getId() + "");
		}
		else if (transactionType == MessageUtils.APPROVE_TRANSACTION){
			
		}
		else if (transactionType == MessageUtils.REJECT_TRANSACTION){
			
		}
		return message;
	}*/
}
