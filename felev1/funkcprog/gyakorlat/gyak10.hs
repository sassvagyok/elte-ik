module Test where

-- folding (hajtogatás)

-- foldl (+) 0 [1..10] == 55

-- foldl'' f z (x:xs) = foldl'' f (f z x) xs
-- foldl (-) (-2) [34,6,12,65,8,11,23] == -161
-- ((((((-2 - 34) -6 ) -12 ) -65 ) -8 ) -11 ) -23 ) == -161

-- foldr'' f z (x:xs) = f x (foldr'' f z xs)
-- foldr (-) (-2) [34,6,12,65,8,11,23] == -3
-- (34 -(6 -(12 -(65 -(8 -(11 -(23 -(-2)))))))) == -3

sum' [] = 0
sum' (x:xs) = x + sum' xs

elem' _ [] = False
elem' e (x:xs) = x == e || elem' e xs

any' :: (a -> Bool) -> [a] -> Bool
any' _ [] = False
any' f (x:xs) = f x || any' f xs

elem'' :: (Eq a) => a -> [a] -> Bool
elem'' x = any (== x)


foldr'' :: (a -> b -> b) -> b -> [a] -> b
foldr'' _ z [] = z
foldr'' f z (x:xs) = f x (foldr'' f z xs)

foldl'' :: (b -> a -> b) -> b -> [a] -> b
foldl''_ z [] = z
foldl'' f z (x:xs) = foldl'' f (f z x) xs

-- sumAcc 0 [1,2,3]
sum'' = sumAcc 0
    where
        sumAcc acc [] = acc
        sumAcc acc (x:xs) = sumAcc (acc + x) xs
-- sum (x:xs) = x + sum xy

sum''' :: Num a => [a] -> a
sum''' = foldl'' (+) 0

product'' :: Num a => [a] -> a
product'' = foldr'' (*) 1

-- scanning (pásztázás) -- részeredmények
-- scanr (+) 0 [1..10] = [55,54,52...10,0]

-- gyűjti az acc-ot / részerdeményeket
scanl'' :: (b -> a -> b) -> b -> [a] -> [b]
scanl'' _ acc [] = [acc]
scanl'' f acc (x:xs) = acc : scanl'' f (f acc x) xs

-- scanl (:) [] "pásztáznivaló" == [], "p", "áp", "sáp", ..., "ólavinzátzsáp"
-- scanr (:) [] "pásztáznivaló" == "pásztáznivaló","ásztáznivaló","sztáznivaló", ..., "ó", []

scanr'' :: (a -> a -> a) -> [a] -> [a]
scanr'' _ [x] = [x]
scanr'' f (x:xs) = f x (head qs) : qs
    where qs = scanr'' f xs


lucas :: [Integer]
lucas = 2 : scanl (\x y -> x + y) 1 lucas

--data beadandó, rekurzív adatok, hajtogatások, függvénykompozíciók