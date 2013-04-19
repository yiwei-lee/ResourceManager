package thu.cs.lyw.rm.adapter;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class OpenStackTest_jersey {

	public static void main(String[] args) throws Exception {
		try{
			Client client = Client.create();
			WebResource webResource = client
			   .resource("https://region-a.geo-1.identity.hpcloudsvc.com:35357/v2.0/tokens");
			String input = "{\"auth\":{\"apiAccessKeyCredentials\":{\"accessKey\":" +
					"\"D7WAWP2LBYLGRZ9FCLNE\",\"secretKey\":\"+fmjdLc4UBvn/gsfEYWnD5AcbOuhEsP30SbJRc/c\"}}}";
			ClientResponse response2 = webResource.type("application/json").accept("application/json")
			   .post(ClientResponse.class, input);
			if (response2.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
				     + response2.getStatus());
			}
			String output = response2.getEntity(String.class);
			System.out.println(output);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
