<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- home filter -->
<div class="home-filter hiden-on-mobile-tablet">
	<div class="home-filter__control" id="home-filter__control">
		<span class="home-filter__title">Sắp xếp theo</span>
		<button class="home-filter__btn btn" id="sortBy-relevancy"
			value="relevancy">Phổ biến</button>
		<button class="btn home-filter__btn" id="sortBy-new" value="new">Mới
			Nhất</button>
		<button class="btn home-filter__btn" id="sortBy-sales" value="sales">Bán
			Chạy</button>
		<div class="home-filter__sort" id="home-filter__sortbyPrice">
			<span class="home-filter__sort-lable">Giá</span> <span
				class="home-filter__sort-icon"><i
				class="fas fa-sort-amount-down-alt"></i></span>

			<ul class="home-filter__sort-list">
				<li class="home-filter__sort-item" id="desc"><span
					class="home-filter__sort-item-link"> Giảm dần <i
						class="fas fa-sort-amount-down-alt"></i>
				</span></li>
				<li class="home-filter__sort-item" id="asc"><span href=""
					class="home-filter__sort-item-link"> Tăng dần <i
						class="fas fa-sort-amount-up-alt"></i>
				</span></li>
			</ul>
		</div>
	</div>
	<div class="home-filter__page">
		<c:if test="${listProduct == null || listProduct.isEmpty()}">
			<div class="home-filter__page-number">
				<span class="home-filter__page-current">0</span>
				/0
			</div>
		</c:if>
		<c:if test="${listProduct != null && !listProduct.isEmpty()}">
			<div class="home-filter__page-number">
				<span class="home-filter__page-current">${page == null ? "1" : page }</span>
				/${totalPage }
			</div>
		</c:if>
		
		<div class="home-filter__page-control">
			<a href="" class="home-filter__page-btn home-filter__page-btn--disabled">
				<i class="home-filter__page-icon fas fa-angle-left"></i>
			</a> 
			<a href="" class="home-filter__page-btn">
				<i class="home-filter__page-icon fas fa-angle-right"></i>
			</a>
		</div>
	</div>
</div>

