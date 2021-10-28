const { expect } = require('chai');
const { createCalculator } = require('./addSubtract');

describe('Summator', () => {
    let instance = null;

    beforeEach(() => {
        instance = createCalculator();
    }
    );

    it('Has all methods', () => {
        expect(instance).to.has.ownProperty('add');
        expect(instance).to.has.ownProperty('subtract');
        expect(instance).to.has.ownProperty('get');
    });

    it('Starts empty', () => {
        expect(instance.get()).to.equal(0);
           });

    it('Adds single number', () => {
        instance.add(1);
        expect(instance.get()).to.equal(1);
    });

    it('Adds multiple numbers', () => {
        instance.add(1);
        instance.add(2);
        expect(instance.get()).to.equal(3);
    });

    it('Subtracts single number', () => {
        instance.subtract(1);
        expect(instance.get()).to.equal(-1);
    });

    it('Subtracts multiple numbers', () => {
        instance.subtract(1);
        instance.subtract(2);
        expect(instance.get()).to.equal(-3);
    });

    it('Adds and subtracts numbers', () => {
        instance.add(1);
        instance.subtract(2);
        expect(instance.get()).to.equal(-1);
    });

    it('Works with numbers as Strings', () => {
        instance.add('1');
        instance.subtract('2');
        expect(instance.get()).to.equal(-1);
    });
})