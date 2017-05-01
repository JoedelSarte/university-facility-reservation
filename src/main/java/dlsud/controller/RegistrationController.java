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

import dlsud.request.registration.RegistrationRequest;
import dlsud.response.registration.RegistrationResponse;
import dlsud.service.registration.RegistrationService;
import dlsud.utilities.AbstractController;

@Controller
@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequestMapping(value=RegistrationController.BASE_MAPPING)
public class RegistrationController extends AbstractController {
	
	public static final String BASE_MAPPING="dlsudRegistration";
	
	@Autowired
	private RegistrationService registrationService;
	
	private Log log;
	
	public RegistrationController(){
		log = LogFactory.getLog(RegistrationController.class);
	}

	@CrossOrigin
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public @ResponseBody String register(@RequestBody String request){
		RegistrationRequest registrationRequest;
		RegistrationResponse RegistrationResponse;
		String response=null;
		
		try{
			log.info(request);
			registrationRequest = unmarshal(request, RegistrationRequest.class);
			RegistrationResponse = registrationService.handleRequest(registrationRequest);
			response = marshal(RegistrationResponse);
		}catch(Exception e){
			log.error(request, e);
		}
		return response;
	}
	
}
