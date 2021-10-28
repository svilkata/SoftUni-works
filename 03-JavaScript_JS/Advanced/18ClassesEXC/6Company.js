class Company {
    constructor() {
        this.departments = {};

    }

    addEmployee(name, salary, position, department) {
        if (!name || !position || !department || !salary || salary < 0) {
            throw new Error("Invalid input!");
        }
        
        if (!this.departments[department]) {
            this.departments[department] = [];
        }

        this.departments[department].push({ name, position, salary });
        return `New employee is hired. Name: ${name}. Position: ${position}`;
    }

    bestDepartment() {
        let currentBest = {
            name: "",
            averSalary: 0,
        };

        for (let deprtKey in this.departments) {
            let averageSalary = 0;

            for (let emplKey in this.departments[deprtKey]) {
                averageSalary += this.departments[deprtKey][emplKey].salary;
            }

            averageSalary = averageSalary / this.departments[deprtKey].length;

            if (currentBest.averSalary < averageSalary) {
                currentBest.name = deprtKey;
                currentBest.averSalary = averageSalary;
            }
        }

        //sorting the best deprtment
        this.departments[currentBest.name].sort((a, b) => {
            // let result = b.salary - a.salary;
            // if (result == 0) {
            //     result = a.name.localeCompare(b.name);
            // }

            // return result;
            return b.salary - a.salary || a.name.localeCompare(b.name);
        })

        //print departments
        let bestDeprtmString = "";
        bestDeprtmString += `Best Department is: ${currentBest.name}\n`;
        bestDeprtmString += `Average salary: ${currentBest.averSalary.toFixed(2)}\n`;
        this.departments[currentBest.name].forEach(empl => {
            // if (this.departments[currentBest.name]) {
                
            // }
            bestDeprtmString += `${empl.name} ${empl.salary} ${empl.position}\n`
        });

        return bestDeprtmString.trim();
    }
}


let c = new Company();
c.addEmployee("Stanimir", 2000, "engineer", "Construction");
c.addEmployee("Pesho", 1500, "electrical engineer", "Construction");
c.addEmployee("Slavi", 500, "dyer", "Construction");
c.addEmployee("Stan", 2000, "architect", "Construction");
c.addEmployee("Stanimir", 1200, "digital marketing manager", "Marketing");
c.addEmployee("Pesho", 1000, "graphical designer", "Marketing");
c.addEmployee("Gosho", 1350, "HR", "Human resources");
console.log(c.bestDepartment());
