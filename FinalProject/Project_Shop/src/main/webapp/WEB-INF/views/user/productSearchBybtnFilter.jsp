<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style>
.pagination-item {
	cursor: pointer;
}
</style>
<!-- home filter -->
<jsp:include page="home-filter.jsp"></jsp:include>
<!-- mobile-category -->
<jsp:include page="mobile-category.jsp"></jsp:include>
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
							<div class="home-product-item__saled">Đã bán ${pro.quantity - pro.quantityExtant }</div>
						</div>
						<div class="home-product-item__origin">${pro.address }</div>
						<div class="home-product-item__favourite">
							<i class="home-product-item__favourite-icon fa fa-check"></i> Yêu
							Thích
						</div>
						<div class="home-product-item__sale-off">
							<div class="home-product-item__sale-off-value">
								<fmt:formatNumber type="percent" maxIntegerDigits="3"
									value="${pro.discount}" />
							</div>
							<div class="home-product-item__sale-off-lable">Giảm</div>
						</div>
					</div>
					<div class="home-product-item__footer">Tìm sản phẩm tương tự</div>
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
				value="${page }">
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

<!-- phân trang cho các sản phẩm(pagination product by ajax) -->
<script>
	<c:if test = "${sortBy.contains('Price') && sortByPrice != null}">
		document.querySelector("#${sortByPrice}.home-filter__sort-item").classList.add('home-filter__sort-item--active');
		<c:if test = "${sortByPrice_str != null && iconSortPrice_str != null}">
			document.querySelector('#home-filter__sortbyPrice .home-filter__sort-lable').innerHTML = `${sortByPrice_str}`;
			document.querySelector('#home-filter__sortbyPrice .home-filter__sort-icon').innerHTML  = `${iconSortPrice_str}`;
		</c:if>
	</c:if>
	<c:if test = "${totalPage != 0}">
		document.getElementById('page-${page == null ? "1" : page}').classList
		.add('pagination-item--active');
	</c:if>
	try {
		$(document).ready(function() {
			$('#pagination .pagination-item--number').click(function() {
				var page = this.value;
				var priceShortest = txtpriceShortest.value;
				var priceTallest = txtpriceTallest.value;
				var sortBy;
				var sortByPrice_str = `${sortByPrice_str}`;
				var iconSortPrice_str = `${iconSortPrice_str}`;
				var sortByPrice = `${sortByPrice}`;
				if(${sortBy.contains('Price')}){
					sortBy = "Price";
				}else{
					sortBy = document.querySelector('.home-filter__btn.btn--primary').value;
				}		
				$.ajax({
					type : "GET",
					url : "listComputerBySort",
					contentType : "application/json",
					data : {
						listId: listId.toString()
	                     , listAddress: listAddress.toString()
	                     , priceShortest: priceShortest.toString() 
	                     , priceTallest: priceTallest.toString()
	 					 , sortBy: sortBy.toString()
	 					 , page : page.toString()
	 					 , sortByPrice_str : sortByPrice_str.toString()
	 					 , iconSortPrice_str : iconSortPrice_str.toString()
	 					 , sortByPrice : sortByPrice.toString()
					},
					success : function(response) {
						$('div#product-container').html(response);
					}
				});

			})
		});
	} catch (error) {
		error.log();
	}
</script>