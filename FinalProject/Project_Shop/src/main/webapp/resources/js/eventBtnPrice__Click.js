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
					var urlStr = document.location.search.substr(1).split('&');
					if (urlStr == '') {
						if (priceShortest > 0 && priceTallest == '') {
							location.href = `getAllProductSearch?priceShortest=${priceShortest}`;
						} else if (priceShortest == '' && priceTallest > 0) {
							location.href = `getAllProductSearch?priceTallest=${priceTallest}`;
						} else if (priceShortest > 0 && priceTallest > 0) {
							location.href = `getAllProductSearch?priceShortest=${priceShortest}&priceTallest=${priceTallest}`;
						}
					} else {
						insertParam2('priceShortest', priceShortest, 'priceTallest', priceTallest);
					}
				})
			}
		});
	});
} catch (error) {
	console.log(error)
}


