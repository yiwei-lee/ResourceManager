package thu.cs.lyw.rm.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import thu.cs.lyw.rm.adapter.EC2Adapter;
import thu.cs.lyw.rm.adapter.RAdapter;
import thu.cs.lyw.rm.adapter.RAdapterFactory;
import thu.cs.lyw.rm.evaluation.REvaluation;
import thu.cs.lyw.rm.evaluation.REvaluator;
import thu.cs.lyw.rm.resource.RNode;
import thu.cs.lyw.rm.resource.RPool;
import thu.cs.lyw.rm.util.NodeType;
import thu.cs.lyw.rm.util.Provider;
import thu.cs.lyw.rm.util.ProviderType;
import thu.cs.lyw.rm.util.Strategy;

@SuppressWarnings("unused")
public class RManager {
	private RPool resourcePool;
	private int userId;
	private HashMap<ProviderType, ArrayList<Provider>> providerMap;
	private PriorityQueue<RTask> taskQueue;
	private HashMap<RTask, RNode> taskMap;
	private REvaluator evaluator;
	private Strategy strategy;
	
	public RManager(int id){
		userId = id;
		resourcePool = new RPool();
		providerMap = new HashMap<ProviderType, ArrayList<Provider>>();
		taskQueue = new PriorityQueue<RTask>();
		taskMap = new HashMap<RTask, RNode>();
		evaluator = new REvaluator();
	}
	//Provider should be added before using the system to manage resources.
	public void addProvider(Provider provider){
		resourcePool.initProvider(provider);
		ArrayList<Provider> providers = providerMap.get(provider.getType());
		if (providers == null){
			providers = new ArrayList<Provider>();
			providerMap.put(provider.getType(), providers);
		}
		providers.add(provider);
	}
	public RNode getNode(RTask task) {
		REvaluation evaluation = evaluator.evaluate(task);
		ArrayList<Provider> providers = providerMap.get(evaluation.provider);
		Provider provider = providers.get(0);
		RNode node = resourcePool.getNode(provider, evaluation);
		return node;
	}
	public void releaseNode(RNode node) {
		resourcePool.releaseNode(node);
	}
	//For test only;
	public static void main(String[] args) {
		//Provider info;
		Provider ec2Provider = new Provider(ProviderType.EC2);
		ec2Provider.addProperty("accessKey", "AKIAJOCSWO3APJUNZEGQ");
		ec2Provider.addProperty("secretKey", "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");
		//Generate task;
		RTask task = new RTask("MICRO", "ami-56e6a404");
		RManager manager = new RManager(0);
		manager.addProvider(ec2Provider);
		RNode node = manager.getNode(task);
		System.out.println("Now u got a EC2 instance. So...it's time to kill it!");
//		RTaskFeedback feedback = new RTaskFeedback();
		manager.releaseNode(node);
	}
	//Utilities used in RM;
}
