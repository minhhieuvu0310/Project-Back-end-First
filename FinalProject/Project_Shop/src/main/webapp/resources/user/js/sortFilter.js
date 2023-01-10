/**
 * Sự kiện click vào pagination
 */
var sortBy;
var lstfiltercontrol = document.getElementById('home-filter__control').getElementsByClassName('home-filter__btn');
for (const btnFilter of lstfiltercontrol) {
	btnFilter.addEventListener('click', function() {
		sortBy = this.value;
		var urlStr = document.location.search.substr(1).split('&');
		if (urlStr == '') {
			location.href = `getAllProductSearch?sortBy=${sortBy}`
		} else {
			insertParam2('sortBy', sortBy, 'sortByPrice', '');
		}


	});
}

for (const option of document.getElementsByClassName('home-filter__sort-item')) {
	option.addEventListener('click', function() {
		sortBy = 'Price';
		sortByPrice = this.id;
		var urlStr = document.location.search.substr(1).split('&');
		if (urlStr == '') {
			location.href = `getAllProductSearch?sortBy=${sortBy}&sortByPrice=${sortByPrice}`
		} else {
			insertParamSortPrice('sortByPrice', sortByPrice, 'sortBy', sortBy);
		}
	});
}

function insertParamSortPrice(key1, value1, key2, value2) {
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
		if (i == j) {
			if (i >= kvp.length) {
				kvp[kvp.length] = [key1, value1].join('=');
			}
			if (j >= kvp.length - 1) {
				kvp[kvp.length] = [key2, value2].join('=');
			}
		}else if(i != j){
			if (i >= kvp.length) {
				kvp[kvp.length] = [key1, value1].join('=');
			}
			if (j >= kvp.length) {
				kvp[kvp.length] = [key2, value2].join('=');
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
	console.log(params);
	if (params == '') {
		location.href = 'home';
	} else {
		location.href = urlNew;

	}
	
}
