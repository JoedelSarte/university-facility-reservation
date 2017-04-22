package dlsud.response.login;

import dlsud.utilities.AbstractResponse;

public class LoginResponse extends AbstractResponse {

	private int userId;
	private int typeId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

}
