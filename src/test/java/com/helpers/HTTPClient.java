package com.helpers;

import java.io.IOException;
import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class HTTPClient {

	static CloseableHttpClient httpclient = HttpClients.createDefault();

	public static String sendGet(String uri) throws ClientProtocolException, IOException {
		HttpGet httpget = new HttpGet(uri);
		return executeRequest(httpget);
	}

	public static String sendPost(String uri, String postBody) throws ClientProtocolException, IOException {
		HttpPost httpPost = new HttpPost(uri);

		// set request header
		httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36");
		httpPost.setHeader("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		httpPost.setHeader("accept-language", "en-US,en;q=0.8,ro;q=0.6");
		httpPost.setHeader("content-type", "application/x-www-form-urlencoded");
		// httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");

		// set request body
		httpPost.setEntity(new StringEntity(postBody));

		return executeRequest(httpPost);
	}

	private static String executeRequest(HttpUriRequest request) throws ParseException, IOException {
		System.out.println("Executing request " + request.getRequestLine());
		CloseableHttpResponse response = httpclient.execute(request);

		// get response string
		HttpEntity entity = response.getEntity();
		System.out.println("Response " + response.getStatusLine());
		String responseBody = EntityUtils.toString(entity);

		return responseBody;
	}

	public static void main(String[] args) throws ClientProtocolException, IOException {

		// login with no token
		String responseBody = sendPost("https://en.wikipedia.org/w/api.php?action=login&format=json", "lgname=gabikissv&lgpassword=pa22w0rd");
		System.out.println(responseBody);

		// get login token
		JSONObject jsonObj = new JSONObject(responseBody);
		String lgtoken = (String) jsonObj.getJSONObject("login").get("token");

		// login with token
		responseBody = sendPost("https://en.wikipedia.org/w/api.php?action=login&format=json", "lgname=gabikissv&lgpassword=pa22w0rd&lgtoken=" + lgtoken);
		System.out.println(responseBody);

		// get watch token
		responseBody = sendGet("https://en.wikipedia.org/w/api.php?action=query&meta=tokens&format=json&&type=watch");
		// System.out.println(responseBody);
		JSONObject jsonObjWatch = new JSONObject(responseBody);
		String watchToken = (String) jsonObjWatch.getJSONObject("query").getJSONObject("tokens").get("watchtoken");
		System.out.println("watchToken: " + watchToken);

		// encode token
		watchToken = URLEncoder.encode(watchToken, "UTF-8");
		System.out.println("watchToken encoded: " + watchToken);

		// ----------- add to watchlist -----------
		String uri = "https://en.wikipedia.org/w/api.php?action=watch&format=json";

		// add apple
		String watchRequestBody = "titles=Apple&token=" + watchToken;
		responseBody = sendPost(uri, watchRequestBody);
		// add banana
		watchRequestBody = "titles=Banana&token=" + watchToken;
		responseBody = sendPost(uri, watchRequestBody);
		// add Pear|Cucumber
		watchRequestBody = "titles=Pear|Cucumber&token=" + watchToken;
		responseBody = sendPost(uri, watchRequestBody);

		// ----------- clear watchlist -----------
		String uriClear = "https://en.wikipedia.org/wiki/Special:EditWatchlist/clear";
		responseBody = sendGet(uriClear);

		// get wpEditToken
		String wpEditToken = responseBody.split("wpEditToken")[1].split("value=\"")[1].split("\"")[0];
		System.out.println("wpEditToken: " + wpEditToken);

		// encode token
		wpEditToken = URLEncoder.encode(wpEditToken, "UTF-8");
		System.out.println("wpEditToken encoded: " + wpEditToken);

		// clear watchlist with token
		String clearBody = "wpEditToken=" + wpEditToken + "&title=Special%3AEditWatchlist%2Fclear";
		responseBody = sendPost(uriClear, clearBody);

		//System.out.println(responseBody);

	}
}