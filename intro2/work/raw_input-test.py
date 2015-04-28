while True:
    filename = raw_input("filename: ")
    if filename == '':
        print 'Thanks, bye.'
        break
    try:
        f = open(filename, 'r')
        s = f.read()
        print '"' + filename + '" has ' + str(len(s)) + ' chars.\n'
        f.close()
    except:
        print filename + ' is not readable!\n'
