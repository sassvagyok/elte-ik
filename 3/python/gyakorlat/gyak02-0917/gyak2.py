import math

def f1():
    age = int(input("Hány éves vagy? "))
    print(f"{2025-age}. évben születtél.")

def f2():
    a = 505
    b = 745
    a, b = b, a
    print(a, b)

def f3():
    bef1 = int(input("Befogó 1: "))
    bef2 = int(input("Befogó 2: "))
    atf = math.sqrt(bef1 ** 2 + bef2 ** 2)
    print(f"Átfogó: {atf}")

def f4():
    line = "Fah-fah, fah-fah-fah, fah-fah, fah"
    print(line.lower().count("fah"))

def f5():
    lst = [1, [ [], [2, ["polizei"], 3], 4], "off"]

    print(f"{lst[1][1][1][0][3:6]}")

def f15():
    t = (1, 2, [10, 20])
    t[2] += [30, 40]
    # ezt ne, hibát dob, de későn

#f1()
f2()
#f3()
f4()
f5()