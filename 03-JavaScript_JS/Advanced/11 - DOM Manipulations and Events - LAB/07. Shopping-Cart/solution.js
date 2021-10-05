function solve() {
   document.getElementsByClassName('shopping-cart')[0].addEventListener('click', onClick);
   const cart = [];
   const output = document.getElementsByTagName('textarea')[0];
   output.value = "";

   document.getElementsByClassName('checkout')[0].addEventListener('click', checkoutF);

   function onClick(ev) {
      if (ev.target.tagName == 'BUTTON' && ev.target.classList.contains('add-product')) {
         const product = ev.target.parentNode.parentNode;
         const name = product.querySelector('.product-title').textContent;
         const price = Number(product.querySelector('.product-line-price').textContent);

         cart.push({
            name,
            price
         });

         output.value += `Added ${name} for ${price.toFixed(2)} to the cart.\n`;

      }
   }

   function checkoutF(ev) {
      const products = new Set();
      cart.forEach(p => products.add(p.name));

      const totalPrice = cart.reduce((acc, currProduct) => acc + currProduct.price, 0);

      output.value += `You bought ${[...products].join(", ")} for ${totalPrice.toFixed(2)}.`;
   }
}