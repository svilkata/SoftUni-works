const { expect, assert } = require('chai');
const isSymmetric = require('./symmetry.js');

describe('Symmetry Checker', () => {
    it('return true for symmetric array', () => {
        expect(isSymmetric[1, 2, 2, 1]).to.be.true;
    })
})