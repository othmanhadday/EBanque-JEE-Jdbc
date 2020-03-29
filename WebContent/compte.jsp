<%@page import="com.othmaneHadday.Dao.ClientDaoImpl"%>
<%@page import="com.othmaneHadday.Dao.IClientDao"%>
<%@page import="com.othmaneHadday.entities.Compte"%>
<%@page import="com.othmaneHadday.entities.Client"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Compte</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />

</head>
<body>
	<%@include file="header.jsp"%>
	<p></p>
	<div class="container col-md-8 col-md-offset-2">
		<%
		IClientDao clientDao =new ClientDaoImpl();
		String nom =(String)request.getAttribute("compteSucces");
		String err =(String)request.getAttribute("err");
		ArrayList<Client> clients=(ArrayList<Client>)request.getAttribute("toutClients");
		ArrayList<Compte> comptes=(ArrayList<Compte>)request.getAttribute("toutComptes");
				if(nom!=null){
					%>
					<div class="alert alert-success">
						<%=nom %>
					</div>
				<%}
				if(err!=null){
					%>
					<div class="alert alert-danger">
						<%=err %>
					</div>
			<%}%>
	
		<div class="panel panel-primary">
			<div class="panel-heading">Ajouter un Compte</div>
			<div class="panel-body">
				<form action="ajouterCompte.co" method="post">
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label for="email">Client : </label> <select
									class="form-control" name="client">
									<%
										for (Client c : clients) {
									%>
									<option value="<%=c.getCode()%>"><%=c.getNom()%></option>
									<%
										}
									%>
								</select>
							</div>
						</div>
						
						<div class="col-md-6">
							<div class="form-group">
								<label for="email">Solde : </label> <input type="text"
									class="form-control" name="solde">
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
						<th>Num Compte</th><th>Nom Client</th><th>Date de Creation</th><th>Solde</th><th></th>
					</tr>
					<% for(Compte c:comptes){ %>
						<tr>
							<td><%= c.getNumCompte() %></td>
							<td><%= clientDao.getClient(c.getClient().getCode()).getNom() %></td>
							<td><%= c.getDateCreation() %></td>
							<td><%= c.getSolde() %> Dh</td>
							<td><a onclick="return confirm('etes vous sure ?')" href="supprimerCompte.co?id=<%=c.getNumCompte()%>" >Supprimer</a></td>
						</tr>
					<% } %>
				</table>
			</div>
		</div>
	</div>
</body>
</html>