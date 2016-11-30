package bg.elsys.ip.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MovieService {
	private static MovieService instance;
	private List<Movie> moviesList = new ArrayList<>();
	private int id = 1;
	
	public static MovieService getInstance() {
		if (instance == null) {
			instance = new MovieService();
		}
		return instance;
	}	
	
	public MovieService() {
		moviesList.add(new Movie(id++, "Transformers", "Michael Bay", "2009", "Action"));
		moviesList.add(new Movie(id++, "Transformers2", "Michael Bay", "2011", "Action"));
		moviesList.add(new Movie(id++, "Titanic", "ASDF", "2011", "Drama"));
		moviesList.add(new Movie(id++, "Tasdfasdf", "ASDF", "2009", "Action"));
	}
	
	public void addMovie(Movie movie){
		moviesList.add(movie);
	}
	
	public List getDirectors(){
		List<String> directors = new ArrayList<String>();
		for(int i = 0; i < moviesList.size(); i++){
			String director = moviesList.get(i).getDirector();
			if(!directors.contains(director)){
				directors.add(director);
			}
		}
		return directors;
	}
	
	public List getGenres(){
		List<String> genres = new ArrayList<String>();
		for(int i = 0; i < moviesList.size(); i++){
			String genre = moviesList.get(i).getGenre();
			if(!genres.contains(genre)){
				genres.add(genre);
			}
		}
		return genres;
	}
	
	public PaginatedResource getMoviesInPagesFiltered(int page, int perPage, String year,String director,String genre) {

		List<Movie> filteredByDirector = moviesList.stream()
				.filter((movie) -> movie.getDirector().equals(director) || director == null)
				.collect(Collectors.toList());
		List<Movie> filteredByGenre = filteredByDirector.stream()
				.filter((movie) -> movie.getGenre().equals(genre) || genre == null)
				.collect(Collectors.toList());
		List<Movie> allFiltered = filteredByGenre.stream()
			.filter((movie) -> movie.getYear().equals(year) || year == null).collect(Collectors.toList());
		int filteredCount = allFiltered.size();
		int lastElement = (page*perPage+perPage > allFiltered.size())?allFiltered.size():page*perPage+perPage;
		List<Movie> filtered = allFiltered.subList(page*perPage, lastElement);
		System.out.println(allFiltered.size()+" "+perPage+" "+allFiltered.size() / perPage);
		int totalPages =(int) Math.ceil((double) filteredCount / perPage);
		return new PaginatedResource(filtered, perPage, page, totalPages);
	}
}
