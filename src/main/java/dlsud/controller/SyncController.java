package dlsud.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dlsud.response.sync.SyncResponse;
import dlsud.service.sync.SyncService;
import dlsud.utilities.AbstractController;

@CrossOrigin
@Controller
@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SyncController extends AbstractController {
	
	public static final String BASE_MAPPING = "sync";
	
	private Log log;
	
	public SyncController(){
		log = LogFactory.getLog(SyncController.class);
	}
	
	@Autowired
	private SyncService syncService;

	@RequestMapping(value=SyncController.BASE_MAPPING)
	public @ResponseBody String doGet(){
		String response = null;
		SyncResponse syncResponse = new SyncResponse();
		try{
			syncResponse = syncService.handleRequest();
			response = marshal(syncResponse);
		}catch(Exception e){
			log.error(e);
		}
		
		return response;
	}
}
