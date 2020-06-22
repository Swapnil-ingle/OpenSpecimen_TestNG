package com.testing.os.data.tc;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.testing.os.data.entity.BaseRowEntity;
import com.testing.os.data.entity.UserRowDetail;
import com.testing.os.data.entity.processor.AbstractEntityProcessor;
import com.testing.os.data.providers.UserDataProvider;
import com.testing.os.data.tc.users.util.UserUtils;
import com.testing.os.data.util.Utility;

public class UserTesting extends AbstractEntityProcessor {
	private static final Logger logger = Logger.getLogger(UserTesting.class);

	@Override
	@Test(dataProviderClass = UserDataProvider.class, 
		dataProvider = "userDataProvider", 
		groups = { "Users" },
		priority = 1)
	public void runTest(BaseRowEntity entity) {
		UserRowDetail user = (UserRowDetail) entity;
		processEntityRow(user);
	}

	@Override
	public String callGETApi(BaseRowEntity entity) throws KeyManagementException, NoSuchAlgorithmException,
			KeyStoreException, ClientProtocolException, IOException {
		UserRowDetail user = (UserRowDetail) entity;

		logger.info("Processing GET for user.");
		logger.debug("(" + user + ")");

		return Utility.processResponse(UserUtils.callGET(user), user);
	}

	@Override
	public String callPOSTApi(BaseRowEntity entity) throws ClientProtocolException, JsonProcessingException,
			IOException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
		UserRowDetail user = (UserRowDetail) entity;
		logger.info("Processing POST for user.");
		logger.debug("(" + user + ")");

		// Add to undo-queue
		undoEntities.addUser(user);

		return Utility.processResponse(UserUtils.callPOST(user), user);
	}

	@Override
	public String callPUTApi(BaseRowEntity entity) throws KeyManagementException, NoSuchAlgorithmException,
			KeyStoreException, ClientProtocolException, IOException {
		UserRowDetail user = (UserRowDetail) entity;
		logger.info("Processing PUT for user.");
		logger.debug("(" + user + ")");

		// Add to undo-queue
		UserRowDetail currentUser = new UserRowDetail(Utility.processResponse(UserUtils.callGET(user), user));
		currentUser.setApiType("PUT");
		undoEntities.addUser(currentUser);
		user.setId(currentUser.getId());

		return Utility.processResponse(UserUtils.callPUT(user), user);
	}

	@Override
	public String callDELETEApi(BaseRowEntity entity) throws KeyManagementException, NoSuchAlgorithmException,
			KeyStoreException, ClientProtocolException, IOException {
		UserRowDetail user = (UserRowDetail) entity;
		logger.info("Processing DELETE for user.");
		logger.debug("(" + user + ")");

		// Add to undo-queue
		UserRowDetail currentUser = new UserRowDetail(Utility.processResponse(UserUtils.callGET(user), user));
		currentUser.setApiType("DELETE");
		undoEntities.addUser(currentUser);
		user.setId(currentUser.getId());

		return Utility.processResponse(UserUtils.callDELETE(user), user);
	}

	@Override
	@Test(groups = { "Users" }, priority = 2)
	public void afterTestCases() {
		logger.info("Undoing the user test cases...");
		undoEntities.undoUsers();
	}
}
