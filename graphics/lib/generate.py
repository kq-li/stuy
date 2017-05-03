import math

def circle(cx, cy, r):
    print 'circle'
    print cx, cy, 0, r

def bezier(x0, y0, x1, y1, x2, y2, x3, y3):
    print 'bezier'
    print x0, y0, x1, y1, x2, y2, x3, y3

def hermite(x0, y0, x1, y1, rx0, rx1, ry0, ry1):
    print 'hermite'
    print x0, y0, x1, y1, rx0, rx1, ry0, ry1

def box(x, y, z, w, h, d):
    print 'box'
    print x, y, z, w, h, d

def sphere(x, y, z, r):
    print 'sphere'
    print x, y, z, r

def torus(x, y, z, cr, tr):
    print 'torus'
    print x, y, z, cr, tr

def push():
    print 'push'

def pop():
    print 'pop'

def rotate(axis, theta):
    print 'rotate'
    print axis, theta

def move(x, y, z):
    print 'move'
    print x, y, z

def display():
    print 'display'

def clear():
    print 'clear'

def save():
    print 'save'
    print 'pic.png'
    
push()
move(250, 250, 250)
rotate('y', -20)
push()
rotate('x', 90)
torus(0, 0, 0, 25, 200)
pop()
push()

for i in range(0, 360, 30):
    rotate('z', 30)
    box(0, 5, 5, 175, 10, 10)

display()
