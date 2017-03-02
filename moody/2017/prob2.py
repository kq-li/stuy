import csv
import pprint

sites = {
    'acadia': {
        'h': {},
        'w': {},
        'aq': {},
        'wl': {},
        'hi': {},
        'v': {}
    },
    
    'cape_hatteras': {
        'h': {},
        'w': {},
        'aq': {},
        'wl': {},
        'hi': {},
        'v': {}
    },

    'kenai_fjords': {
        'h': {},
        'w': {},
        'aq': {},
        'wl': {},
        'hi': {},
        'v': {}
    },

    'olympic': {
        'h': {},
        'w': {},
        'aq': {},
        'wl': {},
        'hi': {},
        'v': {}
    },

    'padre_island': {
        'h': {},
        'w': {},
        'aq': {},
        'wl': {},
        'hi': {},
        'v': {}
    }
}

def initH():
    conversion = {
        'ET': 0,
        'TD': 1,
        'TS': 2,
        'H1': 3,
        'H2': 4,
        'H3': 5,
        'H4': 6,
        'H5': 7
    }
    
    for site in sites:
        reader = csv.reader(open(site + '_h.csv'))

        for row in reader:
            if row != []:
                if row[2] != '' and row[2] != 'ET':
                    sites[site]['h'][int(row[0])] = int(conversion[row[2]] * 36.0 / 7)

    for site in sites:
        for year in range(1991, 2017):
            if year not in sites[site]['h']:
                sites[site]['h'][year] = 0
    
def initW():
    sizes = {
        'acadia': 49052,
        'cape_hatteras': 30351,
        'kenai_fjords': 670080,
        'olympic': 922880,
        'padre_island': 133760
    }
    
    maxW = 0
    minW = 10000

    for site in sites:
        reader = csv.reader(open(site + '_w.csv'))

        curYear = 0
        total = 0
        numFires = 0
        
        for row in reader:
            if row != []:
                if curYear == 0:
                    curYear = int(row[0])
                    total = 0
                    numFires = 0
                elif curYear != int(row[0]):
                    total *= numFires

                    if total >= maxW:
                        maxW = total

                    if total <= minW:
                        minW = total
                    
                    sites[site]['w'][curYear] = total
                    curYear = int(row[0])
                    total = 0
                    numFires = 0

                if row[10] != '' and row[11] != '':
                    total += int(row[10]) * float(row[11]) / sizes[site]
                    numFires += 1                
                    
        total *= numFires
        sites[site]['w'][curYear] = total
        
        if total >= maxW:
            maxW = total
            
        if total <= minW:
            minW = total
                    

    for site in sites:
        for year in range(1991, 2017):
            if year in sites[site]['w']:
                sites[site]['w'][year] = (sites[site]['w'][year] - minW) * 30 / (maxW - minW)
            else:
                sites[site]['w'][year] = 0

def aqConversion(n):
    if 0 <= n <= 50:
        return 1
    elif 51 <= n <= 100:
        return 2
    elif 101 <= n <= 150:
        return 3
    elif 151 <= n <= 200:
        return 4
    else:
        return 5

def initAQ():
    for site in sites:
        reader = csv.reader(open(site + '_aq.csv'))

        curYear = 0
        total = 0
        months = 0
        
        for row in reader:
            if row != []:
                if curYear == 0:
                    curYear = int(row[0])
                    total = 0
                elif int(row[0]) != curYear:
                    if months != 0:
                        total /= months
                        sites[site]['aq'][curYear] = aqConversion(total)
                        curYear = int(row[0])
                        total = 0
                        months = 0

                if row[2] != 'Missing':
                    total += float(row[2])
                    months += 1

        if months != 0:
            total /= months
            sites[site]['aq'][curYear] = aqConversion(total)

        for year in range(1991, 2017):
            if year not in sites[site]['aq']:
                sites[site]['aq'][year] = 0

def initHI():
    maxHI = 0
    minHI = 1000
    
    for site in sites:
        reader = csv.reader(open(site + '_hi.csv'))

        curYear = 0
        total = 0
        months = 0
        
        for row in reader:
            if row != []:
                if curYear == 0:
                    curYear = int(row[0])
                    total = 0
                elif int(row[0]) != curYear:
                    if months != 0:
                        total /= months

                        if total >= maxHI:
                            maxHI = total

                        if total <= minHI:
                            minHI = total
                        
                        sites[site]['hi'][curYear] = total
                        curYear = int(row[0])
                        total = 0
                        months = 0

                if row[2] != 'Missing':
                    total += float(row[2])
                    months += 1

        if months != 0:
            total /= months
            sites[site]['hi'][curYear] = total

            if total >= maxHI:
                maxHI = total

            if total <= minHI:
                minHI = total
                        
    for site in sites:
        for year in range(1991, 2017):
            if year in sites[site]['hi']:
                sites[site]['hi'][year] = (sites[site]['hi'][year] - minHI) * 4 / (maxHI - minHI)
            else:
                sites[site]['hi'][year] = 0

def initWL():
    pass

def initV():
    for site in sites:
        siteData = sites[site]

        for year in range(1991, 2017):
            siteData['v'][year] = siteData['h'][year] + siteData['w'][year] + siteData['aq'][year] + siteData['hi'][year] # + siteData['wl'][year]

initW()
initH()
initAQ()
initHI()
initV()
pprint.pprint(sites)

for site in sites:
    writer = csv.writer(open(site + '_v.csv', 'w'))
    writer.writerow(['Year', 'Vulnerability Rating'])

    for year in range(1991, 2017):
        writer.writerow([year, sites[site]['v'][year]])
