package com.testing.os.data.tc;

import org.testng.annotations.Test;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.testing.os.data.entity.BaseRowEntity;
import com.testing.os.data.entity.processor.AbstractEntityProcessor;
import com.testing.os.data.providers.SiteDataProvider;

public class SiteTesting extends AbstractEntityProcessor {
	private static final Logger logger = Logger.getLogger(SiteTesting.class);

	@Override
	@Test(dataProviderClass = SiteDataProvider.class, 
		dataProvider = "siteDataProvider", 
		groups = { "Sites" },
		priority = 1)
	public void runTest(BaseRowEntity entity) {
		// Will utilize the processEntityRow() in super class
		logger.info("Dummy: Running site TCs");
	}

	@Override
	public String callGETApi(BaseRowEntity entity) {
		// Custom logic for calling GET for site
		return null;
	}

	@Override
	public String callPOSTApi(BaseRowEntity entity) {
		// Getting current state of object and saving adding to undoObject list
		// Custom logic for calling POST for site
		return null;
	}

	@Override
	public String callPUTApi(BaseRowEntity entity) {
		// Getting current state of object and saving adding to undoObject list
		// Custom logic for calling PUT for site
		return null;
	}

	@Override
	public String callDELETEApi(BaseRowEntity entity) {
		// Getting current state of object and saving adding to undoObject list
		// Custom logic for calling DELETE for site
		return null;
	}

	@Override
	@Test(groups = { "Sites" }, priority = 2)
	public void afterTestCases() {
		// Clean-up after the Test Cases
		logger.info("Dummy: Clean-up after site TCs");
	}
}
