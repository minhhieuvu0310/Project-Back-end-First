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
	href="<c:url value="resources/user/fonts/fontawesome-free-6.1.1/css/all.min.css" />"
	rel="stylesheet">

<link href="<c:url value="resources/user/css/grid.css" />" rel="stylesheet">
<!-- Header -->
<link href="<c:url value="resources/user/css/Header/Header.css" />"
	rel="stylesheet">
<!-- Container -->
<link
	href="<c:url value="resources/user/css/category_and_product/container.css" />"
	rel="stylesheet">
<!-- Footer -->
<link href="<c:url value="resources/user/css/Footer/footer.css" />"
	rel="stylesheet">
<link href="<c:url value="resources/user/css/base.css" />" rel="stylesheet">
<style>
.home-filter__sort-item.home-filter__sort-item--active .home-filter__sort-item-link{
	background-color: var(--hover-color);
	color: #EE4D2D;
}

.NoProduct {
	margin: 10rem auto;
	display: flex;
	align-items: center;
	justify-content: center;
}

.NoProduct-container {
	text-align: center;
}

.NoProduct__img {
	width: 13.4rem;
	height: 13.4rem;
	line-height: 1.68rem;
}

.NoProduct__title {
	font-size: 1.8rem;
	margin-top: 15px;
	line-height: 2.16rem;
	font-weight: 500;
}

.NoProduct__hint {
	color: rgb(0 0 0/ 0.54);
	font-size: 1.8rem;
	line-height: 2.16rem;
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
							<c:if test="${listProduct == null || listProduct.isEmpty()}">
								<jsp:include page="NoProduct.jsp"></jsp:include>
							</c:if>
							<c:if test="${listProduct != null && !listProduct.isEmpty()}">
								<!-- home product -->
								<jsp:include page="product.jsp"></jsp:include>
							</c:if>
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
	<script>
		/**
		 * Sự kiện click vào pagination
		 */
		try {
			<c:if test="${listProduct != null && !listProduct.isEmpty()}">
				document.getElementById('page-${page == null ? "1" : page}').classList
				.add('pagination-item--active');
			</c:if>
			<c:if test = "${!sortBy.contains('Price')}">
				document.getElementById('sortBy-${sortBy == null ? "relevancy" : sortBy}').classList
						.add('btn--primary');
			</c:if>
			<c:if test = "${sortBy.contains('Price') && sortByPrice != null}">
				document.querySelector("#${sortByPrice}.home-filter__sort-item").classList.add('home-filter__sort-item--active');	
				var sortByPrice_str;
				sortByPrice_str = ${sortByPrice.contains('desc')}?'Giảm dần' : 'Tăng Dần'
				var iconSortPrice_str ;
				iconSortPrice_str = ${sortByPrice.contains('desc')}?'<i class="fas fa-sort-amount-down-alt"></i>' : '<i class="fas fa-sort-amount-up-alt"></i>'
				document.querySelector('#home-filter__sortbyPrice .home-filter__sort-lable').innerHTML = sortByPrice_str;
				document.querySelector('#home-filter__sortbyPrice .home-filter__sort-icon').innerHTML  = iconSortPrice_str;
			</c:if>
		} catch (error) {
			console.log(error);
		}
	</script>
	<script src="<c:url value="resources/user/js/UpdateParam.js" />"></script>
	<script src="<c:url value="resources/user/js/eventCheckbox__Click.js" />"></script>
	<script src="<c:url value="resources/user/js/eventBtnPrice__Click.js" />"></script>
	<script src="<c:url value="resources/user/js/sortFilter.js" />"></script>
	<script src="<c:url value="resources/user/js/KeySearch.js" />"></script>
	<script>
		try {
			$(document).ready(function() {
				$('#pagination .pagination-item--number').click(function() {
					var page = this.value;
					var urlStr = document.location.search.substr(1).split('&');
					if (urlStr == '') {
						location.href = `${nameController}?page=${page}`;
					} else {
						insertParamPage('page', page);
					}
				});
			})
	
		} catch (error) {
			console.log(error);
		}
	
	
		function insertParamPage(key, value) {
			key = encodeURIComponent(key);
			value = encodeURIComponent(value);
	
			var kvp = document.location.search.substr(1).split('&');
			let i = 0;
	
			for (; i < kvp.length; i++) {
				if (kvp[i].startsWith(key + '=')) {
					let pair = kvp[i].split('=');
					pair[1] = value;
					kvp[i] = pair.join('=');
					break;
				}
			}
	
			if (i >= kvp.length) {
				kvp[kvp.length] = [key, value].join('=');
			}
	
			let params = kvp.join('&');
	
			document.location.search = params;
		}
	</script>
</body>
</html>