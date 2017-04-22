package dlsud.utilities;

public interface DataTransformer {

	<T> T unmarshal(String data, Class<T> clazz);

	String marshal(Object data);
}
