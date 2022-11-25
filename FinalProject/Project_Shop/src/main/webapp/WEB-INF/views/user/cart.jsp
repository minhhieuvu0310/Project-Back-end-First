<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Cart</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap"
	rel="stylesheet">
<link
	href="<c:url value=" resources/fonts/fontawesome-free-6.1.1/css/all.min.css" />"
	rel="stylesheet">
<link href="<c:url value=" resources/css/grid.css" />" rel="stylesheet">
<link href="<c:url value=" resources/css/base.css" />" rel="stylesheet">
<!-- Header -->
<link href="<c:url value=" resources/css/Header/Header.css" />"
	rel="stylesheet">
<link href="<c:url value=" resources/css/cart/cart.css" />"
	rel="stylesheet">
<!-- Footer -->
<link href="<c:url value=" resources/css/Footer/footer.css" />"
	rel="stylesheet">
<style>
.cart__product-detail__details {
	border-bottom: 1px solid rgba(0, 0, 0, .09);
}

.cart__product-detail__details:last-child {
	border: none;
}

</style>
</head>

<body>
	<div class="app" style="overflow: hidden;">
		<!-- *Begin Header -->
		<jsp:include page="header.jsp"></jsp:include>
		<!-- *End Header -->

		<!-- *Begin Cart -->
		<div class="cart-container">
			<div class="grid wide">
				<c:if test="${listCart == null }">
					<jsp:include page="NoCart.jsp"></jsp:include>
				</c:if>
				<c:if test="${listCart != null }">
					<c:if test="${listCart.size() == 0}">
						<jsp:include page="NoCart.jsp"></jsp:include>
					</c:if>
					<c:if test="${listCart.size() > 0}">
						<form action="updateCart" method="post">
							<div class="row sm-gutter row-bg cart-lable">
								<div class="col l-12 m-12 c-12">
									<ul class="cart-lable-list hiden-on-mobile">
										<li class="cart-lable-item item--product">Sản phẩm</li>
										<li class="cart-lable-item item--price">Đơn giá</li>
										<li class="cart-lable-item item--quantity">Số lượng</li>
										<li class="cart-lable-item item--total-money">Số tiền</li>
										<li class="cart-lable-item item--manipulation">Thao tác</li>
									</ul>
								</div>
							</div>

							<div class="row sm-gutter row-bg cart__product-detail">
								<div class="col l-12 m-12 c-12">
									<div class="cart__shop">
										<span class="cart__shop-favourite">Yêu Thích</span>
										<div class="cart__shop-name">NewShop</div>
										<i class="cart__shop-icon-mess fa-solid fa-message"></i>
									</div>
									<c:forEach items="${listCart}" var="cart">
										<div class="cart__product-detail__details">
											<div class="cart__product-info item--product">
												<a
													href="productDetails?productId=${cart.getProduct().productId }"
													class="cart__product-link">
													<div class="cart__product-img"
														style="background-image: url(<c:url value='resources'/>/image/buy/${cart.getProduct().getImages() });">
													</div>
												</a>
												<div class="cart__product-name-classify">
													<div class="cart__product-name">
														<a href=""
															style="color: rgba(0, 0, 0, .87); text-decoration: none;">
															${cart.getProduct().getProductContent()} </a>
													</div>
													<div class="cart__product-classify">
														<div class="cart__product-classify-lable">Phân loại
															hàng:</div>
														<div class="cart__product-classify-name">${cart.color }</div>
													</div>
												</div>
											</div>
											<c:if test="${cart.getProduct().getDiscount() == 0 }">
												<div class="cart__product-price item--price">
													<div class="cart__product-price-new">
														<fmt:formatNumber type="currency" currencySymbol=""
															value="${cart.getProduct().getPriceOutput() }">
														</fmt:formatNumber>
													</div>
												</div>
											</c:if>
											<c:if test="${cart.getProduct().getDiscount() != 0 }">
												<div class="cart__product-price item--price">
													<div class="cart__product-price-old"
														style="padding-left: 16px; width: 90px; overflow: hidden; text-overflow: ellipsis;">
														<fmt:formatNumber type="currency" currencySymbol=""
															value="${cart.getProduct().getPriceOutput() }">
														</fmt:formatNumber>
													</div>
													<div class="cart__product-price-new">
														<fmt:formatNumber type="currency" currencySymbol=""
															value="${cart.getProduct().getProductHasDiscount() }">
														</fmt:formatNumber>
													</div>
												</div>
											</c:if>

											<div class="cart__product-quantity item--quantity">
												<p class="cart__product-quantity-btn btn--subQuantity"
													style="display: flex; align-items: center; justify-content: center;">
													<i class="cart__product-quantity-icon fa-solid fa-minus"></i>
												</p>
												<input type="hidden" name="maxQuantity" class="maxQuantity"
													value="300"> <input type="hidden"
													name="priceOutput" class="priceOutput"
													value="${cart.getProduct().getProductHasDiscount()}">
												<input type="text"
													class="cart__product-quantity-value-input"
													value="${cart.quantity }" name="quantity">
												<p class="cart__product-quantity-btn btn--addQuantity"
													style="display: flex; align-items: center; justify-content: center;">
													<i class="cart__product-quantity-icon fa-solid fa-plus"></i>
												</p>
											</div>
											<div
												class="cart__product-total-money item--total-money hiden-on-mobile totalmoneyProductitems">
												<fmt:formatNumber type="currency" currencySymbol=""
													value="${cart.getProduct().getProductHasDiscount() * cart.getQuantity()}">
												</fmt:formatNumber>
												đ
											</div>
											<ul
												class="cart__product-manipulation-list item--manipulation hiden-on-mobile">
												<li
													class="cart__product-manipulation-item manipulation-item--remove">
													<a
													href="removeCart?productId=${cart.getProduct().productId }"
													class="cart-link--remove"
													style="text-decoration: none; color: #000;"
													onclick="return confirm('Bạn muốn xóa?')">Xóa</a>
												</li>
											</ul>
										</div>
									</c:forEach>
								</div>
							</div>

							<div class="row sm-gutter row-bg">
								<div class="col l-12 m-12 c-12">
									<div class="PayandUpdateCart hiden-on-mobile-tablet">
										<div class="shop__voucher">
											<div class="shop__voucher-label">
												<svg fill="none" viewBox="0 -2 23 22"
													class="shop__voucher-icon">
													<g filter="url(#voucher-filter0_d)">
													<mask id="a" fill="#fff">
													<path fill-rule="evenodd" clip-rule="evenodd"
														d="M1 2h18v2.32a1.5 1.5 0 000 2.75v.65a1.5 1.5 0 000 2.75v.65a1.5 1.5 0 000 2.75V16H1v-2.12a1.5 1.5 0 000-2.75v-.65a1.5 1.5 0 000-2.75v-.65a1.5 1.5 0 000-2.75V2z"></path></mask>
													<path
														d="M19 2h1V1h-1v1zM1 2V1H0v1h1zm18 2.32l.4.92.6-.26v-.66h-1zm0 2.75h1v-.65l-.6-.26-.4.91zm0 .65l.4.92.6-.26v-.66h-1zm0 2.75h1v-.65l-.6-.26-.4.91zm0 .65l.4.92.6-.26v-.66h-1zm0 2.75h1v-.65l-.6-.26-.4.91zM19 16v1h1v-1h-1zM1 16H0v1h1v-1zm0-2.12l-.4-.92-.6.26v.66h1zm0-2.75H0v.65l.6.26.4-.91zm0-.65l-.4-.92-.6.26v.66h1zm0-2.75H0v.65l.6.26.4-.91zm0-.65l-.4-.92-.6.26v.66h1zm0-2.75H0v.65l.6.26.4-.91zM19 1H1v2h18V1zm1 3.32V2h-2v2.32h2zm-.9 1.38c0-.2.12-.38.3-.46l-.8-1.83a2.5 2.5 0 00-1.5 2.29h2zm.3.46a.5.5 0 01-.3-.46h-2c0 1.03.62 1.9 1.5 2.3l.8-1.84zm.6 1.56v-.65h-2v.65h2zm-.9 1.38c0-.2.12-.38.3-.46l-.8-1.83a2.5 2.5 0 00-1.5 2.29h2zm.3.46a.5.5 0 01-.3-.46h-2c0 1.03.62 1.9 1.5 2.3l.8-1.84zm.6 1.56v-.65h-2v.65h2zm-.9 1.38c0-.2.12-.38.3-.46l-.8-1.83a2.5 2.5 0 00-1.5 2.29h2zm.3.46a.5.5 0 01-.3-.46h-2c0 1.03.62 1.9 1.5 2.3l.8-1.84zM20 16v-2.13h-2V16h2zM1 17h18v-2H1v2zm-1-3.12V16h2v-2.12H0zm1.4.91a2.5 2.5 0 001.5-2.29h-2a.5.5 0 01-.3.46l.8 1.83zm1.5-2.29a2.5 2.5 0 00-1.5-2.3l-.8 1.84c.18.08.3.26.3.46h2zM0 10.48v.65h2v-.65H0zM.9 9.1a.5.5 0 01-.3.46l.8 1.83A2.5 2.5 0 002.9 9.1h-2zm-.3-.46c.18.08.3.26.3.46h2a2.5 2.5 0 00-1.5-2.3L.6 8.65zM0 7.08v.65h2v-.65H0zM.9 5.7a.5.5 0 01-.3.46l.8 1.83A2.5 2.5 0 002.9 5.7h-2zm-.3-.46c.18.08.3.26.3.46h2a2.5 2.5 0 00-1.5-2.3L.6 5.25zM0 2v2.33h2V2H0z"
														mask="url(#a)"></path></g>
													<path clip-rule="evenodd"
														d="M6.49 14.18h.86v-1.6h-.86v1.6zM6.49 11.18h.86v-1.6h-.86v1.6zM6.49 8.18h.86v-1.6h-.86v1.6zM6.49 5.18h.86v-1.6h-.86v1.6z"></path>
													<defs>
													<filter id="voucher-filter0_d" x="0" y="1" width="20"
														height="16" filterUnits="userSpaceOnUse"
														color-interpolation-filters="sRGB">
													<feFlood flood-opacity="0" result="BackgroundImageFix"></feFlood>
													<feColorMatrix in="SourceAlpha"
														values="0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 127 0"></feColorMatrix>
													<feOffset></feOffset>
													<feGaussianBlur stdDeviation=".5"></feGaussianBlur>
													<feColorMatrix
														values="0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0.09 0"></feColorMatrix>
													<feBlend in2="BackgroundImageFix"
														result="effect1_dropShadow"></feBlend>
													<feBlend in="SourceGraphic" in2="effect1_dropShadow"
														result="shape"></feBlend></filter></defs></svg>
												<span class="shop__voucher-text">Shop Voucher</span>
											</div>
											<a href="#" class="shop__voucher-link">Chọn Hoặc Nhập Mã</a>
										</div>
										<div class="shop__makepay">
											<div class="PayandUpdateCart__UpdateorBuy">
												<button type="submit" class="btn btn--primary btnCart--Update">Cập Nhật</button>
												<a href="home" class="btn btnCart--buy">Tiếp tục
													mua hàng</a>
											</div>
											<div class="PayandUpdateCart__btn">
												<div class="totalmoney">
													<h3 class="totalmoney__title">Tổng cộng</h3>
													<p class="total">
														Tạm tính : 
                              							<span class="money">
	                                                    	<fmt:formatNumber type="currency" currencySymbol="" value="${total}">
															</fmt:formatNumber>đ
                                                    	</span>
													</p>
												</div>
												<a href="initPayment" class="btn btn--primary btnCart--pay">Tiến Hành Thanh Toán</a>
											</div>
										</div>
									</div>
								</div>
							</div>
						</form>
					</c:if>
				</c:if>

			</div>
		</div>
		<!-- *End Cart -->

		<!-- *Begin Footer -->
		<jsp:include page="footer.jsp"></jsp:include>
		<!-- *End Footer -->
	</div>
	<script src="<c:url value=" resources/js/eventcart.js" />"></script>
</body>

</html>