from functools import reduce

#1
str = "Never odd or even"
print(str[::-1])

#2
d = {"Adam": 11, "Jerry": 4, "Michael": 45, "Ben": 10}
filtered = []
for k, v in d.items():
    if v > 10:
        filtered.append(k)
print(filtered)

filtered_c = [k for k, v in d.items() if v > 10]
print(filtered_c)

#3
lst = [33, 22, 33, 21, 33, 44, 33, 11, 11, 2, 1]
s = {33, 22, 45, 47, 42, 52}
lst_set = set(lst)
metszet = lst_set & s
unio = lst_set | s
kulonbseg = lst_set - s
print(metszet, unio, kulonbseg)

#4
def sum5(*nums):
    total = 0
    for num in nums:
        if num > 5:
            total += num
    
    return total

print(sum5(1,2,3,4,10,20,30))

sum5lambda = lambda *nums : sum(n for n in nums if n > 5)
print(sum5lambda(1,2,3,4,10,20,30))

#5
wopCount = lambda x : x.count("wop")
print(wopCount("wopmowopdowop"))

#6
more5 = lambda lst : [x for x in lst if len(x) > 5]
print(more5(["asd", "asdasd", "asdasdasd"]))

#7
d = {"a": 10, "b": 3, "c": 1}
orderD = lambda d : dict(sorted(d.items(), key=lambda item: item[1]))
print(orderD(d))

#8
minLambda = lambda lst : reduce(lambda x, y: x if x < y else y, lst)
print(minLambda([10, 20, 1, 2]))

#9
first2times = lambda *str : "".join([s[0] * 2 for s in str])
print(first2times("asd", "basd", "casd"))

#10
class Flower:
    name = ""
    mult = 1.0

    def __init__(self, name, color, pretty):
        if not isinstance(name, str):
            raise TypeError("Name: string")
        if not isinstance(color, str):
            raise TypeError("Color: stirng")
        if not isinstance(pretty, int):
            raise TypeError("Pretty: int")
        if not (1 <= pretty <= 10):
            raise ValueError("1 <= Pretty <= 10")  
          
        self.name = name,
        self.color = color,
        self.pretty = pretty

    def __str__(self):
        return f"{self.color} {self.name} {self.pretty * self.mult}/10"
    
    def __eq__(self, flower):
        if not isinstance(flower, Flower):
            raise TypeError("flower: Flower")
        
        return flower.pretty * flower.mult == self.pretty * self.mult
    
    def __lt__(self, flower):
        if not isinstance(flower, Flower):
            raise TypeError("flower: Flower")
        
        return flower.pretty * flower.mult > self.pretty * self.mult

class Rose(Flower):
    name = "Rose"
    mult = 2.0

class Tulip(Flower):
    name = "Tulip"
    mult = 1.5

class Daisy(Flower):
    name = "Daisy"

class Bouquet():
    flowers = []
    added = len(flowers)

    def __init__(self, flower):
        if not isinstance(flower, Flower):
            raise TypeError("flower: Flower")
        self.flowers.append(flower)

    def add(self, *flower):
        for f in flower:
            self.flowers.append(f)

    def __str__(self):
        if self.added == 0:
            raise Exception("Egy sincs")
        
        sorted_flowers = sorted(self.flowers)
        return "\n".join(str(f) for f in sorted_flowers)