<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<title>Search Application</title>
<style type="text/css">
form {
	margin-top: 50px;
}
</style>

</head>
<body>
	<div class="navbar navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<ul class="nav">
					<li class="active"><a href="#">User</a></li>
					<li><a href="Manifest">Developer</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="span12">
				<form class="well form-search" action="Friendlist" method="POST">
					<label>Please Enter Your Diaspora ID:</label> <input type="text"
						name="diaspora_id" class="input-medium search-query">
					<button type="submit" class="btn">
						<i class="icon-search"></i>Submit
					</button>
				</form>
			</div>
		</div>
	</div>
	<div class="navbar navbar-fixed-bottom">
		<footer>
		<p>Immanent 2013</p>
		</footer>
	</div>
	<script src="bootstrap/js/jquery.js"></script>
	<script src="bootstrap/js/bootstrap.min.js"></script>
</body>
</html>