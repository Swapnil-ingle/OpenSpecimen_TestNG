package com.testing.os.data.tc.prereq;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeGroups;

public class SitePreReq {
	private static final Logger logger = Logger.getLogger(SitePreReq.class);

	@BeforeGroups("Sites")
	public void addPrerequisites() {
		logger.info("Dummy: Running pre-requisites before site group");

		try {
			// addInstitute();
			// addSite();
			// addCp();
		} catch (Exception e) {
			logger.info("Error while adding prerequisite: " + e.getMessage());
			Assert.fail("Error while adding prerequisites", e);
		}
	}
}
