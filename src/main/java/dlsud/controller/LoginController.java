package dlsud.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import dlsud.request.login.LoginRequest;
import dlsud.response.login.LoginResponse;
import dlsud.service.login.LoginService;
import dlsud.utilities.AbstractController;

@Controller
@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequestMapping(value=LoginController.BASE_MAPPING)
public class LoginController extends AbstractController {
	
	public static final String BASE_MAPPING="dlsudReservation";
	
	@Autowired
	private LoginService loginService;
	
	private Log log;
	
	public LoginController(){
		log = LogFactory.getLog(LoginController.class);
	}

	@CrossOrigin
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public @ResponseBody String login(@RequestBody String request){
		LoginRequest loginRequest;
		LoginResponse loginResponse;
		String response=null;
		
		try{
			log.info(request);
			loginRequest = unmarshal(request, LoginRequest.class);
			loginResponse = loginService.handleRequest(loginRequest);
			response = marshal(loginResponse);
		}catch(Exception e){
			log.error(request, e);
		}
		return response;
	}
	
}
