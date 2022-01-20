const homeSection = document.getElementById('homeSection');
homeSection.remove(); //откачаме от DOM дървото - по-добър вариант от скриването

const aboutSection = document.getElementById('aboutSection');
aboutSection.remove(); //откачаме от DOM дървото - по-добър вариант от скриването

export function showHomePage(ctx) {
    ctx.showSection(homeSection);
}

export function showAboutPage(ctx) {
    ctx.showSection(aboutSection);
}