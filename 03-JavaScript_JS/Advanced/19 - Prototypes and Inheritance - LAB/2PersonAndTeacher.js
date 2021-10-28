function personAndTeacher() {
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

    class Teacher extends Person {
        constructor(name, email, subject) {
            super(name, email);
            // Person.call(this, name, email);
            this.subject = subject;
        }

        //overriding the toString() method
        toString() {
            let baseClassString = super.toString();
            let zzz = baseClassString.substring(0, baseClassString.length-1);   
            return zzz + `, subject: ${this.subject})`;
        }
    }

    class Student extends Person {
        constructor(name, email, course) {
            super(name, email);
            this.course = course;
        }

        //overriding the toString() method
        toString() {
            let baseClassString = super.toString();
            let zzz = baseClassString.substring(0, baseClassString.length-1);   
            return zzz + `, course: ${this.course})`;
        }
    }

    return {
        Person,
        Teacher,
        Student
    }
}


let p = new Person("John", "email@hit.bg");
console.log(p.toString());

let t = new Teacher("John", "email@hit.bg", "Math");
console.log(t.toString());

let s = new Student("John", "email@hit.bg", 7);
console.log(s.toString());


// console.log(t instanceof Teacher); //true
// console.log(t instanceof Person); //true

// let s = new Student("Peter", "peter@abv.bg", 7);
// console.log(s);