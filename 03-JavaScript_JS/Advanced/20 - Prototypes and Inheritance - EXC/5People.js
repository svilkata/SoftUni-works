function solution() {
    class Employee {
        constructor(name, age) {
            if (new.target == Employee) {
                throw new Error('Employee class must be abstract');
            }
            this.name = name;
            this.age = age;
            this.salary = 0;
            this.tasks = [];
            this.currentTask = 0;
        }

        getSalary() {
            return this.salary;
        }

        work() {
            console.log(this.tasks[this.currentTask]);
            this.currentTask++;
            if (this.tasks.length <= this.currentTask) {
                this.currentTask = 0;
            }
        }

        collectSalary() {
           console.log(`${this.name} received ${this.getSalary()} this month.`);
        }
    }

    class Junior extends Employee {
        constructor(name, age) {
            super(name, age);
            this.tasks = [`${this.name} is working on a simple task.`]
        }
    }

    class Senior extends Employee {
        constructor(name, age) {
            super(name, age);
            this.tasks = [`${this.name} is working on a complicated task.`,
            `${this.name} is taking time off work.`, `${this.name} is supervising junior workers.`];
        }
    }

    class Manager extends Employee {
        constructor(name, age, divident) {
            super(name, age);
            this.dividend = 0;
            this.tasks = [`${this.name} scheduled a meeting.`,
            `${this.name} is preparing a quarterly report.`];
        }

        //overriding
        getSalary() {
            return this.salary + this.dividend;
        }

    }


    return { Employee, Junior, Senior, Manager}
}


const classes = solution();

// const junior = new classes.Junior('Ivan', 25);
// junior.work();
// junior.work();
// junior.salary = 5811;
// junior.collectSalary();

// const sinior = new classes.Senior('Alex', 31);
// sinior.work();
// sinior.work();
// sinior.work();
// sinior.work();

// sinior.salary = 12050;
// sinior.collectSalary();

const manager = new classes.Manager('Tom', 55);

manager.salary = 15000;
manager.work();
manager.work();
manager.work();
manager.collectSalary();
manager.dividend = 2500;
manager.collectSalary();
