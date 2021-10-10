/**
 * 
 * @param {function} area 
 * @param {function} vol 
 * @param {string} input 
 */
function solve(area, vol, input) {
    // console.log(JSON.parse(input));
    const cubes = JSON.parse(input);
    console.log(cubes);
    const result = [];

    for (const cube of cubes) {
        const cubeArea = area.apply(cube);  // --> cube.area()
        const cubeVolume = vol.apply(cube);

        result.push({
            area: cubeArea,
            volume: cubeVolume,
        });
    }

    return result;
}

const dataJSON = `[
    {"x":"1","y":"2","z":"10"},
    {"x":"7","y":"7","z":"10"},
    {"x":"5","y":"2","z":"10"}
    ]`;

console.log(solve(area, vol, dataJSON));

function area() {
    return Math.abs(this.x * this.y);
};

function vol() {
    return Math.abs(this.x * this.y * this.z);
};
