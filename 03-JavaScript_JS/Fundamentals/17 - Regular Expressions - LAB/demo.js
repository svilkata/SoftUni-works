let text = 'Peter: 123 Mark: 456';
let pattern = /(?<username>[A-Z][a-z]+): (\d+)/;
let matches = text.match(pattern);
console.log(matches);
console.log(matches.index);
console.log(matches.input);
console.log(matches.groups.username);
console.log(matches.groups);