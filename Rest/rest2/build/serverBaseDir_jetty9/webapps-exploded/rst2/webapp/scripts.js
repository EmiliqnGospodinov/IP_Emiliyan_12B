$(document).ready(function() {
	$("#button").click(function (){
		$.get({
			url: "http://localhost:8081/rst/api/users",
			dataType: "json",
			success: function(data){
				console.log(data);
				$.each(data, function(index){
					var tr = $('<tr>');
					tr.append("<td> " + data[index].name + "</td>");
					$("#usersTable").append(tr);
				});
			}

		});
	});
});