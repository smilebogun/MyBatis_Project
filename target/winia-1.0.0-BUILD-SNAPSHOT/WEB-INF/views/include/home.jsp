<%-- <%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
</body>
</html> --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../include/header.jsp" %>	

<div class="container ms-5">

		<!-- Carousel -->
	<div id="demo" class="carousel slide" data-bs-ride="carousel">
	
	  <!-- Indicators/dots -->
	  <div class="carousel-indicators">
	    <button type="button" data-bs-target="#demo" data-bs-slide-to="0" class="active"></button>
	    <button type="button" data-bs-target="#demo" data-bs-slide-to="1"></button>
	    <button type="button" data-bs-target="#demo" data-bs-slide-to="2"></button>
	    <button type="button" data-bs-target="#demo" data-bs-slide-to="3"></button>
	    <button type="button" data-bs-target="#demo" data-bs-slide-to="4"></button>
	    
	  </div>
	
	  <!-- The slideshow/carousel -->
	  <div class="carousel-inner">
	    <div class="carousel-item active">
	      <img src="resources/imgs/1.jpg" alt="Los Angeles" class="d-block w-100">
			  <div class="carousel-caption">
			    		<h3>Los Angeles</h3>
			    	<p>We had such a great time in LA!</p>
			  </div>
	    </div>
	    <div class="carousel-item">
	      <img src="resources/imgs/2.jpg" alt="Chicago" class="d-block w-100">
			  <div class="carousel-caption">
			    		<h3>Los Angeles</h3>
			    	<p>We had such a great time in LA!</p>
			  </div>
	    </div>
	    <div class="carousel-item">
	      <img src="resources/imgs/3.jpg" alt="New York" class="d-block w-100">
			  <div class="carousel-caption">
			    		<h3>Los Angeles</h3>
			    	<p>We had such a great time in LA!</p>
			  </div>
	    </div>
	    <div class="carousel-item">
	      <img src="resources/imgs/4.jpg" alt="Seoul" class="d-block w-100">
			  <div class="carousel-caption">
			   		 	<h3>Los Angeles</h3>
			    	<p>We had such a great time in LA!</p>
			  </div>
	    </div>
	    <div class="carousel-item">
	      <img src="resources/imgs/5.jpg" alt="Busan" class="d-block w-100">
		      <div class="carousel-caption">
		    		<h3>Los Angeles</h3>
		    	<p>We had such a great time in LA!</p>
		 	 </div>
	    </div>
	  </div>
	
	  <!-- Left and right controls/icons -->
	  <button class="carousel-control-prev" type="button" data-bs-target="#demo" data-bs-slide="prev">
	    <span class="carousel-control-prev-icon"></span>
	  </button>
	  <button class="carousel-control-next" type="button" data-bs-target="#demo" data-bs-slide="next">
	    <span class="carousel-control-next-icon"></span>
	  </button>
	</div><br>
	
			<!-- Carousel -->
		<div id="demo2" class="carousel slide" data-bs-ride="carousel">
		
		  <!-- Indicators/dots -->
		  <div class="carousel-indicators">
		    <button type="button" data-bs-target="#demo2" data-bs-slide-to="0" class="active"></button>
		    <button type="button" data-bs-target="#demo2" data-bs-slide-to="1"></button>
		    <button type="button" data-bs-target="#demo2" data-bs-slide-to="2"></button>
		    <button type="button" data-bs-target="#demo2" data-bs-slide-to="3"></button>
		    <button type="button" data-bs-target="#demo2" data-bs-slide-to="4"></button>
		  </div>
		
		  <!-- The slideshow/carousel -->
		  <div class="carousel-inner alt=Cinque Terre">
		    <div class="carousel-item active">
