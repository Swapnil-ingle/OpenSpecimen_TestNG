package com.testing.os.data.tc.cleanup;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterGroups;

import com.testing.os.data.util.APICaller;
import com.testing.os.data.util.Config;
import com.testing.os.data.util.Utility;

public class UserPreReqCleanup {
	private static final Logger logger = Logger.getLogger(UserPreReqCleanup.class);

	@AfterGroups("Users")
	public void cleanPrerequisites() {
		try {
			removeCp();
			removeSite();
			removeInstitute();
		} catch (Exception e) {
			logger.info("Error while cleaning prerequisite: " + e.getMessage());
			Assert.fail("Error while cleaning prerequisites", e);
		}
	}

	private String removeCp() throws ClientProtocolException, IOException {
		logger.info("Removing User Prerequisite: CP");

		JSONObject cpJson = Utility.getJsonObjectFromString(Config.getInstance().getPreReqCpJson());
		String cpShortTitle = Utility.getStringAttrFromJSON(cpJson, "shortTitle");

		String getCpUrl = Config.getInstance().getCpApiUrl() + "?title=" + cpShortTitle;
		JSONObject resp = Utility.getJsonFromResponse(APICaller.callGET(getCpUrl));

		String cpId = Utility.toString(Utility.getLongAttrFromJSON(resp, "id"));
		String url = Config.getInstance().getCpApiUrl() + cpId;
		return Utility.processResponse(APICaller.callDELETE(url));
	}

	private String removeSite() throws ClientProtocolException, IOException {
		logger.info("Removing User Prerequisite: Site");

		JSONObject siteJson = Utility.getJsonObjectFromString(Config.getInstance().getPreReqSiteJson());
		String siteName = Utility.getStringAttrFromJSON(siteJson, "name");

		String getSiteUrl = Config.getInstance().getSiteApiUrl() + "?name=" + siteName;
		JSONObject resp = Utility.getJsonFromResponse(APICaller.callGET(getSiteUrl));

		String siteId = Utility.toString(Utility.getLongAttrFromJSON(resp, "id"));
		String url = Config.getInstance().getSiteApiUrl() + siteId;

		return Utility.processResponse(APICaller.callDELETE(url));
	}

	private String removeInstitute() throws ClientProtocolException, IOException {
		logger.info("Removing User Prerequisite: Institute");

		JSONObject instJson = Utility.getJsonObjectFromString(Config.getInstance().getPreReqInstituteJson());
		String instName = Utility.getStringAttrFromJSON(instJson, "name");

		String getInsturl = Config.getInstance().getInstituteApiUrl() + "?name=" + instName;
		JSONObject resp = Utility.getJsonFromResponse(APICaller.callGET(getInsturl));

		String instId = Utility.toString(Utility.getLongAttrFromJSON(resp, "id"));
		String url = Config.getInstance().getInstituteApiUrl() + instId;

		return Utility.processResponse(APICaller.callDELETE(url));
	}
}
