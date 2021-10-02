function createRecord(name, population, trеasury) {
    return result = {
        name,
        population,
        trеasury,
        taxRate : 10,
        collectTaxes : function() {
            this.treasury += Math.floor(this.population * this.taxRate);
        },
        applyGrowth: function (percentage) {
            this.population += Math.floor (this.population * percentage / 100);

        },
        applyRecession: function(percentage) {
            this.trеasury -= Math.ceil(this.trеasury * percentage / 100);
        },
    }
}

const city =
  createRecord('Tortuga',
  7000,
  15000);
city.collectTaxes();
console.log(city.trеasury);
city.applyGrowth(5);
console.log(city.population);


/*city.collectTaxes();
console.log(city.trеasury);

city.applyGrowth(5);
console.log(city.population);

city.applyRecession(5);
console.log(city.trеasury);*/

