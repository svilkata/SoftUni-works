(function solve() {
    String.prototype.ensureStart = function (str) {
        let toStr = this.toString();

        if (toStr.startsWith(str)) {
            return toStr;
        } else {
            return str + toStr;
        }
    }

    String.prototype.ensureEnd = function (str) {
        let toStr = this.toString();

        if (toStr.endsWith(str)) {
            return toStr;
        } else {
            return toStr + str;
        }
    }

    String.prototype.isEmpty = function () {
        return this.length == 0;
    }

    String.prototype.truncate = function (n) {
        let toStr = this.toString();

        if (this.length <= n) {
            return toStr;
        }

        if (this.length < 4) {
            let str = toStr.substring(0, this.length - n);
            str = str + ".".repeat(n);
            return str;
        } else {
            const splitted = toStr.split(" ");
            if (splitted.length == 1) {
                return toStr.substr(0, n - 3) + "...";
            } else {
                let result = "";
                for (let i = 0; i < splitted.length; i++) {
                    if (result.length + splitted[i].length <= n - 3) {
                        result += " " + splitted[i];
                    } else {
                        return result.trim() + "...";
                    }
                }

                return result + "...";
            }
        }
    }

    String.format = function (str, ...params) {
        // let result = str.substring(0, str.indexOf(`${0}`));
        let result = str;

        for (let i = 0; i < params.length; i++) {
            result = result.replace(`{${i}}`, params[i]);
        }

        return result;
    }

})();

// let str = 'my string';
// console.log(str);
// str = str.ensureStart('my');
// str = str.ensureStart('hello ');
// str = str.ensureEnd('ringz');
// console.log(str);
// str = str.truncate(1);
// console.log(str);
// str = str.truncate(14);
// str = str.truncate(8);
// str = str.truncate(4);
// str = str.truncate(2);
str = String.format('The {0} {1} fox',
    'quick', 'brown');
console.log(str);
// str = String.format('jumps {0} {1}',
//     'dog');
