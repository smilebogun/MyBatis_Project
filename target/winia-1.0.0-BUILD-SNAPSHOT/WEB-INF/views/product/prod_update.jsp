<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     

<jsp:include page="../admin/ad_header.jsp"/>

<div class="container w-50 mt-5">
      <h3>상품 수정</h3><br>
      <form action="prodUpdateOk.do" method="post" enctype="multipart/form-data">
         <table class="table table-borderless">
            <tbody>
               <tr>
	           		<td>상품번호</td>
	           		<td><input type="text" class="form-control form-control-sm" name="pnum" value="${pDto.pnum}" readonly/></td>
               </tr>
               <tr>
                  <td>카테고리</td>
                  <td>
                     <select class="form-select form-select-sm" name="pcategory_fk"> 
                     <!-- requestScope.list 생략 -->
                           <c:if test="${categorylist != null || categorylist.size() != 0}">
	                           	<c:forEach var="cDto" items="${categorylist}">
	                           		<%-- <option value="${cDto.code}">${cDto.cat_name}[${cDto.code}]</option> 
	                           		드롭다운메뉴 일치하게 --%>
	                           		<option value="${cDto.code}"
	                           		${(cDto.code == pDto.pcategory_fk) ? 'selected' : ''}>
	                           		[${cDto.code}] ${cDto.cat_name}</option>
	                            </c:forEach>   
                           </c:if>
                     </select>
                  </td>
               </tr>
               <tr>
	                <td>상품명</td>
	                <td><input type="text" class="form-control form-control-sm" name="pname"value="${pDto.pname}"/></td>
               </tr>
               <tr>
                  <td>제조회사</td>
                  <td>
                     <select class="form-select form-select-sm" name="pcompany"> 
                     <!-- requestScope.list 생략 -->
                           <c:if test="${brandlist != null || brandlist.size() != 0}">
	                           	<c:forEach var="bDto" items="${brandlist}">
	                           		<%-- <option value="${cDto.code}">${cDto.catName}[${cDto.code}]</option> 
	                           		드롭다운메뉴 일치하게 --%>
	                           		<option value="${bDto.brand_code}"
	                           		${(bDto.brand_code == pDto.pcompany) ? 'selected' : ''}>
	                           		[${bDto.brand_code}] ${bDto.brand_name}</option>
	                            </c:forEach>   
                           </c:if>
                     </select>
                  <%-- <input type="text" class="form-control form-control-sm" name="pcompany" value="${pDto.pcompany}"/> --%>
                  </td>
               </tr>
               <tr>
                  <td>상품이미지</td>
                  <td>
                  		<img src="<c:url value="/resources/fileRepository/${pDto.pimage}"/>" width="100px"/>
                  		<br><br>
                  		
                  		<!-- 수정할 이미지를 선택시에 pimage로 넘어감 -->
                  		<input type="file" class="form-control form-control-sm" name="pimage"/>
                  		
                  		<!-- 이미지를 수정하지 않을 경우에는 즉, pimage가 null값이면 pimageOld 지금 현재 이미지를 보내줘야 함 -->
                  		<input type="hidden" class="form-control form-control-sm" 
                  				name="pimageOld" value="${pDto.pimage}"/>${pDto.pimage}
                  </td>
               </tr>
               <tr>
                  <td>상품수량</td>
                  <td><input type="text" class="form-control form-control-sm" name="pqty"
                  		value="${pDto.pqty}"/></td>
               </tr>
               <tr>
                  <td>상품가격</td>
                  <td><input type="text" class="form-control form-control-sm" name="price"
                  		value="${pDto.price}"/></td>
               </tr>
               <tr>
                  <td>상품사양</td>
                  <td>
                 	<select class="form-select form-select-sm" name="pspec">
                 	   	 	<c:forEach var="spec" items="${requestScope.pdSpecs}">
	                           		<option value="${spec.name()}"
	                           		${spec.name() == pDto.pspec ? 'selected' : ''}>
	                           		[${spec.name()}] ${spec.getValue()}</option>
	                           									<!-- ${spec.value} 위하고 같음 --> 
	                    	</c:forEach>
	                </select>
                	
            <%-- <input type="text" class="form-control form-control-sm" name="pspec" value="${pDto.pspec}"/> --%>
                 	
                  </td>
               </tr>
               <tr>
                  <td>상품소개</td>
                  <td>
                     <textarea class="form-control" name="pcontent" rows="3">${pDto.pcontent}</textarea>
                  </td>
               </tr>
               <tr>
                  <td>상품포인트</td>
                  <td><input type="text" class="form-control form-control-sm" name="point" 
                  		value="${pDto.point}"/></td>
               </tr>
               <tr>
                  <td colspan="2" class="text-center"><br>
                     <input type="submit" class="btn btn-sm btn-primary" value="상품수정"/>   
                     <input type="reset" class="btn btn-sm btn-secondary" value="취소"/>
                     <a href="prodList.do" class="btn btn-sm btn-outline-primary">상품 리스트</a>   
                  </td>
               </tr>
            </tbody>         
         </table>   
      </form>
   </div> 
<jsp:include page="../admin/ad_footer.jsp"/>

