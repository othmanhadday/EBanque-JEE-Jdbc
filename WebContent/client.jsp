    <%@page import="com.othmaneHadday.entities.Client"%>
<%@page import="java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Client</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
</head>
<body>
	<%@include file="header.jsp"%>
	<p></p>
	<div class="container col-md-8 col-md-offset-2">
		<%
				String nom =(String)request.getAttribute("clientSaved");
				ArrayList<Client> clients=(ArrayList<Client>)request.getAttribute("toutClients");
				if(nom!=null){
					%>
					<div class="alert alert-success">
						<%=nom %>
					</div>
				<%}
			%>
	
		<div class="panel panel-primary">
			<div class="panel-heading">Ajouter un client</div>
			<div class="panel-body">
				<form action="ajouterClient.cl" method="post">
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label for="email">Username:</label> <input type="text"
									class="form-control" name="username">
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label for="pwd">Password:</label> <input type="password"
									class="form-control" name="pwd">
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label for="nom">Nom :</label> <input type="text"
									class="form-control" name="nom">
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-10">
							<div class="form-group">
								<label for="email">Address :</label> <input type="text"
									class="form-control" name="address">
							</div>
						</div>
					</div>
					<button type="submit" class="btn btn-primary">Ajouter</button>
				</form>
			</div>
		</div>
	</div>


	<p></p>
	<div class="container col-md-10 col-md-offset-1">
		<div class="panel panel-primary">
			<div class="panel-heading">listes des Clients</div>
			<div class="panel-body">
				<table class="table table-striped">
					<tr>
						<th>ID</th><th>Nom</th><th>Address</th><th></th><th></th>
					</tr>
					<% for(Client c:clients){ %>
						<tr>
							<td><%= c.getCode() %></td>
							<td><%= c.getNom() %></td>
							<td><%= c.getAddress() %></td>
							<td><a onclick="return confirm('etes vous sure ?')" href="supprimerClient.cl?id=<%=c.getCode()%>" >Supprimer</a></td>
						</tr>													
					<% } %>
				</table>
			</div>
		</div>
	</div>

</body>
</html>