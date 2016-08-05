package model;

import java.io.IOException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class SimplePost {
	
	// Variable declarations
	private String responseBody, restUrl, restParams;
	
	/*
	 * Constructors
	 */
	
	public SimplePost(String restUrl) {
		
		// Initialise the variables
		this.restUrl = restUrl;
		this.restParams = null;
		
		// Execute the REST post
		this.executePost();
	}
	
	public SimplePost(String restUrl, String restParams) {
		
		// Initialise the variables
		this.restUrl = restUrl;
		this.restParams = restParams;
		
		// Execute the REST post
		this.executePost();
	}
	
	/*
	 * Methods
	 */
	
	public String getResponse() {
		return this.responseBody;
	}
	
	public void executePost() {
		
		// Initialise the variables
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost httpPost;
		ResponseHandler<String> responseHandler;
		this.responseBody = null;
		
		try {
			// Initialise the post options
			httpPost = new HttpPost(this.restUrl);
			httpPost.setHeader("Content-Type", "application/json");
			
			// Handle if the params are null
			if(this.restParams != null) {
				StringEntity stringEntity = new StringEntity(this.restParams);
				httpPost.setEntity(stringEntity);
			}
			
			// Initialise the response handler
			responseHandler = new BasicResponseHandler();
			
			// Execute the REST post
			this.responseBody = httpClient.execute(httpPost, responseHandler);
			
			// Close the client
			httpClient.close();
			
		}
		catch(IOException e) {
			System.err.println(e);
		}
		catch(Exception e) {
			System.err.println(e);
		}
		finally {
			// Do nothing
		}
	}
}
