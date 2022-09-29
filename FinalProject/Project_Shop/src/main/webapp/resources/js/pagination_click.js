/**
 * Sự kiện click vào pagination
 */
try {
	var lstPage = document.getElementById('pagination').getElementsByClassName('pagination-item--number');
	lstPage[0].classList.add('pagination-item--active');
	for (const page of lstPage) {
		page.addEventListener('click', function() {
			var kt = this.classList.contains('pagination-item--active');
			if (!kt) {
				this.parentNode.querySelector('.pagination-item--number.pagination-item--active').classList.remove('pagination-item--active');
				this.classList.add('pagination-item--active');
			}
		})
	}

} catch (error) {
	error.log();
}