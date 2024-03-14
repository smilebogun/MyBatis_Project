<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Winia_shop</title>
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
	<link href="<c:url value="resources/css/style.css"/>" rel="stylesheet"/>
</head>
	<!-- favicon.ico 404 (Not Found) -->
    <link rel="icon" href="data:;base64,iVBORw0KGgo=">
    
<style>
	ul{
		list-style:none;	/* - 삭제 */
	}
	
	li>a{
		text-decoration:none;	/* 밑줄 삭제 */
	}
</style>

<body>
<!-- <header> -->

		<div class="navbar navbar-expand-sm container mt-5 mb-3">
			<h3>ACADEMY</h3>
		</div>
									<!-- sticky-top : 스티키 옵션, <body> 안에 <header>를 빼줘야함 -->
		<!-- <nav class="navbar navbar-expand-sm bg-dark navbar-dark sticky-top"> -->
		<nav class="navbar navbar-expand-sm sticky-top">  <!-- css폴더에서 main.css 색상정하기 <head>에 link 넣어주기 -->
		  <!-- <div class="container-fluid"> -->
		  <div class="container">
		    <ul class="navbar-nav w-100"><!--  -->
		      <li class="nav-item">
		        <a class="nav-link" href="<c:url value="userMainForm.do"/>"><i class="fas fa-home white">&nbsp;</i>위니아 ⓔ샵</a> <!-- "nav-link active" 삭제 -->
		      </li>&nbsp;
		      <li class="nav-item">
		        <a class="nav-link" href="<c:url value="ani.do"/>">인공지능</a>
		      </li>&nbsp;&nbsp;&nbsp;&nbsp;
		      <li class="nav-item">
		        <a class="nav-link" href="<c:url value="#"/>">|</a>
		      </li>&nbsp;&nbsp;&nbsp;&nbsp;
<%-- 		      <li class="nav-item">
		        <a class="nav-link" href="<c:url value="memberRegister.do"/>">회원등록</a>
		      </li> --%>
<%-- 		      <li class="nav-item">
		        <a class="nav-link" href="<c:url value="memberList.do"/>">회원리스트</a>
		      </li> --%>
<%-- 		      <li class="nav-item">
		        <a class="nav-link" href="<c:url value="fileUpload.do"/>">파일업로드</a>
		      </li> --%>
		      <li class="nav-item">
		        <a class="nav-link" href="<c:url value="#"/>">카테고리</a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="<c:url value="#"/>">스펙별상품</a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="<c:url value="#"/>">브랜드</a>
		      </li>		      
<%-- 		      <li class="nav-item">
		        <a class="nav-link" href="<c:url value="boardList.do"/>">Board</a>
		      </li>&nbsp;&nbsp; --%>
		      
		      
		      <c:if test="${sessionScope.loginDTO.id == null}">
			      <li class="nav-item ms-auto d-flex"><!-- 부모 li의 w-100을 줘야지 ms-auto가 먹음 -->
			      	<a class="nav-link login" href="<c:url value="/memberLogin.do"/>"><i class="fas fa-user">&nbsp;</i>로그인</a>&nbsp;&nbsp;
	  				<a class="nav-link register" href="<c:url value="/memberRegister.do"/>"><i class="fas fa-user-plus">&nbsp;</i>회원가입</a>&nbsp;&nbsp;
	  				<a class="nav-link" href="javascript:alert('로그인이 필요합니다!!')"><i class="fas fa-shopping-cart">&nbsp;</i>장바구니</a>&nbsp;&nbsp;
				      <%-- <a class="nav-link btn btn-sm btn-outline-light login" href="<c:url value="/memberLogin.do"/>">로그인</a>&nbsp;&nbsp;
				      <a class="nav-link btn btn-sm btn-outline-light register" href="<c:url value="/memberRegister.do"/>">회원가입</a> --%>
			      </li>
		      </c:if>
		      
		      <c:if test="${sessionScope.loginDTO.id != null && sessionScope.mode == 'user'}">
			      <li class="nav-item ms-auto d-flex"><!-- 부모 li의 w-100을 줘야지 ms-auto가 먹음 -->
			      	<a class="me-3 myProfile" type="button" href="<c:url value="/memberInfo.do?find=info&no=${sessionScope.loginDTO.no}"/>">
			      		<i class="fa fa-user-circle">&nbsp;</i>${sessionScope.loginDTO.id} 님 즐거운 하루되세요!!</a>&nbsp;&nbsp;
			      	<a class="nav-link" href="<c:url value="/memberInfo.do?find=pwch&no=${sessionScope.loginDTO.no}"/>"><i class="fas fa-user-edit">&nbsp;</i>내정보관리</a>&nbsp;&nbsp;
	  				<a class="nav-link login" href="<c:url value="javascript:logout()"/>"><i class="fas fa-user-times">&nbsp;</i>로그아웃</a>&nbsp;&nbsp;
	  				<a class="nav-link" href="<c:url value="cartList.do"/>"><i class="fas fa-shopping-cart">&nbsp;</i>장바구니</a>&nbsp;&nbsp;
			        <%--   <a class="nav-link btn btn-sm btn-outline-light login" href="<c:url value="javascript:logout()"/>">로그아웃</a> --%>
			      </li>
		      </c:if>   
		      
		      <c:if test="${sessionScope.loginDTO.id != null && sessionScope.mode == 'admin'}">
		        	<li class="nav-item ms-auto d-flex"><i class="fas fa-user-circle">&nbsp;</i>
		        			관리자 : ${sessionScope.loginDTO.id} 님&nbsp;&nbsp;
				  		<a class="nav-link" href="adminMain.do"><i class="fas fa-user-shield">&nbsp;</i>관리자 홈</a>&nbsp;&nbsp;
				  		<a class="nav-link" href="adminLogout.do"><i class="fas fa-user-times">&nbsp;</i>관리자 로그아웃</a>&nbsp;&nbsp;
				  		<a class="nav-link" href="javascript:alert('테스트 user_ID로 접속하세요!!')">
		  				<i class="fas fa-shopping-cart"></i> 관리자 장바구니</a>&nbsp;&nbsp;
	  				</li>
		      </c:if> 
		      
		      <li class="nav-item">
		        <a class="nav-link"  href="<c:url value="boardList.do"/>"><i class="fas fa-comments">&nbsp;</i>고객센터</a>
		      </li>
		    </ul>
		  </div>
		</nav>

		<script>
		
			function logout(){
				location.href="<c:url value="memberLogout.do"/>";
			}
		
		</script>
		
<main>
	<div class="container mt-5 d-flex">
		<%@ include file="u_left.jsp" %>
		
<!-- </header> -->