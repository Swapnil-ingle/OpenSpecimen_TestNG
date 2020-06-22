package com.testing.os.data.entity.processor;

import org.testng.Assert;
import org.testng.SkipException;

import com.testing.os.data.entity.BaseRowEntity;
import com.testing.os.data.entity.undo.UndoEntities;

public abstract class AbstractEntityProcessor implements EntityProcessor {
	protected UndoEntities undoEntities = new UndoEntities();

	public void processEntityRow(BaseRowEntity entity) {
		if (entity.isIgnore()) {
			throw new SkipException("Row skipped");
		}

		try {
			String apiType = entity.getApiType();

			switch (apiType) {
				case "GET":
					callGETApi(entity);
					break;
				case "POST":
					callPOSTApi(entity);
					break;
				case "PUT":
					callPUTApi(entity);
					break;
				case "DELETE":
					callDELETEApi(entity);
					break;
				default:
					Assert.fail("Invalid API Type - Make sure the API is one of {'GET', 'POST', 'PUT', 'DELETE'}");
					break;
			}
		} catch (Exception e) {
			Assert.fail("Exception while calling API " + entity.getApiType() + " : " + e.getMessage());
		}
	}
}
