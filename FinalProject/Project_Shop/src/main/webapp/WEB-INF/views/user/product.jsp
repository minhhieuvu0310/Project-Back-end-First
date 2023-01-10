<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- list product -->
<div class="home-product" id="home-product">
	<!-- list product -->
	<div id="list-product" class="row sm-gutter">
		<c:forEach items="${listProduct }" var="product">
			<div class="col l-2-4 m-4 c-6  home-product-item">
				<a href="productDetails?productId=${product.productId }"
					class="home-product-item-link">
					<div class="home-product-item__img"
						style="background-image:url(<c:url value='resources'/>/user/image/buy/${product.images });"></div>
					<div class="home-product-item__info">
						<h4 class="home-product-item__name">${product.productContent }</h4>
						<div class="home-product-item__price">
							<c:if test="${product.discount != 0}">
								<div class="home-product-item__price-old">
									<fmt:formatNumber type="currency" currencySymbol=""
										value="${product.priceOutput}">
									</fmt:formatNumber>
								</div>
							</c:if>
							<div class="home-product-item__price-new">
								<fmt:formatNumber type="currency" currencySymbol=""
									value="${product.priceOutput - (product.priceOutput  * product.discount)}">
								</fmt:formatNumber>
							</div>
							<i class="home-product-item__ship fas fa-shipping-fast"></i>
						</div>
						<div class="home-product-item__action">
							<div
								class="home-product-item__save home-product-item__save--saved">
								<i class="home-product-item__save-icon far fa-heart"></i>
							</div>
							<div class="home-product-item__starting-start">
								<i class="star-checked far fa-star"></i> <i
									class="star-checked far fa-star"></i> <i
									class="star-checked far fa-star"></i> <i
									class="star-checked far fa-star"></i> <i
									class="star-uncheck far fa-star"></i>
							</div>
							<div class="home-product-item__saled">Đã bán
								${product.buyItem }</div>
						</div>
						<div class="home-product-item__origin"></div>
						<div class="home-product-item__favourite">
							<i class="home-product-item__favourite-icon fa fa-check"></i> Yêu
							Thích
						</div>
						<c:if test="${product.discount != 0}">
							<div class="home-product-item__sale-off">
								<div class="home-product-item__sale-off-value">
									<fmt:formatNumber type="percent" maxIntegerDigits="3"
										value="${product.discount}" />
								</div>
								<div class="home-product-item__sale-off-lable">Giảm</div>
							</div>
						</c:if>
					</div>
				</a>
			</div>
		</c:forEach>
	</div>
	<!-- pagination -->
	<ul class="pagination home-product__pagination" id="pagination">
		<li class="pagination-item"
			style="${totalPage == 0 ? 'display:none' : ''}"><a
			class="pagination-item__link pagination-item__link--disable"> <i
				class="fas fa-chevron-left pagination-item__icon"></i>
		</a></li>
		<c:forEach items="${listPage }" var="page">
			<li class="pagination-item pagination-item--number" id="page-${page}"
				value="${page }" style="cursor: pointer;">
				<p class="pagination-item__link">${page }</p>
			</li>
		</c:forEach>
		<li class="pagination-item"
			style="${totalPage == 0 ? 'display:none' : ''}"><a
			class="pagination-item__link"> <i
				class="fas fa-chevron-right pagination-item__icon"></i>
		</a></li>
	</ul>
</div>