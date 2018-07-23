<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/shared/header.jsp" />

<c:forEach var="park" items="${parks}">
	<section class="parkList card">
		<c:url var="parkDetailURL" value="/parkDetail">
			<c:param name="parkCode" value="${park.parkCode}"></c:param>
		</c:url>
		<div>
			<a href="${parkDetailURL}">
				<img src="img/parks/${park.parkCode}.jpg" alt="image of ${park.parkName}"/>
			</a>
		</div>
		<div class="list-info">
			<div>
				<a href="${parkDetailURL}">
					<h2>${park.parkName}</h2>
				</a>
			</div>
			<div>
				<p>${park.parkDescription}</p>
			</div>
		</div>
	</section>
</c:forEach>


<c:import url="/WEB-INF/jsp/shared/footer.jsp" />