package thu.cs.lyw.rm.adapter;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.AuthorizeSecurityGroupIngressRequest;
import com.amazonaws.services.ec2.model.CreateKeyPairRequest;
import com.amazonaws.services.ec2.model.CreateSecurityGroupRequest;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.IpPermission;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.RunInstancesResult;
import thu.cs.lyw.rm.evaluation.REvaluation;
import thu.cs.lyw.rm.resource.RNode;
import thu.cs.lyw.rm.util.Provider;

public class EC2Adapter extends RAdapter {
	@Override
	public void initProvider(Provider provider) {
		try{
			AWSCredentials myCredential = new BasicAWSCredentials((String)provider.getProperty("accessKey"), (String)provider.getProperty("secretKey"));
			AmazonEC2 ec2 = new AmazonEC2Client(myCredential);
			ec2.setEndpoint("ec2.ap-southeast-1.amazonaws.com");
			CreateSecurityGroupRequest createSecurityGroupRequest = 
					new CreateSecurityGroupRequest();
			createSecurityGroupRequest.withGroupName("RMSecurityGroup")
					.withDescription("Resource Manager Security Group");
			try{
				ec2.createSecurityGroup(createSecurityGroupRequest);
			}catch(AmazonServiceException ase){
				System.out.println(ase.getMessage());
			}
			IpPermission ipPermission = new IpPermission();
			ipPermission.withIpRanges("166.111.0.0/16", "166.111.0.0/16")
				.withIpProtocol("tcp")
				.withFromPort(22)
				.withToPort(22);
			AuthorizeSecurityGroupIngressRequest authorizeSecurityGroupIngressRequest =
					new AuthorizeSecurityGroupIngressRequest();
			authorizeSecurityGroupIngressRequest.withGroupName("RMSecurityGroup")
				.withIpPermissions(ipPermission);
			try{
				ec2.authorizeSecurityGroupIngress(authorizeSecurityGroupIngressRequest);
			}catch(AmazonServiceException ase){
				System.out.println(ase.getMessage());
			}
			CreateKeyPairRequest createKeyPairRequest = 
					new CreateKeyPairRequest();
			createKeyPairRequest.withKeyName("RM.EC2.Test");
			try{
				ec2.createKeyPair(createKeyPairRequest);
			}catch(AmazonServiceException ase){
				System.out.println(ase.getMessage());
			}
			provider.addProperty("AmazonEC2", ec2);
		} catch (AmazonServiceException ase) {
			System.out.println("Caught Exception: " + ase.getMessage());
			System.out.println("Reponse Status Code: " + ase.getStatusCode());
			System.out.println("Error Code: " + ase.getErrorCode());
			System.out.println("Request ID: " + ase.getRequestId());
		}
	}
	@Override
	public RNode getNodeFromProvider(Provider provider, REvaluation evaluation) {
		RNode node = new RNode(provider.getType());
		RunInstancesRequest runInstancesRequest = new RunInstancesRequest()
		.withImageId(evaluation.image.getImageName())
		.withInstanceType("t1.micro")
		.withMinCount(1)
		.withMaxCount(1)
		.withKeyName("RM.EC2.Test")
		.withSecurityGroups("RMSecurityGroup");
		AmazonEC2 ec2 = (AmazonEC2)provider.getProperty("AmazonEC2");
		RunInstancesResult runInstancesResult = ec2.runInstances(runInstancesRequest);
		Instance instance = runInstancesResult.getReservation().getInstances().get(0);
		node.setIP(instance.getPublicIpAddress());
		return node;
	}
}