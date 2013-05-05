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
				

			
				<h2>Tournaments</h2>
				<c:choose>
					<c:when test="${tournaments.size()==0}">
						<em>No Tournaments.</em>
					</c:when>
					<c:otherwise>
						<table class="simpletablestyle">
							<thead>
								<tr>
									<th>Date</th>
									<th>Entry</th>
									<th>Players</th>
									<th>Place</th>
									<th>Win</th>									
								</tr>
							</thead>
							<tbody>
								<c:set var="rows" value="0"/>
								<c:set var="total" value="0"/>
								<c:set var="investment" value="0"/>
								<c:forEach items="${tournaments}" var="tournament">
									<c:set var="rows" value="${rows+1}"/>
									<c:set var="total" value="${total+tournament.net}"/>
									<c:set var="investment" value="${investment+tournament.fee+tournament.entry}"/>									
									
									<c:if test="${rows > tournaments.size() - 10}">									
										<tr>
											<td><fmt:formatDate value="${tournament.start}" pattern="M/d/yyyy"/></td>
											<td>${tournament.entry}+${tournament.fee}</td>
											<td>${tournament.players}</td>										
											<td>${tournament.place}</td>										
											<td>
												<c:if test="${tournament.win>0}">
													${tournament.DisplayAmount(tournament.win)}
												</c:if>
											</td>
										</tr>									
									</c:if>
																		
								</c:forEach>
								<tr>
									<td colspan="5" style="text-align:left; border-top:#333333 solid 1px; background: #ffffff;">Total Net Winnings: ${total}</td>										
								</tr>
								<tr>
									<td colspan="5" style="text-align:left; background: #ffffff;">Wins: (${won}/${played}) Return on Investment: <fmt:formatNumber type="number" maxFractionDigits="2" value="${ ( total + investment) / investment }" /></td>															
								</tr>																									
							</tbody>
						</table>						
					</c:otherwise>
				</c:choose>
				<br/>
				<br/>
				<a href="<c:url value="add"/>">Add Tournaments</a><br/>		
				<a href="<c:url value="ringgame"/>">Ring Games</a>								
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
