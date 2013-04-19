package thu.cs.lyw.rm.adapter;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class OpenStackTest_jersey {
	public static void main(String[] args) throws Exception {
		try{
			//First step : get token;
			Client client = Client.create();
			WebResource webResource = client
					.resource("https://region-a.geo-1.identity.hpcloudsvc.com:35357/v2.0/tokens");
			String input = "{\"auth\":{\"apiAccessKeyCredentials\":{\"accessKey\":" +
					"\"D7WAWP2LBYLGRZ9FCLNE\",\"secretKey\":\"+fmjdLc4UBvn/gsfEYWnD5AcbOuhEsP30SbJRc/c\"}}}";
			ClientResponse response = webResource.type("application/json").accept("application/json")
					.post(ClientResponse.class, input);
			checkResponseCode(response);
			String output = response.getEntity(String.class);
			JSONObject json = new JSONObject(output);
			String token = json.getJSONObject("access").getJSONObject("token").getString("id");
			
			//Second step : get tenant ID;
			webResource = client.resource("https://region-a.geo-1.identity.hpcloudsvc.com:35357/v2.0/tenants");
			response = webResource.type("application/json").accept("application/json").header("X-Auth-Token", token)
					.header("Conection", "keep-alive")
					.get(ClientResponse.class);
			checkResponseCode(response);
			output = response.getEntity(String.class);
			json = new JSONObject(output);
			String tenantId = json.getJSONArray("tenants").getJSONObject(0).getString("id");
			
			//Third step : get computer address header;
			webResource = client.resource("https://region-a.geo-1.identity.hpcloudsvc.com:35357/v2.0/tokens");
			input = "{\"auth\":{\"apiAccessKeyCredentials\":{\"accessKey\":" +
					"\"D7WAWP2LBYLGRZ9FCLNE\",\"secretKey\":\"+fmjdLc4UBvn/gsfEYWnD5AcbOuhEsP30SbJRc/c\"},\"tenantId\":" +
					"\""+tenantId+"\"}}";
			response = webResource.type("application/json").accept("application/json")
					.post(ClientResponse.class, input);
			checkResponseCode(response);
			output = response.getEntity(String.class);
			json = new JSONObject(output);
			JSONArray jsonArray = json.getJSONObject("access").getJSONArray("serviceCatalog");
			String computeHeader = null;
			for (int i = 0 ; i < jsonArray.length() ; i++){
				json = jsonArray.getJSONObject(i);
				if (!json.getString("type").equals("compute")) continue;
				else{
					computeHeader = (json.getJSONArray("endpoints").getJSONObject(0).getString("publicURL"));
				}
			}
			if (computeHeader == null) System.err.println("Something's wrong!");
			System.out.println(token);
			System.out.println(tenantId);
			System.out.println(computeHeader);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	private static void checkResponseCode(ClientResponse response){
		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
			     + response.getStatus());
		}
	}
}
