import random

def greeting():
    greetings = ['hi', 'hello', 'what\'s up']
    return random.choice(greetings)

def closing():
    closings = ['bye', 'goodbye', 'see ya']
    return random.choice(closings)

def heading(f):
    def inner():
        return '<h1>%s</h1>' % f()

    return inner
    
def quicksort(L):
    if len(L) <= 1:
        return L
    
    pivot = random.choice(L)
    left = [i for i in L if i < pivot]
    right = [i for i in L if i > pivot]

    return quicksort(left) + [pivot] * L.count(pivot) + quicksort(right)

print quicksort([4, 3, 4, 2, 5, 3, 1, 2, 5, 1])
    
