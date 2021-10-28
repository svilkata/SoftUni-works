(function solve() {
    Array.prototype.last = function () {
        return this[this.length - 1];
    }

    Array.prototype.skip = function (n) {
        const result = [];
        for (let i = n; i < this.length; i++) {
            result.push(this[i]);
        }

        return result;
    }

    Array.prototype.take = function (n) {
        const result = [];
        for (let i = 0; i <= n; i++) {
            result.push(this[i]);
        }

        return result;
    }

    Array.prototype.sum = function () {
        return this.reduce((acc, el) => {
            return acc + el;
        }, 0);
    }

    Array.prototype.average = function () {
        return this.reduce((acc, el) => {
            return acc + el /this.length;
        }, 0);
    }
})();

let arr = [1, 2, 3, 12];
console.log(arr.last());
console.log(arr.skip(2));
console.log(arr.sum());
console.log(arr.average());