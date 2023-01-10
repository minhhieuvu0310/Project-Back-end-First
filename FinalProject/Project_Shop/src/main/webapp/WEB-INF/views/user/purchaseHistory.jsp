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
	href="<c:url value=" resources/user/fonts/fontawesome-free-6.1.1/css/all.min.css" />"
	rel="stylesheet">
<link href="<c:url value=" resources/user/css/grid.css" />" rel="stylesheet">
<link href="<c:url value=" resources/user/css/base.css" />" rel="stylesheet">
<!-- Header -->
<link href="<c:url value=" resources/user/css/Header/Header.css" />"
	rel="stylesheet">
<link href="<c:url value=" resources/user/css/Orders/Orders.css" />"
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
                            <img src="<c:url value="resources"/>/user/image/User/${users.getUserImage()}" alt="NoImage" class="myAccount-info__avatar" />
                            <div class="myAccount-info__name">
                                <p>${users.getUserName() }</p>
                                <a href="#" class="editprofile">
                                    <i class="fa-solid fa-pen editprofile__icon"></i>
                                    <span class="editprofile__title">Sửa Hồ Sơ</span>
                                </a>
                            </div>
                        </div>
                    </div>
                    <div class="col l-10 m-12 c-12">
                        <div class="MyOrders">
                            <div class="MyOrders__menubar">
                                <ul class="menubar-list">
                                    <li class="menubar-items">
                                        <a href="#" class="menubar-items__links">Tất cả</a>
                                    </li>
                                    <li class="menubar-items">
                                        <a href="#" class="menubar-items__links">chờ xác nhận</a>
                                    </li>
                                    <li class="menubar-items">
                                        <a href="#" class="menubar-items__links">Chờ lấy hàng</a>
                                    </li>
                                    <li class="menubar-items">
                                        <a href="#" class="menubar-items__links">Đang giao</a>
                                    </li>
                                    <li class="menubar-items">
                                        <a href="#" class="menubar-items__links">Đã giao</a>
                                    </li>
                                    <li class="menubar-items">
                                        <a href="#" class="menubar-items__links">Đã hủy</a>
                                    </li>
                                    <li class="menubar-items">
                                        <a href="#" class="menubar-items__links">trả hàng/hoàn tiền</a>
                                    </li>
                                </ul>
                            </div>
                            <div class="MyOrders__Search">
                                <svg width="19px" height="19px" viewBox="0 0 19 19"><g id="Search-New" stroke-width="1" fill="none" fill-rule="evenodd"><g id="my-purchase-copy-27" transform="translate(-399.000000, -221.000000)" stroke-width="2"><g id="Group-32" transform="translate(400.000000, 222.000000)"><circle id="Oval-27" cx="7" cy="7" r="7"></circle><path d="M12,12 L16.9799555,16.919354" id="Path-184" stroke-linecap="round" stroke-linejoin="round"></path></g></g></g></svg>
                                <input type="text" placeholder="Tìm kiếm theo Tên Shop, ID đơn hàng hoặc Tên Sản phẩm" class="MyOrders__Search-input">
                            </div>
                            <div class="MyOrders__group">
                                <ul class="MyOrders__list">
                                	<c:forEach items="${listOrders }" var="orders">
	                                	<li class="MyOrders__items">
	                                        <div class="MyOrders__items-container">
	                                            <div class="MyOrders__items-header">
	                                                <div class="MyOrders__logoshop">
	                                                    <span class="MyOrders__favorite">Yêu thích</span>
	                                                    <p class="MyOrders__shopname">HieuComputer</p>
	                                                </div>
	                                                <div class="MyOrders__status">
	                                                	<c:if test="${orders.getStatus() }">
		                                                    <div class="MyOrders__status-title">
		                                                        <svg enable-background="new 0 0 15 15" viewBox="0 0 15 15" x="0" y="0" class="shopiconship"><g><line fill="none" stroke-linejoin="round" stroke-miterlimit="10" x1="8.6" x2="4.2" y1="9.8" y2="9.8"></line><circle cx="3" cy="11.2" fill="none" r="2" stroke-miterlimit="10"></circle><circle cx="10" cy="11.2" fill="none" r="2" stroke-miterlimit="10"></circle><line fill="none" stroke-miterlimit="10" x1="10.5" x2="14.4" y1="7.3" y2="7.3"></line><polyline fill="none" points="1.5 9.8 .5 9.8 .5 1.8 10 1.8 10 9.1" stroke-linejoin="round" stroke-miterlimit="10"></polyline><polyline fill="none" points="9.9 3.8 14 3.8 14.5 10.2 11.9 10.2" stroke-linejoin="round" stroke-miterlimit="10"></polyline></g></svg>
		                                                        ĐƠN HÀNG ĐANG ĐƯỢC GIAO
		                                                    </div>
	                                                    </c:if>
	                                                    <c:if test="${!orders.getStatus() }">
	                                                    	<div class="MyOrders__status-content">Đang Xác Thực</div>
	                                                    </c:if>
	                                                    <c:if test="${orders.getStatus() }">
	                                                    	<div class="MyOrders__status-content">Đang giao</div>
	                                                    </c:if>	                                                    
	                                                </div>
	                                            </div>
	                                            <div class="MyOrders__items-content">
	                                                <ul class="MyOrders__orderlist">
	                                                    <c:forEach items="${listOrderdetail }" var="orderdetail">
	                                                    	<c:if test="${orderdetail.getOrders().getOrdersId() == orders.getOrdersId() }">
		                                                    	<li class="MyOrder__orderitem">
		                                                    		<div class="MyOrder__orderitem-wrap">
				                                                        <img src="<c:url value="resources"/>/user/image/buy/${orderdetail.getProduct().getImages() }" alt="" class="MyOrder__orderitem-img">
				                                                        <div class="MyOrder__orderitem-product-description">
				                                                            <div class="MyOrder__orderitem-product-name">${orderdetail.getProduct().getProductContent() }</div>
				                                                            <div class="MyOrder__orderitem-classify">Phân Loại Hàng : ${orderdetail.getColor() }</div>
				                                                            <div class="MyOrder__orderitem-quantity">x${orderdetail.getQuantity() }</div>
				                                                        </div>
			                                                        </div>
			                                                        <div class="MyOrder__orderitem-price">
			                                                        	<c:if test="${orderdetail.getProduct().getDiscount() > 0 }">
			                                                        		<div class="MyOrder__orderitem-oldprice">
									                                            <fmt:formatNumber type="currency" currencySymbol="đ"
																					value="${orderdetail.getProduct().getPriceOutput() }">
																				</fmt:formatNumber>			                                                        		
			                                                        		</div>
			                                                        	</c:if>
			                                                            <div class="MyOrder__orderitem-newprice">
				                                                            <fmt:formatNumber type="currency" currencySymbol="đ"
																						value="${orderdetail.getProduct().getProductHasDiscount() }">
																			</fmt:formatNumber>                                                
			                                                            </div>
			                                                        </div>
			                                                    </li>
	                                                    	</c:if>		                                                    
	                                                    </c:forEach>
	                                                </ul>
	                                            </div>
	                                        </div>
	                                        <div class="MyOrders__items-footer">
	                                            <div class="MyOrders__items-totalmoney">
	                                                <img src="https://deo.shopeemobile.com/shopee/shopee-pcmall-live-sg/pdp/83e10a1f95cb083579c089448ef8dd3b.png" alt="NoImage" class="MyOrders__shop-img">
	                                                <p class="MyOrders__items-totalmoney-title">Tổng Số Tiền:</p>
	                                                <p class="MyOrders__items-totalmoney-content">
						                                <fmt:formatNumber type="currency" currencySymbol="đ"
															value="${orders.getTotalamount()}">
														</fmt:formatNumber>
	                                                </p>
	                                                <p class="MyOrders__items-add">+</p>
	                                                <p class="MyOrders__items-totalmoney-title">Tiền Ship:</p>
	                                                <p class="MyOrders__items-moneyship">40.000đ</p>
	                                            </div>
	                                        </div>
	                                    </li>                               		
                                	</c:forEach>                                                                  
                                </ul>
                            </div>
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
</body>

</html>