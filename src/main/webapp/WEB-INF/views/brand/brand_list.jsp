<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<jsp:include page="../admin/ad_header.jsp"/>

<c:if test="${requestScope.msg != null}">
	<script>
		alert("${requestScope.msg}");
	</script>
</c:if>

<!-- 컨텐츠 -->
<div class="container mt-5 border shadow p-5">   
   <h2>브랜드 리스트</h2><br>
   <table class="table">
   		<thead>
   			<tr>
   				<th>번호</th>
   				<th>코드</th>
   				<th>브랜드명</th>
   				<th>수정</th>
   				<th>삭제</th>
   			</tr>
   		</thead>
   		<tbody>
   			<c:forEach var="dto" items="${list}">
      		<tr>
   				<td>${dto.brand_num}</td>
   				<td>${dto.brand_code}</td>
   				<td>${dto.brand_name}</td>
   				<td><a href="brandUpdate.do?brand_num=${dto.brand_num}" class="btn btn-sm btn-outline-primary">수정</a></td>
   				<td><a href="brandDelete.do?brand_num=${dto.brand_num}" class="btn btn-sm btn-outline-danger">삭제</a></td>
   			</tr>
   			</c:forEach>
   		</tbody>
   </table>
</div>
<jsp:include page="../admin/ad_footer.jsp"/>