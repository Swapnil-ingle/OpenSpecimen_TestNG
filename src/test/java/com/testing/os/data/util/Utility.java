package com.testing.os.data.util;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;

import com.testing.os.data.entity.BaseRowEntity;
import com.testing.os.data.tc.UserTesting;

public class Utility {
	private static final Logger logger = Logger.getLogger(UserTesting.class);

	public static boolean toBoolean(String value) {
		return value == null ? false : Boolean.valueOf(value);
	}

	public static Date toDate(String date) {
		if (date == null || date.isEmpty()) {
			return null;
		}

		try {
			SimpleDateFormat format = new SimpleDateFormat(Config.getInstance().getCsvInputDateFmt());
			return format.parse(date);
		} catch (ParseException e) {
			Assert.fail("Error parsing date: Please confirm that the input date format matches to - "
					+ Config.getInstance().getCsvInputDateFmt());
		}

		return null;
	}

	public static Long toLong(String value) {
		if (value == null) {
			return null;
		}

		return value.isEmpty() ? null : Long.parseLong(value);
	}

	public static boolean isApiReqSuccessful(CloseableHttpResponse resp) {
		int statusCode = resp.getStatusLine().getStatusCode();

		if (statusCode != HttpStatus.SC_OK) {
			return true;
		}

		return false;
	}

	public static JSONObject getJsonObjectFromString(String json) {
		if (json == null || json.isEmpty()) {
			return null;
		} else if (json.charAt(0) == '[') {
			// The resp is probably an array --> resolving it accordingly
			JSONArray jsonArray = new JSONArray(json);
			return jsonArray.optJSONObject(0);
		} else {
			return new JSONObject(json);
		}
	}

	public static JSONObject getJsonFromResponse(CloseableHttpResponse resp)
			throws org.apache.http.ParseException, IOException {
		String stringResp = EntityUtils.toString(resp.getEntity());
		return getJsonObjectFromString(stringResp);
	}

	public static JSONArray getJsonArrayFromResponse(CloseableHttpResponse resp)
			throws org.apache.http.ParseException, IOException {
		String stringResp = EntityUtils.toString(resp.getEntity());

		if (stringResp == null || stringResp.isEmpty()) {
			return null;
		}

		return new JSONArray(stringResp);
	}

	public static CloseableHttpClient getHttpClient() {
		try {
			return HttpClients.custom()
					.setSSLContext(new SSLContextBuilder().loadTrustMaterial(null, TrustAllStrategy.INSTANCE).build())
					.setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE).build();
		} catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException e) {
			Assert.fail("Exception while creating HTTP client", e);
		}

		return null;
	}

	public static String processResponse(HttpResponse response) throws ClientProtocolException, IOException {
		return processResponse(response, null);
	}

	public static String processResponse(HttpResponse response, BaseRowEntity entity)
			throws IOException, ClientProtocolException {
		int statusCode = response.getStatusLine().getStatusCode();
		String stringResp = EntityUtils.toString(response.getEntity());
		logger.debug("Response: " + stringResp);

		if (entity == null) {
			Assert.assertEquals(statusCode, HttpStatus.SC_OK);
			return stringResp;
		}

		if (entity.getApiType().equalsIgnoreCase("POSITIVE")) {
			Assert.assertEquals(statusCode, HttpStatus.SC_OK);
		} else if (entity.getApiType().equalsIgnoreCase("NEGATIVE")) {
			Assert.assertNotEquals(statusCode, HttpStatus.SC_OK);
		}

		return stringResp;
	}

	public static String toString(Long value) {
		if (value == null) {
			return null;
		}

		return String.valueOf(value);
	}

	public static String getStringAttrFromJSON(JSONObject response, String key) {
		return ((response.has(key) && !response.isNull(key))) ? response.getString(key) : null;
	}

	public static Long getLongAttrFromJSON(JSONObject response, String key) {
		return ((response.has(key) && !response.isNull(key))) ? response.getLong(key) : null;
	}

	public static Boolean getBooleanAttrFromJSON(JSONObject response, String key) {
		return ((response.has(key) && !response.isNull(key))) ? response.getBoolean(key) : false;
	}
}
