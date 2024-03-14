<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<jsp:include page="ad_header.jsp"/>

<!-- 컨텐츠 -->
<div class="container w-50 mt-5 border shadow p-5">	
	<h2>관리자 로그인</h2><br>
	
	<form action="adminLoginOk.do" method="post">
	  <div class="mb-3 mt-3">
	    <label for="id" class="form-label">관리자 아이디</label>
	    <input type="text" class="form-control" id="id" placeholder="아이디" name="id" 
	    		value="<c:if test="${sessionScope.loginDTO.id !=null and loginErr != 'idErr'}">${sessionScope.loginDTO.id}</c:if>"/>
<%-- 	    <c:if test="${requestScope.loginErr == 'idErr'}">   
	    	<p class="text-danger">관리자 아이디 불일치!!</p>
	    </c:if> --%>
	  </div>
	  <div class="mb-3">
	    <label for="password" class="form-label">관리자 비밀번호</label>
	    <input type="password" class="form-control" id="password" placeholder="비밀번호" name="password"/>
<%-- 	    <c:if test="${requestScope.loginErr == 'pwdErr'}">
	    	<p class="text-danger">관리자 비밀번호 불일치!!</p>
	    </c:if> --%>
	  </div>
	  		<c:if test="${requestScope.loginErr == 'idErr' || requestScope.loginErr == 'pwdErr'}">   
		    	<p class="text-danger text-center mt-3 mb-0" style="font-size:15px">관리자 아이디 또는 비밀번호 불일치!!</p><br>
		    </c:if>
	  <div class="text-center">
	  	<button type="submit" class="btn btn-primary">로그인</button>
	  	<a href="<c:url value="/"/>" class="btn btn btn-outline-primary me-2">위니아 ⓔ샵</a>
	  </div>
	</form>
</div>

<jsp:include page="ad_footer.jsp"/>