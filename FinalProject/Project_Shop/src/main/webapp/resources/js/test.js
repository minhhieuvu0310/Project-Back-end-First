/**
 * Sự kiện click vào pagination
 */
var sortBy;
var lstfiltercontrol = document.getElementById('home-filter__control').getElementsByClassName('home-filter__btn');
for (const btnFilter of lstfiltercontrol) {
	btnFilter.addEventListener('click', function() {
		sortBy = this.value;
		var urlStr = document.location.search.substr(1).split('&');
		if(urlStr == ''){
			location.href = `getAllProductSearch?sortBy=${sortBy}`
		}else{
			insertParam('sortBy', sortBy);
		}


	});
}

for (const option of document.getElementsByClassName('home-filter__sort-item')) {
	option.addEventListener('click', function() {
		if (!this.classList.contains('home-filter__sort-item--active')) {
			var priceShortest = txtpriceShortest.value;
			var priceTallest = txtpriceTallest.value;
			var kt = this.parentNode.querySelector('.home-filter__sort-item.home-filter__sort-item--active');
			var sortByPrice;
			var sortByPrice_str;
			var iconSortPrice_str;
			if (kt == null) {
				sortByPrice_str = ((this.id == 'desc') ? 'Giảm dần' : 'Tăng Dần');
				iconSortPrice_str = ((this.id == 'desc') ? '<i class="fas fa-sort-amount-down-alt"></i>' : '<i class="fas fa-sort-amount-up-alt"></i>');
				sortby = 'Price';
				sortByPrice = this.id;
				console.log(sortByPrice_str + " " + iconSortPrice_str)
			}
			else {
				sortByPrice_str = ((this.id == 'desc') ? 'Giảm dần' : 'Tăng Dần');
				iconSortPrice_str = ((this.id == 'desc') ? '<i class="fas fa-sort-amount-down-alt"></i>' : '<i class="fas fa-sort-amount-up-alt"></i>');
				sortby = 'Price';
				sortByPrice = this.id;
				console.log(sortByPrice_str + " " + iconSortPrice_str)
			}

		}

	});
}


function insertParam(key, value) {
	key = encodeURIComponent(key);
	value = encodeURIComponent(value);

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

