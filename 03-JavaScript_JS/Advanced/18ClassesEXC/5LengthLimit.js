class Stringer {
    constructor(str, lgth) {
        this.innerString = str;
        this.innerLength = Number(lgth);
    }

    trncLength() {
        if (this.innerLength < 0) {
            this.innerLength = 0;
        }

        if (this.innerLength > this.innerString.length) {
            this.innerLength = this.innerString.length;
        }
    }

    increase(value) {
        this.innerLength += Number(value);
        this.trncLength();
    }

    decrease(value) {
        this.innerLength -= Number(value);
        this.trncLength();
    }

    //overriding
    toString() {
        
        let resultString = "";
        if (this.innerLength <= 0) {
            return "...";
        }

        for (let i = 0; i < Math.min(this.innerLength, this.innerString.length); i++) {
            resultString += this.innerString[i];
        }

        if (this.innerLength < this.innerString.length) {
            resultString += "...";
        }        

        return resultString;
    }
}

let test = new Stringer("Test", 5);
console.log(test.toString()); // Test

test.decrease(3);
console.log(test.toString()); // Te...

test.decrease(5);
console.log(test.toString()); // ...

test.increase(4);
console.log(test.toString()); // Test
