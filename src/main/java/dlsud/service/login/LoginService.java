package dlsud.service.login;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import dlsud.entity.User;
import dlsud.repository.UserRepository;
import dlsud.request.login.LoginRequest;
import dlsud.response.login.LoginResponse;
import dlsud.utilities.AbstractService;
import dlsud.utilities.MessageUtils;

@Service
public class LoginService extends AbstractService<LoginRequest, LoginResponse> {

	private Log log;
	
	@Autowired
	private UserRepository userRepository;
	
	public LoginService() {
		log = LogFactory.getLog(LoginService.class);
	}
	
	@Override
	public Log getLog() {
		return log;
	}

	@Override
	public LoginResponse createResponse(String requestId) {
		
		return null;
	}

	@Override
	public void validateRequest(LoginRequest request) throws Exception {
		if(StringUtils.isEmpty(request.getUsername())){
			throw new Exception("Username can not be null!");
		}
		if(StringUtils.isEmpty(request.getPassword())){
			throw new Exception("Password can not be null!");
		}
	}

	@Override
	protected void handleRequest(String requestId, LoginRequest request, LoginResponse response) throws Exception {
		
	}
	
	public LoginResponse handleRequest(LoginRequest loginRequest){
		LoginResponse loginResponse= new LoginResponse();
		try{
			validateRequest(loginRequest);
			User validUser = userRepository.findByUsernameAndPassword(loginRequest.getUsername().trim(), loginRequest.getPassword());
			if(validUser!=null){
				loginResponse.setCode(LoginResponse.CODE_SUCCESS);
				loginResponse.setMessage(MessageUtils.LOGIN_SUCCESFUL);
				loginResponse.setUserId(validUser.getId());
				loginResponse.setTypeId(validUser.getTypeId());
				log.info(MessageUtils.LOGIN_SUCCESFUL);
			}else{
				loginResponse.setCode(LoginResponse.CODE_FAILED);
				loginResponse.setMessage(MessageUtils.LOGIN_INVALID_USERNAME_PASSWORD);
				log.info(MessageUtils.LOGIN_INVALID_USERNAME_PASSWORD);
			}
		}catch(Exception e){
			log.error(e.getMessage());
			loginResponse.setCode(LoginResponse.CODE_FAILED);
			loginResponse.setMessage(e.getMessage());
		}
		
		return loginResponse;
	}

}
