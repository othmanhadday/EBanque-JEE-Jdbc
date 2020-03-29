<%@page import="com.othmaneHadday.entities.Login"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>login</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
</head>
<body>
	<p></p>
	<div class=" container col-md-6 col-md-offset-3">
		<div class="panel panel-primary">
			<div class="panel-heading">Login Page</div>
			<div class="panel-body">
			<% 
				Login login=(Login)session.getAttribute("loginSession");
			
				if(login!=null){
					request.getRequestDispatcher("/index.po").forward(request, response);
				}
				
				String err=(String)request.getAttribute("error");
				if(err!=null ){%>
					<div class="alert alert-danger">
						<%= err %>
					</div>
				<%}%>
				<form action="loginSub.oh" method="post">
					<div class="form-group">
						<label for="email">Username:</label> 
						<input type="text" class="form-control" name="username">
					</div>
					<div class="form-group">
						<label for="pwd">Password:</label>
						<input type="password" class="form-control" name="pwd">
					</div>
					<button type="submit" class="btn btn-primary">Login</button>
				</form>
			</div>
		</div>

	</div>

</body>
</html>