package com.temp;

import java.applet.*;
import java.awt.*;
import java.io.IOException;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

@SuppressWarnings("serial")
public class HelloWorldApplet extends Applet {
	
	private OkHttpClient client;
	
	public void init() {
		client = new OkHttpClient();		
	}
	
	/** 
	 * @see 
	 * http://www.vogella.com/tutorials/JavaLibrary-OkHttp/article.html
	 * https://docs.oracle.com/javase/tutorial/deployment/applet/invokingAppletMethodsFromJavaScript.html
	 * http://dillonbuchanan.com/programming/communicating-between-java-applets-and-javascript/
	 *  
	 **/
	
	String doGetRequest(String url) throws IOException {
        Request request = new Request.Builder()
            .url(url)
            .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
	}
	
	/** @see http://www.baeldung.com/google-http-client **/
	String doGoogleRequest(String url) throws IOException {
		HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
		HttpRequest request = requestFactory.buildGetRequest(new GenericUrl("http://localhost:8080/AppletTutorial/test.txt"));
		HttpResponse rawResponse = request.execute();
		return rawResponse.parseAsString();
	}
	
	public void paint (Graphics g) {		
		try {
			System.out.println("DEBUGGING APP");
			g.drawString ("DEBUGGING APP", 25, 50);
			g.drawString (doGoogleRequest(""), 25, 100);
			//g.drawString (doGoogleRequest(""), 25, 50);
			//g.drawString (doGetRequest("http://www.vogella.com/tutorials/JavaLibrary-OkHttp/article.html"), 25, 50);
		} catch (IOException e) {
			g.drawString ("HTTP request unsuccessful", 25, 50);
			e.printStackTrace();
		}
   }
}