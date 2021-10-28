function Human(name) {
    this.name = name;
}

Human.prototype.printName = function () {
    console.log(this.name);
}

function Person(name) {
    Human.call(this, name);  //super(name)
}

Person.prototype = Object.create(Human.prototype); //конструктор функцията Person наследява конструктор функцията Human

let human = new Human("Ivan");
human.printName(); //Ivan

let person = new Person("Ivan");
person.printName(); //Ivan