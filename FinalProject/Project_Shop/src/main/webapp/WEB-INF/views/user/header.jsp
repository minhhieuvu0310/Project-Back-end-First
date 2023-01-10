<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style>
.icon-shopee-logo--official-shop {
	background-position: 50%;
	background-size: cover;
	background-repeat: no-repeat;
	width: 8rem;
	height: 5rem;
	cursor: pointer;
	fill: #fff;
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
	color: #fff;
	margin-right: 43px;
}
</style>
<header class="header">
	<div class="grid wide">
		<!-- Begin Nav  -->
		<nav class="header__navbar hiden-on-mobile-tablet">
			<ul class="header__navbar-list">
				<li class="header__navbar-item header__navbar-item--separate">Kênh
					Người Bán</li>
				<li
					class="header__navbar-item header__navbar-item--has-qr header__navbar-item--separate">
					Tải ứng dụng
					<div class="header__qr">
						<img src="<c:url value="resources"/>/user/image/qr/qr-code.png" alt="QR Code"
							class="header__qr-img">
						<div class="header__qr-apps">
							<a href="" class="header_qr-link"> <img
								src="<c:url value="resources"/>/user/image/qr/app-store.png" alt="App Store"
								class="header_qr-dowload-img">
							</a> <a href="" class="header_qr-link"> <img
								src="<c:url value="resources"/>/user/image/qr/gg-play.png" alt="Google Play"
								class="header_qr-dowload-img">
							</a>
						</div>
					</div>
				</li>
				<li class="header__navbar-item"><span
					class="header__navbar-title--no-pointer">Kết nối</span> <a href=""
					class="header__navbar-icon-link"><i
						class="header__navbar-icon fa-brands fa-facebook"></i></a> <a href=""
					class="header__navbar-icon-link"><i
						class="header__navbar-icon fa-brands fa-instagram"></i></a></li>
			</ul>
			<ul class="header__navbar-list">
				<!-- Notify -->
				<li class="header__navbar-item header__navbar-item--has-notify">
					<a href="" class="header__navbar-item-link"> <i
						class="header__navbar-icon far fa-bell"></i> Thông Báo
				</a>
					<div class="header__notify">
						<header class="header__notify-header">
							<h3>Thông báo mới nhận</h3>
						</header>
						<ul class="header__notify-list">
							<li class="header__notify-item header__notify-item--viewed">
								<a href="" class="header__notify-link"> <img
									src="https://laptop88.vn/media/news/3012_laptop-gaming-2022.jpg"
									alt="" class="header__notify-img">
									<div class="header__notify-info">
										<span class="header__notify-name">Tặng minh hiếu bộ đôi
											mã giảm giá tới 70k!!</span> <span
											class="header__notify-descriotion">Mô tả của mã giảm
											giá</span>
									</div>
							</a>
							</li>
							<li class="header__notify-item"><a href=""
								class="header__notify-link"> <img
									src="https://laptop88.vn/media/news/3012_laptop-gaming-2022.jpg"
									alt="" class="header__notify-img">
									<div class="header__notify-info">
										<span class="header__notify-name">Tặng minh hiếu bộ đôi
											mã giảm giá tới 70k!!</span> <span
											class="header__notify-descriotion">Mô tả của mã giảm
											giá</span>
									</div>
							</a></li>
							<li class="header__notify-item header__notify-item--viewed">
								<a href="" class="header__notify-link"> <img
									src="https://laptop88.vn/media/news/3012_laptop-gaming-2022.jpg"
									alt="" class="header__notify-img">
									<div class="header__notify-info">
										<span class="header__notify-name">Tặng minh hiếu bộ đôi
											mã giảm giá tới 70k!!</span> <span
											class="header__notify-descriotion">Mô tả của mã giảm
											giá</span>
									</div>
							</a>
							</li>
							<li class="header__notify-item header__notify-item--viewed">
								<a href="" class="header__notify-link"> <img
									src="https://laptop88.vn/media/news/3012_laptop-gaming-2022.jpg"
									alt="" class="header__notify-img">
									<div class="header__notify-info">
										<span class="header__notify-name">Tặng minh hiếu bộ đôi
											mã giảm giá tới 70k!!</span> <span
											class="header__notify-descriotion">Mô tả của mã giảm
											giá</span>
									</div>
							</a>
							</li>
							<li class="header__notify-item header__notify-item--viewed">
								<a href="" class="header__notify-link"> <img
									src="https://laptop88.vn/media/news/3012_laptop-gaming-2022.jpg"
									alt="" class="header__notify-img">
									<div class="header__notify-info">
										<span class="header__notify-name">Tặng minh hiếu bộ đôi
											mã giảm giá tới 70k!!</span> <span
											class="header__notify-descriotion">Mô tả của mã giảm
											giá</span>
									</div>
							</a>
							</li>
						</ul>
						<footer class="header__notify-footer">
							<a href="" class="header__notify-footer-btn">Xem Tất Cả</a>
						</footer>
					</div>
				</li>
				<!-- Help -->
				<li class="header__navbar-item"><a href=""
					class="header__navbar-item-link"> <i
						class="header__navbar-icon far fa-question-circle"></i> Hỗ Trợ
				</a></li>
				<c:if test="${sessionScope.users == null }">
					<li
						class="header__navbar-item header__navbar-item--strong header__navbar-item--separate">
						<a href="initRegister" style="text-decoration: none; color: #fff;">
							Đăng kí </a>
					</li>
					<li class="header__navbar-item header__navbar-item--strong"><a
						href="initLoginFontend"
						style="text-decoration: none; color: #fff;"> Đăng Nhập </a></li>
				</c:if>

				<c:if test="${sessionScope.users != null }">
					<li class="header__navbar-item header__navbar-user"><img
						src="<c:url value="resources"/>/user/image/User/${sessionScope.users.userImage == null ? 'default.png' : sessionScope.users.userImage}"
						alt="" class="header__navbar-user-img"> <span
						class="header__navbar-user-name">${sessionScope.users.fullName}</span>
						<ul class="header__navbar-user-menu">
							<li class="header__navbar-user-menu-item"><a href="initUpdateMyAccount">Tài
									Khoản Của Tôi</a></li>
							<li class="header__navbar-user-menu-item"><a href="history">Đơn
									Mua</a></li>
							<li
								class="header__navbar-user-menu-item header__navbar-user-menu-item-separate "><a
								href="LogoutFontend">Đăng Xuất</a></li>
						</ul></li>
				</c:if>

			</ul>
		</nav>
		<!-- End Nav  -->
		<div class="header-with-search">
			<label for="mobile-search" class="header__mobile-search"> <i
				class="header__mobile-search-icon fas fa-search"></i>
			</label>
			<div class="header__logo header__logo2">
				<a href="home" class="header__logo-link"> <svg
						enable-background="new 0 0 48 48" viewBox="0 0 48 48" x="0" y="0"
						class="shopee-svg-icon icon-shopee-logo--official-shop header__logo-img">
						<path
							d="M44.4,11.5C44.4,11.5,44.4,11.5,44.4,11.5l-9.9,0C34.3,5.1,29.7,0,24,0S13.7,5.1,13.5,11.5H3.6v0c-0.5,0-0.9,0.4-0.9,0.9c0,0,0,0,0,0.1h0l1.4,30.9c0,0.1,0,0.2,0,0.3c0,0,0,0,0,0.1l0,0.1l0,0c0.2,2.2,1.8,3.9,3.9,4l0,0h31.4c0,0,0,0,0,0c0,0,0,0,0,0h0.1l0,0c2.2-0.1,3.9-1.8,4.1-4l0,0l0,0c0,0,0,0,0-0.1c0-0.1,0-0.1,0-0.2l1.5-31h0c0,0,0,0,0,0C45.3,11.9,44.9,11.5,44.4,11.5z M24,2.8c4.1,0,7.5,3.9,7.6,8.7H16.4C16.5,6.7,19.9,2.8,24,2.8z M31.9,35.8c-0.3,2.3-1.7,4.2-3.9,5.1c-1.2,0.5-2.8,0.8-4.1,0.7c-2-0.1-3.9-0.6-5.6-1.4c-0.6-0.3-1.6-1-2.3-1.5c-0.2-0.1-0.2-0.3-0.1-0.5c0.2-0.2,0.8-1.2,0.9-1.3c0.1-0.2,0.4-0.2,0.6-0.1c0,0,0.2,0.2,0.3,0.2c1.7,1.3,3.8,2.3,6.2,2.4c3,0,5.2-1.4,5.6-3.5c0.4-2.3-1.4-4.3-4.9-5.4c-1.1-0.3-3.9-1.5-4.5-1.8c-2.5-1.4-3.6-3.3-3.4-5.6c0.2-3.2,3.2-5.6,7-5.6c1.8,0,3.5,0.4,5,1c0.6,0.2,1.6,0.8,2,1.1c0.3,0.2,0.2,0.4,0.1,0.6c-0.2,0.2-0.6,0.9-0.8,1.2c-0.1,0.2-0.3,0.2-0.5,0.1c-1.9-1.3-3.9-1.7-5.7-1.8c-2.6,0.1-4.6,1.6-4.7,3.7c0,1.9,1.4,3.3,4.5,4.3C29.9,29.6,32.3,32,31.9,35.8z"></path>
					</svg>
				</a> <span>NewShop</span>
			</div>
			<input type="checkbox" hidden id="mobile-search"
				class="mobile-search-checkbox">
			<div class="header__search">
				<div class="header__search-input-wrap">
					<input type="text" class="header__search-input"
						placeholder="Tìm Kiếm Sản Phẩm" id="KeySearch"
						value="${KeySearch }">
				</div>
				<button class="header__search-btn" id="btn--searchKey">
					<i class="header__search-btn-icon fa-solid fa-magnifying-glass"></i>
				</button>
			</div>
			<!-- Begin cart -->
			<div class="header__cart">
				<div class="header__cart-wrap">
					<i class="header__cart-icon fa-solid fa-cart-shopping"></i>
					<c:if test="${sessionScope.listCart == null }">
						<div class="header__cart-list header__cart-list--no-cart">
							<!-- No-Cart : header__cart-list--no-cart -->
							<!-- Has-Cart : header__cart-list--has-cart -->
							<img src="<c:url value="resources"/>/user/image/Nocart/no_cart.png"
								alt="" class="header__cart-no-cart-img"> <span
								class="header__cart-list-no-cart-msg">Chưa có sản phẩm</span>
						</div>
					</c:if>
					<c:if test="${sessionScope.listCart != null }">
						<c:if test="${sessionScope.listCart.size() == 0 }">
							<div class="header__cart-list header__cart-list--no-cart">
								<!-- No-Cart : header__cart-list--no-cart -->
								<!-- Has-Cart : header__cart-list--has-cart -->
								<img src="<c:url value="resources"/>/user/image/Nocart/no_cart.png"
									alt="" class="header__cart-no-cart-img"> <span
									class="header__cart-list-no-cart-msg">Chưa có sản phẩm</span>
							</div>
						</c:if>
						<c:if test="${sessionScope.listCart.size() != 0 }">
							<div class="header__cart-list header__cart-list--has-cart">
								<!-- No-Cart : header__cart-list--no-cart -->
								<!-- Has-Cart : header__cart-list--has-cart -->
								<!--begin cart with product -->
								<h4 class="header__cart-heading">Sản phẩm đã thêm</h4>
								<ul class="header__cart-list-product">
									<c:forEach items="${sessionScope.listCart}" var="cart">
										<li class="header__cart-item"><img
										src="<c:url value="resources"/>/user/image/buy/${cart.getProduct().getImages() }" alt="NoImage"
										class="header__cart-item-img">
										<div class="header__cart-item-info">
											<div class="header__cart-item-head">
												<h5 class="header__cart-item-name">
													${cart.getProduct().getProductContent() }
												</h5>
												<span class="header__cart-item-price">
													<fmt:formatNumber type="currency" currencySymbol="" value="${cart.getProduct().getProductHasDiscount() }">
													</fmt:formatNumber>đ
												</span>
											</div>
											<div class="header__cart-item-body">
												<span class="header__cart-item-number">x ${cart.getQuantity() }</span>
											</div>
										</div></li>
									</c:forEach>
									
								</ul>
								<!--end cart with product -->
								<div class="header__cart-footer">
									<a href="cart" class="btn btn--primary header__cart-see-cart"
										id="header__cart-see-cart">Xem giỏ hàng</a>
								</div>
							</div>
							<span class="header__cart-count">${sessionScope.listCart.size() }</span>
						</c:if>
					</c:if>

				</div>
			</div>
			<ul class="header-sort-bar">
				<li class="header-sort-item"><a href=""
					class="header-sort-link">Liên Quan</a></li>
				<li class="header-sort-item header-sort-item--active"><a
					href="" class="header-sort-link ">Mới Nhất</a></li>
				<li class="header-sort-item"><a href=""
					class="header-sort-link ">Bán Chạy</a></li>
				<li class="header-sort-item "><a href=""
					class="header-sort-link">Giá</a></li>
			</ul>
</header>