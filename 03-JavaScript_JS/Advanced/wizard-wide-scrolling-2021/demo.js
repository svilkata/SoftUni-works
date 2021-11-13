const element = document.getElementById('some-element-you-want-to-animate');
let start, previousTimeStamp;

function step(timestamp) {
  if (start === undefined)
    start = timestamp;
  const elapsed = timestamp - start;

  //muhaha

  if (previousTimeStamp !== timestamp) {
    // Math.min() is used here to make sure the element stops at exactly 200px
    const count = Math.min(0.1 * elapsed, 200);
    element.style.transform = 'translateX(' + count + 'px)';
  }

  if (elapsed < 2000) { // Stop the animation after 2 seconds
    previousTimeStamp = timestamp
    window.requestAnimationFrame(step);
  }

  console.log("abv");
}

window.requestAnimationFrame(step);