package dlsud.service.registration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import dlsud.entity.User;
import dlsud.repository.UserRepository;
import dlsud.request.registration.RegistrationRequest;
import dlsud.response.registration.RegistrationResponse;
import dlsud.utilities.AbstractService;
import dlsud.utilities.MessageUtils;
import dlsud.utilities.Response;

@Service
public class RegistrationService extends AbstractService<RegistrationRequest, RegistrationResponse> {
	
	private Log log;
	
	@Autowired
	private UserRepository userRepository;
	
	public RegistrationService() {
		log = LogFactory.getLog(RegistrationService.class);
	}
	
	@Override
	public Log getLog() {
		return log;
	}

	@Override
	public RegistrationResponse createResponse(String requestId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void validateRequest(RegistrationRequest request) throws Exception {

		if(StringUtils.isEmpty(request.getUsername())){
			throw new Exception(MessageUtils.REGISTRATION_MISSING_REQUIRED_FIELD);
		}
		if(StringUtils.isEmpty(request.getPassword())){
			throw new Exception(MessageUtils.REGISTRATION_MISSING_REQUIRED_FIELD);
		}
		if(StringUtils.isEmpty(request.getTypeId())){
			throw new Exception(MessageUtils.REGISTRATION_MISSING_REQUIRED_FIELD);
		}
		
		User existingUserIdNumber = userRepository.findByIdNumber(request.getIdNumber());
		User existingUserUsr= userRepository.findByUsername(request.getUsername());
		
		if(request.getTypeId()!=4&&request.getTypeId()!=5){
			if(!StringUtils.isEmpty(request.getIdNumber()) && existingUserIdNumber!=null){
				throw new Exception(MessageUtils.REGISTRATION_EXISTING_USER);
			}
		}
		if(!StringUtils.isEmpty(request.getUsername()) && existingUserUsr!=null){
			throw new Exception(MessageUtils.REGISTRATION_EXISTING_USER);
		}
		
	}

	@Override
	protected void handleRequest(String requestId, RegistrationRequest request, RegistrationResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public RegistrationResponse handleRequest(RegistrationRequest registrationRequest){
		RegistrationResponse registrationResponse= new RegistrationResponse();
		User user;
		try{
			validateRequest(registrationRequest);
			user = new User();
			user.setUsername(registrationRequest.getUsername());
			user.setPassword(registrationRequest.getPassword());
			user.setAddress(registrationRequest.getAddress());
			user.setCivilStatus(registrationRequest.getCivilStatus());
			user.setCollegeId(registrationRequest.getCollegeId());
			user.setContactNumber1(registrationRequest.getContactNumber1());
			user.setContactNumber2(registrationRequest.getContactNumber2());
			user.setContactNumber3(registrationRequest.getContactNumber3());
			user.setCys(registrationRequest.getCys());
			user.setEmail(registrationRequest.getEmail());
			user.setGender(registrationRequest.getGender());
			user.setIdNumber(registrationRequest.getIdNumber());
			user.setName(registrationRequest.getName());
			user.setReligion(registrationRequest.getReligion());
			user.setTypeId(registrationRequest.getTypeId());
			userRepository.save(user);
			registrationResponse.setCode(Response.CODE_SUCCESS);
			registrationResponse.setMessage(MessageUtils.REGISTRATION_SUCCESSFUL);
		}catch(Exception e){
			log.error(e.getMessage());
			registrationResponse.setCode(Response.CODE_FAILED);
			registrationResponse.setMessage(MessageUtils.REGISTRATION_FAIL);
			
		}
		return registrationResponse;
	}

}
