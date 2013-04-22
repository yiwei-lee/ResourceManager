package thu.cs.lyw.rm.manager;

import thu.cs.lyw.rm.util.NodeType;
import thu.cs.lyw.rm.util.ProviderType;
import thu.cs.lyw.rm.util.SystemImage;

public class RTask {
	private NodeType perferredType;
	private ProviderType providerType;
	private SystemImage image;
	public RTask(String providerType, String system, String perferredType){
		this.providerType = ProviderType.valueOf(providerType);
		this.perferredType = NodeType.valueOf(perferredType);
		image = new SystemImage(system);
	}
	public RTask(String providerType, String system){
		this.providerType = ProviderType.valueOf(providerType);;
		this.perferredType = NodeType.MICRO;
		image = new SystemImage(system);
	}
	public NodeType getPerferredType(){
		return perferredType;
	}
	public SystemImage getImage(){
		return image;
	}
	public ProviderType getProviderType(){
		return providerType;
	}
}

class RTaskFailure {
	
}

class RTaskFeedback {
	
}