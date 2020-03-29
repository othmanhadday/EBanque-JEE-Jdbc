<%@page import="com.othmaneHadday.entities.Login"%>
<% Login login =(Login)session.getAttribute("loginSession"); 
	if(login==null){
		request.getRequestDispatcher("/login.oh").forward(request, response);
	}
%>


<div class="navbar navbar-inverse">
	<ul class="nav navbar-nav">
		<li><a href="client.cl">Client</a></li>
		<li><a href="compte.co">Compte</a></li>
		<li><a href="operation.po">Operation</a></li>
		<li><a href="#"><%=login.getUsername() %></a></li>
		<li><a href="Deconnexion">Deconnexion</a>
	</ul>
</div>