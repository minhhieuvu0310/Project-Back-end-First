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
			insertParam2('sortBy', sortBy , 'sortByPrice','');
		}


	});
}

for (const option of document.getElementsByClassName('home-filter__sort-item')) {
	option.addEventListener('click', function() {
		sortBy = 'Price';
		sortByPrice = this.id;
		var urlStr = document.location.search.substr(1).split('&');
		if(urlStr == ''){
			location.href = `getAllProductSearch?sortBy=${sortBy}&sortByPrice=${sortByPrice}`
		}else{
			insertParam2('sortBy', sortBy,'sortByPrice',sortByPrice);
		}
	});
}




