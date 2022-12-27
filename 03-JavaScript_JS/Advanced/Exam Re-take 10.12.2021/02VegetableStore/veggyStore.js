class VegetableStore {
    constructor(owner, location) {
        this.owner = owner;
        this.location = location;
        this.availableProducts = [];//array of strings all info
        // this.setAvailableProducts = new Set();
        // this.totalPrice = 0.00; //the purchases of all customers 
    }

    checkContainsProductTypeInAvailableProducts(typeToCompare){
        for (const currProd of this.availableProducts) {
            if (currProd.type == typeToCompare) {
                return currProd;
            }
        }

        return false;
    }

    loadingVegetables(vegetables) {
        for (let veg of vegetables) {
            let [type, quantity, price] = veg.split(" ");

            let isInList = this.checkContainsProductTypeInAvailableProducts(type);
            if (!isInList) {
                this.availableProducts.push({type : type, quantity: quantity, price :price});
            } else {
                isInList.quantity = Number(isInList.quantity) + Number(quantity) + "";
                if (Number(isInList.price) < Number(price)) {
                    isInList.price = price;
                }
            }                        
        }

        let result = [];
        this.availableProducts.forEach(pr => result.push(pr.type));

        return "Successfully added " + result.join(", ");
    }

    buyingVegetables(selectedProducts) {
        let totalPrice = 0.00;
        for (const productToBuyFrom of selectedProducts) {
            let [type, quantity] = productToBuyFrom.split(" ");
            let isInList = this.checkContainsProductTypeInAvailableProducts(type);
            if (!isInList) {
                throw new Error(`${type} is not available in the store, your current bill is $${totalPrice.toFixed(2)}.`);
            } else {
                if (Number(isInList.quantity) < quantity) {
                    throw new Error(`The quantity ${quantity} for the vegetable ${type} is not available in the store, your current bill is $${totalPrice.toFixed(2)}.`);
                }

                totalPrice += Number(quantity) * Number(isInList.price);
                isInList.quantity = Number(isInList.quantity) - quantity + "";                
            }
        }

        return `Great choice! You must pay the following amount $${totalPrice.toFixed(2)}.`;
    }

    rottingVegetable (type, quantity){
        let isInList = this.checkContainsProductTypeInAvailableProducts(type);
        if (!isInList) {
            throw new Error(`${type} is not available in the store.`);
        } else {
            if (Number(isInList.quantity) < quantity) {
                isInList.quantity = "0";
                return `The entire quantity of the ${type} has been removed.`;
            } else {
                isInList.quantity = Number(isInList.quantity) - quantity + "";
                return `Some quantity of the ${type} has been removed.`;
            }            
        }
    }

    revision () {
        let result = "Available vegetables:\n";
        this.availableProducts
        .sort((f, s) => Number(f.price) - Number(s.price))
        .forEach(pr => {
            const {type, quantity, price} = pr;
            result += `${type}-${quantity}-$${price}\n`;
        });

        result += `The owner of the store is ${this.owner}, and the location is ${this.location}.`;

        return result;

    }

    
}

// let vegStore = new VegetableStore("Jerrie Munro", "1463 Pette Kyosheta, Sofia");
// console.log(vegStore);
// console.log(vegStore.loadingVegetables(["Okra 2.5 3.5", "Beans 10 2.8", "Celery 5.5 2.2", "Celery 0.5 2.5"]));


// let vegStore = new VegetableStore("Jerrie Munro", "1463 Pette Kyosheta, Sofia");
// console.log(vegStore.loadingVegetables(["Okra 2.5 3.5", "Beans 10 2.8", "Celery 5.5 2.2", "Celery 0.5 2.5"]));
// console.log(vegStore.buyingVegetables(["Okra 1"]));
// console.log(vegStore.buyingVegetables(["Beans 8", "Okra 1.5"]));
// console.log(vegStore.buyingVegetables(["Banana 1", "Beans 2"]));

// let vegStore = new VegetableStore("Jerrie Munro", "1463 Pette Kyosheta, Sofia");
// console.log(vegStore.loadingVegetables(["Okra 2.5 3.5", "Beans 10 2.8", "Celery 5.5 2.2", "Celery 0.5 2.5"]));
// console.log(vegStore.rottingVegetable("Okra", 1));
// console.log(vegStore.rottingVegetable("Okra", 2.5));
// console.log(vegStore.buyingVegetables(["Beans 8", "Okra 1.5"]));

let vegStore = new VegetableStore("Jerrie Munro", "1463 Pette Kyosheta, Sofia");
console.log(vegStore.loadingVegetables(["Okra 2.5 3.5", "Beans 10 2.8", "Celery 5.5 2.2", "Celery 0.5 2.5"]));
console.log(vegStore.rottingVegetable("Okra", 1));
console.log(vegStore.rottingVegetable("Okra", 2.5));
console.log(vegStore.buyingVegetables(["Beans 8", "Celery 1.5"]));
console.log(vegStore.revision());
