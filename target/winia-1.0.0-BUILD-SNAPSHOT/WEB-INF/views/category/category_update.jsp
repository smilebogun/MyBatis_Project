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
      <form action="catUpdateOk.do" method="post">
         <table class="table table-borderless">
            <tbody>
               <tr>
	           		<td>카테고리 번호</td>
	           		<td>
	           			<input type="text" class="form-control form-control" name="cat_num" value="${dto.cat_num}" readonly/>
	           		</td>
               </tr>
               <tr>
               		<td>카테고리 코드</td>
               		<td>
               			<input type="text" class="form-control form-control" name="code" value="${dto.code}"/>
               		</td>
               </tr>
               
               <tr>
                  	<td>카테고리 명</td>
					<td>
						<input type="text" class="form-control form-control" name="cat_name" value="${dto.cat_name}"/>
					</td>
               </tr>
               <tr>
                  <td colspan="2" class="text-center"><br><br>
                     <input type="submit" class="btn btn btn-primary" value="카테고리 수정"/>   
                     <input type="reset" class="btn btn btn-secondary" value="취소"/>
                     <a href="catList.do" class="btn btn btn-outline-primary">카테고리 리스트</a>   
                  </td>
               </tr>
            </tbody>         
         </table>   
      </form>
   </div> 
<jsp:include page="../admin/ad_footer.jsp"/>

