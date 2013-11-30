<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String error = "";
	error = (String) request.getAttribute("error");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description"
	content="Bootbusiness | Short description about company">
<meta name="author" content="Your name">
<title>Diaspora | Search Application</title>
<!-- Bootstrap -->
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- Bootstrap responsive -->
<link href="bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">
<!-- Font awesome - iconic font with IE7 support -->
<link href="bootstrap/css/font-awesome.css" rel="stylesheet">
<link href="bootstrap/css/font-awesome-ie7.css" rel="stylesheet">
<!-- Bootbusiness theme -->
<link href="bootstrap/css/boot-business.css" rel="stylesheet">
<link href="bootstrap/css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<!-- Start: HEADER -->
	<header> <!-- Start: Navigation wrapper -->
	<div class="navbar navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<a href="../SearchApp/" class="brand brand-bootbus">Diaspora Search
					Application</a>
				<!-- Below button used for responsive navigation -->
				<button type="button" class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<!-- Start: Primary navigation -->
				<div class="nav-collapse collapse">
					<ul class="nav pull-right">
						<li><a href="../SearchApp/" >Home</a></li>
						<li><a href="http://immanent.github.io/" target="_blank">About</a></li>
						<li><a href="http://immanent.github.io/contact_us.html" target="_blank">Contact us</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<!-- End: Navigation wrapper --> </header>
	<!-- End: HEADER -->
	<div class="content">
		<div class="container">
			<div class="page-header">
				<h1>Contact Search</h1>
			</div>
			<div class="row">
				<div class="span6 offset3">
					<h4 class="widget-header">
						<i class="icon-search" style="padding-right: 10px"></i>Search
						Friends in Diaspora Network
					</h4>
					<div class="widget-body">

						<%if (!error.isEmpty()) {
							out.println("<div class=\"alert alert-danger\" style=\"margin-left:30px; margin-right: 30px; text-align: center;\">");
							out.println("<strong>Error</strong>");
							out.println(" - "+error);
							out.println("</div>");
						}%>

						<form class="form-horizontal" action="ContactSearch?action=search"
							method="POST">
							<div class="control-group">
								<label class="control-label" for="first_name">First Name</label>
								<div class="controls">
									<input class="input-xlarge" type="text" id="first_name"
										name="first_name" placeholder="Josh">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="last_name">Last Name</label>
								<div class="controls">
									<input class="input-xlarge" type="text" id="last_name"
										name="last_name" placeholder="Nicholas">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="diaspora_handle">Diaspora ID</label>
								<div class="controls">
									<input class="input-xlarge" type="text" id="diaspora_handle"
										name="diaspora_handle" placeholder="test@example.com">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="location">Location</label>
								<div class="controls">
									<input class="input-xlarge" type="text" id="location"
										name="location" placeholder="landon">
								</div>
							</div>
							<div class="control-group">
								<div class="controls">
									<button type="submit" class="btn btn-primary">Search</button>
									<a href="../SearchApp/user" class="btn">Cancel</a>
								</div>
							</div>
						</form>

					</div>
				</div>
			</div>
		</div>
	</div>
	</br>
	</br>
	</br>
	</br>
	<div class="navbar navbar-fixed-bottom">
		<footer>
		<div class="container">
			<p>&copy; 2013 Immanent, All Rights Reserved.</p>
		</div>
		</footer>
	</div>
	<!-- End: FOOTER -->
	<script type="text/javascript" src="bootstrap/js/jquery.min.js"></script>
	<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="bootstrap/js/boot-business.js"></script>
</body>