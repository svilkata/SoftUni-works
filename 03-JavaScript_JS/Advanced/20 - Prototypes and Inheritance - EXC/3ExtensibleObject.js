function extensibleObject() {
    return {
        extend: function(template) {
            let objProto = Object.getPrototypeOf(this);
            let templateEntries = Object.entries(template);
            for(const [key, value] of templateEntries){
                if (typeof value == "function") {
                    objProto[key] = value; //към прототипа на текущия обект
                } else {
                    this[key] = value; //към текущия обект
                }
            }
        }
    }
}

const template = {
    extensionMethod: function () { },
    extensionProperty: 'someString'
}

const myObj = extensibleObject();
myObj.extend(template);
console.log(myObj);
