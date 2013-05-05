<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
				<h1>Welcome to JBoss!</h1>

				<div>
					<p>You have successfully deployed a Spring MVC web application.</p>
					<h3>Your application can run on:</h3>
					<img src="<c:url value="/static/resources/gfx/dualbrand_as7eap.png"/>"/>
				</div>
				

			
				<h2>Ring Games</h2>
				<c:choose>
					<c:when test="${ringgames.size()==0}">
						<em>No ring games.</em>
					</c:when>
					<c:otherwise>
						<table class="simpletablestyle">
							<thead>
								<tr>
									<th>Date</th>									
									<th>Minutes</th>									
									<th>Win</th>									
								</tr>
							</thead>
							<tbody>
								<c:set var="rows" value="0"/>
								<c:set var="total" value="0"/>
								<c:set var="minutes" value="0"/>
								<c:forEach items="${ringgames}" var="ringgame">
									<c:set var="rows" value="${rows+1}"/>
									<c:set var="total" value="${total+ringgame.net}"/>
									<c:set var="minutes" value="${minutes+ringgame.minutes}"/>
									
									<c:if test="${rows > ringgames.size() - 10}">									
										<tr>
											<td>${ringgame.start}</td>
											<td>${ringgame.minutes}</td>
											<td>${ringgame.DisplayAmount(ringgame.net)}</td>
										</tr>
									</c:if>
									
								</c:forEach>
								<tr>
									<td>Total</td>										
									<td>
										<fmt:formatNumber type="number" maxFractionDigits="2" value="${ minutes / 60}" /> Hrs
									</td>										
									<td>${total}</td>										
								</tr>								
								<tr>
									<td>Per Hour</td>										
									<td></td>										
									<td>
										<fmt:formatNumber type="number" maxFractionDigits="2" value="${ ( 60 * total) / minutes }" />									
									</td>										
								</tr>								
							</tbody>
						</table>						
					</c:otherwise>
				</c:choose>
				<br/>
				<a href="<c:url value="ringgameadd"/>">Add Ring Game</a><br/>		
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
