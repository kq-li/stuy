def isInt(n):
    return n == int(n)

# Fill out the body of this method
# All inputs are integers and you should fill A, and B with the amount of each crop
# you should grow
def get_crop_amounts1(A_water, A_labor, B_water, B_labor, total_water, total_labor):
    det = float(A_water * B_labor - A_labor * B_water)

    if A_water == 0 and B_water == 0 and A_labor == 0 and B_labor == 0:
        return -1, -1

    if det == 0:
        test = -1
        
        if A_water == 0:
            if A_labor == 0:
                test = 1
            else:
                return -1, -1
        else:
            test = total_labor - total_water * (A_labor / A_water)

        if test == 0:
            check = 0
            ans = 0
            x_i = total_water / B_water

            for i in range(x_i + 1):
                if (total_water - B_water * i) % A_water == 0:
                    if check > 0:
                        return -1, -1

                    check += 1
                    ans = i

            if check == 1:
                return (total_water - B_water * ans) / A_water, ans
            else:
                return -1, -1
        elif test == 1:
            if total_water % B_water == 0:
                return 0, total_water / B_water
            else:
                return -1, -1
                
        return -1, -1

    inv_mat = [[B_labor / det, -B_water / det],
               [-A_labor / det, A_water / det]]

    A = inv_mat[0][0] * total_water + inv_mat[0][1] * total_labor
    B = inv_mat[1][0] * total_water + inv_mat[1][1] * total_labor

    if A < 0 or B < 0:
        return -1, -1

    if not isInt(A) or not isInt(B):
        return -1, -1
    
    return A, B

def get_crop_amounts2(A_water, A_labor, B_water, B_labor, total_water, total_labor):
    A_cost = A_water + A_labor
    B_cost = B_water + B_labor
    total_cost = total_water + total_labor

    if total_cost == 0:
        return 0, 0

    if A_cost == 0 and B_cost == 0:
        return -1, -1

    if A_water == 0 and B_water == 0 and total_water > 0:
        return -1, -1
    elif A_water == 0 and B_water > 0:
        if total_water % B_water != 0:
            return -1, -1
        
        
    cap = 0
    
    if A_water == 0:
        cap = total_labor / A_labor + 1
    elif A_labor == 0:
        cap = total_water / A_water + 1
    else:
        cap = min(total_labor / A_labor, total_water / A_water) + 1

    for A in range(cap):
        water_left = total_water - A * A_water
        labor_left = total_labor - A * A_labor

        if (B_water == 0 and water_left > 0) or (B_labor == 0 and labor_left > 0):
            return -1, -1

        if B_water == 0 and water_left == 0:
            if labor_left % B_labor == 0:
                B = labor_left / B_labor
                return A, B

        if B_labor == 0 and labor_left == 0:
            if water_left % B_water == 0:
                B = water_left / B_water
                return A, B

        if water_left % B_water == 0 and labor_left % B_labor == 0:
            B_from_water = water_left / B_water
            B_from_labor = labor_left / B_labor

            if B_from_water == B_from_labor:
                B = B_from_water
                return A, B

    return -1, -1

def get_crop_amounts(A_water, A_labor, B_water, B_labor, total_water, total_labor):
    if get_crop_amounts1(A_water, A_labor, B_water, B_labor, total_water, total_labor) == (-1, -1):
        return get_crop_amounts2(A_water, A_labor, B_water, B_labor, total_water, total_labor)
    elif get_crop_amounts2(A_water, A_labor, B_water, B_labor, total_water, total_labor) == (-1, -1):
        return get_crop_amounts1(A_water, A_labor, B_water, B_labor, total_water, total_labor)
    
if __name__ == "__main__":
    with open("FastidiousFarmingIN.txt", "r") as f:
        while True:
            s = f.readline()
            if s == '':
                break
            A_water, A_labor = [int(x) for x in s.split(" ")]
            B_water, B_labor = [int(x) for x in f.readline().split(" ")]
            total_water, total_labor = [int(x) for x in f.readline().split(" ")]
            A, B = get_crop_amounts(A_water, A_labor, B_water, B_labor, total_water, total_labor)
            print "%d %d" % (A, B)
