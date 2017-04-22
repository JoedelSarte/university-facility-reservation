package dlsud.utilities;

public abstract class AbstractRequest implements Request {

	private int userId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
}
