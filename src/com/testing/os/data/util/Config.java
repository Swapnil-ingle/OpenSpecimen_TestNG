package com.testing.os.data.util;

import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.binary.Base64;

public class Config {
	// TODO: Take this from a properties file.
	public final static String username = "admin";

	public final static String password = "********";

	public final static String os_url = "https://test.openspecimen.org/os-mysql"; // Without trailing forward-slash ('/')

	public final static String INPUT_DATE_FMT = "mm/DD/yyyy";

	public static final String USER_FILE_NAME = "/Users/swapnil/Downloads/OS_USER_API_TC_SHEET_1.csv";

	public static String getAuthHeader() {
		return getAuthHeader(null);
	}

	public static String getAuthHeader(String authHeader) {
		String defAuth = username + ":" + password;

		if (authHeader != null && !authHeader.isEmpty()) {
			defAuth = authHeader;
		}

		byte[] encodedAuth = Base64.encodeBase64(defAuth.getBytes(StandardCharsets.ISO_8859_1));
		return "Basic " + new String(encodedAuth);
	}

	public static String getUsersApiUrl() {
		return os_url + "/rest/ng/users/";
	}

	public static String getInstituteApiUrl() {
		return os_url + "/rest/ng/institutes/";
	}

	public static String getSiteApiUrl() {
		return os_url + "/rest/ng/sites/";
	}

	public static String getCpApiUrl() {
		return os_url + "/rest/ng/collection-protocols/";
	}

	/////////////////////////
	// Prerequisite JSON
	/////////////////////////

	public static final String PRE_REQ_INSTITUTE_JSON = "{\"name\": \"Test_INST\"}";

	public static final String PRE_REQ_SITE_JSON = "{\"coordinators\":[],\"instituteName\":\"Test_INST\",\"name\":\"Test_Site\",\"type\":\"Repository\"}";

	public static final String PRE_REQ_CP_JSON = "{\n" + "  \"coordinators\":[],\n"
			+ "  \"cpSites\":[{\"siteName\":\"Test_Site\"}],\n" + "  \"title\":\"Test CP #1000\",\n"
			+ "  \"shortTitle\":\"Test_CP\",\n" + "  \"principalInvestigator\":{\n"
			+ "    \"emailAddress\":\"admin@localhost\"\n" + "  }\n" + "}";
}
