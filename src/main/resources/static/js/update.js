function update(userId, event) {
	event.preventDefault(); // 폼태그 액션 막기
	let data = $("#profileUpdate").serialize(); // key=value

	$.ajax({
		type: "put",
		url: `/api/user/${userId}`,
		data: data,
		contentType: "application/x-www-form-urlencoded; charset=utf-8",
		dataType: "json"
	}).done(res => { // HttpStatus 200 OK
		// console.log("성공", res);
		location.href = `/user/${userId}`;
	}).fail(error => {
		if (error.data == null) {
			alert(error.responseJSON.message);
		} else {
			alert(JSON.stringify(error.responseJSON.data));
		}
	});
}