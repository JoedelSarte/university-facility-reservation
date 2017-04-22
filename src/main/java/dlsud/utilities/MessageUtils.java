package dlsud.utilities;

public class MessageUtils {
	
	public static String LOGIN_SUCCESFUL = "Login Succesful";
	public static String LOGIN_INVALID_USERNAME_PASSWORD = "Username/Password is invalid";
	public static String REGISTRATION_SUCCESSFUL = "Succesfully Registered User";
	public static String REGISTRATION_FAIL = "Registration Fail";
	public static String REGISTRATION_MISSING_REQUIRED_FIELD = "Please Complete Required Fields";
	public static String REGISTRATION_EXISTING_USER = "Existing User";
	
	
	public static String APPROVE_TRANSACTION_SMS = "Your Transaction has been rejected. Your reference number is ";
	public static String REJECT_TRANSACTION_SMS = "Your Transaction has been approved. Your reference number is ";;
	public static String PROCESS_TRANSACTION_SMS = "Your request is now on process. Wait for the confirmation for further details. Your reference number is ";
	public static String TRANSACTION_FAIL = "Your transaction has failed to be created.";
	public static String TRANSACTION_PROCESSING_FAIL = "Processing of the selected transaction failed.";
	public static String TRANSACTION_PROCESSING_SUCCESS = "Processing of the selected transaction succeed";
	
	public static int PROCESS_TRANSACTION = 0;
	public static int APPROVE_TRANSACTION = 1;
	public static int REJECT_TRANSACTION = 2;

}
