/**
 * 
 */
 try {
    var sortby;
    var lstfiltercontrol = document.getElementById('home-filter__control').getElementsByClassName('home-filter__btn');
    for (const btnFilter of lstfiltercontrol) {
        btnFilter.addEventListener('click',function(){
            var kt = this.classList.contains('btn--primary');
            if(!kt){
                this.parentNode.querySelector('.home-filter__btn.btn--primary.btn').classList.remove('btn--primary');
                this.classList.add('btn--primary');
                sortby = this.value;
            }
        });
    }

    for (const option of document.getElementsByClassName('home-filter__sort-item')) {
        option.addEventListener('click',function(){
            
            if(!this.classList.contains('home-filter__sort-item--active')){
                var kt = this.parentNode.querySelector('.home-filter__sort-item.home-filter__sort-item--active');
                var sortByPrice;
                if(kt == null){
                    this.classList.add('home-filter__sort-item--active');
                    var sortByPrice_str = ((this.id == 'desc')? 'Giảm dần' : 'Tăng Dần');
                    var iconSortPrice_str = ((this.id == 'desc')? '<i class="fas fa-sort-amount-down-alt"></i>' : '<i class="fas fa-sort-amount-up-alt"></i>');
                    this.parentNode.parentNode.parentNode.querySelector('.home-filter__sort-lable').innerHTML = sortByPrice_str;
                    this.parentNode.parentNode.parentNode.querySelector('.home-filter__sort-icon').innerHTML = iconSortPrice_str;
                    sortby = 'Price';
                    sortByPrice = this.id;
                    // console.log(sortby + " " + sortByPrice)
                }
                else{
                    this.parentNode.querySelector('.home-filter__sort-item.home-filter__sort-item--active').classList.remove('home-filter__sort-item--active');
                    this.classList.add('home-filter__sort-item--active');
                    var sortByPrice_str = ((this.id == 'desc')? 'Giảm dần' : 'Tăng Dần');
                    var iconSortPrice_str = ((this.id == 'desc')? '<i class="fas fa-sort-amount-down-alt"></i>' : '<i class="fas fa-sort-amount-up-alt"></i>');
                    this.parentNode.parentNode.parentNode.querySelector('.home-filter__sort-lable').innerHTML = sortByPrice_str;
                    this.parentNode.parentNode.parentNode.querySelector('.home-filter__sort-icon').innerHTML = iconSortPrice_str;
                    sortby = 'Price';
                    sortByPrice = this.id;
                    // console.log(sortby + " " + sortByPrice)
                }
            }
            
        }) 
        console.log(sortby)
    }
} catch (error) {
    error.log();
}