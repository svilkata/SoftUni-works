function create(words) {
   const content = document.getElementById('content');
   content.addEventListener('click', reveal);

   //create <div>
   for (const word of words) {
      const div = document.createElement('div');
      const para = document.createElement('p');
      para.textContent = word;
      para.style.display = 'none';
      div.appendChild(para);
      // div.addEventListener('click', reveal);

      content.appendChild(div);

      function reveal(ev) {
         if (ev.target.tagName == 'DIV' && ev.target != content) {
            ev.currentTarget.children[0].style.display = '';
         }
      }
   }

   //create <p>

   //set<p> content

   //configure <p> to be hidden (display: none)

   //add <p> to <div>

   //add <div> tp page

   //configure <div> eventListener

}