var canvasElement = document.getElementById('canvas');
var width = 800;
var height = 600;
canvasElement.width = width;
canvasElement.height = height;
var context = canvasElement.getContext('2d');
var size = 0;
var clicked = false;
var mouse = [0, 0];
var increasing = true;
var circles = [];
var currentCircle = null;
var animationFrame = 0;

function drawCircle(circle) {
    context.save();
    context.beginPath();
    context.arc(circle.x, circle.y, circle.r, 0, 2 * Math.PI);
    context.fillStyle = 'blue';
    context.fill();
    context.restore();
}

function clear() {
    context.clearRect(0, 0, width, height);
}

canvasElement.addEventListener('mousedown', (e) => {
    if (e.button == 0) {
        clicked = true;
    } else if (e.button == 1) {
        console.log('hello');
        clicked = false;
        e.preventDefault();
    }
});

canvasElement.addEventListener('mouseup', (e) => {
    if (e.button == 0) {
        increasing = true;
        size = 0;
        clicked = false;
        circles.push(currentCircle);
    }
});

canvasElement.addEventListener('mousemove', (e) => {
    mouse = [e.offsetX, e.offsetY];
});

function update() {
    if (clicked) {
        if (increasing) {
            if (size >= 100) {
                increasing = false;
            } else {
                size++;
            }
        } else {
            if (size <= 0) {
                increasing = true;
            } else {
                size--;
            }
        }

        currentCircle = {
            x: mouse[0],
            y: mouse[1],
            r: size
        };
    } else {
        currentCircle = null;
    }
}
    
function draw() {
    clear();

    for (var circle of circles) {
        drawCircle(circle);
    }

    if (currentCircle) {
        drawCircle(currentCircle);
    }
}

function animate() {
    animationFrame = window.requestAnimationFrame(run);
}

function run() {
    update();
    draw();
    animate();
}

run();
