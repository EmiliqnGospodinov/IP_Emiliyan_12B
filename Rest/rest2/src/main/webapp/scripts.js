$(document).ready(function() {
	//$("#moviesTable").tablesorter();
	var perPage = 2;
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
					$("#filterGenre option:not(:first-child)").remove();
					for (var i = 0; i < response.length; i++) {
						$("#filterGenre").append($("<option></option>").text(response[i]).attr("value",response[i]));
					}
					// $("#filterGenre option:selected").val();
					// $("#filterYear").val();
	  	}
		});
	}

	function getMovies(){
		var params = {page: currentPage,perPage: perPage};
		if($("#filterDirector").val() !== "") params["director"] = $("#filterDirector").val();
		if($("#filterGenre option:selected").val() !== "") params["genre"] = $("#filterGenre option:selected").val();
		if($("#filterYear").val() !== "") params["year"] = $("#filterYear").val();
		$.get({
		  url: "http://localhost:8080/rst2/api/movies",
		  dataType: "json",
			data: params,
			success: function(response) {
				if (++currentPage > response.totalPages - 1) {
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
				getGenres();
				getDirectors();
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
	$("#search").on("click", function(event){
		event.preventDefault();
		currentPage = 0;
		$("#moviesTable tr:not(:first-child)").remove();
		$("#nextPage").show();
		getMovies();
		$("#filterDirector").val("");
		$("#filterYear").val("");
	})
});
