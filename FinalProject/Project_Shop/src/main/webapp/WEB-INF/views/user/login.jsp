<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap"
	rel="stylesheet">
<link
	href="<c:url value="resources/user/fonts/fontawesome-free-6.1.1/css/all.min.css" />"
	rel="stylesheet">
<link href="<c:url value="resources/user/css/grid.css" />" rel="stylesheet">
<link href="<c:url value="resources/user/css/base.css" />" rel="stylesheet">
<link
	href="<c:url value="resources/user/css/Login_and_ Register/Login_and_Register.css" />"
	rel="stylesheet">
<link href="<c:url value="resources/user/css/Footer/footer.css" />"
	rel="stylesheet">
<link href="<c:url value="resources/user/css/Responsive/responsive.css" />"
	rel="stylesheet">
<link
	href="<c:url value="resources/user/css/Responsive/responsive__login.css" />"
	rel="stylesheet">
<style>
.icon-shopee-logo--official-shop {
	background-position: 50%;
	background-size: cover;
	background-repeat: no-repeat;
	width: 8rem;
	height: 5rem;
	cursor: pointer;
	fill: #ee4d2d;
	margin-bottom: 9px;
}

.header__logo2 {
	display: flex;
	align-items: center;
	justify-content: center;
}

.header__logo2 span {
	font-size: 24px;
	font-weight: 700;
	margin-right: 43px;
}
</style>
</head>
<body>
	<div class="app" style="overflow: hidden">
		<!--*Begin Header  -->
		<nav class="header-login">
			<div class="grid wide">
				<div class="header-login-wap">
					<div class="header__logo header__logo2">
						<a href="home" class="header__logo-link"> <svg
								enable-background="new 0 0 48 48" viewBox="0 0 48 48" x="0"
								y="0"
								class="shopee-svg-icon icon-shopee-logo--official-shop header__logo-img">
                                <path
									d="M44.4,11.5C44.4,11.5,44.4,11.5,44.4,11.5l-9.9,0C34.3,5.1,29.7,0,24,0S13.7,5.1,13.5,11.5H3.6v0c-0.5,0-0.9,0.4-0.9,0.9c0,0,0,0,0,0.1h0l1.4,30.9c0,0.1,0,0.2,0,0.3c0,0,0,0,0,0.1l0,0.1l0,0c0.2,2.2,1.8,3.9,3.9,4l0,0h31.4c0,0,0,0,0,0c0,0,0,0,0,0h0.1l0,0c2.2-0.1,3.9-1.8,4.1-4l0,0l0,0c0,0,0,0,0-0.1c0-0.1,0-0.1,0-0.2l1.5-31h0c0,0,0,0,0,0C45.3,11.9,44.9,11.5,44.4,11.5z M24,2.8c4.1,0,7.5,3.9,7.6,8.7H16.4C16.5,6.7,19.9,2.8,24,2.8z M31.9,35.8c-0.3,2.3-1.7,4.2-3.9,5.1c-1.2,0.5-2.8,0.8-4.1,0.7c-2-0.1-3.9-0.6-5.6-1.4c-0.6-0.3-1.6-1-2.3-1.5c-0.2-0.1-0.2-0.3-0.1-0.5c0.2-0.2,0.8-1.2,0.9-1.3c0.1-0.2,0.4-0.2,0.6-0.1c0,0,0.2,0.2,0.3,0.2c1.7,1.3,3.8,2.3,6.2,2.4c3,0,5.2-1.4,5.6-3.5c0.4-2.3-1.4-4.3-4.9-5.4c-1.1-0.3-3.9-1.5-4.5-1.8c-2.5-1.4-3.6-3.3-3.4-5.6c0.2-3.2,3.2-5.6,7-5.6c1.8,0,3.5,0.4,5,1c0.6,0.2,1.6,0.8,2,1.1c0.3,0.2,0.2,0.4,0.1,0.6c-0.2,0.2-0.6,0.9-0.8,1.2c-0.1,0.2-0.3,0.2-0.5,0.1c-1.9-1.3-3.9-1.7-5.7-1.8c-2.6,0.1-4.6,1.6-4.7,3.7c0,1.9,1.4,3.3,4.5,4.3C29.9,29.6,32.3,32,31.9,35.8z">
                                </path>
                            </svg>
						</a><span>Đăng Nhập</span>
					</div>
					<div class="header-login__help fs-1-4">
						<a href="" class="header-login__help-link">Bạn cần giúp đỡ?</a>
					</div>
				</div>
			</div>
		</nav>
		<!-- *End Header -->
		<!-- *Login from  -->
		<div class="container-login">
			<div class="grid wide">
				<div class="from-login">
					<div class="from-login-img hiden-on-mobile-tablet"></div>
					<div class="form-login-body" style="right: calc(50% - 25%);">
						<form:form action="LoginFontend" modelAttribute="users" method="post">
							<div class="auth-from">
								<!-- Containner From -->
								<div class="auth-from-container">
									<input type="hidden" name="action" value="${action }">
									<input type="hidden" name="productId" value="${productId }">
									<!-- Header -->
									<div class="auth-from__header">
										<h3 class="auth-from_heading">Đăng Nhập</h3>
										<a href="initRegister" class="auth-from__switch-btn" style="text-decoration: none">Đăng Ký</a>
									</div>
									<!-- Input -->

									<div class="auth-from__from">
										<c:if test="${error.isEmpty() == false}">
											<div class="auth-from__group" style="color: red; font-weight: 400; font-size: 1.2rem;">
												<p>${error }</p>
											</div>
										</c:if>
										
										<div class="auth-from__group">
											<form:input type="text" placeholder="Tên Đăng Nhập Của Bạn" id="login-name__input" class="auth-from__input" path="userName"/>
										</div>
										<div class="auth-from__group">
											<form:input type="password" class="auth-from__input" placeholder="Nhập Mật Khẩu" id="login-password__input" path="passWord"/> 
										</div>
									</div>
									<!-- Policy -->
									<div class="auth-from__aside">
										<div class="auth-from__help">
											<div class="auth-from__help-link auth-from__help-forgot">
												Quên mật khẩu</div>
											<span class="auth-from__help-separater"></span>
											<div class="auth-from__help-link">Cần trợ giúp?</div>
										</div>
									</div>
									<!-- Button -->
									<div class="auth-from__controls">
										<a href="initRegister" class="btn btn-normal auth-from__controls-back" id="btn-controls-back__register" style="color: #000">BACK</a>
										<button class="btn btn--primary" id="btn-login">ĐĂNG
											NHẬP</button>
									</div>
								</div>
								<!-- Social -->
								<div class="auth-from__social auth-from__social--login"></div>
							</div>

						</form:form>
					</div>
				</div>
			</div>
		</div>
		<!-- *Begin Footer -->
		<jsp:include page="footer.jsp"></jsp:include>
		<!-- *End Footer -->
	</div>
</body>
</html>