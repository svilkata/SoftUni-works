import page from '//unpkg.com/page/page.mjs';

const main = document.querySelector('main');

function homePage(ctx, next) {
    console.log(ctx);
    console.log(next);
    main.innerHTML = '<h2>Home page </h2><p>Welcome to our site</p>';
}

function catalogPage() {
    main.innerHTML = '<h2>Catalog</h2><p>List of items</p><a href="/catalog/1234">Product</a>';
}

function detailsPage(ctx) {
    console.log(ctx);
    main.innerHTML = '<h2>Product</h2><p>Product details</p><button>Buy now</button>';
    document.querySelector('button').addEventListener('click', () => {
        page.redirect('/checkout');
    });
}

function checkoutPage() {
    main.innerHTML = '<h2>Cart details</h2><p>Products in cart</p>';
}

function aboutPage() {
    main.innerHTML = '<h2>About us</h2><p>Contact: +15184388 215</p>';
}

page('/home', homePage);
page('/catalog', catalogPage);
// page('/catalog/:productNumber', detailsPage); //хвани адрес /catalog и след него има нещо още. Това означава :id
page('/catalog/:id', detailsPage);  //вложени множество параметри

page('/checkout', checkoutPage);
page('/about', aboutPage);

page.redirect('/', '/home'); //ако въведем главния адрес без / или главния адрес с / само, то отиди на homePage
page.start(); //стартираме го, то само ще си потърси един вид функцията showContent

  













