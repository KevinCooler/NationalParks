<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>National Parks Geek</title>
    <c:url value="/css/npGeek.css" var="cssHref" />
    <link rel="stylesheet" href="${cssHref}">
</head>

<body>
    <header>
    		<c:url value="/" var="homePageHref" />
    		<c:url value="/img/logo.png" var="logoSrc" />
        <a href="${homePageHref}">
        		<img class="logo" src="${logoSrc}" alt="National Parks Geek logo" />
        </a>
    </header>
    <nav>
        <ul>
        	<c:url value="/survey" var="surveyURL"/>
            <li><a href="${homePageHref}">Home</a></li>
            <li><a href="${surveyURL}">Survey</a></li>
        </ul>
    </nav>
