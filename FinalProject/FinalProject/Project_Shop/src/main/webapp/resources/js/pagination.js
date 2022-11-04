/**
 * 
 */

try {
	$(document).ready(function() {
		$('#pagination .pagination-item--number').click(function() {
			var page = this.value;
			var urlStr = document.location.search.substr(1).split('&');
			if (urlStr == '') {
				location.href = `${nameController}?page=${page}`;
			} else {
				insertParamPage('page', page);
			}
		});
	})

} catch (error) {
	con
}


function insertParamPage(key, value) {
	key = encodeURIComponent(key);
	value = encodeURIComponent(value);

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

	let params = kvp.join('&');

	document.location.search = params;
}