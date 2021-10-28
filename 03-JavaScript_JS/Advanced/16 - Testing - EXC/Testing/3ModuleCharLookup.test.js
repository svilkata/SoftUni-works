const {expect, assert} = require("chai");
const {lookupChar} = require("./3ModuleCharLookup");

describe("Check Char", () => {
    it("returns undefined", () => {
        expect(lookupChar(1, 1)).to.be.undefined;
        expect(lookupChar({}, 1)).to.be.undefined;
        expect(lookupChar([], 1)).to.be.undefined;
        expect(lookupChar(1, 'asd')).to.be.undefined;
        expect(lookupChar({}, 'asd')).to.be.undefined;
        expect(lookupChar([], 'asd')).to.be.undefined;
        expect(lookupChar("asta la vista baby", 3.3)).to.be.undefined;
    } );

    it("returns an empty string", () => {
        expect(lookupChar("asta la vista baby", -1)).to.be.equal("Incorrect index");
        expect(lookupChar("asta la vista baby", 200)).to.be.equal("Incorrect index");
        
    } );

    it("returning the result/ the letter", () => {
        expect(lookupChar("asta la vista baby", 0)).to.be.equal("a".charAt(0));
        expect(lookupChar("asta la vista baby", 2)).to.be.equal("t".charAt(0));
    } );
});