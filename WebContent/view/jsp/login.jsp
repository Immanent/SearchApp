<%@ page import="com.immanent.models.LoginModel"%><%@page
	language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<title>SearchApp - Login</title>

<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
<style type="text/css">
/* Override some defaults */
html,body {
	background-color: #eee;
}

body {
	padding-top: 40px;
}

.container {
	width: 300px;
}

/* The white background content wrapper */
.container>.content {
	background-color: #fff;
	padding: 20px;
	margin: 0 -20px;
	-webkit-border-radius: 10px 10px 10px 10px;
	-moz-border-radius: 10px 10px 10px 10px;
	border-radius: 10px 10px 10px 10px;
	-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .15);
	-moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .15);
	box-shadow: 0 1px 2px rgba(0, 0, 0, .15);
}

.login-form {
	margin-left: 65px;
}

legend {
	margin-right: -50px;
	font-weight: bold;
	color: #404040;
}
</style>
</head>
<body>
	<div class="container">
		<div class="content">
			<div class="row">
				<div class="login-form">
					<h2>Login</h2>
					<%
						if (request.getAttribute("error") != null) {
							String error = (String) request.getAttribute("error");
							out.println("<tr bgcolor=" + "#FFFFCC" + " " + "class="
									+ "show" + ">");
							out.println("<td width=" + "25" + "><img src="
									+ "images/error.png" + " /></td> <td width=" + "246"
									+ "+><p class=" + "error" + ">" + error + "</p></td> ");
						}
					%>
					<form action="Login" method="post">
						<fieldset>
							<div class="clearfix">
								<input type="text" placeholder="Username">
							</div>
							<div class="clearfix">
								<input type="password" placeholder="Password">
							</div>
							<button class="btn btn-primary" type="submit">Sign in</button>
						</fieldset>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
