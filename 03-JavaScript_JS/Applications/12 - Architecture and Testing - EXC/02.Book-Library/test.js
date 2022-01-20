const { chromium } = require('playwright-chromium');
const { expect } = require('chai');

let browser, page; // Declare reusable variables]

const mockData = {
    "d953e5fb-a585-4d6b-92d3-ee90697398a0": {
        "author": "J.K.Rowling",
        "title": "Harry Potter and the Philosopher's Stone"
    },
    "d953e5fb-a585-4d6b-92d3-ee90697398a1": {
        "author": "Svetlin Nakov",
        "title": "C# Fundamentals"
    }
};

describe('Tests', async function () {
    this.timeout(6000);

    before(async () => { browser = await chromium.launch(); });
    // before(async () => { browser = await chromium.launch({ headless: false, slowMo: 500 }); });
    after(async () => { await browser.close(); });
    beforeEach(async () => { page = await browser.newPage(); }); //преди всеки тест
    afterEach(async () => { await page.close(); }); //след всеки тест

    it('works', async () => {
        await page.goto('http://localhost:5500');
        await page.screenshot({ path: 'page.png' });
    });

    it('if all books are loaded and displayed', async () => {
        // Две звездички означава хващай всички hostove
        await page.route('**/jsonstore/collections/books*', (rout) => {
            rout.fulfill({
                status: 200,
                headers: {
                    'Access-Control-Allow-Origin': '*',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(mockData)
            });
        });
        await page.goto('http://localhost:5500');

        await page.click('text=Load All Books');

        await page.waitForSelector('text=Harry Potter');

        //селектираме каквото си искаме и го преобразуваме в масив
        const rows = await page.$$eval('tr', rows => rows.map(r => r.textContent.trim())); //$$eval() е добре да го използваме в контролирана среда
        console.log(rows);
        expect(rows[1]).to.contains('Harry Potter');
        expect(rows[1]).to.contains('Rowling');
        expect(rows[2]).to.contains('C# Fundamentals');
        expect(rows[2]).to.contains('Nakov');
    });

    it.only('can create a book', async () => {
        await page.goto('http://localhost:5500');

        // В playwright  > търси едно ниво надолу спрямо предния селектор, докато >> търси в дълбочина навсякъде в елемента
        await page.fill('form#createForm >> input[name="title"]', 'Title A');
        await page.fill('form#createForm >> input[name="author"]', 'Author B');

        const [reqquest] = await Promise.all([
            page.waitForRequest(request => request.method = 'POST'), // чакаме за каквато и да е post заявка
            page.click('form#createForm >> text=Submit')
        ]);

        const data = JSON.parse(reqquest.postData());
        console.log(data);
        expect(data.title).to.equal('Title A');
        expect(data.author).to.equal('Author B');
    });
})