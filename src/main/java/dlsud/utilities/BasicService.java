package dlsud.utilities;

import org.apache.commons.logging.Log;

public interface BasicService <X extends Request, Y extends Response> {

	Log  getLog();

	Y handleRequest(String requestId, X request);

	Y createResponse(String requestId);

	void validateRequest(X request) throws Exception;
}
