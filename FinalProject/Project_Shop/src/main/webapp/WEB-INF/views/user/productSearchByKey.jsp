<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="col l-12 m-12 c-12">
	<div class="product-container" style="margin: 0 24px;">
		<div class="home-filter hiden-on-mobile-tablet">
			<div class="home-filter__control" id="home-filter__control">
				<span class="home-filter__title">Kết quả tìm kiếm cho từ khoá
					: ${keySearch }</span>
				<a href="home" class="home-filter__btn btn--primary btn"
					id="sortBy-relevancy" value="relevancy">Quay Lại</a>
			</div>
			<div class="home-filter__page">
				<div class="home-filter__page-number">
					<span class="home-filter__page-current">${page == null ? "1" : page }</span>
					/${totalPage }
				</div>
				<div class="home-filter__page-control">
					<a href=""
						class="home-filter__page-btn home-filter__page-btn--disabled">
						<i class="home-filter__page-icon fas fa-angle-left"></i>
					</a> <a href="" class="home-filter__page-btn"> <i
						class="home-filter__page-icon fas fa-angle-right"></i>
					</a>
				</div>
			</div>
		</div>
		<jsp:include page="mobile-category.jsp"></jsp:include>
		<!-- home product -->
		<div class="home-product" id="home-product">
			<!-- list product -->
			<div id="list-product" class="row sm-gutter">
				<c:forEach items="${list }" var="pro">
					<div class="col l-2-4 m-4 c-6  home-product-item">
						<a href="computerDetails?comId = ${pro.comId }"
							class="home-product-item-link">
							<div class="home-product-item__img"
								style="background-image:url(<c:url value='resources'/>/image/buy/${pro.primaryImage});"></div>
							<div class="home-product-item__info">
								<h4 class="home-product-item__name">${pro.comName }</h4>
								<div class="home-product-item__price">
									<div class="home-product-item__price-old">${pro.priceOutput }</div>
									<div class="home-product-item__price-new">
										<fmt:formatNumber type="currency" currencySymbol=""
											value="${pro.priceOutput - (pro.priceOutput  * pro.discount)}">
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
										${pro.quantity - pro.quantityExtant }</div>
								</div>
								<div class="home-product-item__origin">${pro.address }</div>
								<div class="home-product-item__favourite">
									<i class="home-product-item__favourite-icon fa fa-check"></i>
									Yêu Thích
								</div>
								<div class="home-product-item__sale-off">
									<div class="home-product-item__sale-off-value">
										<fmt:formatNumber type="percent" maxIntegerDigits="3"
											value="${pro.discount}" />
									</div>
									<div class="home-product-item__sale-off-lable">Giảm</div>
								</div>
							</div>
							<div class="home-product-item__footer">Tìm sản phẩm tương
								tự</div>
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
					<li class="pagination-item pagination-item--number"
						id="page-${page}" value="${page }">
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
	</div>
</div>
	

	<!-- phân trang cho các sản phẩm(pagination product by ajax) -->
	<script>
	<c:if test = "${totalPage != null && totalPage != 0}">
		document.getElementById('page-${page == null ? "1" : page}').classList
		.add('pagination-item--active');
	</c:if>
	try {
		$(document).ready(function() {
			$('#pagination .pagination-item--number').click(function() {		
			});
		});
	} catch (error) {
		error.log();
	}
</script>