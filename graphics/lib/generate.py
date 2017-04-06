import math

def drawCircle(cx, cy, r):
    print 'circle'
    print cx, cy, 0, r

def drawBezier(x0, y0, x1, y1, x2, y2, x3, y3):
    print 'bezier'
    print x0, y0, x1, y1, x2, y2, x3, y3

def drawHermite(x0, y0, x1, y1, rx0, rx1, ry0, ry1):
    print 'hermite'
    print x0, y0, x1, y1, rx0, rx1, ry0, ry1

def rotate(axis, theta):
    print 'rotate'
    print axis, theta

def move(x, y, z):
    print 'move'
    print x, y, z

def apply():
    print 'apply'

def display():
    print 'display'

def clear():
    print 'clear'

def save():
    print 'save'
    print 'pic.png'
    
n = 12
for i in range(n):
    drawCircle(0, 250, 200)
    drawBezier(0, 100, -100, 150, -100, 200, 0, 250)
    drawHermite(0, 250, 0, 400, -250, 0, 250, 0)
    rotate('y', 180 / n)
    apply()

move(250, 0, 0)
apply()
display()
save()


# cx = 250
# cy = 250
# cr = 150

# for r in range(cr - 1, cr + 2):
    # print 'circle'
    # print cx, cy, 0, r

# for y in range(125, 376, 25):
    # dx = round(math.sqrt(cr ** 2 - (cy - y) ** 2))
    # dy = 25
    # x0 = int(cx - dx)
    # x1 = int(cx - dx / 3)
    # x2 = int(cx + dx / 3)
    # x3 = int(cx + dx)
    # y0 = int(y)
    # y1 = int(y - dx / cr * dy)
    # y2 = int(y - dx / cr * dy)
    # y3 = int(y)
    # print 'bezier'
    # print x0, y0, x1, y1, x2, y2, x3, y3

# for x in range(100, 376, 25):
    # x0 = int(cx)
    # x1 = int(cx)
    # y0 = int(cy - cr)
    # y1 = int(cy + cr)
    # rx0 = int(cx - x) * 4
    # rx1 = int(x - cx) * 4
    # ry0 = int(math.sqrt((4 * cr) ** 2 - rx0 ** 2))
    # ry1 = int(math.sqrt((4 * cr) ** 2 - rx1 ** 2))
    # print 'hermite'
    # print x0, y0, x1, y1, rx0, ry0, rx1, ry1

# print 'display'
