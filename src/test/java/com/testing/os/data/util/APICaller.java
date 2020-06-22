package com.testing.os.data.util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;

public class APICaller {
	public static CloseableHttpResponse callGET(String url) throws ClientProtocolException, IOException {
		return callGET(url, null);
	}

	public static CloseableHttpResponse callGET(String url, String authHeader)
			throws ClientProtocolException, IOException {
		HttpGet request = new HttpGet(url);
		request.setHeader(HttpHeaders.AUTHORIZATION, Config.getInstance().getAuthHeader(authHeader));

		CloseableHttpClient client = Utility.getHttpClient();
		return client.execute(request);
	}

	public static CloseableHttpResponse callPOST(String url, String json) throws ClientProtocolException, IOException {
		return callPOST(url, null, json);
	}

	public static CloseableHttpResponse callPOST(String url, String authHeader, String json)
			throws ClientProtocolException, IOException {
		HttpPost request = new HttpPost(url);
		return processRequest(authHeader, json, request);
	}

	public static CloseableHttpResponse callPUT(String url, String json) throws ClientProtocolException, IOException {
		return callPUT(url, null, json);
	}

	public static CloseableHttpResponse callPUT(String url, String authHeader, String json)
			throws ClientProtocolException, IOException {
		HttpPut request = new HttpPut(url);
		return processRequest(authHeader, json, request);
	}

	public static CloseableHttpResponse callDELETE(String url) throws ClientProtocolException, IOException {
		return callDELETE(url, null);
	}

	public static CloseableHttpResponse callDELETE(String url, String authHeader)
			throws ClientProtocolException, IOException {
		HttpDelete request = new HttpDelete(url);
		request.setHeader(HttpHeaders.AUTHORIZATION, Config.getInstance().getAuthHeader(authHeader));

		CloseableHttpClient client = Utility.getHttpClient();
		return client.execute(request);
	}

	private static CloseableHttpResponse processRequest(String authHeader, String json,
			HttpEntityEnclosingRequestBase request) throws IOException, ClientProtocolException {
		request.setHeader(HttpHeaders.AUTHORIZATION, Config.getInstance().getAuthHeader(authHeader));

		HttpEntity httpEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
		request.setEntity(httpEntity);

		CloseableHttpClient client = Utility.getHttpClient();
		return client.execute(request);
	}
}
