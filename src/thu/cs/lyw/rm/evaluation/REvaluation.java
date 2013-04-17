package thu.cs.lyw.rm.evaluation;

import thu.cs.lyw.rm.util.NodeType;
import thu.cs.lyw.rm.util.ProviderType;
import thu.cs.lyw.rm.util.SystemImage;

public class REvaluation {
	public ProviderType provider;
	public SystemImage image;
	public NodeType type;
	public double cpu;
	public double memory;
	public double disk;
	public REvaluation(){
		provider = ProviderType.EC2;
		type = NodeType.MICRO;
		cpu = 0;
		memory = 0;
		disk = 0;
	}
}
