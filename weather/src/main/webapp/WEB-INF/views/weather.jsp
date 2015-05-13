<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<style>
.error {
	color: #ff0000;
}

</style>
</head>

<body>
	<div id="main">
		<h2>Weather By Zip Code</h2>
		 		<form:form method="POST" commandName="weather" action="/weather/getWeatherReport">
		 		
				<table>
				<tr>
					<td>Zip Code:</td>
					<td><form:input path="zipCode" /></td>
					<td><form:errors path="zipCode" cssClass="error" /></td>
				</tr>
				<tr>
					<td colspan="3"><input type="submit" /></td>
				</tr>
			</table>
				</form:form>
				
				<c:if test="${not empty weather.cityName}">
			<table>
				<tr>
					<th colspan="5" align="center"><br>
						<h3>Weather Information by Zip Code</h3>
					</th>
				</tr>
				<tr>
					<th>Zip Code</th>
					<th>City</th>
					<th>State</th>
					<th>Temperature(F)</th>
				</tr>
				<tr align="center">
					<td>${weather.zipCode}</td>
					<td>${weather.cityName}</td>
					<td>${weather.stateName}</td>
					<td>${weather.tempFarheneit}</td>
				</tr>
			</table>
		</c:if>
		
		
		
	</div>
</body>
</html>