package org.test.swipejobs.jobmatch.service;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.test.swipejobs.jobmatch.model.Job;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.*;

public class JobLookupService {
	
	
	private static ObjectMapper mapper = new ObjectMapper();
	
	public static double findDistance(double lat1, double lat2, double lon1, double lon2)
	{
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515 * 1.609344;
		

		return (dist);
	}

	private static final double deg2rad(double deg)
	{
	    return (deg * Math.PI / 180.0);
	}
	 
	private static final double rad2deg(double rad)
	{
	    return (rad * 180 / Math.PI);
	}
	
	public  String getJobsForEmployee(int keyOne) {
		/*
		 * This method performs the following tasks
		 * 1. First Get all the employee ID
		 * 2. Retrieves all the Jobs available, note that Ideally, we need to maintain a cache for all the jobs,
		 * and the job should be periodically cached here and there should not be a frequent call to the all jobs
		 * 3. Check for the matching jobs for a given employee skill set, note that, in a real time situation I will 
		 * have an employee and job object with the respective data structure holding these values, Here we do not
		 * create object, hence performing all the complex type casting.
		 * 4. Check for the Skills, driving license requirement, and the distance factor to filter the job
		 * 5. The distance is obtained using a method obtained from google, an widely available method. 
		 */
	    Map[] jobs = null;
	    Map worker = new HashMap();
		try {
			String allJobs = getAllJobs();
			String data = getWorkerDetail(keyOne);
		    
			if(allJobs == null || data == null){
				return new String();
			}
			
			jobs = mapper.readValue(allJobs, HashMap[].class);
			worker = mapper.readValue(getWorkerDetail(keyOne), HashMap.class);
		    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    ArrayList matchingJob = new ArrayList();
	    
	    boolean b = (boolean)(worker.get("hasDriversLicense"));
	    ArrayList<String> certificates = (ArrayList<String>)worker.get("certificates");
	    double longitude = Double.parseDouble((String)((LinkedHashMap)(worker.get("jobSearchAddress"))).get("longitude"));
	    double latitude = Double.parseDouble((String)((LinkedHashMap)(worker.get("jobSearchAddress"))).get("latitude"));
	    int maxDistance = (Integer)((LinkedHashMap)(worker.get("jobSearchAddress"))).get("maxJobDistance");

	    for (Map node : jobs)
	    {
	    	boolean req = (boolean)node.get("driverLicenseRequired");
	    	if(req && !b)
	    	{
	    		continue;
	    	}
	    	if(!certificates.containsAll((ArrayList<String>)node.get("requiredCertificates")))
	    	{
	    		continue;
	    	}
	    	//Check for distances
	    	 double joblongitude = Double.parseDouble((String)((LinkedHashMap)(node.get("location"))).get("longitude"));
	 	    double joblatitude = Double.parseDouble((String)((LinkedHashMap)(node.get("location"))).get("latitude"));
	 	   
	    	if(findDistance(latitude,joblatitude,longitude,joblongitude)>maxDistance)
	    	{
	    		continue;
	    	}
	    	else
	    	{
	    		System.out.println("Adding..."+node);
	    		matchingJob.add(node);
	    		if(matchingJob.size()==3)
	    			break;
	    	}
	    }	
	    	 try {
				//System.out.println(mapper.writeValueAsString(matchingJob));
				return mapper.writeValueAsString(matchingJob);	
			} catch (IOException ioe) {
				// TODO Auto-generated catch block
				ioe.printStackTrace();
				return new String();
			}	
	    }

	public  String getAllJobs()
	{	
		
		String output = null;
		
		try {

			URL url = new URL("http://test.swipejobs.com/api/jobs");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));

			
			if ((output = br.readLine()) == null) {
				return null;
			}
			
			
			
						
			conn.disconnect();

		  } catch (MalformedURLException e) {

			e.printStackTrace();

		  } catch (IOException e) {

			e.printStackTrace();

		  }

		return output;
	}
	
	
	public  String getWorkerDetail(int workerId)
	{	
		
		String output = null;
		
		try {

			URL url = new URL("http://test.swipejobs.com/api/workers");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));

			
			System.out.println("Output from Server .... \n");
			if ((output = br.readLine()) != null) {
               // Job job = new ObjectMapper().readValue(output, Job.class);
			}
			
		    JSONArray jsonarray = new JSONArray(output);
		    if(workerId > jsonarray.length())
		    	return null;
	        JSONObject jsonobject = jsonarray.getJSONObject(workerId);
	        

			conn.disconnect();
			return jsonobject.toString();

		  } catch (MalformedURLException e) {

			e.printStackTrace();

		  } catch (IOException e) {

			e.printStackTrace();

		  } catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	
	
}
