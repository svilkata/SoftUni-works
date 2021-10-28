class Hex {
    constructor(num) {
        this.value = Number(num);
    }

    valueOf() {
        return this.value;
    }

    //override
    toString() {
        return "0x" + this.value.toString(16).toUpperCase();
    }

    plus(value){
        this.value += value;
        return this;
    }

    minus(value){
        this.value -= value;
        return this;
    }

    static parse(hexValue){
        return parseInt(hexValue, 16);
    }
}

// module.exports = {Hex};

// let FF = new Hex(255);
// console.log(FF.toString());
// // console.log(FF.valueOf() + 1 == 256);
// let a = new Hex(10);
// let b = new Hex(5);
// let c = new Hex(155);
// let act2 = a.plus(c).toString();
// console.log(act2);
// console.log(a.plus(b).toString());
// console.log(a.plus(b).toString()==='0xF');
// console.log(Hex.parse('AAA'));

// let Hex = result;
// let FF = new Hex(255);
// assert.instanceOf(FF,Hex);
// assert.equal(FF.valueOf(),255);

// let act = FF.toString();
// let exp = "0xFF";
// assert.equal(act,exp);

// assert.isTrue(FF.valueOf() -1  == 254);
let a = new Hex(10);
let b = new Hex(5);
let c = new Hex(155);
// let act2 = a.plus(c).toString();
// let exp2 = "0xA5";
// assert.equal(act2,exp2);
let act3 = a.minus(b).toString();
console.log(act3);
// let exp3 = "0x5";
// assert.equal(act3,exp3);
