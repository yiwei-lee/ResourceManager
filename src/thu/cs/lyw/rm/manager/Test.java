package thu.cs.lyw.rm.manager;

import thu.cs.lyw.rm.resource.RNode;
import thu.cs.lyw.rm.util.Provider;
import thu.cs.lyw.rm.util.ProviderType;

public class Test {
	public static void main(String[] args) {
		//Initiate providers & manager;
		Provider ec2Provider = new Provider(ProviderType.EC2);
		ec2Provider.addProperty("accessKey", "AKIAJOCSWO3APJUNZEGQ");
		ec2Provider.addProperty("secretKey", "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");
		
		Provider openStackProvider = new Provider(ProviderType.OpenStack);
		openStackProvider.addProperty("username", "lywthu");
		openStackProvider.addProperty("password", "liyiwei!@#900614");
		
		RManager manager = new RManager(0);
		manager.addProvider(ec2Provider);
		manager.addProvider(openStackProvider);
		
		//Generate task;
		RTask task1 = new RTask(ProviderType.OpenStack, "84536", "MICRO");
		RTask task2 = new RTask(ProviderType.EC2, "ami-56e6a404", "MICRO");
		
		//Get nodes;
		RNode node1 = manager.getNode(task1);
		RNode node2 = manager.getNode(task2);
		if (node1 == null || node2 == null){
			System.err.println("Error : cannot get available node.");
			return;
		}
		System.out.println("Test : Now u got VM instances. So...it's time to kill them!");
//		RTaskFeedback feedback = new RTaskFeedback();
		manager.releaseNode(node1);
		manager.releaseNode(node2);
	}
}
