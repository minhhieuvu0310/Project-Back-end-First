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
				//location.href = `getAllProductSearch?providerName=${listProvider}`;
			} else {
				for (let i = 0; i < listProvider.length; i++) {
					const element = listProvider[i];
					if (element == this.value) {
						listProvider.splice(i, 1);
						//location.href=`getAllProductSearch?providerName=${listProvider}`;
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

	$(document).ready(function() {
		$('.category-group-item-check').click(function() {


			var urlStr = document.location.search.substr(1).split('&');
			if (urlStr == '') {
				if (listProvider.length > 0 && listCatalog.length == 0) {
					location.href = `getAllProductSearch?providerName=${listProvider}`;
				} else if (listProvider.length == 0 && listCatalog.length > 0) {
					location.href = `getAllProductSearch?catalogName=${listCatalog}`;
				}
			} else {
				if (listProvider.length > 0 && listCatalog.length > 0) {
					insertParam2('providerName', listProvider, 'catalogName', listCatalog);
				} else if (listProvider.length > 0 && listCatalog.length == 0) {				
					insertParam('providerName', listProvider);
				} else if (listProvider.length == 0 && listCatalog.length > 0) {
					insertParam('catalogName', listCatalog);
				}
			}
		})
	});

	/*var listProviderLink = document.getElementById('provider').getElementsByClassName('category-group-item-link');
	for (const item of listProviderLink) {
		item.addEventListener('click', function(event) {
			if (this.querySelector('.category-group-item-check').checked == false) {
				listProvider.push(this.querySelector('.category-group-item-check').value);
				location.href = `getAllProductSearch?providerName=${listProvider}`
			}
			else {
				for (let i = 0; i < listProvider.length; i++) {
					const element = listProvider[i];
					if (element == this.querySelector('.category-group-item-check').value) {
						listProvider.splice(i, 1);
						location.href = `getAllProductSearch?providerName=${listProvider}`;
					}
				}	
			}
		});
	}*/
} catch (error) {
	console.log(error)
}

function insertParam(key, value) {
	key = encodeURIComponent(key);
	value = encodeURIComponent(value);

	if (value = '') {

	}
	// kvp looks like ['key1=value1', 'key2=value2', ...]
	var kvp = document.location.search.substr(1).split('&');
	let i = 0;

	for (; i < kvp.length; i++) {
		if (kvp[i].startsWith(key + '=')) {
			let pair = kvp[i].split('=');
			pair[1] = value;
			kvp[i] = pair.join('=');
			break;
		}
	}

	if (i >= kvp.length) {
		kvp[kvp.length] = [key, value].join('=');
	}

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
	console.log(kvp);

	// can return this or...
	let params = kvp.join('&');
	console.log(params);


	// reload page with new params
	document.location.search = params;
}

function insertParam2(key1, value1, key2, value2) {
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


	if (i >= kvp.length) {
		kvp[kvp.length] = [key1, value1].join('=');
	}
	if (j >= kvp.length) {
		kvp[kvp.length] = [key2, value2].join('=');
	}

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
	console.log(kvp);

	// can return this or...
	let params = kvp.join('&');
	console.log(params);


	// reload page with new params
	document.location.search = params;
}

function removeParam(key, sourceURL) {
	var rtn = sourceURL.split("?")[0],
		param,
		params_arr = [],
		queryString = (sourceURL.indexOf("?") !== -1) ? sourceURL.split("?")[1] : "";
	if (queryString !== "") {
		params_arr = queryString.split("&");
		for (var i = params_arr.length - 1; i >= 0; i -= 1) {
			param = params_arr[i].split("=")[0];
			if (param === key) {
				params_arr.splice(i, 1);
			}
		}
		
		if (params_arr.length) rtn = rtn + "?" + params_arr.join("&");
	}
	location.href = rtn;
}
