package org.test.swipejobs.jobmatch.resources;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.test.swipejobs.jobmatch.model.Job;
import org.test.swipejobs.jobmatch.service.JobLookupService;

@Path("/jobsearch")
public class JobLookupResource {
	
	JobLookupService service = new JobLookupService();

	@GET
    @Path("/jobs")
	@Produces(MediaType.APPLICATION_JSON)
	
	public String getAllJobs()
	{
			return service.getAllJobs();
	}
	
	@GET
    @Path("/emp/{empid}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getMatchingJob(@PathParam("empid") int empid){
		
		return service.getJobsForEmployee(empid);
	}
	
	
	

}
