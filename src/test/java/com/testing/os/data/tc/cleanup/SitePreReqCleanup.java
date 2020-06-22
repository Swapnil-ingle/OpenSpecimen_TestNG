package com.testing.os.data.tc.cleanup;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterGroups;

public class SitePreReqCleanup {
	private static final Logger logger = Logger.getLogger(UserPreReqCleanup.class);

	@AfterGroups("Sites")
	public void cleanPrerequisites() {
		logger.info("Dummy: Cleaning up after site group");

		try {
			// removeCp();
			// removeSite();
			// removeInstitute();
		} catch (Exception e) {
			logger.info("Error while cleaning prerequisite: " + e.getMessage());
			Assert.fail("Error while cleaning prerequisites", e);
		}
	}
}
