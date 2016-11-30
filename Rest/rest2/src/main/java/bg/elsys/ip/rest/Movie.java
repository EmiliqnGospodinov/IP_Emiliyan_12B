package bg.elsys.ip.rest;

import io.swagger.annotations.ApiModelProperty;

public class Movie {
	
	@ApiModelProperty(required = true)
	private int id;
	private String name;
	private String director;
	private String year;
	private String genre;
	
	public Movie(){
		
	}
	
	public Movie(int id, String name, String director, String year, String genre) {
		this.id = id;
		this.name = name;
		this.director = director;
		this.year = year;
		this.genre = genre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	
}
