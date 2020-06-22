package com.testing.os.data.entity.processor;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import org.apache.http.client.ClientProtocolException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.testing.os.data.entity.BaseRowEntity;

public interface EntityProcessor {
	public void runTest(BaseRowEntity entity);

	public String callGETApi(BaseRowEntity entity) throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, ClientProtocolException, IOException;

	public String callPOSTApi(BaseRowEntity entity) throws ClientProtocolException, JsonProcessingException, IOException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException;

	public String callPUTApi(BaseRowEntity entity) throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, ClientProtocolException, IOException;

	public String callDELETEApi(BaseRowEntity entity) throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, ClientProtocolException, IOException;

	public void afterTestCases();
}
