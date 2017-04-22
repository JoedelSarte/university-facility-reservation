package dlsud.utilities;

public interface Response {

	public static final int CODE_FAILED = 0;
	public static final int CODE_SUCCESS = 1;

	int getCode();

	void setCode(int code);

	String getMessage();

	void setMessage(String message);
}
