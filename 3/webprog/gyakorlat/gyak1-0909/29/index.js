const temps = [3.4, 1.2, -0.7, -2, 20, 6];

// a
const frozen = temps.filter(x => x < 0);

// b
const addC = temps.map(x => x + " C");
//temps.forEach((x, i, arr) => arr[i] = x + " C"); - megváltoztatja az eredetit

// c
const highest = Math.max(...temps);
// const highest2 = temps.reduce((m, x) => Math.max(m, x));
// const highest3 = temps.reduce((m, x) => m > x ? m : x);

// d
const lessThan20 = temps.filter(x => x < 20).length;

// e
const hasMoreThan40 = temps.some(x => x > 40);

// f
const isEveryPositive = temps.every(x => x > 0);
// const isEveryPositive2 = temps.some(x => x <= 0);

// g
const firstHigherThan10 = temps.find(x => x > 10);

//+feladat
//„Igaz-e, hogy van legalább egy olyan pozitív szám, aminek a tizedesrésze 0.4 vagy nagyobb?”
const hasMoreThan04 = temps.some(x => x > 0 && Number((x - Math.floor(x)).toFixed(3)));

//++feladat
//„Add meg az első olyan negatív számot, amely kisebb, mint -1!”
const negativeLessThanMinusOne = temps.find(x => x < -1);

console.log(`${temps}\n${frozen}\n${addC}\n${highest}\n${lessThan20}\n${hasMoreThan40}\n${isEveryPositive}\n${firstHigherThan10}`);