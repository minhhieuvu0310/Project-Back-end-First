/**
	 * Sự kiện click vào checkbox lấy ra danh sách nhà sản xuất với danh sách nơi sản xuất
	 * Author:Vũ Minh Hiếu
	 * Date:30/08/2022
	 */

try {
	var listCheckboxs = document.getElementById('category-group-list--produre').querySelectorAll('.category-group-item-check');
	var listId = [];
	for (const listCheckbox of listCheckboxs) {

		if (listCheckbox.checked === true) {
			listId.push(listCheckbox.id)
		}
		listCheckbox.addEventListener("click", function() {
			var ProduceId = this.id;
			if (this.checked == true) {
				listId.push(ProduceId);
			} else {
				for (let i = 0; i < listId.length; i++) {
					const element = listId[i];
					if (element == this.id) {
						listId.splice(i, 1);
					}
				}
			}
			console.log(listId);
		})
	}

	var listcb_address = document.getElementById('category-group-list--address').querySelectorAll('.category-group-item-check');
	var listAddress = [];
	for (const cb_item of listcb_address) {
		if (cb_item.checked === true) {
			listAddress.push(cb_item.value);
		}
		cb_item.addEventListener('click', function() {
			var AddressValue = this.value;
			if (this.checked == true) {
				listAddress.push(AddressValue);
			} else {
				for (let i = 0; i < listAddress.length; i++) {
					const element = listAddress[i];
					if (element == this.value) {
						listAddress.splice(i, 1);
					}
				}
			}
			console.log(listAddress)
		})
	}


	$(document).ready(function() {
		$('.category-group-item-check').click(function() {
			var sortBy;
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
			}
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
					sortByPrice: sortByPrice.toString(),
					sortByPrice_str: sortByPrice_str.toString(),
					iconSortPrice_str: iconSortPrice_str.toString()
				},
				success: function(response) {
					$('div#product-container').html(response);
				}
			});
		})
	});
} catch (error) {
	error.log();
}

