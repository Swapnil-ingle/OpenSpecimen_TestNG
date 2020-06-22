package com.testing.os.data.tc.prereq;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeGroups;

import com.testing.os.data.util.APICaller;
import com.testing.os.data.util.Config;
import com.testing.os.data.util.Utility;

public class UserPreReq {
	private static final Logger logger = Logger.getLogger(UserPreReq.class);

	@BeforeGroups("Users")
	public void addPrerequisites() {
		try {
			addInstitute();
			addSite();
			addCp();
		} catch (Exception e) {
			logger.info("Error while adding prerequisite: " + e.getMessage());
			Assert.fail("Error while adding prerequisites", e);
		}
	}
	
	private String addInstitute() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException,
			ClientProtocolException, IOException {
		logger.info("User Prerequisite: Adding institute");

		String url = Config.getInstance().getInstituteApiUrl();
		return Utility.processResponse(APICaller.callPOST(url, Config.getInstance().getPreReqInstituteJson()));
	}

	private String addSite() throws ClientProtocolException, IOException, KeyManagementException,
			NoSuchAlgorithmException, KeyStoreException {
		logger.info("User Prerequisite: Adding Site");

		String url = Config.getInstance().getSiteApiUrl();
		return Utility.processResponse(APICaller.callPOST(url, Config.getInstance().getPreReqSiteJson()));
	}

	private String addCp() throws ClientProtocolException, IOException, KeyManagementException,
			NoSuchAlgorithmException, KeyStoreException {
		logger.info("User Prerequisite: Adding Cp");

		String url = Config.getInstance().getCpApiUrl();
		return Utility.processResponse(APICaller.callPOST(url, Config.getInstance().getPreReqCpJson()));
	}
}
