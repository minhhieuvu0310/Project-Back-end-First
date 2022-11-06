<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="col l-2 m-0 c-0">
	<nav class="category">
		<h3 class="category-heading">
			<i class="category-heading-icon fas fa-list-ul"></i> Bộ Lọc Tìm Kiếm
		</h3>
		<div class="category-group" id="provider">
			<div class="category-group-title">Hãng Sản Xuất</div>
			<ul class="category-group-list" id="category-group-list--produre">
				<c:forEach items="${listProvider }" var="provider">
					<li class="category-group-item" style="cursor: pointer;">
						<div class="category-group-item-link" style="text-decoration: none; color: var(--text-color); ">
							<c:if test="${!lstProvider.isEmpty() && lstProvider.contains(provider.providerName)}">
								<input type="checkbox" class="category-group-item-check"
									value="${provider.providerName}" checked="checked">
							</c:if> 
							<c:if test="${lstProvider.isEmpty() || !lstProvider.contains(provider.providerName)}">
								<input type="checkbox" class="category-group-item-check"
									value="${provider.providerName}">
							</c:if> 
							<%-- <input type="checkbox" class="category-group-item-check" value="${provider.providerName}">	--%>
							<span>${provider.providerName}</span>
						</div>
					</li>
				</c:forEach>
			</ul>
		</div>
		<div class="category-group">
			<div class="category-group-title">Nhu Cầu</div>
			<ul class="category-group-list" id="category-group-list--catalog">
				<c:forEach items="${listCatalogs }" var="catalog">
					<li class="category-group-item" style="cursor: pointer;">
						<div class="category-group-item-link" style="text-decoration: none; color: var(--text-color); ">
							<c:if test="${!lstCataLog.isEmpty() && lstCataLog.contains(catalog.catalogName)}">
								<input type="checkbox" class="category-group-item-check"
									value="${catalog.catalogName}" checked="checked">
							</c:if> 
							<c:if test="${lstCataLog.isEmpty() || !lstCataLog.contains(catalog.catalogName)}">
								<input type="checkbox" class="category-group-item-check"
									value="${catalog.catalogName}">
							</c:if> 
							<span>${catalog.catalogName}</span>
						</div>
					</li>
				</c:forEach>
			</ul>
		</div>
		<div class="category-group">
			<div class="category-group-title">Khoảng Giá</div>
			<div class="category-group-filter" id="category-group-filter--price">
						<input type="text" 
							class="category-group-filter-input"placeholder="đ Từ" 
							name="priceShortest" onkeypress="return isNumberKey(event)" value="${priceShortest}"> 
					<i class="fas fa-arrow-right"></i>
						<input type="text"
								class="category-group-filter-input" placeholder="đ Đến"
								name="priceTallest" onkeypress="return isNumberKey(event)" value="${priceTallest}">
				
			</div>
			<span id="error_inputPrice"
				style="color: red; padding-left: 8px; display: block; display: -webkit-box; max-width: 100%; margin: 0 auto; font-size: 12px; -webkit-line-clamp: 2; -webkit-box-orient: vertical; overflow: hidden; text-overflow: ellipsis;"></span>
			<button class="btn btn--primary category-group-filter-btn"
				id="btn--price">Áp Dụng</button>
		</div>
		<a href="home"
			class="btn btn--primary category-group-filter-btn category-group--margin">
			Làm Mới </a>
	</nav>
</div>
