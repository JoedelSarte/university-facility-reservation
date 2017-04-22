package dlsud.utilities;

public abstract class AbstractController {

	private final DataTransformer dataTransformer;

	public AbstractController() {
		dataTransformer = new JsonDataTransformer();
	}

	public <T> T unmarshal(String data, Class<T> clazz) {
		return dataTransformer.unmarshal(data, clazz);
	}

	public String marshal(Object data) {
		return dataTransformer.marshal(data);
	}
}
