<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
							data-toggle="dropdown">API Functions<b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li class="nav-header">Functionalities</li>
								<li><a href="Friendlist">View Profile</a></li>
								<li><a href="#">View Friend List</a></li>
								<li><a href="#">View Scopess</a></li>
							</ul></li>
						<li><a href="Manifest">Developer</a></li>
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
	<!-- Start: MAIN CONTENT -->
	<div class="content">
		<!-- Start: slider -->
		<div class="slider">
			<div class="container-fluid">
				<div id="heroSlider" class="carousel slide">
					<div class="carousel-inner">
						<div class="active item">
							<div class="hero-unit">
								<div class="row-fluid">
									<div class="span7 marketting-info">
										<h1>View Your Diaspora Profile</h1>
										<p>Using this functionality in Diaspora API you will be
											able to view your Diaspora profile details.</p>
										<h3>
											<a href="Friendlist" class="btn btn-primary">Try now</a> <a href="#"
												class="btn">Learn more</a>
										</h3>
									</div>
									<div class="span5">
										<img src="bootstrap/img/diaspora-social-network.jpg"
											class="thumbnail">
									</div>
								</div>
							</div>
						</div>
						<div class="item">
							<div class="hero-unit">
								<div class="row-fluid">
									<div class="span7 marketting-info">
										<h1>View Application Access Scopes</h1>
										<p>Using this functionality in Diaspora API you will be
											able to view the access levels you have allowed.</p>
										<h3>
											<a href="#" class="btn btn-primary">Try now</a> <a
												href="#" class="btn">Learn more</a>
										</h3>
									</div>
									<div class="span5">
										<img src="bootstrap/img/Diaspora.png" class="thumbnail">
									</div>
								</div>
							</div>
						</div>
					</div>
					<a class="left carousel-control" href="#heroSlider"
						data-slide="prev"><</a> <a class="right carousel-control"
						href="#heroSlider" data-slide="next">></a>
				</div>
			</div>
		</div>
		<!-- End: slider -->
		<!-- Start: PRODUCT LIST -->
		<div class="container">
			<div class="page-header">
				<h2>API Functionalities</h2>
			</div>
			<div class="row-fluid">
				<ul class="thumbnails">
					<li class="span4">
						<div class="thumbnail">
							<img src="bootstrap/img/diaspora-profile.jpg" alt="product name">
							<div class="caption">
								<h3>Get profile details</h3>
								<p>Using this functionality in Diaspora API you will be able
									to view your Diaspora profile details.</p>
							</div>
							<div class="widget-footer">
								<p>
									<a href="Friendlist" class="btn btn-primary">Try now</a>&nbsp; <a
										href="#" class="btn">Read more</a>
								</p>
							</div>
						</div>
					</li>
					<li class="span4">
						<div class="thumbnail">
							<img src="bootstrap/img/scopes.jpg" alt="product name">
							<div class="caption">
								<h3>Get access scopes</h3>
								<p>Using this functionality in Diaspora API you will be able
									to view the access levels you have allowed.</p>
							</div>
							<div class="widget-footer">
								<p>
									<a href="#" class="btn btn-primary">Try now</a>&nbsp; <a
										href="#" class="btn">Read more</a>
								</p>
							</div>
						</div>
					</li>
					<li class="span4">
						<div class="thumbnail">
							<img src="bootstrap/img/friendlist.jpg" alt="product name">
							<div class="caption">
								<h3>Search Friends</h3>
								<p>Using this functionality in Diaspora API you will be able
									to search friend by their real name.</p>
							</div>
							<div class="widget-footer">
								<p>
									<a href="ContactSearch" class="btn btn-primary">Try now</a>&nbsp; <a
										href="#" class="btn">Read more</a>
								</p>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</div>
		<!-- End: PRODUCT LIST -->
	</div>
	<!-- End: MAIN CONTENT -->
	<!-- Start: FOOTER -->
	<footer>
		<div class="container">
			<p>&copy; 2013 Immanent, All Rights Reserved.</p>
		</div>
	</footer>
	<!-- End: FOOTER -->
	<script type="text/javascript" src="bootstrap/js/jquery.min.js"></script>
	<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="bootstrap/js/boot-business.js"></script>
</body>

</html>