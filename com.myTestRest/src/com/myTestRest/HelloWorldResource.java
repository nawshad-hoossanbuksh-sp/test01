package com.myTestRest;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.*;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.MatrixParam;

@Path("/helloworld")
public class HelloWorldResource {
		
		@POST
		@Consumes("text/plain")
		public void postClichedMessage(String message){
			
		}


		/*
		@GET
		@Path("contextquery")
		public Response getQueryTwo(@Context UriInfo info ){
			String from = info.getQueryParameters().getFirst("from");
			String to = info.getQueryParameters().getFirst("to");
			List<String> orderBy = info.getQueryParameters().get("orderBy");
			return Response.status(200).entity(" - Returning from:"+from+" to:"+to+" order By:"+orderBy.toString()).build();
			
		}
		*/
		
		@GET
		@Path("/query")
		public Response getUserFromQuery(
				@QueryParam("from") int from,
				@QueryParam("to") int to,
				@QueryParam("orderBy") List<String> orderBy
				){
			
			return Response.status(200).entity("Returning from:"+from+" to:"+to+" order By:"+orderBy.toString()).build();
		}
		
		@GET
		@Produces("text/plain")
		@Path("/users/{username}")
		public Response getUser(@PathParam("username") String userName){
			return Response.status(200).entity("Hello "+userName).build();
		}
		
		
		@GET
		@Path("/books/{year}")
		public Response getBooks(@PathParam("year") String year,
				@MatrixParam("author") String author
				){
			return Response.status(200).entity("Get books "+year+" "+author).build();
		}
		
		
		/* At the end, to avoid overriding the others */
		/*
		@GET
		@Produces("text/plain")
		public String getClichedMessage(){
			return "Hello World";
		}
		*/
		
	
}
