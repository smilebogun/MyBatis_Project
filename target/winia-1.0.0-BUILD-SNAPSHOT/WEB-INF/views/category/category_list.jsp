<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<jsp:include page="../admin/ad_header.jsp"/>

<!-- requestScope.msg 대신 msg만 적어줘도 됌 redirectAttributes.addFlashAttribute 는 둘다 가능 -->
<c:if test="${requestScope.msg != null}">		
	<script>
		alert("${requestScope.msg}");
	</script>
</c:if>

<%-- <c:if test="${msg != null}">		
	<script>
		alert("${msg}");
	</script>
</c:if> --%>

<%-- <c:if test="${param.msg != null}">		
	<script>
		alert("${param.msg}");
	</script>
</c:if> --%>

<!-- 컨텐츠 -->
<div class="container mt-5 border shadow p-5">   
   <h2>카테고리 리스트</h2><br>
   <table class="table">
   		<thead>
   			<tr>
   				<th>번호</th>
   				<th>코드</th>
   				<th>카테고리명</th>
   				<th>수정</th>
   				<th>삭제</th>
   			</tr>
   		</thead>
   		<tbody>
   			<c:forEach var="dto" items="${list}">
      		<tr>
   				<td>${dto.cat_num}</td>
   				<td>${dto.code}</td>
   				<td>${dto.cat_name}</td>
   				<td><a href="catUpdate.do?cat_num=${dto.cat_num}" class="btn btn-sm btn-outline-primary">수정</a></td>
   				<td><a href="catDelete.do?cat_num=${dto.cat_num}" class="btn btn-sm btn-outline-danger">삭제</a></td>
   			</tr>
   			</c:forEach>
   		</tbody>
   </table>
</div>

<jsp:include page="../admin/ad_footer.jsp"/>