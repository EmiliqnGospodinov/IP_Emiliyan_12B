$(document).ready(function() {
	//$("#moviesTable").tablesorter();
	var perPage = 1;
	var currentPage = 0;
	var years = [], directors = [], genres = [];
	function appendMovie(movie){
		var newRow = $("<tr></tr>");
		newRow.append($("<td></td>").text(movie.name));
		newRow.append($("<td></td>").text(movie.director));
		newRow.append($("<td></td>").text(movie.year));
		newRow.append($("<td></td>").text(movie.genre));
		$("#moviesTable").append(newRow);
	}
	function getDirectors(){
		$.get({
		  url: "http://localhost:8080/rst2/api/movies/directors",
		  dataType: "json",
			success: function(response){
					directors = response;
					$("#filterDirector").autocomplete({
						source: directors
					})
	  	}
		});
	}
	function getGenres(){
		$.get({
		  url: "http://localhost:8080/rst2/api/movies/genres",
		  dataType: "json",
			success: function(response){
					for (var i = 0; i < response.length; i++) {
						$("#filterGenre").append($("<option></option>").text(response[i]).attr("value",response[i]));
					}
	  	}
		});
	}

	function getMovies(){
		$.get({
		  url: "http://localhost:8080/rst2/api/movies",
		  dataType: "json",
			data: {page: currentPage,perPage: perPage},
			success: function(response) {
				currentPage++;
				if (currentPage >= response.totalPages) {
					$("#nextPage").hide();
				}
				$.each(response.moviesList, function(index){
					var movie = response.moviesList[index];
					appendMovie(movie);
				});
	  	}
		});
	}
	$("#addMovieForm").submit(function(event){
		event.preventDefault();

		$.post({
			url: "http://localhost:8080/rst2/api/movies",
			contentType: "application/json; charset=utf-8",
		  dataType: "json",
			data: JSON.stringify({
							"name": $("input[name='name']").val(),
							"director": $("input[name='director']").val(),
							"year": $("input[name='year']").val(),
							"genre":  $("input[name='genre']").val()
						}),
			success: function(response){
				alert("Movie: " +response.name+ " created.");
				$("#nextPage").show();
			}
		})
		$(this).find("input[type='text']").val("");
	});
	getMovies();
	getGenres();
	getDirectors();
	$("#nextPage").on("click", function(){
		getMovies();
	});
		// $("#filterDirector").val();
		// $("#filterGenre option:selected").val();
		// $("#filterYear").val();
});
