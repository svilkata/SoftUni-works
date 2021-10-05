function solve() {
  // configure eventListeners
  //select all buttons
  //first button -> table generation
  //second button -> buy furniture
  const table = document.querySelector('.table tbody');
  const [input, output] = Array.from(document.querySelectorAll('textarea'));
  // console.log(input, output);

  const [generateBtn, buyBtn] = Array.from(document.querySelectorAll('button'));
  // console.log(generateBtn, buy);

  generateBtn.addEventListener('click', generate);
  buyBtn.addEventListener('click', buy);


  //# table geneartion
  /**
   * 
   * @param {Event} e 
   */
  function generate(e) {
    //read input JSON and parse it
    //for every array element, create table row
    e.target
    const data = JSON.parse(input.value);

    for (const item of data) {
      const row = document.createElement('tr');

      const imgCell = document.createElement('td');
      const nameCell = document.createElement('td');
      const priceCell = document.createElement('td');
      const decFactorCell = document.createElement('td');
      const checkCell = document.createElement('td');

      const img = document.createElement('img');
      img.src = item.img;
      imgCell.appendChild(img);

      const nameP = document.createElement('p');
      nameP.textContent = item.name;
      nameCell.appendChild(nameP);

      const priceP = document.createElement('p');
      priceP.textContent = item.price;
      priceCell.appendChild(priceP);

      const decP = document.createElement('p');
      decP.textContent = item.decFactor;
      decFactorCell.appendChild(decP);

      const check = document.createElement('input');
      check.type = 'checkbox';
      checkCell.appendChild(check);

      row.appendChild(imgCell);
      row.appendChild(nameCell);
      row.appendChild(priceCell);
      row.appendChild(decFactorCell);
      row.appendChild(checkCell);

      table.appendChild(row);
    }




  }


  //# buy furniture
  function buy(e) {
    // select all checkboxes
    //filter only checked boxes
    //repeat for every selected checkbox
    //-- select parent row
    //-- read item information
    const furniture = Array.from(document.querySelectorAll('input[type="checkbox"]:checked'))
      .map(x => x.parentElement.parentElement)
      .map(r => ({   //слагаме обикновени скоби за да върнем поток от обекти, иначе с къдравите скоби само ще иска return
        name: r.children[1].textContent,
        price: Number(r.children[2].textContent),
        decFactor: Number(r.children[3].textContent),
      }))
      .reduce((accumulator, currentValue, currIndex, initialArray) => {
        accumulator.names.push(currentValue.name);
        accumulator.total += currentValue.price;
        accumulator.decFactor += currentValue.decFactor / initialArray.length;

        return accumulator;
      }, { names: [], total: 0, decFactor: 0 }); //initial value

    const names = [];
    let total = 0;
    let decFactor = 0;

    for (let item of furniture) {
      names.push(item.name);
      total += item.price;
      decFactor += item.decFactor;
    }

    const result = `Bought furniture: ${names.join(", ")}
Total price: ${total.toFixed(2)}
Average decoration factor: ${decFactor / names.length}`;

    //display output
    output.value = result;
  }

}