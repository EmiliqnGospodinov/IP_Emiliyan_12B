package bg.elsys.ip.rest;

import java.util.List;

/**
 * Deviding the found elements from @Get 
 * 
 **/
public class PaginatedResource { 
	private List<Movie> moviesList;
	private int perPage;
	private int page;
	private int totalPages;
	
	public PaginatedResource(List<Movie> moviesList, int perPage, int page, int totalPages){
		this.moviesList = moviesList;
		this.page = page;
		this.perPage = perPage;
		this.totalPages = totalPages;
	}

	
	public int getTotalPages() {
		return totalPages;
	}


	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}


	public List<Movie> getMoviesList() {
		return moviesList;
	}

	public void setMoviesList(List<Movie> moviesList) {
		this.moviesList = moviesList;
	}

	public int getPerPage() {
		return perPage;
	}

	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
}
