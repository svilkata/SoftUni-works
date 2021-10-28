const { expect, assert } = require("chai");
const { mathEnforcer } = require("./4ModuleMathEnforcer");

describe("Test math enforcer functionality", () => {
    describe("addFive", () => {
        it("expect undefined on wrong input type", () => {
            expect(mathEnforcer.addFive('asd')).to.be.undefined;
            // expect(mathEnforcer.addFive({})).to.be.undefined;
            expect(mathEnforcer.addFive([])).to.be.undefined;
            expect(mathEnforcer.addFive([1])).to.be.undefined;
            expect(mathEnforcer.addFive('5')).to.be.undefined;
            expect(mathEnforcer.addFive(undefined)).to.be.undefined;
        });

        it("expect proper number outcome", () => {
            expect(mathEnforcer.addFive(5)).to.be.equal(10);
            expect(mathEnforcer.addFive(-4)).to.be.equal(1);
            expect(mathEnforcer.addFive(-20)).to.be.equal(-15);
            expect(mathEnforcer.addFive(5.002)).to.be.closeTo(10.002, 0.01);
            expect(mathEnforcer.addFive(-10.002)).to.be.closeTo(-5.002, 0.01);
        });
    });

    describe("subtractTen", () => {
        it("expect undefined on wrong input type", () => {
            expect(mathEnforcer.subtractTen('asd')).to.be.undefined;
            // expect(mathEnforcer.subtractTen({})).to.be.undefined;
            expect(mathEnforcer.subtractTen([])).to.be.undefined;
            expect(mathEnforcer.subtractTen([2])).to.be.undefined;
            expect(mathEnforcer.subtractTen('5')).to.be.undefined;
            expect(mathEnforcer.subtractTen(undefined)).to.be.undefined;
        });

        it("expect proper number outcome", () => {
            expect(mathEnforcer.subtractTen(5)).to.be.equal(-5);
            expect(mathEnforcer.subtractTen(20)).to.be.equal(10);
            expect(mathEnforcer.subtractTen(-4)).to.be.equal(-14);
            expect(mathEnforcer.subtractTen(15.002)).to.be.closeTo(5.002, 0.01);
            expect(mathEnforcer.subtractTen(-15.002)).to.be.closeTo(-25.002, 0.01);
        });
    });

    describe("sum", () => {
        it("expect undefined on wrong input type", () => {
            expect(mathEnforcer.sum('asd')).to.be.undefined;
            expect(mathEnforcer.sum(5)).to.be.undefined;
            expect(mathEnforcer.sum({})).to.be.undefined;
            expect(mathEnforcer.sum([])).to.be.undefined;
            expect(mathEnforcer.sum('5')).to.be.undefined;
            expect(mathEnforcer.sum('asd', 'asd')).to.be.undefined;
            expect(mathEnforcer.sum('asd', 'asd')).to.be.undefined;
            expect(mathEnforcer.sum(5, 'asd')).to.be.undefined;
            expect(mathEnforcer.sum('zzz', -3)).to.be.undefined;
            expect(mathEnforcer.sum(4, {})).to.be.undefined;
            expect(mathEnforcer.sum({}, 5)).to.be.undefined;
            expect(mathEnforcer.sum({}, {})).to.be.undefined;
            expect(mathEnforcer.sum([], [])).to.be.undefined;
            expect(mathEnforcer.sum([10, 10])).to.be.undefined;
            expect(mathEnforcer.sum(8, [])).to.be.undefined;
            expect(mathEnforcer.sum([], 7)).to.be.undefined;
            expect(mathEnforcer.sum('5', 5)).to.be.undefined;
            expect(mathEnforcer.sum(undefined, 8)).to.be.undefined;
        });

        it("expect proper number outcome", () => {
            expect(mathEnforcer.sum(5, 4)).to.be.equal(9);
            expect(mathEnforcer.sum(5, -4)).to.be.equal(1);
            expect(mathEnforcer.sum(-4, 5)).to.be.equal(1);
            expect(mathEnforcer.sum(-4, -3)).to.be.equal(-7);
            expect(mathEnforcer.sum(15.002, 1)).to.be.closeTo(16.002, 0.01);
            expect(mathEnforcer.sum(15.002, 3.003)).to.be.closeTo(18, 0.01);
            it('should return sum when both arguments are numbers [-1.1,-1.1]', () => {
                assert.equal(mathEnforcer.sum(-1.1, -1.1), -2.2);
            });
            it('should return sum when both arguments are numbers [1.1,1.1]', () => {
                assert.equal(mathEnforcer.sum(1.1, 1.1), 2.2);
            });
        });
    });
});