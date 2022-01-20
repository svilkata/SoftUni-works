const { chromium } = require('playwright-chromium');
const { expect } = require('chai');
let browser, page; // Declare reusable variables
describe('E2E tests', async function () {
    this.timeout(60000);

    before(async () => { browser = await chromium.launch({headless: false, slowMo: 2000}); });
    after(async () => { await browser.close(); });
    beforeEach(async () => { page = await browser.newPage(); });
    afterEach(async () => { await page.close(); });

    it('initial load', async () => {
        await page.goto('http://localhost:5500');
        await page.screenshot({ path: 'page.png' });
    });

    it('initial load', async () => {
        await page.goto('http://localhost:5500');

        await page.waitForSelector('.accordion'); //Чакаме докато браузърът не зареди нещо с клас accordion, await спира изпълението на кода надолу докато не се появи исканото

        const content = await page.textContent('#main');
        expect(content).to.contains('Scalable Vector Graphics');
        expect(content).to.contains('Open standard');
        expect(content).to.contains('Unix');
        expect(content).to.contains('ALGOL');
    });

    it('more buttons test', async () => {
        await page.goto('http://localhost:5500');
        await page.waitForSelector('.accordion');

        await page.click('text=More');//case insensitive, ще намери първия такъв елемент

        // await page.waitForRequest(); // това няма смисъл, трябва да изчакаме отговора от сървъра
        await page.waitForResponse(/articles\/details/i);

        await page.waitForSelector('.accordion p'); //може да не е нужен този ред
        const visible = await page.isVisible('.accordion p');

        expect(visible).to.be.true;
    });

    it('Less buttons test', async () => {
        await page.goto('http://localhost:5500');
        await page.waitForSelector('.accordion');

        await page.click('text=More');//case insensitive, ще намери първия такъв елемент
        await page.waitForResponse(/articles\/details/i);

        await page.waitForSelector('.accordion p', {state: 'visible'}); 

        await page.click('text=Less');
        const visible = await page.isVisible('.accordion p');

        expect(visible).to.be.false;
    });

    it.only('form input', async () => {
        await page.goto('http://localhost:5500');
        await page.fill('[name="email"]', 'Peter');

        await page.waitForTimeout(60000);
    })
});
















