/**
 * 
 * @return
 */

try {
    var btnAddQuantity = document.getElementsByClassName('btn--addQuantity');
    var btnSubQuantity = document.getElementsByClassName('btn--subQuantity');
    for (const btnadditem of btnAddQuantity) {
        btnadditem.addEventListener('click',function(){
            var quantity = btnadditem.parentNode.querySelector('.cart__product-quantity-value-input').value;
            var maxquantity = btnadditem.parentNode.querySelector('.maxQuantity').value;
            var totalmoneyProductitems = btnadditem.parentNode.parentNode.querySelector('.totalmoneyProductitems');
            var price = btnadditem.parentNode.querySelector('.priceOutput');
            if(parseInt(quantity) >= 0 && parseInt(quantity) < parseInt(maxquantity)){
                this.parentNode.querySelector('.cart__product-quantity-value-input').value = parseInt(quantity) + 1;
                var totalmoney = parseFloat(price.value) * (parseInt(quantity) + 1);
                totalmoneyProductitems.innerHTML = new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(totalmoney) ;
            }     
        })
    }

    for (const btnsubitem of btnSubQuantity) {
        btnsubitem.addEventListener('click',function(){
            var quantity = btnsubitem.parentNode.querySelector('.cart__product-quantity-value-input').value;
            var maxquantity = btnsubitem.parentNode.querySelector('.maxQuantity').value;
            var totalmoneyProductitems = btnsubitem.parentNode.parentNode.querySelector('.totalmoneyProductitems');
            var price = btnsubitem.parentNode.querySelector('.priceOutput');
            if(parseInt(quantity) > 0 && parseInt(quantity) < parseInt(maxquantity)){
                this.parentNode.querySelector('.cart__product-quantity-value-input').value = parseInt(quantity) - 1;
                var totalmoney = parseFloat(price.value) * (parseInt(quantity) - 1);
                totalmoneyProductitems.innerHTML = new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(totalmoney);
            }     
        })
    }
    
    var lstquantity = document.getElementsByClassName('cart__product-quantity-value-input');
    for (const quantityItem of lstquantity) {
        quantityItem.addEventListener('blur',function(){
            var totalmoneyProductitems = this.parentNode.parentNode.querySelector('.totalmoneyProductitems');
            var price = this.parentNode.querySelector('.priceOutput');
            if(quantityItem.value != ''){
                var totalmoney = parseFloat(price.value) * parseInt(this.value);
                totalmoneyProductitems.innerHTML = new Intl.NumberFormat('en-IN', { maximumSignificantDigits: 3 }).format(totalmoney) + 'đ';
            }  
        });
    }

} catch (error) {
    console.log(error);
}