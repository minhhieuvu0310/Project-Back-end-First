/**
 * 
 */
 try{
	var btnSearch = document.getElementById('btn--searchKey');
	btnSearch.addEventListener('click',function(){
		var KeySearch = document.getElementById('KeySearch').value;
		var urlStr = document.location.search.substr(1).split('&');
		if(urlStr == ''){
			location.href = `getAllProductSearch?KeySearch=${KeySearch}`;
		}else{
			insertParam('KeySearch',KeySearch);
		}
	})
	
	
}catch (error) {
	console.log(error)
}
