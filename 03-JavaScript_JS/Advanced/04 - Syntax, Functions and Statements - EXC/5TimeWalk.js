/**
 * 
 * @param {Number} steps 
 * @param {Number} footprintMeters 
 * @param {Number} speedKM_H 
 */

function timeToWalk(steps, footprintMeters, speedKM_H) {
    let speedMeterPerSecond = speedKM_H / 3.6;
    let sWayinMeters = footprintMeters * steps;
    let timesTheStudentRest = Math.floor(sWayinMeters / 500);
    let resultSeconds = sWayinMeters / speedMeterPerSecond + timesTheStudentRest * 60;

    let hours = Math.floor(resultSeconds / 3600);
    let minutes = Math.floor(resultSeconds / 60);
    let timeSec = Math.round(resultSeconds - (minutes * 60));


    console.log(`${(hours.toString()).padStart(2, 0)}:${minutes.toString().padStart(2, 0)}:${timeSec.toString().padStart(2, 0)}`);
}

timeToWalk(4000, 0.60, 5);
timeToWalk(2564, 0.70, 5.5);