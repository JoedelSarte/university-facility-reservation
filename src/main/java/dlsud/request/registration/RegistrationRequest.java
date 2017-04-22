package dlsud.request.registration;

import dlsud.entity.User;
import dlsud.utilities.Request;

public class RegistrationRequest extends User implements Request{
	
	private int userId;

	@Override
	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public int getUserId() {
		return userId;
	}
	
}
