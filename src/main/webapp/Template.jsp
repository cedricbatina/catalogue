<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>.
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
 <head>
  <title>BetoBantu.com ${param.title} </title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/ressources/Librairie.css">
 </head>
 <body>
  <jsp:include page="/Entete.jsp"/>
  <jsp:include page="/${param.content}.jsp"/>
 </body>
</html>