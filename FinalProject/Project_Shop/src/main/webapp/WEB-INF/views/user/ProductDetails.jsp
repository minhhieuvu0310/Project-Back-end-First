<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product Details</title>
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
<!-- Header -->
<link href="<c:url value="resources/user/css/Header/Header.css" />"
	rel="stylesheet">

<link
	href="<c:url value="resources/user/css/product__details/product__detail.css" />"
	rel="stylesheet">

<!-- Footer -->
<link href="<c:url value="resources/user/css/Footer/footer.css" />"
	rel="stylesheet">
	

	
<style>
    .product-detail__attribute-btn--active {
         color: var(--primary-color);
         border: 1px solid var(--primary-color);
     }
</style>

</head>
<body>
	<div class="app" style="overflow: hidden;">
		<!-- *Begin Header -->
		<jsp:include page="header.jsp"></jsp:include>
		<!-- *End Header -->

		<div class="product-detail-container">
			<div class="grid wide">
				<div class="row sm-gutter product-detail__info">
					<div class="col l-5 m-12 c-12">
						<div class="product-detail__introduce">
							<div class="product-detail__img-top">
								<img
									src="<c:url value="resources"/>/user/image/buy/${product.images }"
									alt="No Image" class="product-detail__img-main" />
							</div>
							<div class="product-detail__img-bottom">
								<c:forEach items="${allImages }" var="image">
									<img
										src="<c:url value="resources"/>/user/image/All__ImageProduct/${image}"
										alt="NoIamge" class="product-detail__img-sub">
								</c:forEach>
							</div>
							<div class="product-detail__like-share">
								<div class="product-detail__like-lable">Chia sẻ</div>
								<div class="product-detail__sharing">
									<i class="product-detail__sharing-icon far fa-heart"></i> Đã
									thích(44)
								</div>
							</div>
						</div>
					</div>
					<div class="col l-7 m-12 c-12">
						<form action="addcart" method="post" id="frmProductdetails">
							<div class="product-detail__details">
								<h3 class="product-detail__title">
									<div class="product-detail__favourite">Yêu Thích</div>
									<span>${product.productContent }</span>
								</h3>
								<div class="product-detail__feedback">
									<div class="product-detail__rate-star">
										<span class="product-detail__number-star">5.0</span>
										<div class="product-detail__rating-star">
											<i class="star-checked far fa-star"></i> <i
												class="star-checked far fa-star"></i> <i
												class="star-checked far fa-star"></i> <i
												class="star-checked far fa-star"></i> <i
												class="star-checked far fa-star"></i>
										</div>
									</div>
									<div class="product-detail__sold">
										<span class="product-detail__sold-number">${product.buyItem}</span>
										<span class="product-detail__sold-lable">Đã Bán</span> <i
											class="product-detail__sold-icon far fa-question-circle"></i>
									</div>
								</div>
								<div class="product-detail__price">
									<div class="product-detail__price-wrap">
										<c:if test="${product.discount > 0 }">
											<div class="product-detail__price-old">
												<fmt:formatNumber type="currency" currencySymbol=""
													value="${product.priceOutput}">
												</fmt:formatNumber>
											</div>
										</c:if>										
										<div class="product-detail__price-new">
											<fmt:formatNumber type="currency" currencySymbol=""
												value="${product.priceOutput - (product.priceOutput  * product.discount)}">
											</fmt:formatNumber>
										</div>
										<c:if test="${product.discount > 0 }">
											<div class="product-detail__sale-off">
												<div class="product-detail__sale-off-value">
													<fmt:formatNumber type="percent" maxIntegerDigits="3"
														value="${product.discount}" />
												</div>											
												<div class="product-detail__sale-off-lable">Giảm</div>
											</div>
										</c:if>
									</div>
								</div>
								<div class="product-detail__Configuration">
									<div style="padding: 0 20px;">
										<div class="product-detai__insurance">
											<div class="product-detail__Configuration-name">Bảo
												hiểm</div>
											<div class="product-detail__insurance-name">
												Bảo hiểm Thiết bị điện tử
												<div class="product-detail__insurance-new">Mới</div>
											</div>
											<a href="" class="product-detail__insurance-link">Tìm
												Hiểu Thêm</a>
										</div>
										<c:if test="${not empty allcolor }">
											<div class="product-detail__attribute" id="product-detail__attribute--color">
												<div class="product-detail__Configuration-name">Color</div>
												<ul class="product-detail__attribute-list">
													<c:forEach items="${allcolor }" var="allcolor">
														<li class="product-detail__attribute-item">
															<input type="hidden" name="" value="${allcolor}">
															<p class="product-detail__attribute-btn btn">${allcolor}</p>
														</li>
													</c:forEach>
												</ul>
											</div>
											<p id="errorNoClickoptionColor" style="color:red; margin-left: 120px; font-size: 1.3rem; text-transform :capitalize;"></p>
										</c:if>										
										
										<div class="product-detail__quantity">
											<div class="product-detail__Configuration-name">Số
												Lượng</div>
											<div class="product-detail__quantity-value">
												<p class="product-detail__quantity-btn" id="btnCart--sub" style="display: flex;align-items: center;justify-content: center;">
													<i class="product-detail__quantity-icon fa-solid fa-minus"></i>
												</p>
												<input type="text" class="product-detail__quantity-value-input" name="quantity" value="1" id="quantity" onkeypress="return isNumberKey(event)">
												<p class="product-detail__quantity-btn" id="btnCart--add" style="display: flex;align-items: center;justify-content: center;">
													<i class="product-detail__quantity-icon fa-solid fa-plus"></i>
												</p>
											</div>
											<div class="product-detail-available">
												<span id="quantity--max"><fmt:formatNumber type="NUMBER" maxIntegerDigits="3" value="${product.quantity - product.buyItem }" /></span>
												sản phẩm có sẵn
											</div>
										</div>
										<p id="errorQuantityInput" style="color:red; margin-left: 120px; font-size: 1.3rem;  text-transform :capitalize;"></p>
										<div class="product-detail__btn">
											<input type="hidden" name ="productId" value="${product.productId}" >
											<div class="btn product-detail__btn-add-cart" id="btn--AddCart">
												<i class="fa-solid fa-cart-plus"></i> 
												<span>Thêm vào Giỏ hàng</span>
											</div>
											<div class="btn btn--primary product-detail__btn-buy">Mua Ngay</div>
										</div>
									</div>
								</div>
								<div
									style="border-top: 1px solid rgba(0, 0, 0, 0.05); margin-top: 30px">
									<div class="details__footer">
										<img
											src="https://deo.shopeemobile.com/shopee/shopee-pcmall-live-sg/pdp/83e10a1f95cb083579c089448ef8dd3b.png"
											alt="" class="details__footer-img"> <span
											class="details__commit-lable">Shop Đảm Bảo</span> <span
											class="details__Refund-lable">3 Ngày Trả Hàng / Hoàn
											Tiền</span>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
				<div class="row sm-gutter product-detail__desc">
					<div class="col l-12 m-12 c-12">
						<div style="padding: 10px;">
							<div style="padding: 15px 15px 0;">
								<div class="product-detail__desc-lable">CHI TIẾT SẢN PHẨM</div>
								<div class="product-detail__desc-detail">
									<p>${product.productContentDetail }</p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- *Begin Footer -->
		<jsp:include page="footer.jsp"></jsp:include>
		<!-- *End Footer -->
	</div>
	<script src="<c:url value="resources/user/js/UpdateParam.js" />"></script>
	<script src="<c:url value="resources/user/js/KeySearch.js" />"></script>
	<script src="<c:url value="resources/user/js/producDetails.js" />"></script>
</body>
</html>