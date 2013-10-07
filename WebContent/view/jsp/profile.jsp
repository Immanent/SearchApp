<%@page import="org.json.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%JSONObject profile = (JSONObject)request.getAttribute("profile"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description"
	content="Bootbusiness | Short description about company">
<meta name="author" content="Your name">
<title>Diaspora | Test Application</title>
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
				<a href="index.html" class="brand brand-bootbus">Diaspora Test
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
						<li><a href="user">Home</a></li>
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
        <article class="article">
          <div class="page-header">
            <h1>Diaspora <small><% out.println(profile.get("diaspora_handle"));%></small></h1>
          </div>
          <div class="row">
            <div class="span10 offset1">            
              <div class="row bottom-space">
                <div class="span3 center-align">
                  <img src="<%out.println(profile.get("image_url"));%>" class="thumbnail">
                </div>
                <div class="span7">
                  <p class="team-member-description">
                    <% 
                     out.println(profile.toString());
                    %>
                  </p>
                </div>
              </div>                           
            </div>
          </div>
        <article>
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