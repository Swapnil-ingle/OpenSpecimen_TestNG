package com.testing.os.data.users;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.testing.os.data.entity.UserRowDetail;
import com.testing.os.data.providers.UserDataProvider;
import com.testing.os.data.util.APICaller;
import com.testing.os.data.util.Config;
import com.testing.os.data.util.Utility;

public class UserTesting {
	private UndoObjects undoObjects = new UndoObjects();

	@Test(dataProviderClass = UserDataProvider.class, dataProvider = "userDataProvider")
	public void testUser(UserRowDetail user) {
		if (user.isIgnore()) {
			throw new SkipException("User row skipped - EmailAddress: " + user.getEmailAddress());
		}

		try {
			String apiMethod = user.getApiType();
			UserRowDetail undoUser = null;

			switch (apiMethod) {
				case "GET":
					callUserGET(user);
					break;
				case "POST":
					undoObjects.addUser(user);
					callUserPOST(user);
					break;
				case "PUT":
					undoUser = new UserRowDetail(callUserGET(user));
					undoUser.setApiType("PUT");
					undoObjects.addUser(undoUser);

					user.setId(undoUser.getId());
					callUserPUT(user);
					break;
				case "DELETE":
					undoUser = new UserRowDetail(callUserGET(user));
					undoUser.setApiType("DELETE");
					undoObjects.addUser(undoUser);

					user.setId(undoUser.getId());
					callUserDELETE(user);
					break;
			default:
				Assert.fail("Invalid API Type: Make sure the API is one of {'GET', 'POST', 'PUT', 'DELETE'}");
				break;
			}
		} catch (Exception e) {
			Assert.fail("Exception while calling user API " + user.getApiType() + " : " + e.getMessage());
		}
	}

	@AfterClass
	public void afterUserTestCases() {
		System.out.println("Undoing the test cases...");
		undoObjects.undoUsers();
	}

	public static String callUserGET(UserRowDetail user) throws KeyManagementException, NoSuchAlgorithmException,
			KeyStoreException, ClientProtocolException, IOException {
		Long userId = user.getId() == null ? Utility.getUserIdFromLoginName(user) : user.getId();
		String url = Config.getUsersApiUrl() + userId;

		HttpResponse resp = APICaller.callGET(url, user.getRunAs());
		return Utility.processResponse(resp, user);
	}

	public static String callUserPOST(UserRowDetail user) throws KeyManagementException, NoSuchAlgorithmException,
			KeyStoreException, ClientProtocolException, IOException {
		HttpResponse resp = APICaller.callPOST(Config.getUsersApiUrl(), user.getRunAs(), user.toJsonString());
		return Utility.processResponse(resp, user);
	}

	public static String callUserPUT(UserRowDetail user) throws KeyManagementException, NoSuchAlgorithmException,
			KeyStoreException, ClientProtocolException, IOException {
		Long userId = user.getId() == null ? Utility.getUserIdFromLoginName(user) : user.getId();
		String url = Config.getUsersApiUrl() + userId;

		HttpResponse resp = APICaller.callPUT(url, user.getRunAs(), user.toJsonString());
		return Utility.processResponse(resp, user);
	}

	public static String callUserDELETE(UserRowDetail user) throws KeyManagementException, NoSuchAlgorithmException,
			KeyStoreException, ClientProtocolException, IOException {
		Long userId = user.getId() == null ? Utility.getUserIdFromLoginName(user) : user.getId();
		String url = Config.getUsersApiUrl() + userId;

		HttpResponse resp = APICaller.callDELETE(url, user.getRunAs());
		return Utility.processResponse(resp, user);
	}
}
