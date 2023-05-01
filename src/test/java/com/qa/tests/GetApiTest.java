package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;

public class GetApiTest extends TestBase {
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
	@Test(priority=1)
	public void getTest() throws ClientProtocolException, IOException
	{
		
		restclient=new RestClient();
	    closeablehttpresponse=restclient.get(url);
		
		
		//a.status code:
				int statuscode=closeablehttpresponse.getStatusLine().getStatusCode();
				System.out.println("status code is" + statuscode);
				Assert.assertEquals(statuscode,RESPONSE_STATUS_CODE_200);
				
				//b.json string
				String responseString=EntityUtils.toString(closeablehttpresponse.getEntity(),"UTF-8");
				
				JSONObject responseJson=new JSONObject(responseString);
				System.out.println("Responce JSON from API--->"+ responseJson);
				//single value assertion
				//per page
				String perPageValue=TestUtil.getValueByJPath(responseJson,"/per_page");
				System.out.println("value of per page is....>"+ perPageValue);
			    Assert.assertEquals(Integer.parseInt(perPageValue),6);
				
			    //total:
			    String totalValue=TestUtil.getValueByJPath(responseJson, "/total");
			    System.out.println("value of total is--->"+ totalValue);
			    Assert.assertEquals(Integer.parseInt(totalValue),12);
			    
			    //get the value from JSON ARRAY:
			     String id=TestUtil.getValueByJPath(responseJson, "/data[0]/id");
			     String email=TestUtil.getValueByJPath(responseJson, "/data[0]/email");
			     String firstname=TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");
			     String lastname=TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
			     String avtar=TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");
			     
			     System.out.println(id);
			     System.out.println(email);
			     System.out.println(firstname);
			     System.out.println(lastname);
			     System.out.println(avtar);
				//c.All Headers
				Header[] headersArray=closeablehttpresponse.getAllHeaders();
				HashMap<String, String> allHeaders=new HashMap<String,String>();
				for(Header header:headersArray)
				{
					allHeaders.put(header.getName(),header.getValue());
					
				}
				System.out.println("Headers Array--->"+ allHeaders);
		
	}
	
	@Test(priority=2)
	public void getapiTestwithHeadere() throws ClientProtocolException, IOException
	{
		
		restclient=new RestClient();
		
		HashMap<String, String> headerMap=new HashMap<String,String>();
		headerMap.put("Content-Type", "application/json");
		
	    closeablehttpresponse=restclient.get(url,headerMap);
		
		
		//a.status code:
				int statuscode=closeablehttpresponse.getStatusLine().getStatusCode();
				System.out.println("status code is" + statuscode);
				Assert.assertEquals(statuscode,RESPONSE_STATUS_CODE_200);
				
				//b.json string
				String responseString=EntityUtils.toString(closeablehttpresponse.getEntity(),"UTF-8");
				
				JSONObject responseJson=new JSONObject(responseString);
				System.out.println("Responce JSON from API--->"+ responseJson);
				//single value assertion
				//per page
				String perPageValue=TestUtil.getValueByJPath(responseJson,"/per_page");
				System.out.println("value of per page is....>"+ perPageValue);
			    Assert.assertEquals(Integer.parseInt(perPageValue),6);
				
			    //total:
			    String totalValue=TestUtil.getValueByJPath(responseJson, "/total");
			    System.out.println("value of total is--->"+ totalValue);
			    Assert.assertEquals(Integer.parseInt(totalValue),12);
			    
			    //get the value from JSON ARRAY:
			     String id=TestUtil.getValueByJPath(responseJson, "/data[0]/id");
			     String email=TestUtil.getValueByJPath(responseJson, "/data[0]/email");
			     String firstname=TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");
			     String lastname=TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
			     String avtar=TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");
			     
			     System.out.println(id);
			     System.out.println(email);
			     System.out.println(firstname);
			     System.out.println(lastname);
			     System.out.println(avtar);
				//c.All Headers
				Header[] headersArray=closeablehttpresponse.getAllHeaders();
				HashMap<String, String> allHeaders=new HashMap<String,String>();
				for(Header header:headersArray)
				{
					allHeaders.put(header.getName(),header.getValue());
					
				}
				System.out.println("Headers Array--->"+ allHeaders);
		
	}
}
