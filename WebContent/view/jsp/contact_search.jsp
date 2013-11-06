<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Contact Search</title>
</head>
<body>
	<div class="center-align">
		<form action="ContactSearch?action=search" method="POST">
			<p>First Name : </p>
			<input type="text" name="first_name"></br>
			<p>Last Name : </p>
			<input type="text" name="last_name"></br>
			<p>Diaspora handle : </p>
			<input type="text" name="diaspora_handle"></br>
			<p>Location : </p>
			<input type="text" name="location"></br>
			<input type="submit" value="Search" class="btn btn-primary btn-large">
		</form>
	</div>
</body>
</html>