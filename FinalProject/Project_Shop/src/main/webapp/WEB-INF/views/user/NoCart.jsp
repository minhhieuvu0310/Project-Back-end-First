<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style>
.no-cart{
    padding: 76px 0;
    height: 40rem;
}

.no-cart-container{
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
}

.no-cart__img{
    width: 14.8rem;
    height: 14.8rem;
}

.no-cart__title{
    font-size: 1.4rem;
    margin-top: 18px;
    color: rgba(0,0,0,.4);
    font-weight: 700;
}

.btnNoCart--home{
    margin-top: 17px;
    text-transform: uppercase;
}
</style>
<div class="row sm-gutter no-cart">
	<div class="col l-12 m-12 c-12">
		<div class="no-cart-container">
			<img src="<c:url value="resources"/>/image/Nocart/no_cart.png" alt="NoImage"
				class="no-cart__img">
			<p class="no-cart__title">Giỏ hàng của bạn còn trống</p>
			<a href="home" class="btn btn--primary btnNoCart--home">Mua Ngay</a>
		</div>
	</div>
</div>