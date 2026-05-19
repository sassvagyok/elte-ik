SELECT DISTINCT szeret.nev
FROM szeret sz1, szeret sz2, szeret sz3
WHERE sz1.nev = sz2.nev AND sz2 = sz3.nev AND sz1.gyumolcs <> sz2.gyumolcs AND sz2.gyumolcs <> sz3.gyumolcs AND sz1.gyumolcs <> sz2.gyumolcs;

SELECT b.dnev
FROM dolgozo f, dolgozo b, dolgozo ff
WHERE b.fonoke = f.dkod AND f.fonoke = ff.dkod AND ff.dnev = "KING";

CREATE TABLE osztaly AS SELECT * FROM kotroczo.osztaly;
SELECT * FROM osztaly;

SELECT *
FROM dolgozo NATURAL JOIN osztaly
WHERE telephely = "Chicago" OR telephely = "Dallas";