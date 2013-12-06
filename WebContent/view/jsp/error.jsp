<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	Throwable throwable = (Throwable) request.getAttribute("throwable");
	String requestUri = (String) request.getAttribute("requestUri");
	String servletName = (String) request.getAttribute("servletName");
	String message = (String) request.getAttribute("message");
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
</head>
<body>
	<!-- Start: HEADER -->
	<header> <!-- Start: Navigation wrapper -->
	<div class="navbar navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<a href=# class="brand brand-bootbus">Diaspora Search
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
						<li><a href="ServiceController">Home</a></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown">About<b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="http://immanent.github.io/">Blog</a></li>
							</ul></li>
						<li><a href="#">FAQ</a></li>
						<li><a href="#">Contact us</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<!-- End: Navigation wrapper --> </header>
	<!-- End: HEADER -->
	<div class="content">
		<div class="container">
			<div class="row">
				<div class="span12">
					<div class="hero-unit center">
						<h1>
							Page Not Found <small><font face="Tahoma" color="red">Error
									<%
							if (throwable == null && message == null) {
								out.println("<h2>Error information is missing</h2>");
							} else if (message != null) {
								out.println("The status code : " + message);
							} else {
								out.println(message);
								out.println("Servlet Name : " + servletName + "</br></br>");
								out.println("Exception Type : " + throwable.getClass().getName() + "</br></br>");
								out.println("The request URI: " + requestUri + "<br><br>");
								out.println("The exception message: " + throwable.getMessage());
							}
						%>
							</font></small>
						</h1>
						<br />
						<p>
							The page you requested could not be found, either contact your
							webmaster or try again. Use your browsers <b>Back</b> button to
							navigate to the page you have prevously come from
						</p>
						<p>
							<b>Or you could just press this neat little button:</b>
						</p>
						<a href="./ServiceController" class="btn btn-large btn-info"><i
							class="icon-home icon-white"></i> Take Me Home</a>
					</div>
					<br />
				</div>
			</div>
			<
		</div>
	</div>
	<div class="navbar navbar-fixed-bottom">
		<footer>
		<hr class="footer-divider">
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
</html>