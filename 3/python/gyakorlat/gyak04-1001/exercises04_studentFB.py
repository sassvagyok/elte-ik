# 1. Feladat: Lambda két szám szorzására
multiply = lambda x, y : x * y
print(multiply(2, 3))  # Kimenet: 6

# 2. Feladat: Lambda két szám maximumának megtalálására
maximum = lambda x, y: x if x >= y else y
print(maximum(2, 3))  # Kimenet: 3

# 3. Feladat: Lambda egy szám páros voltának ellenőrzésére
is_even = lambda x : x % 2 == 0
print(is_even(4))  # Kimenet: True

# 4. Feladat: Lambda egy string megfordítására
reverse_string = lambda x : x[::-1]
print(reverse_string("hello"))  # Kimenet: "olleh"

# 5. Feladat: Lambda egy szám négyzetének kiszámítására
square = lambda x : x ** 2
print(square(4))  # Kimenet: 16

# 6. Feladat: Lambda páros számok szűrésére egy listából
filter_even = lambda lst: [x for x in lst if x % 2 == 0]
print(filter_even([1, 2, 3, 4, 5, 6]))  # Kimenet: [2, 4, 6]

# 7. Feladat: Lambda stringek listájának nagybetűssé alakítására
to_uppercase = lambda lst: [x.upper() for x in lst]
print(to_uppercase(["hello", "world"]))  # Kimenet: ["HELLO", "WORLD"]

# 8. Feladat: Lambda tuple-ök listájának rendezésére a második elem alapján
sort_by_second = lambda lst : sorted(lst, key=(lambda x : x[1]))
print(sort_by_second([(1, 3), (2, 2), (3, 1)]))  # Kimenet: [(3, 1), (2, 2), (1, 3)]

# 9. Feladat: Lambda stringek hosszának megtalálására egy listában
lengths = lambda lst : [len(x) for x in lst]
print(lengths(["hello", "world"]))  # Kimenet: [5, 5]

# 10. Feladat: Lambda egy konstans hozzáadására minden elemhez egy listában
add_constant = lambda lst, c : [x + c for x in lst]
print(add_constant([1, 2, 3], 5))  # Kimenet: [6, 7, 8]

# 11. Feladat: Lambda két lista metszetének megtalálására
intersection = lambda l1, l2 : [x for x in l1 if x in l2]
print(intersection([1, 2, 3], [2, 3, 4]))  # Kimenet: [2, 3]

# 12. Feladat: Lambda annak ellenőrzésére, hogy egy string csak számjegyeket tartalmaz-e
is_digit = lambda x : x.isdigit()
print(is_digit("123"))  # Kimenet: True

# 13. Feladat: Lambda duplikátumok eltávolítására egy listából
remove_duplicates = lambda lst : list(set(lst))
print(remove_duplicates([1, 2, 2, 3, 4, 4, 5]))  # Kimenet: [1, 2, 3, 4, 5]

# 14. Feladat: Lambda egy szám faktoriálisának kiszámítására
factorial = lambda x : x if x == 1 else x*factorial(x - 1)
print(factorial(5))  # Kimenet: 120

# 15. Feladat: Lambda annak ellenőrzésére, hogy egy string palindróm-e
is_palindrome = lambda x : x == x[::-1]
print(is_palindrome("racecar"))  # Kimenet: True