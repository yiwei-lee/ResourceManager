package thu.cs.lyw.rm.adapter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

public class OpenStackTest_httpclient {

	public static void main(String[] args) throws Exception {
		DefaultHttpClient  httpclient = new DefaultHttpClient();
		HttpPost post = new HttpPost("https://region-a.geo-1.identity.hpcloudsvc.com:35357/v2.0/tokens");
		post.addHeader("Accept","application/json");
		post.addHeader("Content-Type","application/json");
		post.setEntity(new StringEntity("{\"auth\":{\"apiAccessKeyCredentials\":{\"accessKey\":\"D7WAWP2LBYLGRZ9FCLNE\",\"secretKey\":\"+fmjdLc4UBvn/gsfEYWnD5AcbOuhEsP30SbJRc/c\"}}}"));
		HttpResponse response = httpclient.execute(post);
		printResponse(response);
	}
	private static void printResponse(HttpResponse response) throws Exception{
		BufferedReader r = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		StringBuilder total = new StringBuilder();
		String line = null;
		while ((line = r.readLine()) != null) {
		   total.append(line);
		}
		r.close();
		System.out.println(total.toString());
	}
}
