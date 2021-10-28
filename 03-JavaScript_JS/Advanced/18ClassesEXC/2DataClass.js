class Request{
    constructor(method, uri, version, message){
        this.method = method;
        this.uri = uri;
        this.version = version;
        this.message = message;
        this.response = undefined;
        this.fulfilled = false;
    }
}

// let myData = new Request('GET', 'http://google.com', 'HTTP/1.1', '');
let myData = new Request('GET', 'http://softuni.bg', 'HTTP/1.2', 'new message');
console.log(myData);
