package com.testing.os.data.tc.users.util;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.testng.Assert;

import com.testing.os.data.entity.UserRowDetail;
import com.testing.os.data.tc.UserTesting;
import com.testing.os.data.util.APICaller;
import com.testing.os.data.util.Config;
import com.testing.os.data.util.Utility;

public class UserUtils {
	private static final Logger logger = Logger.getLogger(UserTesting.class);

	public static HttpResponse callGET(UserRowDetail user) throws KeyManagementException, NoSuchAlgorithmException,
			KeyStoreException, ClientProtocolException, IOException {
		Long userId = user.getId() == null ? getUserIdFromLoginName(user) : user.getId();
		String url = Config.getInstance().getUsersApiUrl() + userId;

		return APICaller.callGET(url, user.getRunAs());
	}

	public static HttpResponse callPOST(UserRowDetail user) throws KeyManagementException, NoSuchAlgorithmException,
			KeyStoreException, ClientProtocolException, IOException {
		return APICaller.callPOST(Config.getInstance().getUsersApiUrl(), user.getRunAs(), user.toJsonString());
	}

	public static HttpResponse callPUT(UserRowDetail user) throws KeyManagementException, NoSuchAlgorithmException,
			KeyStoreException, ClientProtocolException, IOException {
		Long userId = user.getId() == null ? getUserIdFromLoginName(user) : user.getId();
		String url = Config.getInstance().getUsersApiUrl() + userId;

		return APICaller.callPUT(url, user.getRunAs(), user.toJsonString());
	}

	public static HttpResponse callDELETE(UserRowDetail user) throws KeyManagementException, NoSuchAlgorithmException,
			KeyStoreException, ClientProtocolException, IOException {
		Long userId = user.getId() == null ? getUserIdFromLoginName(user) : user.getId();
		String url = Config.getInstance().getUsersApiUrl() + userId;

		return APICaller.callDELETE(url, user.getRunAs());
	}

	public static Long getUserIdFromLoginName(UserRowDetail user) throws KeyManagementException,
			NoSuchAlgorithmException, KeyStoreException, ClientProtocolException, IOException {
		if (user.getLoginName() == null || user.getLoginName().isEmpty()) {
			Assert.fail("Cannot resolve user ID from loginName: LoginName is Empty");
		}

		String url = Config.getInstance().getUsersApiUrl() + "?loginName=" + user.getLoginName();

		CloseableHttpResponse resp = APICaller.callGET(url, user.getRunAs());
		int statusCode = resp.getStatusLine().getStatusCode();
		JSONArray users = Utility.getJsonArrayFromResponse(resp);

		if (statusCode != HttpStatus.SC_OK || users.isEmpty()) {
			Assert.fail("Cannot resolve user ID from loginName: User with loginName does not exists");
		}

		logger.debug("Got user from loginName: " + users.toString());
		return Utility.getLongAttrFromJSON(users.getJSONObject(0), "id");
	}
}
