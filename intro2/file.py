value = open('values.txt').read()
operations = open('operations.txt').read()

def listInList(valueL):
    L = []
    for i in valueL:
        value_temp = i.split(',')
        L.append(value_temp)
    return L

def calculate(valueString,operationString,labelList):
    valueL = value.split('\n')
    opL = operations.split('\n')
    nestList = listInList(valueL)
    valueList = [[int(j) for j in i] for i in nestList]
    answers = ""
    for i in opL:
        op_temp = i.split(' ')
        num1 = valueList[labelList.index(op_temp[0])]
        num2 = valueList[labelList.index(op_temp[2])]
        if op_temp[1] == "+":
            answers += str(num1 + num2) + "\n"
        if op_temp[1] == "-":
            answers += str(num1 - num2) + "\n"
        if op_temp[1] == "/":
            answers += str(num1 / num2) + "\n"
        if op_temp[1] == "*":
            answers += str(num1 * num2) + "\n"
    return answers

L = ['a','e','z','d']
V = "9,3,2,4"
O = "a + e"

print calculate(V,O,L)
