<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Winia_shop_AD</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
  										<!-- Ajax -->
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script> 
  										<!-- 폰트어썸 -->
  	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css">
  	<!-- bootstrap -->       
  	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
  	<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css" rel="stylesheet"/>
  	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- css폴더에서 main.css 색상정하기 : css customize를 하려면 가장 밑에 bootstrap 밑에 적기 -->
	<link href="<c:url value="resources/css/main.css"/>" rel="stylesheet"/>
</head>

<style>
	ul{
		list-style:none;	/* - 삭제 */
	}
	
	li>a{
		text-decoration:none;	/* 밑줄 삭제 */
	}
</style>

<body>
<header>   
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <div class="container-fluid">
    <ul class="navbar-nav w-100">
	     <li class="nav-item ms-left" style="color:white; line-height:40px">
	     	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="text-white" href="userMainForm.do"><i class="fas fa-home white"></i> 위니아 ⓔ샵</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	     	<a class="text-white" href="adminMain.do"><i class="fas fa-user-shield"></i> 관리자 홈</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	     </li>

<%--  1.	<c:set var="ctxPath" value="${pageContext.request.contextPath}"/> <!-- href="./" 위와 같음 -->
        	<a class="nav-link active" href="${ctxPath}">Shop</a> --%>
<!--  2.   <a class="nav-link active" href="<c:url value="/"/>">Shop</a> --> <!-- href="./" 위와 같음 -->
<!--  3.   <a class="nav-link active" href="<%=request.getContextPath()%>">Shop</a> --> <!-- href="./" 위와 같음 -->

	<c:if test="${sessionScope.loginDTO.id != null}">
	<%-- <c:if test="${sessionScope.loginDTO.id != null && sessionScope.mode == 'admin'}"> --%>
      <li class="nav-item">
        <a class="nav-link" href="catInput.do">카테고리등록</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="catList.do">카테고리리스트</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="brandInput.do">브랜드등록</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="brandList.do">브랜드리스트</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="prodInput.do">상품등록</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="prodList.do">상품리스트</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="adminMemberList.do">회원리스트</a>
      </li>
	</c:if>
	
      <li class="nav-item ms-auto" style="color:white; line-height:40px">
      	 <c:if test="${sessionScope.loginDTO.name != null}">
	  		<span class="text-white"><i class="fa fa-user-circle">&nbsp;</i>관리자 : ${sessionScope.loginDTO.name} 님
	  		&nbsp;</span>
	  	</c:if>
	  	<c:if test="${sessionScope.loginDTO.name == null}">
	  		<a class="text-white" href="adminLogin.do"><i class="fas fa-user">&nbsp;</i>관리자 로그인</a>&nbsp;&nbsp;&nbsp;&nbsp;
	  	</c:if>
	  	<c:if test="${sessionScope.loginDTO.name != null}">
	  		<a class="text-white" href="adminLogout.do"><i class="fas fa-user-times">&nbsp;</i>관리자 로그아웃</a>&nbsp;&nbsp;&nbsp;&nbsp;
	  	</c:if>
      </li>
    </ul>
  </div>
</nav>
</header>