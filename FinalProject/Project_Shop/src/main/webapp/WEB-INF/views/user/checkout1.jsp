<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Thông Tin Nhận Hàng</title>
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
<link href="<c:url value=" resources/css/CheckOut1/checkout1.css" />"
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

		<!-- *Begin CheckOut1 -->
		<div class="checkout1-container">
            <div class="grid wide">
                <div class="row sm-gutter ">
                    <div class="col l-7 m-12 c-12 customer-info">
                        <form action="prePayment" method="post" class="customer-info__form" id="jsCustomer-form">
                        	<input type="hidden" name="total" value="${total}" >
                            <div class="purchase-info__form">
                                <h3 class="purchase-info__title">Thông Tin mua hàng</h3>
                                <div class="purchase-info__input-container">
                                    <div class="purchase-info__input-item">
                                        <div class="purchase-info__group purchase-info__group-w45">
                                            <div class="purchase-info__lable">Họ Và Tên</div>
                                            <input required class="purchase-info__input" placeholder="Họ Và Tên" value="${users.getFullName() }"></input>
                                        </div>
                                        <div style="width: 16px;"></div>
                                        <div class="purchase-info__group purchase-info__group-w45">
                                            <div class="purchase-info__lable">Số Điện Thoại</div>
                                            <input required class="purchase-info__input" placeholder="Số Điện Thoại" value="${users.getPhone()}"></input>
                                        </div>
                                    </div>
                                    <div class="purchase-info__input-item">
                                        <div class="purchase-info__group">
                                            <div class="purchase-info__lable">Email</div>
                                            <input required class="purchase-info__input" placeholder="Email" value="${users.getEmail() }"></input>
                                        </div>
                                    </div>
                                    <div class="purchase-info__input-item">
                                        <div class="purchase-info__group">
                                            <div class="purchase-info__lable">Tỉnh/ Thành phố, Quận/Huyện, Phường/Xã
                                            </div>
                                            <input class="purchase-info__input" value="${users.getAddress() }"
                                                placeholder="Tỉnh/ Thành phố, Quận/Huyện, Phường/Xã"></input>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="purchase-info__form">
                                <h3 class="purchase-info__title">Thông Tin nhận hàng</h3>
                                <div class="purchase-info__input-container">
                                    <div class="purchase-info__input-item">
                                        <div class="purchase-info__group purchase-info__group-w45">
                                            <div class="purchase-info__lable">Họ Và Tên</div>
                                            <input name="name" class="purchase-info__input" placeholder="Họ Và Tên"></input>
                                        </div>
                                        <div style="width: 16px;"></div>
                                        <div class="purchase-info__group purchase-info__group-w45">
                                            <div class="purchase-info__lable">Số Điện Thoại</div>
                                            <input name="phone" class="purchase-info__input" placeholder="Số Điện Thoại"></input>
                                        </div>
                                    </div>
                                    <div class="purchase-info__input-item">
                                        <div class="purchase-info__group">
                                            <div class="purchase-info__lable">Email</div>
                                            <input name="email" class="purchase-info__input" placeholder="Email"></input>
                                        </div>
                                    </div>
                                    <div class="purchase-info__input-item">
                                        <div class="purchase-info__group">
                                            <div class="purchase-info__lable">Tỉnh/ Thành phố, Quận/Huyện, Phường/Xã
                                            </div>
                                            <input name="address" class="purchase-info__input"
                                                placeholder="Tỉnh/ Thành phố, Quận/Huyện, Phường/Xã"></input>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="purchase-info__controls">
                                <button class="btn btn--primary btn--continue">Tiếp Tục</button>
                                <a href="#" class="btn btn--comeback">Quay lại</a>
                            </div>
                        </form>
                    </div>
                    <div class="col l-5 m-12 c-12 listCart-container">
                        <div class="listCart">
                            <h3 class="listCart__header">Đơn Hàng</h3>
                            <ul class="listCart__list">
                            	<c:forEach items="${sessionScope.listCart}" var="cart">
	                            	<li class="listCart__item">
	                                    <div class="listCart__item-left">
	                                        <img src="<c:url value="resources"/>/image/buy/${cart.getProduct().getImages() }" alt="NoImage" class="listCart__item-img">
	                                        <span class="listCart__item-quantity">${cart.getQuantity() }</span>
	                                    </div>
	                                    <div class="listCart__item-center">
	                                        <p class="listCart__item-name">${cart.getProduct().getProductContent() }</p>
	                                    </div>
	                                    <div class="listCart__item-right">
	                                        <span class="listCart__item-price">
		                                        <fmt:formatNumber type="currency" currencySymbol="đ" value="${cart.getProduct().getProductHasDiscount() }">
												</fmt:formatNumber>
	                                        </span>
	                                    </div>
	                                </li>
                            	</c:forEach>                                
                            </ul>
                            <div class="listCart__total">
                                <div class="total-line-subtotal">
                                    <span class="total-line-subtotal-left">Tạm Tính : </span>
                                    <span class="total-line-subtotal-right">
                                    	<fmt:formatNumber type="currency" currencySymbol="đ" value="${total }">
										</fmt:formatNumber>
                                    </span>
                                </div>
                                <div class="total-line-total">
                                    <span class="total-line-name">Tổng Cộng : </span>
                                    <span class="total-line-money">
                                    	<fmt:formatNumber type="currency" currencySymbol="đ" value="${total }">
										</fmt:formatNumber>
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>	
		<!-- *End CheckOut1 -->

		<!-- *Begin Footer -->
		<jsp:include page="footer.jsp"></jsp:include>
		<!-- *End Footer -->
	</div>
	<script>
        var purchase_group = document.getElementsByClassName('purchase-info__group');
        for (const item of purchase_group) {
            item.addEventListener('click', function (event) {
                var kt = item.classList.contains('purchase-info__group-focus');
                var kt2 = document.querySelector('.customer-info__form .purchase-info__group.purchase-info__group-focus');
                if (kt2 != null) {
                    if (!kt) {
                        hidenBorder();
                        item.classList.add('purchase-info__group-focus');
                    }
                } else {
                    item.classList.add('purchase-info__group-focus')
                }
            })
        }

        var checkout1 = document.getElementsByTagName('body')[0];
        checkout1.addEventListener('click', function () {
            hidenBorder();
        });

        var Customer_form = document.getElementById('jsCustomer-form');
        Customer_form.addEventListener('click', function (event) {
            event.stopPropagation();
        });

        function hidenBorder() {
            if (document.querySelector('.purchase-info__group.purchase-info__group-focus') != null) {
                document.querySelector('.purchase-info__group.purchase-info__group-focus').classList.remove('purchase-info__group-focus');
            }
        }
    </script>
</body>

</html>