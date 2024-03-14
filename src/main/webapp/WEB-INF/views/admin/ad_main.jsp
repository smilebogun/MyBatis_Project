<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
   
<jsp:include page="ad_header.jsp"/>
<!-- 컨텐츠 -->
<div class="container mt-5">   
   <h2>관리자 페이지</h2><br>
   <ul>
   	<c:if test="${sessionScope.loginDTO.id != null}">
	<%-- <c:if test="${sessionScope.loginDTO.id != null && sessionScope.mode == 'admin'}"> --%>
      <li><a href="catInput.do">쇼핑몰 - 카테고리 등록</a></li>
      <li><a href="catList.do">쇼핑몰 - 카테고리 리스트</a></li>
      <br>
      <li><a href="brandInput.do">쇼핑몰 - 브랜드 등록</a></li>
      <li><a href="brandList.do">쇼핑몰 - 브랜드 리스트</a></li>
      <br>
      <li><a href="prodInput.do">쇼핑몰 - 상품 등록</a></li>
      <li><a href="prodList.do">쇼핑몰 - 상품 리스트</a></li>
      <br>
      <li><a href="adminMemberList.do">쇼핑몰 - 회원 리스트</a></li>
	</c:if>
	<c:if test="${sessionScope.loginDTO.id == null}">
		<li>&nbsp;&nbsp;&nbsp;&nbsp;<a href="adminLogin.do"><i class="fas fa-user">&nbsp;</i>관리자 로그인</a></li>
	</c:if>
   </ul>
</div>
<jsp:include page="ad_footer.jsp"/>