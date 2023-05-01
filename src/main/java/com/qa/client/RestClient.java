package com.qa.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


public class RestClient {
	
	//1.GET Method without headers
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException
	{
		CloseableHttpClient httpClient=	HttpClients.createDefault();
		HttpGet httpget=new HttpGet(url);//http get request
		CloseableHttpResponse closeablehttpresponse =httpClient.execute(httpget);//hit the GET URL
		return closeablehttpresponse;
		
		
		//2.GET method with headers:
	}
	public CloseableHttpResponse get(String url,HashMap<String, String> headerMap) throws ClientProtocolException, IOException
	{
		CloseableHttpClient httpClient=	HttpClients.createDefault();
		HttpGet httpget=new HttpGet(url);//http get request
		
		for(Map.Entry<String, String> entry:headerMap.entrySet())
		{  
			httpget.addHeader(entry.getKey(),entry.getValue());
			
		}
		CloseableHttpResponse closeablehttpresponse =httpClient.execute(httpget);//hit the GET URL
		return closeablehttpresponse;
		
		}
	
	//3.POST Method:
	public CloseableHttpResponse post(String url,String entitystring,HashMap<String,String> headermap) throws ClientProtocolException, IOException
	{
		CloseableHttpClient httpClient=HttpClients.createDefault();
		HttpPost httppost=new HttpPost(url); //http post request
		httppost.setEntity(new StringEntity(entitystring));// for payload
		
		//for headers:
		for(Map.Entry<String, String> entry:headermap.entrySet())
		{  
			httppost.addHeader(entry.getKey(),entry.getValue());
			
		}
		CloseableHttpResponse closeablehttpresponse =httpClient.execute(httppost);//hit the POST URL
		return closeablehttpresponse;
		
		}
	
	
	

}
