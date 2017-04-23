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

import dlsud.request.transaction.TransactionRequest;
import dlsud.response.transaction.TransactionResponse;
import dlsud.service.transaction.TransactionService;
import dlsud.utilities.AbstractController;


@Controller
@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequestMapping(value=TransactionController.BASE_MAPPING)
public class TransactionController extends AbstractController{
	
	
		public static final String BASE_MAPPING="transaction";
		
		@Autowired
		private TransactionService transactionService;
		
		private Log log;
		
		public TransactionController(){
			log = LogFactory.getLog(TransactionController.class);
		}
		
		@CrossOrigin
		@RequestMapping(value="/process", method=RequestMethod.POST)
		public @ResponseBody String processTransaction(@RequestBody String request){
			return doTransaction(request, 1);
		}

		@CrossOrigin
		@RequestMapping(value="/approve", method=RequestMethod.POST)
		public @ResponseBody String  approveTransaction(@RequestBody String request){
			return doTransaction(request, 2);
		}
		
		@CrossOrigin
		@RequestMapping(value="/disapprove", method=RequestMethod.POST)
		public @ResponseBody String rejectTransaction(@RequestBody String request){
			return doTransaction(request, 3);
		}
		
		@CrossOrigin
		@RequestMapping(value="/delete", method=RequestMethod.POST)
		public @ResponseBody String deleteTransaction(@RequestBody String request){
			return doTransaction(request, 4);
		}
		
		@CrossOrigin
		@RequestMapping(value="/adminView", method=RequestMethod.POST)
		public @ResponseBody String viewTransaction(@RequestBody String request){
			return doTransaction(request, 5);
		}
		
		@CrossOrigin
		@RequestMapping(value="/userView", method=RequestMethod.POST)
		public @ResponseBody String viewTransactionPerUser(@RequestBody String request){
			return doTransaction(request, 6);
		}
		
		public String doTransaction(String request, int operationId){
			TransactionRequest transactionRequest;
			TransactionResponse transactionResponse;
			String responseData = null;
			
			try{
				log.info(request);
				transactionRequest = unmarshal(request, TransactionRequest.class);
				transactionResponse = transactionService.doRequest(transactionRequest, operationId);
				responseData = marshal(transactionResponse);
			}catch(Exception e){
				log.error(request, e);
			}
			
			return responseData;
		}

}
