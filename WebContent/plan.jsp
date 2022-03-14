<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">

<title>Plan</title>
</head>
<body class="container" style="height: 100vh;">
	<div class="h-100 row align-items-center">
		<div class="col">
			<div class="text-center fs-2 fw-bold mb-5">${userName }님의계획을
				실행하세요</div>

			<form action="PlanInsertServlet.do"
				class="row g-3 justify-content-center" method="post">
				<div class="col-auto fs-5">계획</div>
				<div class="col-auto">
					<input type="text" class="form-control" name="content"
						placeholder="계획을 입력하세요">
				</div>
				<div class="col-auto">
					<input type="hidden" name="userName" value="${userName }">
					<input type="submit" value="등록" class="btn btn-secondary mb-3">
				</div>
			</form>
			
			<form action="DeleteServlet.do" method="post">
				<c:set var="number" value="${number}"/>
				<c:forEach var="bean" items="${v }">
					<div class="text-center mb-1 ">
						<span class="mx-2"> ${number}.</span><span class="mx-2">${bean.content }</span> <span class="mx-2">${bean.date }</span>
						 <input type="hidden" name="no" value="${bean.no }"> 
						 <input type="submit" class="btn btn-outline-secondary" value="삭제">
					</div>
					<c:set var="number" value="${number=number+1 }"/>
				</c:forEach>
			</form>
			<!-- 페이지 카운터링 -->
			<div class="text-center mb-5"> 
			<c:if test="${count>0 }">
				<c:set var = "pageCount" value="${count/pageSize + (count%pageSize ==0? 0:1) }" />
				<c:set var="startPage" value="${1 }" />
				
				<c:if test="${currentPage % 10 !=0 }">
					<fmt:parseNumber var="result" value="${currentPage/10 }" integerOnly="true" />
					<c:set var="startPage" value="${result *10 +1}" />
				</c:if>
				<c:if test="${currentPage % 10 ==0 }">
					<c:set var="startPage" value="${(result-1) *10 +1}" />
				</c:if>
				<c:set var="pageBlock" value="10" />
				<c:set var="endPage" value="${startPage + pageBlock-1 }" />
				<c:if test="${endPage>pageCount}">
					<c:set var="endPage" value="${pageCount }" />
				</c:if>
	
				<c:if test="${startPage>10 }">
					<a href = "PlanListServlet.do?pageNum=${startPage-10 }">[이전]</a>
				</c:if>
				
				<c:forEach var="i" begin="${startPage }" end="${endPage }">
					<a href="PlanListServlet.do?pageNum=${i}">${i }</a>
				</c:forEach>
				<c:if test="${endPage<pageCount }">
					<a href = "PlanListServlet.do?pageNum=${startPage+10 }">[다음]</a>
				</c:if>
			</c:if>
			
			</div>
			
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous"></script>
</body>
</html>