<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     

<jsp:include page="../admin/ad_header.jsp"/>

<c:if test="${requestScope.msg != null}">
	<script>
		alert("${requestScope.msg}");
	</script>
</c:if>

<div class="container w-50 mt-5 border shadow p-5">
      <h3>카테고리 수정</h3><br>
      <form action="brandUpdateOk.do" method="post">
         <table class="table table-borderless">
            <tbody>
               <tr>
	           		<td>브랜드 번호</td>
	           		<td>
	           			<input type="text" class="form-control form-control" name="brand_num" value="${dto.brand_num}" readonly/>
	           		</td>
               </tr>
               <tr>
               		<td>브랜드 코드</td>
               		<td>
               			<input type="text" class="form-control form-control" name="brand_code" value="${dto.brand_code}"/>
               		</td>
               </tr>
               
               <tr>
                  	<td>브랜드 명</td>
					<td>
						<input type="text" class="form-control form-control" name="brand_name" value="${dto.brand_name}"/>
					</td>
               </tr>
               <tr>
                  <td colspan="2" class="text-center"><br><br>
                     <input type="submit" class="btn btn btn-primary" value="브랜드 수정"/>   
                     <input type="reset" class="btn btn btn-secondary" value="취소"/>
                     <a href="brandList.do" class="btn btn btn-outline-primary">브랜드 리스트</a>   
                  </td>
               </tr>
            </tbody>         
         </table>   
      </form>
   </div> 
<jsp:include page="../admin/ad_footer.jsp"/>

