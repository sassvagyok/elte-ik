SELECT * FROM dolgozo;
SELECT * FROM osztaly;
SELECT * FROM fiz_kategoria;

SELECT MAX(fizetes)
FROM dolgozo;

SELECT SUM(fizetes), AVG(fizetes)
FROM dolgozo
WHERE oazon = 20;

SELECT oazon, AVG(fizetes)
FROM dolgozo
GROUP BY oazon;

SELECT telephely, AVG(fizetes)
FROM dolgozo NATURAL JOIN osztaly
GROUP BY telephely;

SELECT telephely, AVG(fizetes)
FROM dolgozo NATURAL JOIN osztaly
GROUP BY telephely
HAVING AVG(fizetes) > 2000;

SELECT oazon, AVG(fizetes)
FROM dolgozo
GROUP BY oazon
HAVING COUNT(*) >= 4;

SELECT kategoria
FROM dolgozo JOIN fiz_kategoria ON fizetes BETWEEN also AND felso
GROUP BY kategoria
HAVING COUNT(*) = 3;

SELECT kategoria
FROM dolgozo JOIN fiz_kategoria ON fizetes BETWEEN also AND felso
GROUP BY kategoria
HAVING COUNT(DISTINCT oazon) = 1;

SELECT foglalkozas
FROM dolgozo
GROUP BY foglalkozas
HAVING COUNT(dnev) > 2 AND AVG(fizetes) > 1000;