package com.testing.os.data.prereq;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import org.apache.http.client.ClientProtocolException;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;

import com.testing.os.data.util.APICaller;
import com.testing.os.data.util.Config;
import com.testing.os.data.util.Utility;

public class Prerequisites {
	@BeforeSuite
	public void addPrerequisites() {
		try {
			addInstitute();
			addSite();
			addCp();
		} catch (Exception e) {
			System.out.println("Error while adding prerequisite: " + e.getMessage());
			Assert.fail("Error while adding prerequisites", e);
		}
	}

	private String addInstitute() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException,
			ClientProtocolException, IOException {
		System.out.println("Prerequisite: Adding institute");

		String url = Config.getInstituteApiUrl();
		return Utility.processResponse(APICaller.callPOST(url, Config.PRE_REQ_INSTITUTE_JSON));
	}

	private String addSite() throws ClientProtocolException, IOException, KeyManagementException,
			NoSuchAlgorithmException, KeyStoreException {
		System.out.println("Prerequisite: Adding Site");

		String url = Config.getSiteApiUrl();
		return Utility.processResponse(APICaller.callPOST(url, Config.PRE_REQ_SITE_JSON));
	}

	private String addCp() throws ClientProtocolException, IOException, KeyManagementException,
			NoSuchAlgorithmException, KeyStoreException {
		System.out.println("Prerequisite: Adding Cp");

		String url = Config.getCpApiUrl();
		return Utility.processResponse(APICaller.callPOST(url, Config.PRE_REQ_CP_JSON));
	}
}
