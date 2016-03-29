package com.sailpoint.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.entity.StringEntity;
// import org.json.simple.JSONObject;
import org.apache.http.client.HttpClient;
import javax.json.*;
import java.io.*; 

public class TestMain {
	
	
	
    private static final String iiqIP = "192.168.0.12";
    private static final int iiqPort = 8080;
    private static final String iiqUser = "spadmin";
    private static final String iiqPass = "admin";
    
    
    
    
    // inner class Thread
    
    static class ThreadDemo extends Thread {
    	   private Thread t;
    	   private String threadName;
    	   private int  roleIndex;
    	   
    	   ThreadDemo( String name, int i){
    		   roleIndex=i;
    	       threadName = name;
    	       System.out.println("Creating " +  threadName );
    	   }
    	   public void run() {
    	      System.out.println("Running " +  threadName );
    	      
    	      try{
  	         	CredentialsProvider provider = new BasicCredentialsProvider();
  	            UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(iiqUser, iiqPass);
  	            provider.setCredentials(AuthScope.ANY, credentials);
  	            HttpClient client = HttpClientBuilder.create().setDefaultCredentialsProvider(provider).build();
  	            
  	            String strIdentity = "Carl.Foster";
  	            
  	            List<String> roles = new ArrayList<String>();
  	        
  	            roles.add("Biz Local AD group12");
  	            roles.add("Biz Local AD group14");
  	            roles.add("Biz Local AD group15");
  	            roles.add("Biz Local AD group16");
  	        	 
  	 	        
  			        String iiqRequest = "http://" + iiqIP + ":" + String.valueOf(iiqPort) + "/iiq64p4/rest/workflows/LCM%20Provisioning/launch";
  			        System.out.println("\nRequest: " + iiqRequest);
  			        HttpPost post = new HttpPost(iiqRequest);
  		
  			        
  			        JsonBuilderFactory factory = Json.createBuilderFactory(null);
  			        JsonObject json = factory.createObjectBuilder()
  			        	.add("workflowArgs",factory.createObjectBuilder()
  					            .add("identityName", strIdentity)
  					            .add("flow", "RolesRequest")
  					            .add("requester", "spadmin")
  					            .add("launcher", "spadmin")
  					            .add("sessionOwner", "spadmin")
  					            .add("planMap",factory.createObjectBuilder()
  					                    .add("nativeIdentity", strIdentity)
  					                    .add("accounts", factory.createArrayBuilder()
  					        	                .add(factory.createObjectBuilder()
  					        	                    .add("application", "IIQ")
  					        	                    .add("nativeIdentity", strIdentity)
  					        	                    .add("op","Modify")
  					        	                    .add("attributes", factory.createArrayBuilder()
  					        	        	                .add(factory.createObjectBuilder()
  					        	        	                    .add("name", "assignedRoles")
  					        	        	                    .add("op", "Add")
  					        	        	                    .add("value",roles.get(roleIndex))
  					        	        	                    
  					        	        	                 )
  					        	        	         )
  					        	                    
  					        	                 )
  					        	         )
  					            )
  			            )
  			            .build();
  			        
  			        
  			        StringEntity se = new StringEntity(json.toString());
  			        System.out.println("Outbound JSON: " + json.toString());
  			        se.setContentType("application/json");
  			        post.setEntity(se);
  			        HttpResponse response = client.execute(post);
  			        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
  			        String line = "";
  			        while ((line = rd.readLine()) != null) {
  			            System.out.println(line);
  			        }
  		        }
  		        catch (Exception e){
  		        	System.out.println(e);
  		        }
 	   
    	    
    	     System.out.println("Thread " +  threadName + " exiting.");
    	   }
    	   
    	   public void start ()
    	   {
    	      System.out.println("Starting " +  threadName );
    	      if (t == null)
    	      {
    	         t = new Thread (this, threadName);
    	         t.start ();
    	      }
    	   }

    	}
        
    
    
    

	public static void main(String[] args) {
    	
    	 ThreadDemo T1 = new ThreadDemo( "Thread-1", 0);
         T1.start();
         
         ThreadDemo T2 = new ThreadDemo( "Thread-2", 1);
         T2.start();
        
         ThreadDemo T3 = new ThreadDemo( "Thread-3", 2);
         T3.start();
        
         ThreadDemo T4 = new ThreadDemo( "Thread-4", 3);
         T4.start();
        
    }

}
