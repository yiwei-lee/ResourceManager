package thu.cs.lyw.rm.evaluation;

import thu.cs.lyw.rm.manager.RTask;
import thu.cs.lyw.rm.util.NodeType;
import thu.cs.lyw.rm.util.Strategy;

public class REvaluator {
	private Strategy strategy;
	public REvaluator(Strategy strategy){
		this.strategy = strategy;
	}
	public REvaluator(){
		this.strategy = Strategy.FIFO;
	}
	//Get evaluation;
	public REvaluation evaluate(RTask task){
		REvaluation evaluation = new REvaluation();
		NodeType perferredType = task.getPerferredType();
		evaluation.type = perferredType;
		evaluation.image = task.getImage();
		return evaluation;
	}
	//Sets and gets;
	public Strategy getStrategy(){
		return strategy;
	}
	public void setStrategy(Strategy strategy){
		this.strategy = strategy;
	}
}
