from functools import reduce
import pandas as pd
import matplotlib.pyplot as plt

# 1.
def hatelove(str):
    return str.lower().replace("hate", "love")

print(hatelove("I HATE the way that you dress, I hatE the way that you teach this"))

# 2.
shorter_than_5 = lambda *args : reduce(lambda acc, s : acc + (1 if len(s) < 5 else 0), args, 0)

print(shorter_than_5("asd", "asdasd", "a"))

# 3.
has_dress = lambda str : "dress" in str

print(has_dress("dresses"))

# 4.
d = {"Rudolf" : 5, "Mikulás" : 4, "Grincs" : 1, "Húsvéti nyúl" : 2, "Jesszuska" : 5}

less_than_5 = [key for key, value in d.items() if value < 5]

print(less_than_5)

# 5.
df = pd.read_csv("vending_machine.csv")

# 1.
print(df.info())
print(df.describe())

# 2.
print(df.isnull().sum())
print(df.isnull().sum().sum())

# 3.
df = df.fillna(value=0)

# 4.
df.to_csv("vending_clean.csv", index=False)

# 5.
df["Profit"] = df["Price"] * df["Sold_Units"] * 700
df_profit = df.groupby("Item")["Profit"].sum()

# 6.
best_5 = df_profit.sort_values(ascending=False).head(5)
print(best_5)

plt.figure(figsize=(10, 5))
plt.plot(best_5)
plt.title("5 bestseller")
plt.xlabel("Termék")
plt.ylabel("Profit")
plt.grid()
plt.show()