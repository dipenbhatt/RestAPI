package com.qa.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.Users;

public class postApiTest extends TestBase {

	TestBase testbase;
	String serviceurl;
	String apiurl;
	String url;
	RestClient restclient;
	CloseableHttpResponse closeablehttpresponse;
	

	@BeforeMethod
	public void setUp() throws ClientProtocolException, IOException
	{
		
		testbase=new TestBase();
		serviceurl=prop.getProperty("url");
		apiurl=prop.getProperty("serviceURL");
		
		url=serviceurl+apiurl;
		
	}
	
	@Test
	public void postAPITest() throws StreamWriteException, DatabindException, IOException
	{
		restclient=new RestClient();
		
		HashMap<String, String> headerMap=new HashMap<String,String>();
		headerMap.put("Content-Type", "application/json");

       //Jakson API:
		ObjectMapper mapper=new ObjectMapper();
		Users users=new Users("morpheus","leader");//expected users object
		
		//object to json file:
		mapper.writeValue(new File("C:\\QA\\SeleniumWorkSpace\\restapi\\src\\main\\java\\com\\qa\\data\\users.json"), users);
		
		//object to json on string
		String userjsonstring=mapper.writeValueAsString(users);
		System.out.println(userjsonstring);
		
		closeablehttpresponse=restclient.post(url, userjsonstring, headerMap);//call the API
		
		//validate response from API
		//1.status code:
		int statuscode=closeablehttpresponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statuscode,testbase.RESPONSE_STATUS_CODE_201);
		
		//2.Jsonstring:
		String responseString=EntityUtils.toString(closeablehttpresponse.getEntity(),"UTF-8");
		
		JSONObject responsejson=new JSONObject(responseString);
		System.out.println("the response from API is"+ responseString);
		
		Users usersResObj=mapper.readValue(responseString, Users.class);//actual user object
		System.out.println(usersResObj);
		
		Assert.assertTrue(users.getName().equals(usersResObj.getName()));
		Assert.assertTrue(users.getJob().equals(usersResObj.getJob()));
		
		System.out.println(usersResObj.getId());
		System.out.println(usersResObj.getCreatedAt());
	}
}
