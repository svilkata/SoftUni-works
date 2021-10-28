const { expect, assert } = require('chai');
const isSymmetric = require('./symmetry.js');
const sum = require('./sumNumbers.js');

describe('Symmetry Checker', () => {
    it('return true for symmetric array', () => {
        expect(isSymmetric([1, 2, 2, 1])).to.be.true;
    });

    it('returns false for non-symmetric array', () => {
        expect(isSymmetric([1, 2, 3])).to.be.false;
    });

    it('Returns false for non-array', () => {
        expect(isSymmetric(5)).to.be.false;
    });

    it('Returns false for type-different symmetric array', () => {
        expect(isSymmetric([1, 2, '1'])).to.be.false;
    });

    //Overloading of tests
    it('return true for symmetric array with odd elements', () => {
        expect(isSymmetric([1, 2, 3, 2, 1])).to.be.true;
    });

    it('return true for symmetric array with strings', () => {
        expect(isSymmetric(['ab', 'c', 'ab'])).to.be.true;
    });
})

describe('Sum checker', () => {
    it("sum it baby", () => {
        assert.equal(sum([1, 2, 3]), 6);
        // expect(sum([1,2,3])) == 6 ;
    })

    it("sum it baby", () => {
        // assert.equal(sum([1, 2, 3]), 6);
        expect(sum(1), 6);
    })
})