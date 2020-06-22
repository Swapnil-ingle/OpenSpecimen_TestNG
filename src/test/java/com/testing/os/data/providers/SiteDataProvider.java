package com.testing.os.data.providers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.opencsv.exceptions.CsvException;

public class SiteDataProvider {
	@DataProvider(name = "siteDataProvider")
	public Iterator<Object[]> userDataProvider() throws IOException, CsvException {
		List<Object[]> testCases = new ArrayList<>();
		return testCases.iterator();
	}
}
