class Human {
    constructor(firstName, age){
        this.name = firstName;
        this.age = age;
    }

    sing() {
        console.log(`${this.name} - lqlqlql`);
    }
}

let pers1 = new Human('Ivan', 28);
let pers2 = new Human('Gosho', 30);
pers1.sing();
pers2.sing();















