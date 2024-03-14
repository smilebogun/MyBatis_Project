<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<jsp:include page="ad_header.jsp"/>

<!-- msg 띄우는 것 -->
<c:if test="${requestScope.msg != null}">
	<script>
		alert("${requestScope.msg}");
	</script>
</c:if>

<!-- 컨텐츠 -->
<div class="container mt-5 border shadow p-5">   
   <h2>회원 리스트</h2><br>
   <table class="table">
   		<thead class='table-dark'>
   			<tr>
   				<th>번호</th>
   				<th>아이디</th>
   				<th>비밀번호</th>
   				<th>이름</th>
   				<th>나이</th>
   				<th>전화번호</th>
   				<th>이메일</th>
   				<th>우편번호</th>
   				<th>주소</th>
   				<th>상세주소</th>
   				<!-- <th>참고항목</th> -->
   				<th>수정</th>
   				<th>삭제</th>
   			</tr>
   		</thead>
   		<tbody>
   			<c:forEach var="dto" items="${requestScope.list}">
      		<tr>
      			<th>${dto.no}</th>
   				<th>${dto.id}</th>
   				<th>${dto.pw.substring(0,4)}</th>
   				<th>${dto.name}</th>
   				<th>${dto.age}</th>
   				<th>${dto.tel}</th>
   				<th>${dto.email}</th>
   				<th>${dto.zipcode}</th>
   				<th>${dto.road_addr}</th>
   				<th>${dto.detail_addr}</th>
   				<%-- <th>${dto.plus_addr}</th> --%>
   				<th><a href="adminMemberUpdate.do?no=${dto.no}" class="btn btn-sm btn-info">수정</a></th>
   				<th><a href="adminMemberDelete.do?no=${dto.no}" class="btn btn-sm btn-danger">삭제</a></th>
   			</tr>
   			</c:forEach>
   		</tbody>
   </table>
</div>

<jsp:include page="ad_footer.jsp"/>