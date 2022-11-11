<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Cart</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link href="<c:url value=" resources/fonts/fontawesome-free-6.1.1/css/all.min.css" />"
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
        .PayandUpdateCart {
            display: flex;
            align-items: center;
            justify-content: space-between;
            padding: 15px;
            margin: 5px;
        }

        .PayandUpdateCart__UpdateorBuy {
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .btnCart--buy {
        	text-decoration:none;
        	color : #000;
            background-color: #e7e7e7;
            margin-left: 8px;
        }

        .totalmoney__title {
            font-size: 1.9rem;
            margin: 0;
        }

        .total {
            background-color: #e7e7e7;
            height: 32px;
            width: 240px;
            line-height: 32px;
            padding-left: 16px;
        }

        .money {
            margin-left: 16px;
        }

        .PayandUpdateCart__btn {
            font-size: 1.4rem;
        }

        .btnCart--pay {
            margin-top: 16px;
            width: 170px;
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
                                                <a href="productDetails?productId=${cart.getProduct().productId }" class="cart__product-link">
                                                    <div class="cart__product-img"
                                                        style="background-image: url(<c:url value='resources'/>/image/buy/${cart.getProduct().getImages() });">
                                                    </div>
                                                </a>
                                                <div class="cart__product-name-classify">
                                                    <div class="cart__product-name">
                                                        <a href="" style="color: rgba(0, 0, 0, .87); text-decoration: none;">
                                                         	${cart.getProduct().getProductContent()}
                                                         </a>
                                                    </div>
                                                    <div class="cart__product-classify">
                                                        <div class="cart__product-classify-lable">
                                                            Phân loại hàng:
                                                        </div>
                                                        <div class="cart__product-classify-name">${cart.color }</div>
                                                    </div>
                                                </div>
                                            </div>
                                            <c:if test="${cart.getProduct().getDiscount() == 0 }">
	                                            <div class="cart__product-price item--price">
	                                                <div class="cart__product-price-new">
	                                                	<fmt:formatNumber type="currency" currencySymbol="" value="${cart.getProduct().getPriceOutput() }">
														</fmt:formatNumber>       	
	                                                </div>
	                                            </div>
                                            </c:if>
                                            <c:if test="${cart.getProduct().getDiscount() != 0 }">
	                                            <div class="cart__product-price item--price">
	                                                <div class="cart__product-price-old" style="padding-left: 16px;width: 90px;overflow: hidden;text-overflow: ellipsis; ">
	                                                	<fmt:formatNumber type="currency" currencySymbol="" value="${cart.getProduct().getPriceOutput() }">
														</fmt:formatNumber> 
	                                                </div>
	                                                <div class="cart__product-price-new">
	                                                	<fmt:formatNumber type="currency" currencySymbol="" value="${cart.getProduct().getProductHasDiscount() }">
														</fmt:formatNumber>	                                                
	                                                </div>
	                                            </div>
                                            </c:if>
                                            
                                            <div class="cart__product-quantity item--quantity">
                                                <p class="cart__product-quantity-btn btn--subQuantity"
                                                    style="display: flex; align-items: center; justify-content: center;">
                                                    <i class="cart__product-quantity-icon fa-solid fa-minus"></i>
                                                </p>
                                                <input type="hidden" name="maxQuantity" class="maxQuantity" value="300">
                                                <input type="hidden" name="priceOutput" class="priceOutput"
                                                    value="${cart.getProduct().getProductHasDiscount()}">
                                                <input type="text" class="cart__product-quantity-value-input" value="${cart.quantity }"
                                                    name="quantity">
                                                <p class="cart__product-quantity-btn btn--addQuantity"
                                                    style="display: flex; align-items: center; justify-content: center;">
                                                    <i class="cart__product-quantity-icon fa-solid fa-plus"></i>
                                                </p>
                                            </div>
                                            <div class="cart__product-total-money item--total-money hiden-on-mobile totalmoneyProductitems">
                                                <fmt:formatNumber type="currency" currencySymbol="" value="${cart.getProduct().getProductHasDiscount() * cart.getQuantity()}">
												</fmt:formatNumber>	đ
											</div>
                                            <ul
                                                class="cart__product-manipulation-list item--manipulation hiden-on-mobile">
                                                <li class="cart__product-manipulation-item manipulation-item--remove">
                                                    <a href="removeCart?productId=${cart.getProduct().productId }" class="cart-link--remove"
                                                        style="text-decoration: none; color: #000;"  onclick="return confirm('Bạn muốn xóa?')">Xóa</a>
                                                </li>
                                            </ul>
                                        </div>
                                      </c:forEach>
                                </div>
                            </div>
                            

                            <div class="row sm-gutter row-bg">
                                <div class="col l-12 m-12 c-12">
                                    <div class="PayandUpdateCart">
                                        <div class="PayandUpdateCart__UpdateorBuy">
                                            <button type="submit" class="btn btn--primary btnCart--Update">Cập
                                                Nhật</button>
                                            <a href="home" class="btn btnCart--buy">Tiếp tục mua hàng</a>
                                        </div>
                                        <div class="PayandUpdateCart__btn">
                                            <div class="totalmoney">
                                                <h3 class="totalmoney__title">Tổng cộng</h3>
                                                <p class="total">
                                                    Tạm tính : 
                                                    <span class="money">
                                                    	<fmt:formatNumber type="currency" currencySymbol="" value="${total}">
														</fmt:formatNumber>	đ
                                                    </span>
                                                </p>
                                            </div>
                                            <a href="#" class="btn btn--primary btnCart--pay">Tiến
                                                Hành Thanh Toán</a>
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