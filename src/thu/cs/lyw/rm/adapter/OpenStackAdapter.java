package thu.cs.lyw.rm.adapter;

import com.sun.jersey.api.client.Client;
import thu.cs.lyw.rm.evaluation.REvaluation;
import thu.cs.lyw.rm.resource.RNode;
import thu.cs.lyw.rm.util.Provider;

public class OpenStackAdapter extends RAdapter {
	private static Client client  = Client.create();
	
	@Override
	public void initProvider(Provider provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public RNode getNodeFromProvider(Provider provider, REvaluation evaluation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void releaseNodeFromProvider(RNode node) {
		// TODO Auto-generated method stub
		
	}

}
