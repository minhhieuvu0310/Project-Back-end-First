/**
	 * Sự kiện click vào checkbox lấy ra danh sách nhà sản xuất với danh sách nơi sản xuất
	 * Author:Vũ Minh Hiếu
	 * Date:30/08/2022
	 */

try {
	var listCheckboxs = document.getElementById('category-group-list--produre').querySelectorAll('.category-group-item-check');
	var listProvider = [];
	for (const listCheckbox of listCheckboxs) {

		if (listCheckbox.checked === true) {
			listProvider.push(listCheckbox.value)
		}
		listCheckbox.addEventListener("click", function(event) {
			var Provider = this.value;
			if (this.checked == true) {
				listProvider.push(Provider);
			} else {
				for (let i = 0; i < listProvider.length; i++) {
					const element = listProvider[i];
					if (element == this.value) {
						listProvider.splice(i, 1);
					}
				}
			}
			console.log(listProvider);
		})

	}



	var listcb_catalog = document.getElementById('category-group-list--catalog').querySelectorAll('.category-group-item-check');
	var listCatalog = [];
	for (const cb_item of listcb_catalog) {
		if (cb_item.checked === true) {
			listCatalog.push(cb_item.value);
		}
		cb_item.addEventListener('click', function() {
			var AddressValue = this.value;
			if (this.checked == true) {
				listCatalog.push(AddressValue);
			} else {
				for (let i = 0; i < listCatalog.length; i++) {
					const element = listCatalog[i];
					if (element == this.value) {
						listCatalog.splice(i, 1);

					}
				}
			}
			console.log(listCatalog)
		})
	}

	for (let element of document.getElementsByClassName('category-group-item-check')) {
		element.addEventListener('click', function() {
			var urlStr = document.location.search.substr(1).split('&');
			if (urlStr == '') {
				if (listProvider.length > 0 && listCatalog.length == 0) {
					location.href = `getAllProductSearch?providerName=${listProvider}`;
				} else if (listProvider.length == 0 && listCatalog.length > 0) {
					location.href = `getAllProductSearch?catalogName=${listCatalog}`;
				}
			} else {
				insertParam2('providerName', listProvider, 'catalogName', listCatalog);
			}
		});
	}
} catch (error) {
	console.log(error)
}

