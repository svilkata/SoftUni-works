export function updateScoreboard(newResult) { … }
export const homeTeam = 'Tigers';

export { addResult, homeTeam as host };

(function (scope) {
    const selector = 'loading';
    const loadingElement = document.querySelector(selector);
    const show = () => loadingElement.style.display = '';
    const hide = () => ladingElement.style.display = 'none';
    // Only this is visible to the global scope
    scope.loading = { show, hide };
})(window);




