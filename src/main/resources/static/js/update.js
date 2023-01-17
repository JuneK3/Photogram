function update(userId, event) {
	event.preventDefault();
	let data = $("#profileUpdate").serialize();

	$.ajax({
		type: "put",
		url: `/api/user/${userId}`,
		data: data,
		contentType: "application/x-www-form-urlencoded; charset=utf-8",
		dataType: "json"
	}).done(res => { // HttpStatus 200 OK
		location.href = `/user/${userId}`;
	}).fail(error => {
		if (error.responseJSON.payload == null) {
			alert(error.responseJSON.message);
		} else {
			alert(JSON.stringify(error.responseJSON.payload));
		}
	});
}