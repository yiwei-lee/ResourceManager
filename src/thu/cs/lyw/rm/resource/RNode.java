package thu.cs.lyw.rm.resource;

import thu.cs.lyw.rm.util.ProviderType;

public class RNode {
	private String IP;
	private ProviderType providerType;
	public RNode(ProviderType providerType){
		this.providerType = providerType;
	}
	public void setIP(String IP){
		this.IP = IP;
	}
	public String getIP(){
		return IP;
	}
	public ProviderType getProviderType(){
		return providerType;
	}
}
