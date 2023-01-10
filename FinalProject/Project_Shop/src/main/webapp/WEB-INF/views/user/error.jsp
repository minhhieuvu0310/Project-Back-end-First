<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Lỗi</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap"
	rel="stylesheet">
<link
	href="<c:url value=" resources/user/fonts/fontawesome-free-6.1.1/css/all.min.css" />"
	rel="stylesheet">
<link href="<c:url value=" resources/user/css/grid.css" />" rel="stylesheet">
<link href="<c:url value=" resources/user/css/base.css" />" rel="stylesheet">
<!-- Header -->
<link href="<c:url value=" resources/user/css/Header/Header.css" />"
	rel="stylesheet">
<link href="<c:url value=" resources/user/css/Error/Error.css" />"
	rel="stylesheet">
<!-- Footer -->
<link href="<c:url value=" resources/user/css/Footer/footer.css" />"
	rel="stylesheet">
</head>

<body>
	<div class="app" style="overflow: hidden;">
		<!-- *Begin Header -->
		<jsp:include page="header.jsp"></jsp:include>
		<!-- *End Header -->

		<!-- *Begin Cart -->
		<div class="error-container">
            <div class="grid wide">
                <div class="row sm-gutter row-bg">
                    <div class="col l-12 m-12 c-12">
                        <div class="error">
                            <div class="error__title">
                                <div class="error__icon">
                                    <i class="fa-sharp fa-solid fa-bug"></i>
                                </div>
                                <p class="error__text">Error</p>
                            </div>
                            <p class="error__content">${error }</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
		<!-- *End Cart -->

		<!-- *Begin Footer -->
		<jsp:include page="footer.jsp"></jsp:include>
		<!-- *End Footer -->
	</div>
	<script src="<c:url value=" resources/user/js/eventcart.js" />"></script>
</body>

</html>