<%@page import="com.othmaneHadday.entities.Versement"%>
<%@page import="javax.servlet.jsp.tagext.TryCatchFinally"%>
<%@page import="com.othmaneHadday.entities.Retrait"%>
<%@page import="com.othmaneHadday.entities.Operation"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.sun.org.apache.bcel.internal.ExceptionConstants"%>
<%@page import="com.othmaneHadday.entities.Client"%>
<%@page import="com.othmaneHadday.entities.Compte"%>
<%@page import="com.othmaneHadday.entities.Login"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>operation</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
</head>
<body>
	<%
		Long code = 0L;
		Compte compte = (Compte) request.getAttribute("compte");
		Client client = (Client) request.getAttribute("client");
		ArrayList<Operation>operations=(ArrayList<Operation>)request.getAttribute("operations");
		if (compte != null) {
			code = compte.getNumCompte();
		}
	%>

	<%@include file="header.jsp"%>
	<div class="col-md-6">
		<div class="panel panel-primary">
			<div class="panel-heading">Consultation d'un compte</div>
			<div class="panel-body">
				<form action="operation.po" method="get">
					<div>
						<label>Code Compte</label> <input type="number" value="<%=code%>"
							name="codeCompte" />
						<button type="submit" class="btn btn-primary">Ok</button>
					</div>
				</form>
				<%
					String err = (String) request.getAttribute("exception");
					if (err != null) {
				%>
				<div class="text-danger"><%=err%></div>
				<%
					}
				%>
			</div>
		</div>

		<%
			if (compte != null) {
		%>
		<div class="panel panel-primary">
			<div class="panel-heading">Information sur le compte</div>
			<div class="panel-body">
				<div>
					<label>Num Compte : </label> <label><%=compte.getNumCompte()%></label>
				</div>
				<div>
					<label>Client : </label> <label><%=client.getNom()%></label>
				</div>
				<div>
					<label>Solde : </label> <label><%=compte.getSolde()%> Dh</label>
				</div>
				<div>
					<label>Date Creation : </label> <label><%=compte.getDateCreation()%></label>
				</div>
			</div>
		</div>
		<%
			}
		%>
	</div>


	<div class="col-md-6">
		<%
			if (compte != null) {
		%>
		<div class="panel panel-primary">
			<div class="panel-heading">Operation sur le compte</div>
			<div class="panel-body">
			<% if (err != null) { %>
			<div class="alert alert-danger">
				<%=err %>
			</div>
			<%} %>
				<form action="ajouterOperation.po" method="post">
					<div>
						<label>Compte :</label> <input type="hidden" name="codeCompte"
							value="<%=compte.getNumCompte()%>" /> <label><%=compte.getNumCompte()%></label>
					</div>
					<div>
						<input type="radio" name="typeOperation" value="Vers"
							checked="checked"
							onchange="document.getElementById('forVirment').style.display='none'" />
						<label>Versement</label> <input type="radio" name="typeOperation"
							value="Retr"
							onchange="document.getElementById('forVirment').style.display='none'" />
						<label>Retrait</label> <input type="radio" name="typeOperation"
							value="Vir"
							onchange="document.getElementById('forVirment').style.display='block'" />
						<label>Virement</label>
					</div>
					<div id="forVirment" style="display: none">
						<labe>Vers : </labe>
						<input type="text" name="codeCompte2" />
					</div>
					<div>
						<labe>Montant : </labe>
						<input type="text" name="montant" />
					</div>
					<div class="text-danger"></div>
					<button type="submit" class="btn btn-primary">save</button>
				</form>
			</div>
		</div>
		<%
			}
		%>
		
		<%
			if (compte != null) {
		%>
		
		<div class="panel panel-primary">
			<div class="panel-heading">Listes des operation</div>
			<div class="panel-body">
				<table class="table table-striped">
						<tr>
							<th>Num</th>
							<th>Type</th>
							<th>Date</th>
							<th>Montant</th>
						</tr>
						<% for(Operation o:operations){ %>
						<tr>
							<th><%= o.getNumOperation() %></th>
							<% 
								try{
									Retrait r=(Retrait)o;
									if(r.getType_Mouvement()!=null) {%>
								<th><%= r.getType_Mouvement() %></th>
								<%
									}
								}catch(Exception e){}
							%>
							<% 
								try{
									Versement v=(Versement)o;
									if(v.getType_Mouvement()!=null) {%>
								<th><%= v.getType_Mouvement() %></th>
								<%
									}
								}catch(Exception e){}
							%>
							<th><%= o.getDateOperation() %></th>
							<th><%= o.getMontantMouvement() %> Dh</th>
						</tr>
						<% } %>
					</table>
			</div>
		</div>
		
		<%
			}
		%>
	</div>
</body>
</html>