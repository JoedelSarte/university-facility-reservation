package dlsud.utilities;

import org.apache.commons.logging.Log;

public abstract class AbstractService<X extends Request, Y extends Response> implements BasicService<X, Y> {

	public AbstractService() {
		// Empty constructor.
	}

	@Override
	public Y handleRequest(String requestId, X request) {
		Y response = createResponse(requestId);
		try {
			validateRequest(request);
			handleRequest(requestId, request, response);
		}catch (Exception e) {
			Log  log = getLog();
			if (log != null) {
				log.error(requestId, e);
			}
			response.setCode(Response.CODE_FAILED);
		}
		return response;
	}

	protected abstract void handleRequest(String requestId, X request, Y response) throws Exception;

}
