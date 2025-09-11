# 1. gyakorlat: bevezető feladatok
# Ajánlott VSCode bővítmények: Pylance, Code Runner, Python (Microsofté)
# Ha nincs telepítve a matplotlib: pip install matplotlib
#                     ezzel telepíthető a csomag az utolsó feladathoz (cmd környezetben)

print("0. feladat")
# Írjuk meg a FizzBuzz feladatot! A feladatot számsorozatra értelmezzük.
# Ha egy szám osztható 3-mal, írjuk ki, hogy Fizz, ha 5-tel, írjuk ki, hogy Buzz, ha 15-tel,
# írjuk ki, hogy FizzBuzz! Ha egyikkel sem, írjuk ki a számot.
def FizzBuzz(list):
    for num in list:
        if num % 15 == 0:
            print("FizzBuzz", end = " ")
        elif num % 3 == 0:
            print("Fizz", end = " ")
        elif num % 5 == 0:
            print("Buzz", end = " ")
        else:
            print(num, end = " ")
    print("\n")

FizzBuzz(range(1, 61))


print('1.feladat')
#1-től 120-ig a számok listában
list = []

for i in range(1, 121):
    list.append(i);

print(list)


print('2.feladat')
#a lista elemek összege
print(sum(list))


print('3.feladat')
#3-mal osztható listaelemek a listában
list_mod3 = []
for num in list:
    if num % 3 == 0:
        list_mod3.append(num)

print(list_mod3)


print('4.feladat')
# A listaelemek szorzata
szorzat = 1

for num in list:
    szorzat = szorzat * num

print(szorzat)


print('5.feladat')
# Faktoriálisok 1-től 70-ig, rekurzív függvénnyel


print('70-ig a számok faktoriálisa:')

print('5b.feladat')
# Faktoriálisok kiszámítása rekurzió nélkül


print('6.feladat')
# Prímek az eredeti listában és db számuk


print("7. feladat")
# Generáljunk random számokat 1-99 között, majd tároljuk el őket!
# Használjuk a random modult!
# HF Generáljunk 2000 random számot 1-99 között, 
# Rajzoljuk ki az eloszlásukat a matplotlib könyvtár segítségével!