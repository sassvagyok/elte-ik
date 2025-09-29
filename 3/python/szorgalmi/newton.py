def newton_method(n, A, m):
    if A <= 0:
        return print("A > 0")
    
    if m < 2:
        return print("m >= 2")
    
    if n == 0:
        return 1
    else:
        a_n = newton_method(n - 1, A, m)
        current = 1 / m * (A / a_n ** (m - 1) + (m - 1) * a_n)
        print(f"{n}: {current}")
        return current

def get_results(n, A, m):
    print(f"{A} {m}. gyöke {n}x végrehajtva:\n")
    newton_result = newton_method(n, A, m)
    built_in_result = A ** (1 / m)
    print(f"\nNewton-féle: {newton_result}\tBeépített: {built_in_result}\n")

get_results(10, 2, 2)
get_results(10, 10, 3)
get_results(10, 25, 2)
get_results(20, 15, 4)