<!-- 	홈페이지에서 불러올때	<img src="https://www.winia-eshop.com/download/prod.do?image=/topBanner/51/listImg.jpg" 
		      				alt="닥터마사지 출시!!" class="d-block w-100"> -->
		      <a href="ucategoryList.do?code=a003&categoryName=건강헬스가전">
		      	<img src="<c:url value="resources/imgs/prodmain1.jpg"/>" alt="닥터마사지 출시!!" class="d-block w-100">
		      </a>
				<div class="carousel-caption">
				    <h3>닥터마사지 출시!!</h3>
				    <p>We had such a great time in Winia!</p>
				</div>
		    </div>
		    <div class="carousel-item">
		      <a href="ubrandList.do?brandCode=b002&brandName=KLASSE">
		      	<img src="<c:url value="resources/imgs/prodmain2.jpg"/>" alt="Klasse 신제품 출시!!" class="d-block w-100">
		      </a>
		      		<div class="carousel-caption">
					    <h3>Klasse 신제품 출시!!</h3>
					    <p>We had such a great time in Winia!</p>
					</div>
		    </div>
 		    <div class="carousel-item">
		      <a href="prodView.do?pnum=65&pSpec=RECOMMEND">
		      	<img src="<c:url value="resources/imgs/prodmain3.jpg"/>" alt="정수기 초특가전" class="d-block w-100">
		      </a>
		      		<div class="carousel-caption">
					    <h3>정수기 초특가전</h3>
					    <p>We had such a great time in Winia!</p>
					</div>
		    </div>
		    <div class="carousel-item">
		      <a href="ucategoryList.do?code=a010&categoryName=제휴렌탈">
		      	<img src="<c:url value="resources/imgs/prodmain4.jpg"/>" alt="제휴렌탈 프로모션" class="d-block w-100">
		      </a>
		      		<div class="carousel-caption">
					    <h3>제휴렌탈 프로모션</h3>
					    <p>We had such a great time in Winia!</p>
					</div>
		    </div>
		    <div class="carousel-item">
		      <a href="ubrandList.do?brandCode=b003&brandName=딤채">
		      	<img src="<c:url value="resources/imgs/prodmain5.jpg"/>" alt="딤채 특가행사" class="d-block w-100">
		      </a>
		      		<div class="carousel-caption">
					    <h3>딤채 특가행사</h3>
					    <p>We had such a great time in Winia!</p>
					</div>
		    </div>
		  </div>
		
		  <!-- Left and right controls/icons -->
		  <button class="carousel-control-prev" type="button" data-bs-target="#demo2" data-bs-slide="prev">
		    <span class="carousel-control-prev-icon"></span>
		  </button>
		  <button class="carousel-control-next" type="button" data-bs-target="#demo2" data-bs-slide="next">
		    <span class="carousel-control-next-icon"></span>
		  </button>
		</div><br>

		<!-- 유효성검사 메인에서 뜨는 상품 인기,추천,신규상품이 없다면, 있다면 -->
	<c:forEach var="key" items="${key}">
		<c:if test="${map[key].size() != 0 }">
			<h3 class="mt-3">[${key}] 상품</h3>
			<div class="d-flex">
				<c:set var="cnt" value="0"/>	<!-- 4배수 일 경우 줄내리고 다음포문 새로시작 - 4배수 카운트 -->
				<c:forEach var="pDto" items="${map[key]}">		
					<c:set var="cnt" value="${cnt+1}"/>	<!-- 4배수 일 경우 줄내리고 다음포문 새로시작 - 4배수 카운트 +1 -->
					<!-- Card -->
						<%@include file="../product/card.jsp" %>
					<!-- Card End -->
					<c:if test="${cnt%4==0}">
						</div>
						<div class="d-flex mt-3"> <!-- div테그 종료/재시작, 4배수 일 경우 줄내리고 다음포문 새로시작 -->
					</c:if>
				</c:forEach>
			</div>
		</c:if>
		<c:if test="${map[key].size() == 0 }">
			<br>${key} 상품이 없습니다!!<br>
		</c:if>
	</c:forEach>
	
</div>
<%@ include file="../include/footer.jsp" %>
