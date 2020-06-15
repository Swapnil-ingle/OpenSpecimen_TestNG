package com.testing.os.data.providers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.opencsv.exceptions.CsvException;
import com.testing.os.data.entity.UserRowDetail;
import com.testing.os.data.readers.CSVFileReader;
import com.testing.os.data.util.Config;
import com.testing.os.data.util.Utility;

public class UserDataProvider {
	@DataProvider(name = "userDataProvider")
	public Iterator<Object[]> userDataProvider() throws IOException, CsvException {
		CSVFileReader csvReader = new CSVFileReader(Config.USER_FILE_NAME);
		List<Object[]> testCases = new ArrayList<>();

		for (String[] row : csvReader.readAll()) {
			UserRowDetail user = getUserRowDetail(row);
			testCases.add(new UserRowDetail[] { user });
		}

		return testCases.iterator();
	}

	private UserRowDetail getUserRowDetail(String[] row) {
		UserRowDetail userRowDetail = new UserRowDetail();

		userRowDetail.setId(Utility.toLong(row[1]));
		userRowDetail.setFirstName(row[2]);
		userRowDetail.setLastName(row[3]);
		userRowDetail.setEmailAddress(row[4]);
		userRowDetail.setDomainName(row[5]);
		userRowDetail.setLoginName(row[6]);
		userRowDetail.setInstituteId(Utility.toLong(row[7]));
		userRowDetail.setInstituteName(row[8]);
		userRowDetail.setPrimarySite(row[9]);
		userRowDetail.setType(row[10]);
		userRowDetail.setPhoneNumber(row[11]);
		userRowDetail.setManageForms(Utility.toBoolean(row[12]));
		userRowDetail.setDnd(Utility.toBoolean(row[13]));
		userRowDetail.setAddress(row[14]);
		userRowDetail.setTimeZone(row[15]);
		userRowDetail.setCreationDate(Utility.toDate(row[16]));
		userRowDetail.setActivityStatus(row[17]);

		// BaseEntity Attributes
		userRowDetail.setIgnore(Utility.toBoolean(row[18]));
		userRowDetail.setApiType(row[19]);
		userRowDetail.setRunAs(row[20]);
		userRowDetail.setTcId(row[21]);
		userRowDetail.setDesc(row[22]);
		userRowDetail.setTcType(row[23]);

		return userRowDetail;
	}
}
