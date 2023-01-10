<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Sửa Thông Tin Cá Nhân</title>
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
<link href="<c:url value=" resources/user/css/MyAccount/MyAccount.css" />"
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
		<div class="cart-container ">
			<div class="grid wide">
				<div class="row  sm-gutter myAccount">
					<div class="col l-2 m-12 c-12">
						<div class="myAccount-info">
							<img
								src="<c:url value="resources"/>/user/image/User/${users.getUserImage()}"
								alt="NoImage" class="myAccount-info__avatar" />
							<div class="myAccount-info__name">
								<p>minhhieu0310</p>
								<a href="#" class="editprofile"> <i
									class="fa-solid fa-pen editprofile__icon"></i> <span
									class="editprofile__title">Sửa Hồ Sơ</span>
								</a>
							</div>
						</div>
					</div>
					<div class="col l-10 m-12 c-12">
						<div class="myAccount-info-detail">
							<div class="myAccount__title">
								<h3>Hồ Sơ Của Tôi</h3>
								<p>Quản lý thông tin hồ sơ để bảo mật tài khoản</p>
							</div>

							<form:form action="UpdateMyAccount" modelAttribute="users"
								method="post" enctype="multipart/form-data">
								<div class="myAccount__form">
									<div class="myAccount__editInfo">
										<div class="myAccount__form-input">
											<div class="myAccount__group">
												<label for="" class="myAccount__label">Tên đăng nhập</label>
												<input class="myAccount__input" readonly="readonly"
													value="${users.getUserName() }"></input>
											</div>
											<div class="myAccount__group">
												<label for="" class="myAccount__label">Tên</label>
												<form:input path="fullName" class="myAccount__input"
													value="${users.getFullName() }"></form:input>
											</div>
											<div class="myAccount__group">
												<label for="" class="myAccount__label">Email</label>
												<form:input path="email" class="myAccount__input"
													value="${users.getEmail() }"></form:input>
											</div>
											<div class="myAccount__group">
												<label for="" class="myAccount__label">Phone</label>
												<form:input path="phone" class="myAccount__input"
													value="${users.getPhone() }"></form:input>
											</div>
											<div class="myAccount__group">
												<label for="" class="myAccount__label">Address</label>
												<form:input path="address" class="myAccount__input"
													value="${users.getAddress() }"></form:input>
											</div>
										</div>
										<div class="myAccount__form-control">
											<button class="btn btn--primary btn--saveUsers">Lưu</button>
										</div>
									</div>
									<div class="myAccount__editAvatar">
										<img
											src="<c:url value="resources"/>/user/image/User/${users.getUserImage()}"
											alt="NoImage" class="myAccount__avatar" id="coverPreview">
										<input type="file" accept=".jpg,.jpeg,.png"
											style="display: none;" id="userImg" name="imageUser">
										<label class="btn btn--choose-photo" id="btn--choose-photo"
											for="userImg">Chọn Ảnh</label>
										<div class="myAccount__avatar-descirption">
											<p class="myAccount__avatar-maxData">Dụng lượng file tối
												đa 1 MB</p>
											<p class="myAccount__avatar-typeFile">Định dạng:.JPEG,
												.PNG</p>
										</div>
									</div>
								</div>
							</form:form>
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
	<script>
	let coverPreview = document.getElementById('coverPreview');
    let cover = document.getElementById('userImg');

    coverPreview.addEventListener('click',_=>cover.click());

    cover.addEventListener("change",_=>{
        let file = cover.files[0];
        let reader = new FileReader();
        reader.onload = function (){
            coverPreview.src = reader.result;
        }
        reader.readAsDataURL(file);
    });
	</script>
</body>

</html>