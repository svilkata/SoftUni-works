const {expect} = require('chai');
const rgbToHexColor = require('./rgb-to-hex');

describe('RGB converter', () => {
    describe('Happy path', () => {
        it('converts white', () => {
            expect(rgbToHexColor(255, 255, 255)).to.equal('#FFFFFF');
            expect(() => rgbToHexColor()).to.throw(Error);
            
        });
    
        it('converts black', () => {
            expect(rgbToHexColor(0, 0, 0)).to.equal('#000000')
        });
    
        it('converts SoftUni dark black', () => {
            expect(rgbToHexColor(35, 68, 101)).to.equal('#234465');
        });
    })

    describe('Invalid parameter', () => {
        it('returns undefined for missing parameters', () => {
            expect(rgbToHexColor(255)).to.be.undefined;
        });

        it('returns undefined for values out of range', () => {
            expect(rgbToHexColor(-1, -1, -1)).to.be.undefined;
        });

        it('returns undefined for values out of range', () => {
            expect(rgbToHexColor(256, 256, 256)).to.be.undefined;
        });

        it('returns undefined for invalid parameter type', () => {
            expect(rgbToHexColor('5', 256, 'ass')).to.be.undefined;
        });
    })
    
})