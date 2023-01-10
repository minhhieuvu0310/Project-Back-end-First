<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Phương Thức Thanh Toán</title>
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
<link href="<c:url value=" resources/user/css/CheckOut2/checkout2.css" />"
	rel="stylesheet">
<!-- Footer -->
<link href="<c:url value=" resources/user/css/Footer/footer.css" />"
	rel="stylesheet">
<style>


</style>
</head>

<body>
	<div class="app" style="overflow: hidden;">
		<!-- *Begin Header -->
		<jsp:include page="header.jsp"></jsp:include>
		<!-- *End Header -->

		<!-- *Begin CheckOut2 -->
		<div class="checkout2-container">
            <div class="grid wide">
                <div class="row sm-gutter ">
                    <div class="col l-7 m-12 c-12 paymentmethod">
                        <form action="payment" method="post" class="paymentmethod__form" id="jspaymentmethod-form">
                        	<input type="hidden" name="paymentmethod" value="Thanh Toán khi nhận hàng">
                        	<input type="hidden" name="orderId" value="${Id }">
                            <div class="paymentmethod-info">
                                <h2 class="paymentmethod__title">Lựa chọn phương thức thanh toán</h2>
                                <ul class="paymentmethod-list">
                                    <li class="paymentmethod-item" id="1">
                                        <label class="paymentmethod__label">
                                            <img class="paymentmethod__img" src="<c:url value="resources"/>/image/pay/1.PNG"></img>
                                        </label>
                                        <p class="paymentmethod__content">Thẻ nạp</p>
                                    </li>
                                    <li class="paymentmethod-item" id="2">
                                        <label class="paymentmethod__label">
                                            <img class="paymentmethod__img" src="<c:url value="resources"/>/image/pay/payment1.png"></img>
                                        </label>
                                        <p class="paymentmethod__content">Thẻ Tín Dụng</p>
                                    </li>
                                    <li class="paymentmethod-item" id="3">
                                        <label class="paymentmethod__label">
                                            <img class="paymentmethod__img" src="<c:url value="resources"/>/image/pay/pic4.png"></img>
                                        </label>
                                        <p class="paymentmethod__content">Thanh Toán Khi Giao Hàng</p>
                                    </li>
                                </ul>
                            </div>

                            <div class="paymentmethod__btn">
                                <div class="paymentmethod__notice paymentmethod__notice--requirement"
                                    id="jspaymentmethod__notice--requirement">
                                    <p class="paymentmethod__content">Vui lòng chọn chức năng thanh toán</p>
                                </div>
                                <div class="paymentmethod__notice paymentmethod__notice--unactive">
                                    <p class="paymentmethod__content">Xin lỗi chức năng thanh toán này chưa được hệ
                                        thống hỗ trợ</p>
                                </div>
                                <div class="paymentmethod__notice" id="jspaymentmethod__selected">
                                    <p class="paymentmethod__content" id="jspaymentmethod__selected-text"></p>
                                </div>
                                <!-- <button class="btn btn--primary btn--Pay">Tiếp Tục</button> -->
                                <button class="btn btn--primary btn--Pay" id="jsbtn--Pay"
                                    style="display: none;">Tiếp Tục</button>
                                <div style="clear: both ;"></div>
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
		<!-- *End CheckOut2 -->

		<!-- *Begin Footer -->
		<jsp:include page="footer.jsp"></jsp:include>
		<!-- *End Footer -->
	</div>
	<script>
        var paymentmethod = document.getElementsByClassName('paymentmethod-item');
        var paymentmethod__noticetext = document.getElementById('jspaymentmethod__selected-text');
        var paymentmethod__noticerequirement = document.getElementById('jspaymentmethod__notice--requirement');
        var jsbtn__Pay = document.getElementById('jsbtn--Pay');
        for (const item of paymentmethod) {
            item.addEventListener('click', function (event) {
                var kt = item.classList.contains('paymentmethod-item--active');
                var kt2 = document.querySelector('.paymentmethod-item.paymentmethod-item--active');
                if (kt2 != null) {
                    if (!kt) {
                        hidenBorder();
                        item.classList.add('paymentmethod-item--active');
                        if (item.id == '3') {
                            paymentmethod__noticetext.innerHTML = 'Bạn vui lòng thanh toán khi nhận hàng';
                            jsbtn__Pay.style.display = 'flex'
                        } else {
                            paymentmethod__noticetext.innerHTML = 'Hiện tại hệ thống chưa hỗ trợ chức năng thanh toán này , bạn vui lòng chọn chức năng thanh toán khác';
                            jsbtn__Pay.style.display = 'none'
                        }
                    }
                } else {
                    item.classList.add('paymentmethod-item--active');
                    if (item.id == '3') {
                        paymentmethod__noticetext.innerHTML = 'Bạn vui lòng thanh toán khi nhận hàng';
                        jsbtn__Pay.style.display = 'flex';
                    } else {
                        paymentmethod__noticetext.innerHTML = 'Hiện tại hệ thống chưa hỗ trợ chức năng thanh toán này , bạn vui lòng chọn chức năng thanh toán khác';
                        jsbtn__Pay.style.display = 'none';
                    }
                }
                paymentmethod__noticerequirement.style.display = 'none';
            })

        }

        var checkout2 = document.getElementsByTagName('body')[0];
        checkout2.addEventListener('click', function () {
            hidenBorder();
            paymentmethod__noticetext.innerHTML = '';
            paymentmethod__noticerequirement.style.display = 'block';
        });

        var paymentmethod_form = document.getElementById('jspaymentmethod-form');
        paymentmethod_form.addEventListener('click', function (event) {
            event.stopPropagation();
        });



        function hidenBorder() {
            if (document.querySelector('.paymentmethod-item.paymentmethod-item--active') != null) {
                document.querySelector('.paymentmethod-item.paymentmethod-item--active').classList.remove('paymentmethod-item--active');
            }
        }


    </script>
	
</body>

</html>