<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/shared/header.jsp" />

<section class="result-title">
	<h2>Today's Survey Results</h2>
	<p> Here are the results for today's survey of the most popular national parks.</p>
</section>


<c:forEach var="result" items="${results}" varStatus="i">
	<c:url var="parkDetailURL" value="/parkDetail">
		<c:param name="parkCode" value="${result.key.parkCode}"></c:param>
	</c:url>
	
	<section class="survey-results card result${i.index}">
		<div class="survey-image">
			<c:url value="/img/parks/${result.key.parkCode}.jpg" var="imgLink"/>
			<a href="${parkDetailURL}"><img src="${imgLink}"></a>
		</div>
		<div class="result-detail">
			<a class="result-detail" href="${parkDetailURL}"><b>${result.key.parkName}</b>:</a>
		</div>
			
		<div class="result-detail">
			<span class="vote-count" id="${result.key.parkName} votes">${result.value}</span>&nbsp;votes
		</div>
	</section>
</c:forEach>


<c:import url="/WEB-INF/jsp/shared/footer.jsp" />