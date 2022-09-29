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
					var sortBy = document.querySelector('.home-filter__btn.btn--primary').value;
					var priceShortest = txtpriceShortest.value;
					var priceTallest = txtpriceTallest.value;
					$.ajax({
						type: "GET",
						url: "listComputerBySort",
						contentType: "application/json",
						data: {
							listId: listId.toString(),
							listAddress: listAddress.toString(),
							sortBy: sortBy.toString(),
							priceShortest: priceShortest.toString(),
							priceTallest: priceTallest.toString(),
						},
						success: function(response) {
							$('div#product-container').html(response);
						}
					});
					// var priceShortest = txtpriceShortest.value;
					// var priceTallest = txtpriceTallest.value;
					// console.log(priceShortest);
					// console.log(priceTallest);
				})
				errorPrice.style.height = '0px';
				errorPrice.innerHTML = "";
			}
		});
	});
} catch (error) {
	error.log()
}