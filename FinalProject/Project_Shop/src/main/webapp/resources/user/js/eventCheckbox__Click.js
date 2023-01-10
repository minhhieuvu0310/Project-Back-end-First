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
				insertParamCheckBox('providerName', listProvider, 'catalogName', listCatalog);
			}
		});
	}
} catch (error) {
	console.log(error)
}

function insertParamCheckBox(key1, value1, key2, value2) {
	key1 = encodeURIComponent(key1);
	value1 = encodeURIComponent(value1);
	key2 = encodeURIComponent(key2);
	value2 = encodeURIComponent(value2);

	// kvp looks like ['key1=value1', 'key2=value2', ...]
	var kvp = document.location.search.substr(1).split('&');
	let i = 0, j = 0;

	for (; i < kvp.length; i++) {
		if (kvp[i].startsWith(key1 + '=')) {
			let pair = kvp[i].split('=');
			pair[1] = value1;
			kvp[i] = pair.join('=');
			break;
		}
	}

	for (; j < kvp.length; j++) {
		if (kvp[j].startsWith(key2 + '=')) {
			let pair = kvp[j].split('=');
			pair[1] = value2;
			kvp[j] = pair.join('=');
			break;
		}
	}

	if (value1 == '' || value2 == '') {
		if (i >= kvp.length && value1 != '') {
			kvp[kvp.length] = [key1, value1].join('=');
		}
		if (j >= kvp.length && value2 != '') {
			kvp[kvp.length] = [key2, value2].join('=');
		}
	} else if (value1 != '' && value2 != '') {
		if(i>j){
			if (i >= kvp.length) {
				kvp[kvp.length] = [key1, value1].join('=');
			}
			if (j >= kvp.length - 1) {
				kvp[kvp.length - 1] = [key2, value2].join('=');
			}
		}else if(i<j){
			if (i+1 >= kvp.length) {
				kvp[kvp.length] = [key1, value1].join('=');
			}
			if (j >= kvp.length - 1) {
				kvp[kvp.length - 1] = [key2, value2].join('=');
			}
		}
		
	}


	console.log(kvp);

	var n = kvp.length
	for (let i = 0; i < kvp.length; i++) {
		if (kvp[i].startsWith('sortBy=')) {
			const tam = kvp[i];
			kvp[i] = kvp[n - 1];
			kvp[n - 1] = tam;
		} else {
			continue
		}
	}

	let is = 0;
	var listValeEmpty = [''];
	for (; is < kvp.length; is++) {
		let element = kvp[is].split('=');
		console.log(is + ' ' + kvp[is]);
		if (element[1] == '') {
			listValeEmpty.push(kvp[is]);
		}
	}

	kvp = kvp.filter(val => !listValeEmpty.includes(val));
	for (let i = 0; i < kvp.length; i++) {
		if (kvp[i].startsWith('page=')) {
			kvp.splice(i, 1);
			break;
		}
	}

	let params = kvp.join('&');
	let urlNew = 'getAllProductSearch?' + kvp.join('&');

	if (params == '') {
		location.href = 'home';
	} else {
		location.href = urlNew;

	}
}