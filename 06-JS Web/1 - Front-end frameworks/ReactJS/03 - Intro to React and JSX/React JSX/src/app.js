const rootDomElement = document.getElementById('root');
console.dir(rootDomElement);
const rootReactElement = ReactDOM.createRoot(rootDomElement);

// const headingReactElement = React.createElement('h1', {}, 'Hello from React');
// const secondHeadingReactElement = React.createElement('h2', {}, 'Some slogan here');
// const headerReactElement = React.createElement('header', {}, headingReactElement, secondHeadingReactElement);
// console.log(JSON.parse(JSON.stringify(headingReactElement)));

const headerReactElement = (
    <header className="container">
        <h1>Hello from React</h1>
        <h2>Slogan here</h2>
        <p>Mrrrrrrrr</p>

        <button>Click</button>
    </header>
);

rootReactElement.render(headerReactElement);
