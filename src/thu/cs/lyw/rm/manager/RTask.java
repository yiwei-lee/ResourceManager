package thu.cs.lyw.rm.manager;

import thu.cs.lyw.rm.util.NodeType;
import thu.cs.lyw.rm.util.SystemImage;

public class RTask {
	private NodeType perferredType;
	private SystemImage image;
	public RTask(String perferredType, String system){
		this.perferredType = NodeType.valueOf(perferredType);
		image = new SystemImage(system);
	}
	public RTask(String system){
		this.perferredType = NodeType.MICRO;
		image = new SystemImage(system);
	}
	public NodeType getPerferredType(){
		return perferredType;
	}
	public SystemImage getImage(){
		return image;
	}
}

class RTaskFailure {
	
}

class RTaskFeedback {
	
}