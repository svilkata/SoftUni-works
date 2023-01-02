import pageNumber from './dep-for-page-2';

function call() {
    console.log(`Hello from page ${pageNumber}`);
    console.log('Hello from page-2.js');
}



export default {
    call
};