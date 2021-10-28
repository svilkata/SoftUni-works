const {expect, assert} = require("chai");
const {isOddOrEven} = require("./2Module_EvenOdd");

describe("Testing Even or Even", () => {
    it("Test Invalid type of input", ()=> {
        expect(isOddOrEven(1)).to.be.undefined;
        expect(isOddOrEven({})).to.be.undefined;
        expect(isOddOrEven([])).to.be.undefined;
    });

    it("Test Even input", ()=> {
        expect(isOddOrEven("asds")).to.be.equal("even");
        expect(isOddOrEven("")).to.be.equal("even");
        // expect(isOddOrEven("asds")).to.be.string("even");
    });

    it("Test Odd input", ()=> {
        expect(isOddOrEven("asd")).to.be.equal("odd");
        // expect(isOddOrEven("asds")).to.be.string("even");
    });
})