package com.testing.os.data.entity.undo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.SkipException;

import com.testing.os.data.entity.UserRowDetail;
import com.testing.os.data.tc.users.util.UserUtils;

public class UndoEntities {
	private static final Logger logger = Logger.getLogger(UndoEntities.class);

	private List<UserRowDetail> users = new ArrayList<>();

	public List<UserRowDetail> getUsers() {
		return users;
	}

	public void setUsers(List<UserRowDetail> users) {
		this.users = users;
	}

	public void addUser(UserRowDetail user) {
		this.users.add(user);
	}

	public void undoUsers() {
		for (UserRowDetail user : users) {
			String apiType = user.getApiType();

			switch (apiType) {
			case "POST":
				undoPost(user);
				break;
			case "PUT":
				undoPut(user);
				break;
			case "DELETE":
				undoDelete(user);
				break;
			default:
				Assert.fail("Cannot undo given API: " + apiType);
				break;
			}
		}
	}

	private void undoPost(UserRowDetail user) {
		String userJson = null;
		try {
			userJson = user.toJsonString();
			UserUtils.callDELETE(user);
			logger.info("Successfully undone POST API call for user.");
			logger.debug("(" + user + ")");
		} catch (Exception e) {
			Assert.fail("Failed to undo user POST operation: " + e.getMessage() + " Please undo manually " + userJson);
		}
	}

	private void undoPut(UserRowDetail user) {
		String userJson = null;
		try {
			userJson = user.toJsonString();
			UserUtils.callPUT(user);
			logger.info("Successfully undone PUT API call for user.");
			logger.debug("(" + user + ")");
		} catch (Exception e) {
			throw new SkipException(
					"Failed to undo user PUT operation: " + e.getMessage() + " Please undo manually " + userJson);
		}
	}

	private void undoDelete(UserRowDetail user) {
		String userJson = null;
		try {
			userJson = user.toJsonString();
			UserUtils.callPOST(user);
			logger.info("Successfully undone DELETE API call for user.");
			logger.debug("(" + user + ")");
		} catch (Exception e) {
			throw new SkipException(
					"Failed to undo user DELETE operation: " + e.getMessage() + " Please undo manually " + userJson);
		}
	}
}
