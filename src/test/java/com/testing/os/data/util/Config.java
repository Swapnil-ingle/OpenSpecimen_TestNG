package com.testing.os.data.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

public class Config {
	private static final Logger logger = Logger.getLogger(Config.class);

	private Properties prop;

	private static Config config = null;

	private Config() {}

	public static Config getInstance() {
		if (config == null) {
			config = new Config();

			config.loadProps();
		}

		return config;
	}

	private void loadProps() {
		logger.debug("Loading properties from file for first time...");

		try (InputStream input = Config.class.getClassLoader().getResourceAsStream("testing.properties")) {
			prop = new Properties();

			if (input == null) {
				System.out.println("Sorry, unable to find testing.properties");
				return;
			}

			prop.load(input);

			logger.debug(prop.getProperty("os.username"));
			logger.debug(prop.getProperty("os.password"));
			logger.debug(prop.getProperty("os.url"));
			logger.debug(prop.getProperty("csv.input_date_fmt"));
			logger.debug(prop.getProperty("csv.user_file"));
			logger.debug(prop.getProperty("pre_req.institute_json"));
			logger.debug(prop.getProperty("pre_req.site_json"));
			logger.debug(prop.getProperty("pre_req.cp_json"));
		} catch (IOException ex) {
			logger.error("Error while loading properties from the file", ex);
		}
	}

	public String getOsUsername() {
		return getProperty("os.username");
	}

	public String getOsPassword() {
		return getProperty("os.password");
	}

	public String getOsUrl() {
		return getProperty("os.url");
	}

	public String getCsvInputDateFmt() {
		return getProperty("csv.input_date_fmt");
	}

	public String getCsvUserFile() {
		return getProperty("csv.user_file");
	}

	public String getPreReqInstituteJson() {
		return getProperty("pre_req.institute_json");
	}

	public String getPreReqSiteJson() {
		return getProperty("pre_req.site_json");
	}

	public String getPreReqCpJson() {
		return getProperty("pre_req.cp_json");
	}

	public String getAuthHeader() {
		return getAuthHeader(null);
	}

	public String getAuthHeader(String authHeader) {
		String defAuth = getOsUsername() + ":" + getOsPassword();

		if (authHeader != null && !authHeader.isEmpty()) {
			defAuth = authHeader;
		}

		byte[] encodedAuth = Base64.encodeBase64(defAuth.getBytes(StandardCharsets.ISO_8859_1));
		return "Basic " + new String(encodedAuth);
	}

	public String getUsersApiUrl() {
		return getOsUrl() + "/rest/ng/users/";
	}

	public String getInstituteApiUrl() {
		return getOsUrl() + "/rest/ng/institutes/";
	}

	public String getSiteApiUrl() {
		return getOsUrl() + "/rest/ng/sites/";
	}

	public String getCpApiUrl() {
		return getOsUrl() + "/rest/ng/collection-protocols/";
	}

	private String getProperty(String name) {
		if (prop == null) {
			logger.error(String.format("Unable to load '%s' value - No Properties not loaded", name));
		}

		return prop.getProperty(name);
	}
}
