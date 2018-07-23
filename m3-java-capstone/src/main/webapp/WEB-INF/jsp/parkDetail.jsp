<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/shared/header.jsp" />

	<section class="detail-park-image">
		<c:url value="/img/parks/${park.parkCode}.jpg" var="imgLink"/>
		<img src="${imgLink}">
	</section>
	
	<section class="detail-name">
		<h2>${park.parkName}</h2>
	</section>

	<section class="card detail-info-card">
	
		<div class = "detail-description">
			<div>
				<i>"${park.inspirationalQuote}"</i> <br/>- ${park.inspirationalQuoteSource}
			</div><br>
				
			<div>
				${park.parkDescription}
			</div>
		</div>
	
		<div class="detail-park-details">
			
			<b>Location:</b> ${park.state}
			<b>Size:</b> ${park.acreage} acres
			<b>Elevation:</b> ${park.elevationInFeet} ft
			<b>Trail Length:</b> ${park.milesOfTrail} mi
			<b>Campsites:</b> ${park.numberOfCampsites}
			<b>Climate:</b> ${park.climate}
			<b>Year Founded:</b> ${park.yearFounded}
			<b>Annual Visitors:</b> ${park.annualVisitorCount}
			<b>Entry Fee:</b> $${park.entryFee}
			<b>Animal Species:</b> ${park.numberOfAnimalSpecies}
		</div>
	
	</section>
	
	<section class="detail-name">
		<h2>Five Day Weather Forecast</h2>
		<div>
			<c:choose>
			<c:when test="${celsius}">
				<c:url value="/parkDetail" var="calcLink">
					<c:param name="parkCode" value="${park.parkCode}"/>
					<c:param name="convert" value="false"/>
				</c:url>
				<a class= "converter" href="${calcLink}">Convert to Fahrenheit</a>
				<c:set var="temp" value="C"/>
			</c:when>
			<c:otherwise>
				<c:url value="/parkDetail" var="calcLink">
					<c:param name="parkCode" value="${park.parkCode}"/>
					<c:param name="convert" value="true"/>
				</c:url>
				<a class="converter" href="${calcLink}">Convert to Celsius</a>
				<c:set var="temp" value="F"/>
			</c:otherwise>
		</c:choose>
		</div>
	</section>
	
	<section class="detail-weather card">
		<c:forEach var="weather" items="${weatherForecast}" varStatus="count">
			<c:choose>
				<c:when test="${count.index == 0}">
					<div class="weather-today">
						<c:url value="/img/weather/${weather.forecast}.png" var="weatherImgLink"/>
						<img src="${weatherImgLink}">
						<div><b>High: </b> <span id="high-temp">${weather.high}</span>&deg; ${temp} <b>Low: </b><span id="low-temp">${weather.low}</span>&deg; ${temp}</div>
						<div id="weather-comment">${weather.comment}</div>
					</div> 
				</c:when>
				<c:otherwise>
					<div class="weather-forecast">
					<c:url value="/img/weather/${weather.forecast}.png" var="weatherImgLink"/>
					<img src="${weatherImgLink}">
					<div><b>High: </b> ${weather.high}&deg; ${temp}</div>
					<div><b>Low: </b> ${weather.low}&deg; ${temp}</div>
					</div>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</section>


<c:import url="/WEB-INF/jsp/shared/footer.jsp" />