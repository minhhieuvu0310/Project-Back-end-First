<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Web Bán Máy Tính</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap"
	rel="stylesheet">
<link
	href="<c:url value="resources/fonts/fontawesome-free-6.1.1/css/all.min.css" />"
	rel="stylesheet">

<link href="<c:url value="resources/css/grid.css" />" rel="stylesheet">
<!-- Header -->
<link href="<c:url value="resources/css/Header/Header.css" />"
	rel="stylesheet">
<!-- Container -->
<link
	href="<c:url value="resources/css/category_and_product/container.css" />"
	rel="stylesheet">
<!-- Footer -->
<link href="<c:url value="resources/css/Footer/footer.css" />"
	rel="stylesheet">
<link href="<c:url value="resources/css/base.css" />" rel="stylesheet">
<style>
.home-filter__sort-item--active .home-filter__sort-item-link {
	background-color: var(- -hover-color);
	color: var(- -primary-color);
}
</style>
</head>

<body>
	<div class="app" style="overflow: hidden;">
		<!-- *Begin Header -->
		<jsp:include page="header.jsp"></jsp:include>
		<!-- *End Header -->
		<div class="container">
			<div class="grid wide">
				<div class="row sm-gutter">
					<!-- begin category -->
					<jsp:include page="category.jsp"></jsp:include>
					<!-- End category -->
					<!-- Begin Product -->
					<div class="col l-10 m-12 c-12">
						<div id="product-container">
							<!-- home filter -->
							<jsp:include page="home-filter.jsp"></jsp:include>
							<!-- mobile-category -->
							<jsp:include page="mobile-category.jsp"></jsp:include>
							<!-- home product -->
							<jsp:include page="product.jsp"></jsp:include>
						</div>
					</div>
					<!-- End Product -->
				</div>
			</div>
		</div>
		<!-- *Begin Footer -->
		<jsp:include page="footer.jsp"></jsp:include>
		<!-- *End Footer -->
	</div>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.js"
		integrity="sha512-CX7sDOp7UTAq+i1FYIlf9Uo27x4os+kGeoT7rgwvY+4dmjqV0IuE/Bl5hVsjnQPQiTOhAX1O2r2j5bjsFBvv/A=="
		crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	<script src="<c:url value="resources/js/eventCheckbox__Click.js" />"></script>
	<script src="<c:url value="resources/js/eventBtnPrice_Click.js" />"></script>
	<script src="<c:url value="resources/js/test.js" />"></script>
	<script>
	
	</script>
</body>
</html>