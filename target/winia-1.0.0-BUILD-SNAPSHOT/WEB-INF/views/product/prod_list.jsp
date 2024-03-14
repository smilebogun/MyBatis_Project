<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  
<jsp:include page="../admin/ad_header.jsp"/>

<c:if test="${requestScope.msg != null}">
	<script>
		alert("${requestScope.msg}")
	</script>
</c:if>

<style>
	td{vertical-align:middle}
</style>

<div class="container w-75 mt-5 border shadow p-5">
	<h3>상품 리스트</h3><br>
	<table class="table">
		<thead>
			<tr>
				<th>번호</th>					
				<th>카테고리 코드</th>
				<th>이미지</th>
				<th>상품명</th>
				<th>제조사</th>
				<th>사양</th>
				<th>가격</th>
				<th>수량</th>
				<th>&nbsp;수정</th>
				<th>삭제</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${list == null || list.size() == 0 }">
				<tr>
					<td>상품이 존재하지 않습니다!!</td>
				</tr>
			</c:if>
			<c:if test="${list != null && list.size() != 0 }">
				<c:forEach var="dto" items="${list}">
				<tr>
					<td>${dto.pnum}</td>
					<td>${dto.pcategory_fk}</td>
					<td>
						<%-- <img src="<%= request.getContextPath()%>/uploadedFile/${dto.pimage}" width="60px"/> --%>
						<img src="<c:url value="resources/fileRepository/${dto.pimage}"/>" width="60px"/>
						<!-- 위와 동일한 내용 c:url은 Context 경로를 자동으로 붙여줌 -->
					</td>
					<td>${dto.pname}</td>
					<td>${dto.pcompany}</td>
					<td>${dto.pspec}</td>
					<td>${dto.price}</td>
					<td>${dto.pqty}</td>
					<td>
						<a href="prodUpdate.do?pnum=${dto.pnum}" class="btn btn-sm btn-info">수정</a>
					</td>
					<td>
						<a href="javascript:prodDel('${dto.pnum}', '${dto.pimage}')" class="btn btn-sm btn-danger">삭제</a>
					</td>
				</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
</div>
<script>
	function prodDel(pnum, pimage){
		let isDel = confirm("삭제 하시겠습니까?");  
		// 예,아니오 중에 예를 누르면 True로 리턴됌
		if(isDel){
			location.href="./deleteProduct.do?pnum="+pnum+"&pimage="+pimage;
		}
	}

</script>
<jsp:include page="../admin/ad_footer.jsp"/>

