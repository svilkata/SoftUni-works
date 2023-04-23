var rootDomElement = document.getElementById('root');
console.dir(rootDomElement);
var rootReactElement = ReactDOM.createRoot(rootDomElement);

// const headingReactElement = React.createElement('h1', {}, 'Hello from React');
// const secondHeadingReactElement = React.createElement('h2', {}, 'Some slogan here');
// const headerReactElement = React.createElement('header', {}, headingReactElement, secondHeadingReactElement);
// console.log(JSON.parse(JSON.stringify(headingReactElement)));

var headerReactElement = React.createElement(
    "header",
    { className: "container" },
    React.createElement(
        "h1",
        null,
        "Hello from React"
    ),
    React.createElement(
        "h2",
        null,
        "Slogan here"
    ),
    React.createElement(
        "p",
        null,
        "Mrrrrrrrr"
    ),
    React.createElement(
        "button",
        null,
        "Click"
    )
);

rootReactElement.render(headerReactElement);