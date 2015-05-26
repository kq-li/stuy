var animating = false;
var credits = false;

function initialize() {
  document.getElementById('enter').onclick = function() {                                     
    showContainerEl(document.getElementById('content'));
  };
  var pathEl = document.getElementsByClassName('path-text');
  for (var i = 0; i < pathEl.length; i++) {
    pathEl[i].onclick = function(j) { return function() { showPath(j + 1); }; }(i);
  }
  document.getElementById('nav-home').onclick = function() {
    if (!animating) {
      if (credits) {
        credits = false;
        slideOutBottom(document.getElementById('credits'));
      } else {
        showContainerEl(document.getElementById('content'));
      }
    }
  };
  document.getElementById('nav-pause').onclick = function() {
    var audioEl = document.getElementsByClassName('audio-div');
    for (var i = 0; i < audioEl.length; i++) {
      audioEl[i].children[0].pause();
    }
  }
  document.getElementById('nav-credits').onclick = function() {
    if (!animating) {
      if (!credits) {
        credits = true;
        slideInBottom(document.getElementById('credits'));
      } else {
        credits = false;
        slideOutBottom(document.getElementById('credits'));
      }
    }
  };
}

function showContainerEl(el) {
  if (!animating) {
    var containerElArray = document.getElementById('container').children;
    var otherEl = [];
    for (var i = 0; i < containerElArray.length; i++) {
      if (containerElArray[i] != el && containerElArray[i].style.display != 'none' &&
          containerElArray[i].id != 'credits') {
        otherEl.push(containerElArray[i]);
        fadeOut(containerElArray[i]);
      }      
    }
    if (el !== undefined && el.style.display == 'none') {
      var timer = setTimeout(function() { fadeIn(el) }, 2000);
    } 
  }
}
              
function fadeIn(el) {
  if (!animating) {
    animating = true;
    el.style.display = 'block';
    el.style.opacity = 0;
    var timer = setInterval(function() {
      if (parseFloat(el.style.opacity) < 1) {
        el.style.opacity = parseFloat(el.style.opacity) + 0.05;
      } else {
        clearInterval(timer);
        el.style.opacity = 1;
        animating = false;
      }
    }, 50);
  }
}

function fadeOut(el) {
  if (!animating) {
    animating = true;
    el.style.display = 'block';
    el.style.opacity = 1;
    var timer = setInterval(function() {
      if (parseFloat(el.style.opacity) > 0) {
        el.style.opacity = parseFloat(el.style.opacity) - 0.05;
      } else {
        clearInterval(timer);
        el.style.opacity = 0;
        el.style.display = 'none';
        animating = false;
      }
    }, 50);
  }
}

function slideInBottom(el) {
  if (!animating) {
    animating = true;
    el.style.display = 'block';
    el.style.top = document.body.scrollHeight + 'px';
    var timer = setInterval(function() {
      if (parseInt(el.style.top) > 0) {
        el.style.top = parseFloat(el.style.top) - 0.5
                     - parseFloat(el.style.top)
                     / (document.body.scrollHeight / 2) + 'px';
      } else {
        clearInterval(timer);
        el.style.top = '0px';
        animating = false;
      }
    }, 1);
  }
}

function slideOutBottom(el) {
  if (!animating) {
    animating = true;
    el.style.display = 'block';
    el.style.top = '0px';
    var timer = setInterval(function() {
      if (parseInt(el.style.top) < document.body.scrollHeight) {
        el.style.top = parseFloat(el.style.top) + 0.5
                     + parseFloat(el.style.top)
                     / (document.body.scrollHeight / 2) + 'px';
      } else {
        clearInterval(timer);
        el.style.display = 'none';
        el.style.top = '0px';
        animating = false;
      }
    }, 1);
  }
}

function showPath(num) {
  if (!animating) {
    if (document.getElementById('audio' + num + '-div') == undefined) {
      createPath(num);
    } else {
      showContainerEl(document.getElementById('audio' + num + '-div'));
    }
  }                 
}

function createPath(num) {
  var container = document.getElementById('container');
  var div = document.createElement('div');
  var audio = document.createElement('audio');
  var source = document.createElement('source');
  source.type = 'audio/mp3';
  div.id = 'audio' + num + '-div';
  div.className = 'audio-div';
  div.style.opacity = 0;
  div.style.display = 'none';

  switch (num) {
    case 1:
      source.src = 'data/PianoGuys_AThousandYears.mp3';
      break;
    case 2:
      source.src = 'data/Darude_Sandstorm.mp3';
      break;
    case 3:
      source.src = 'data/Mozart_HornConcerto4_Eflatmajor_Rondo.mp3';
      break;
  }
  
  audio.appendChild(source);
  audio.controls = true;
  div.appendChild(audio);
  container.appendChild(div);
  showContainerEl(div);
}
