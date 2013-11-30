<%@page import= "java.util.ArrayList"%>
<%@page import= "com.immanent.models.dao.ContactDetail"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
    ArrayList<ContactDetail> resultSet = (ArrayList<ContactDetail>) request.getAttribute("search_result");
    String diasporaID = (String) request.getAttribute("diasporaID");
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
						<li><a href="../SearchApp/ContactSearch" >Contact Search</a></li>
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
				<h1>Search Results</h1>
			</div>
			<div>
				<%if (resultSet.size()==0) {
					out.println("<div class=\"alert alert-danger\" style=\"margin-left:30px; margin-right: 30px; text-align: center;\">");
					out.println("No result found!");
					out.println("</div>");
				}%>
				<table class="table table-hover">
					<tbody>
					
				<% for (ContactDetail result : resultSet) {
				%>
					<tr>
				      <td style="width: 150px">
				      	<img class="img-polaroid" src="<%out.println(result.getAvatar()); %>" style="width:100px; height:100px">
				      </td>	
				      <td>
				      	<p>
							<strong>Name:</strong> 
							<span>
								<% 										
									String[] split = diasporaID.split("@");
									String url = "http://"+split[1]+"/people?q="+result.getDiasporaHandle();
								%>
								<a href="<%out.println(url);%>" target="_blank">
									<%out.println(result.getFirstName()+" "+result.getLastName());%>
								</a>
							</span>
						</p>
						<p>
							<strong> Diaspora ID:</strong>
							<span> 
								<%out.println(result.getDiasporaHandle());%>
							</span>
						</p>
						<p>
							<strong>Location:</strong>
							<span> 
								<%out.println(result.getLocation());%>
							</span>
						</p>
				      </td>	      
				    </tr>
				<%}%>
				    
					</tbody>			
				</table>
				
				</div>
		</div>
	</div>
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

</html>