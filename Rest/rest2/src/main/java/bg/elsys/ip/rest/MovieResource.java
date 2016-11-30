package bg.elsys.ip.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import io.swagger.annotations.Api;

@Path("/movies")
@Api(value = "Api for querying users")
public class MovieResource {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
    @Path("/directors")
	public List getDirectors(){
		MovieService movieInstance = MovieService.getInstance();
		return movieInstance.getDirectors();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
    @Path("/genres")
	public List getGenres(){
		MovieService movieInstance = MovieService.getInstance();
		return movieInstance.getGenres();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public PaginatedResource getMovies(@QueryParam("page")int page,@QueryParam("perPage") int perPage,
			@QueryParam("year") String year, @QueryParam("director")String director,@QueryParam("genre")String genre){
		MovieService movieInstance = MovieService.getInstance();
		PaginatedResource moviesInPages = movieInstance.getMoviesInPagesFiltered(page, perPage, year, director, genre);
		
		return moviesInPages;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createMovie(Movie movie){
		MovieService.getInstance().addMovie(movie);
		return Response.ok(movie).status(Status.CREATED).build();
	}
}

