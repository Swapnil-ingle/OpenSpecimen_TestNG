package com.testing.os.data.users;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.SkipException;

import com.testing.os.data.entity.UserRowDetail;

public class UndoObjects {
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
			UserTesting.callUserDELETE(user);
		} catch (Exception e) {
			Assert.fail("Failed to undo user POST operation: " + e.getMessage() + " Please undo manually " + userJson);
		}
	}

	private void undoPut(UserRowDetail user) {
		String userJson = null;
		try {
			userJson = user.toJsonString();
			UserTesting.callUserPUT(user);
		} catch (Exception e) {
			throw new SkipException("Failed to undo user PUT operation: " + e.getMessage() + " Please undo manually " + userJson);
		}
	}

	private void undoDelete(UserRowDetail user) {
		String userJson = null;
		try {
			userJson = user.toJsonString();
			UserTesting.callUserPOST(user);
		} catch (Exception e) {
			throw new SkipException("Failed to undo user DELETE operation: " + e.getMessage() + " Please undo manually " + userJson);
		}
	}
}
