<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<html>

	<head>
		<title>Spring MVC Poker Application</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel="stylesheet" type="text/css" href="<c:url value="/static/resources/css/screen.css"/>"/>
	</head>

	<body>
		<div id="container">
			<div class="dualbrand">
				<img src="<c:url value="/static/resources/gfx/dualbrand_logo.png"/>"/>
			</div>
			<div id="content">
				<h1><fmt:message key="tournament.title"/></h1>

				<div>
					<p>You have successfully deployed a Spring MVC web application.</p>
					<h3>Your application can run on:</h3>
					<img src="<c:url value="/static/resources/gfx/dualbrand_as7eap.png"/>"/>
				</div>
				
		
				<form:form commandName="newTournament" id="reg">
					<h2>Member Registration</h2>
					<p>Enforces annotation-based constraints defined on the model class.</p>
					<table>
						<tbody>
							<tr>
								<td><form:label path="type">Type:</form:label></td>
								<td><form:select path="type" items="${type}"/></td>
								<td><form:errors class="invalid" path="type"/></td>
							</tr>
							<tr>
								<td><form:label path="site">Site:</form:label></td>								
								<td><form:select path="site" items="${dao.Sites()}" itemValue="id" itemLabel="name"/></td>
								<td><form:errors class="invalid" path="site"/></td>
							</tr>
							<tr>
								<td><form:label path="game">Game:</form:label></td>								
								<td><form:select path="game" items="${dao.Games()}" itemValue="id" itemLabel="name"/></td>
								<td><form:errors class="invalid" path="game"/></td>
							</tr>	
							<tr>
								<td><form:label path="start">Start:</form:label>
								<td><form:input path="start"/></td>
								<td><form:errors class="invalid" path="start"/></td>
							</tr>	
							<tr>
								<td><form:label path="end">End:</form:label>
								<td><form:input path="end"/></td>
								<td><form:errors class="invalid" path="end"/></td>
							</tr>	
							<tr>
								<td><form:label path="entry">Entry:</form:label>
								<td><form:input path="entry"/></td>
								<td><form:errors class="invalid" path="entry"/></td>
							</tr>	
							<tr>
								<td><form:label path="fee">Fee:</form:label>
								<td><form:input path="fee"/></td>
								<td><form:errors class="invalid" path="fee"/></td>
							</tr>	
							<tr>
								<td><form:label path="players">Players:</form:label>
								<td><form:input path="players"/></td>
								<td><form:errors class="invalid" path="players"/></td>
							</tr>	
							<tr>
								<td><form:label path="place">Place:</form:label>
								<td><form:input path="place"/></td>
								<td><form:errors class="invalid" path="place"/></td>
							</tr>	
							<tr>
								<td><form:label path="win">Win:</form:label>
								<td><form:input path="win"/></td>
								<td><form:errors class="invalid" path="win"/></td>
							</tr>	
						</tbody>
					</table>
					<table>
						<tr>
							<td>
								<input type="submit" value="Register" class="register"/>
							</td>
						</tr>
					</table>
				</form:form>
				
				<a href="/springmvc">List Tournaments</a>									
				
			</div>
			
			<div id="aside">
				<p>Learn more about JBoss Enterprise Application Platform 6.</p>
				<ul>
					<li><a href="http://red.ht/jbeap-6-docs">Documentation</a></li>
					<li><a href="http://red.ht/jbeap-6">Product Information</a></li>
				</ul>
			</div>
			<div id="footer">
			    <p>
					This project was generated from a Maven archetype from JBoss.<br />
			    </p>
			</div>
		</div>
	</body>
</html>
