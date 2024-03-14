<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     

<jsp:include page="../admin/ad_header.jsp"/>

<div class="container w-50 mt-5 border shadow p-5">
      <h3>상품 등록</h3><br>
      <form action="prodRegisterOk.do" method="post" enctype="multipart/form-data">
         <table class="table table-borderless">
            <tbody>
               <tr>
                  <td>카테고리</td>
                  <td>
                     <select class="form-select form-select-sm" name="pcategory_fk"> 
                     <!-- requestScope.list 생략 -->
                     	   <c:if test="${categorylist == null || categorylist.size() == 0}">                       
                           		<option value="">카테고리 없음</option>
                           </c:if>
                           <c:if test="${categorylist != null || categorylist.size() != 0}">
	                           	<c:forEach var="dto" items="${categorylist}">
	                           		<option value="${dto.code}">[${dto.code}] ${dto.cat_name}</option>
	                            </c:forEach>   
                           </c:if>
                     </select>
                  </td>
               </tr>
               <tr>
                  <td>상품명</td>
                  <td><input type="text" class="form-control form-control-sm" name="pname"/></td>
               </tr>
               <tr>
                  <td>제조회사</td>
                  <td><!-- <input type="text" class="form-control form-control-sm" name="pcompany"/> -->
                  <select class="form-select form-select-sm" name="pcompany"> 
                     <!-- requestScope.list 생략 -->
                     	   <c:if test="${brandlist == null || brandlist.size() == 0}">                       
                           		<option value="">브랜드 없음</option>
                           </c:if>
                           <c:if test="${brandlist != null || brandlist.size() != 0}">
	                           	<c:forEach var="dto" items="${brandlist}">
	                           		<option value="${dto.brand_code}">[${dto.brand_code}] ${dto.brand_name}</option>
	                            </c:forEach>   
                           </c:if>
                     </select>
                  </td>
               </tr>
               <tr>
                  <td>상품이미지</td>
                  <td><input type="file" class="form-control form-control-sm" name="pimage"/></td>
               </tr>
               <tr>
                  <td>상품수량</td>
                  <td><input type="text" class="form-control form-control-sm" name="pqty"/></td>
               </tr>
               <tr>
                  <td>상품가격</td>
                  <td><input type="text" class="form-control form-control-sm" name="price"/></td>
               </tr>
               <tr>
                  <td>상품사양</td>
                  <td>
                     <select class="form-select form-select-sm" name="pspec">
<%--					<option value="none" selected>일반</option>
 						<option value="${dto.code}>추천</option>
						<option value="NEW">추천</option>
						<option value="HIT">추천</option> --%>
                        <c:forEach var="spec" items="${requestScope.pdSpecs}">
	                           		<option value="${spec.name()}">[${spec.name()}] ${spec.getValue()}</option>
	                           									<!-- ${spec.value} 위하고 같음 --> 
	                    </c:forEach>                    
                     </select>
                  </td>
               </tr>
               <tr>
                  <td>상품소개</td>
                  <td>
                     <textarea class="form-control" name="pcontent" rows="3"></textarea>
                  </td>
               </tr>
               <tr>
                  <td>상품포인트</td>
                  <td><input type="text" class="form-control form-control-sm" name="point"/></td>
               </tr>
               <tr>
                  <td colspan="2" class="text-center"><br>
                     <input type="submit" class="btn btn-sm btn-primary" value="상품등록"/>   
                     <input type="reset" class="btn btn-sm btn-secondary" value="취소"/>
                     <a href="prodList.do" class="btn btn-sm btn-outline-primary">상품 리스트</a>   
                  </td>
               </tr>
            </tbody>         
         </table>   
      </form>
   </div> 
<jsp:include page="../admin/ad_footer.jsp"/>

