/**
 * Sự kiện click vào nút của form giá cả
 * Author:Vũ Minh Hiếu
 * Date:06/09/2022
 */
function isNumberKey(e) {
	var charCode = (e.which) ? e.which : e.keyCode;
	if (charCode > 31 && (charCode < 48 || charCode > 57))
		return false;
	return true;
}
try {
	var txtpriceShortest = document.getElementById('category-group-filter--price').firstElementChild;
	var txtpriceTallest = document.getElementById('category-group-filter--price').lastElementChild;
	var btnPrice = document.getElementById('btn--price');
	var errorPrice = document.getElementById("error_inputPrice");

	$(document).ready(function() {
		var txtpriceShortest = document.getElementById('category-group-filter--price').firstElementChild;
		var txtpriceTallest = document.getElementById('category-group-filter--price').lastElementChild;
		var errorPrice = document.getElementById("error_inputPrice");

		$('#btn--price').click(function() {
			var priceShortest = txtpriceShortest.value;
			var priceTallest = txtpriceTallest.value;
			if ((priceShortest != '') && (priceTallest != '') && (parseInt(priceShortest) >= parseInt(priceTallest))) {
				errorPrice.innerHTML = '*Vui lòng điền khoảng giá phù hợp*';
				errorPrice.style.height = '40px';
				txtpriceShortest.value = '';
				txtpriceTallest.value = '';
			}
			else if ((priceShortest == '') && (priceTallest == '')) {
				errorPrice.innerHTML = '*Vui lòng điền khoảng giá phù hợp*';
				errorPrice.style.height = '40px';
			}
			else {
				$('#btn--price').click(function() {
					/*var sortBy;
					var sortByPrice = '';
					var sortByPrice_str = '';
					var iconSortPrice_str = '';
					if (document.querySelector('.home-filter__btn.btn--primary') != null) {
						sortBy = document.querySelector('.home-filter__btn.btn--primary').value;
						sortByPrice = '';
						sortByPrice_str = '';
						iconSortPrice_str = '';
					} else {
						var sortBy = 'Price';
						sortByPrice = document.querySelector('.home-filter__sort-item.home-filter__sort-item--active').id;
						sortByPrice_str = document.querySelector('#home-filter__sortbyPrice .home-filter__sort-lable').innerHTML;
						iconSortPrice_str = document.querySelector('#home-filter__sortbyPrice .home-filter__sort-icon').innerHTML;
					}*/
					if (listProvider.length == 0 && listCatalog.length == 0) {
						if (priceShortest > 0 && priceTallest == '') {
							location.href = `getAllProductSearch?priceShortest=${priceShortest}`
						} else if (priceShortest == '' && priceTallest >= 0) {
							location.href = `getAllProductSearch?priceTallest=${priceTallest}`
						}
					}else if(listProvider.length != 0 && listCatalog.length == 0){
						if (priceShortest > 0 && priceTallest == '') {
							location.href = `getAllProductSearch?providerName=${listProvider}&priceShortest=${priceShortest}`
						} else if (priceShortest == '' && priceTallest >= 0) {
							location.href = `getAllProductSearch?providerName=${listProvider}&priceTallest=${priceTallest}`
						}
					}else if(listProvider.length == 0 && listCatalog.length != 0){
						if (priceShortest > 0 && priceTallest == '') {
							location.href = `getAllProductSearch?catalogName=${listCatalog}&priceShortest=${priceShortest}`
						} else if (priceShortest == '' && priceTallest >= 0) {
							location.href = `getAllProductSearch?catalogName=${listCatalog}&priceTallest=${priceTallest}`
						}
					}
					errorPrice.style.height = '0px';
					errorPrice.innerHTML = "";
				})
			}
		});
	});
} catch (error) {
	error.log()
}