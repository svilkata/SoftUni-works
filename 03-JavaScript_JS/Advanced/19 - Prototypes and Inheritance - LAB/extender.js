class Person {
    constructor(name, email) {
        this.name = name;
        this.email = email;
    }
    toString() {
        let className = this.constructor.name;
        return `${className} (name: ${this.name}, email: ${this.email})`;
    }
}

class Teacher {
    constructor(name, email, subject) {
        this.name = name;
        this.email = email;
        this.subject = subject;
    }

    toString() {
        let className = this.constructor.name;
        return `${className} (name: ${this.name}, email: ${this.email})`;
    }
}

function extendPrototypeFunct(classDefinition) {
    classDefinition.prototype.species = "Human";
    classDefinition.prototype.toSpeciesString = function () {
        return `I am a ${this.species}. ${this.toString()}`;
    }
}

/*extendPrototypeFunct(Person);
let p = new Person("Pesho", "email@hit.bg");
console.log(p.species);
console.log(p.toSpeciesString());*/


let t = new Teacher("John", "email@hit.bg", "Math");
console.log("before", t.species);
extendPrototypeFunct(Teacher);
console.log("after", t.species);