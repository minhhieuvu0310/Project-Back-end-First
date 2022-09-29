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
		<div class="home-filter__page-number">
			<span class="home-filter__page-current">${page == null ? "1" : page }</span>
			/${totalPage }
		</div>
		<div class="home-filter__page-control">
			<a href=""
				class="home-filter__page-btn home-filter__page-btn--disabled"> <i
				class="home-filter__page-icon fas fa-angle-left"></i>
			</a> <a href="" class="home-filter__page-btn"> <i
				class="home-filter__page-icon fas fa-angle-right"></i>
			</a>
		</div>
	</div>
</div>

<script>
	<c:if test = "${!sortBy.contains('Price')}">
	document.getElementById('sortBy-${sortBy == null ? "relevancy" : sortBy}').classList
			.add('btn--primary');
	</c:if>
try {
		var sortBy;
	    var lstfiltercontrol = document.getElementById('home-filter__control').getElementsByClassName('home-filter__btn');
	    for (const btnFilter of lstfiltercontrol) {
	        btnFilter.addEventListener('click',function(){
	        	sortBy = this.value;
	            var priceShortest = txtpriceShortest.value;
	            var priceTallest = txtpriceTallest.value;
	        	$.ajax({
	 				type: "GET",
	 				url: "listComputerBySort",
	 				contentType: "application/json",
	 				data: { listId: listId.toString()
	                     , listAddress: listAddress.toString()
	                     , priceShortest: priceShortest.toString() 
	                     , priceTallest: priceTallest.toString()
	 					 , sortBy: sortBy.toString()
	 					 ,
	 						},
	 				success: function(response) {
	 					$('div#product-container').html(response);
	 				}
	 			});
	        });
	    }

	    for (const option of document.getElementsByClassName('home-filter__sort-item')) {
	        option.addEventListener('click',function(){            
	            if(!this.classList.contains('home-filter__sort-item--active')){
	                var kt = this.parentNode.querySelector('.home-filter__sort-item.home-filter__sort-item--active');
	                var sortByPrice;
	                if(kt == null){
	                    this.classList.add('home-filter__sort-item--active');
	                    var sortByPrice_str = ((this.id == 'desc')? 'Giảm dần' : 'Tăng Dần');
	                    var iconSortPrice_str = ((this.id == 'desc')? '<i class="fas fa-sort-amount-down-alt"></i>' : '<i class="fas fa-sort-amount-up-alt"></i>');
	                    this.parentNode.parentNode.parentNode.querySelector('.home-filter__sort-lable').innerHTML = sortByPrice_str;
	                    this.parentNode.parentNode.parentNode.querySelector('.home-filter__sort-icon').innerHTML = iconSortPrice_str;
	                    sortby = 'Price';
	                    sortByPrice = this.id;
	                    // console.log(sortby + " " + sortByPrice)
	                }
	                else{
	                    this.parentNode.querySelector('.home-filter__sort-item.home-filter__sort-item--active').classList.remove('home-filter__sort-item--active');
	                    this.classList.add('home-filter__sort-item--active');
	                    var sortByPrice_str = ((this.id == 'desc')? 'Giảm dần' : 'Tăng Dần');
	                    var iconSortPrice_str = ((this.id == 'desc')? '<i class="fas fa-sort-amount-down-alt"></i>' : '<i class="fas fa-sort-amount-up-alt"></i>');
	                    this.parentNode.parentNode.parentNode.querySelector('.home-filter__sort-lable').innerHTML = sortByPrice_str;
	                    this.parentNode.parentNode.parentNode.querySelector('.home-filter__sort-icon').innerHTML = iconSortPrice_str;
	                    sortby = 'Price';
	                    sortByPrice = this.id;
	                    // console.log(sortby + " " + sortByPrice)
	                }
	            }
	            
	        }) 
	    }
	} catch (error) {
	    error.log();
	}
</script>
