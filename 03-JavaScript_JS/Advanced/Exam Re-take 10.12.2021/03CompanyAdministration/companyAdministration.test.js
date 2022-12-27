const { expect } = require('chai');
const companyAdministration = require('./companyAdministration.js');

describe("Tests …", function () {
    describe("hiringEmployee function tests", function () {
        it("notAprogrammer", function () {
            expect(() => companyAdministration.hiringEmployee()).to.throw(Error, 'We are not looking for workers for this position.');
            expect(() => companyAdministration.hiringEmployee("Ivan", "qdkopekach", 3)).to.throw(Error, 'We are not looking for workers for this position.');
        });

        it("EnoughSkilledProgrammer", function () {
            expect(companyAdministration.hiringEmployee("Ivan", "Programmer", 4)).to.be.equal('Ivan was successfully hired for the position Programmer.');
            expect(companyAdministration.hiringEmployee("Ivan", "Programmer", 3)).to.be.equal('Ivan was successfully hired for the position Programmer.');

        });

        it("NotEnoughSkilledProgrammer", function () {
            expect(companyAdministration.hiringEmployee("Ivan", "Programmer", 2)).to.be.equal('Ivan is not approved for this position.');
        });
    });


    describe("calculateSalary", function () {
        it("InputValidationOfHours", function () {
            expect(() => companyAdministration.calculateSalary(-5)).to.throw(Error, 'Invalid hours');
            expect(() => companyAdministration.calculateSalary(true)).to.throw(Error, 'Invalid hours');
            expect(() => companyAdministration.calculateSalary("Pesho")).to.throw(Error, 'Invalid hours');
            expect(() => companyAdministration.calculateSalary([])).to.throw(Error, 'Invalid hours');
            expect(() => companyAdministration.calculateSalary({})).to.throw(Error, 'Invalid hours');
            expect(() => companyAdministration.calculateSalary(-1)).to.throw(Error, 'Invalid hours');
        });

        it("Calculate Salary without bonus", function () {
            expect(companyAdministration.calculateSalary(10)).to.be.equal(150);
            expect(companyAdministration.calculateSalary(50)).to.be.equal(750);
            expect(companyAdministration.calculateSalary(160)).to.be.equal(2400);
        });

        it("Calculate Salary With bonus", function () {
            expect(companyAdministration.calculateSalary(161)).to.be.equal(3415);
        });
    });

    describe("firedEmployee", function () {
        it("InputValidationIfNotArray", function () {
            expect(() => companyAdministration.firedEmployee(-5, 0)).to.throw(Error, 'Invalid input');
            expect(() => companyAdministration.firedEmployee({}, 0)).to.throw(Error, 'Invalid input');
            expect(() => companyAdministration.firedEmployee(null, 0)).to.throw(Error, 'Invalid input');
            expect(() => companyAdministration.firedEmployee(false, 0)).to.throw(Error, 'Invalid input');
            expect(() => companyAdministration.firedEmployee([], 0)).to.throw(Error, 'Invalid input');
            expect(() => companyAdministration.firedEmployee([], -1)).to.throw(Error, 'Invalid input');
            expect(() => companyAdministration.firedEmployee([], 1)).to.throw(Error, 'Invalid input');
        });

        it("InputValidationIndex", function () {
            expect(() => companyAdministration.firedEmployee(["Petar", "Ivan", "George"], -2)).to.throw(Error, 'Invalid input');
            expect(() => companyAdministration.firedEmployee(["Petar", "Ivan", "George"], 3)).to.throw(Error, 'Invalid input');
            expect(() => companyAdministration.firedEmployee(["Petar", "Ivan", "George"], [])).to.throw(Error, 'Invalid input');
            expect(() => companyAdministration.firedEmployee(["Petar", "Ivan", "George"], {})).to.throw(Error, 'Invalid input');
            expect(() => companyAdministration.firedEmployee(["Petar", "Ivan", "George"], null)).to.throw(Error, 'Invalid input');
            expect(() => companyAdministration.firedEmployee(["Petar", "Ivan", "George"], "bai Ivan")).to.throw(Error, 'Invalid input');
            expect(() => companyAdministration.firedEmployee(["Petar", "Ivan", "George"], 33.53)).to.throw(Error, 'Invalid input');
            expect(() => companyAdministration.firedEmployee(["Petar", "Ivan", "George"], -33.53)).to.throw(Error, 'Invalid input');
        });

        it("firing that person", function () {
            expect(companyAdministration.firedEmployee(["Petar", "Ivan", "George"], 2)).to.be.equal("Petar, Ivan");
            expect(companyAdministration.firedEmployee(["Petar", "Ivan", "George"], 1)).to.be.equal("Petar, George");
        });
    });


});
