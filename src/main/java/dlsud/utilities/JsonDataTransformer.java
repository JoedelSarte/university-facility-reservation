package dlsud.utilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonDataTransformer implements DataTransformer {
	
	private Gson  gson = new GsonBuilder().create();

	public JsonDataTransformer() {
		// Empty constructor.
	}

	@Override
	public <T> T unmarshal(String data, Class<T> clazz) {
		return gson.fromJson(data, clazz);
	}

	@Override
	public String marshal(Object data) {
		return gson.toJson(data);
	}

}
