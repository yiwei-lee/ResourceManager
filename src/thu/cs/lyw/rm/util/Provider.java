package thu.cs.lyw.rm.util;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public class Provider {
	private ProviderType providerType;
	private String username;
	private HashMap<String, Object> properties;
	public Provider (ProviderType providerType){
		this.providerType = providerType;
		properties = new HashMap<String, Object>();
	}
	public void addProperty(String key, Object value){
		properties.put(key, value);
	}
	public void delProperty(String key){
		properties.remove(key);
	}
	public Object getProperty(String key){
		return properties.get(key);
	}
	public ProviderType getType(){
		return providerType;
	}
}