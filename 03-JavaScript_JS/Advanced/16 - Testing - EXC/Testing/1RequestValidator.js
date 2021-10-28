function fn(obj){
    const validMethods = ["GET", "POST", "DELETE", "CONNECT"];
    const validVersions = ["HTTP/0.9", "HTTP/1.0", "HTTP/1.1", "HTTP/2.0"];
    const uriRegex = /(^[\w.+]+$)/;
    const messageRegex = /([<>\\&'")])/;
    
    if (obj.method == undefined || !validMethods.includes(obj.method)) {
        throw new Error(`Invalid request header: Invalid Method`);
    }

    if (obj.uri == undefined || obj.uri == "" || !uriRegex.test(obj.uri)) {
        throw new Error(`Invalid request header: Invalid URI`);
    }

    if (obj.version == undefined || !validVersions.includes(obj.version)) {
        throw new Error(`Invalid request header: Invalid Version`);
    }

    if (obj.message == undefined || messageRegex.test(obj.message)) {
        throw new Error(`Invalid request header: Invalid Message`);
    }

    return obj;
}

console.log(
fn({
    method: 'GET',
    uri: 'svn.public.catalog',
    version: 'HTTP/1.1',
 
  }));




  