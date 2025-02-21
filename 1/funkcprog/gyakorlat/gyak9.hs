module Test where

add2 :: Num a => [a] -> [a]
add2 [] = []
add2 (x:xs) = (x + 2) : add2 xs

negateAll :: [Bool] -> [Bool]
negateAll [] = []
negateAll (x:xs) = not x : negateAll xs


h :: Num a => Bool -> a
h True = 1
h False = 0

hAll :: Num a => [Bool] -> [a]
hAll [] = []
hAll (x:xs) = h x : hAll xs


map' :: (a -> a) -> [a] -> [a]
map' _ [] = []
map' f (x:xs) = f x : map' f xs

-- ez a ketto ugyanaz
--a -> b -> a
--a -> (b -> a)

f :: a -> b -> a
f x y = x

g :: a -> (b -> a)
g x = \y -> x

-- (\x -> x + 2) 1 == (+ 2) 1 == (2 +) 1

fx :: Int -> Int
fx = (+) 1 -- (1+) (+1)


--ugyanaz, mint map'
map'' :: (a -> b) -> [a] -> [b]
map'' f l = [f x | x <- l]


filter' :: (a -> Bool) -> [a] -> [a]
filter' _ [] = []
filter' p (x:xs)
    | p x = x : filter' p xs
    | otherwise = filter' p xs

--ugyanaz, mint filter'
filter'' :: (a -> Bool) -> [a] -> [a]
filter'' p xs = [x | x <- xs, p x]
--map'' (*2) (filter'' (>2) [1,2,3,4,5]) == [6,8,10] /kivalogatja a kettonel nagyobbakat, aztan 2-vel beszorozza oket/

-- a ketto otvozve
mapFilter :: (a -> Bool) -> (a -> b) -> [a] -> [b]
mapFilter _ _ [] = []
mapFilter p f (x:xs)
    | p x = f x : mapFilter p f xs
    | otherwise = mapFilter p f xs


zipWith' :: (a -> b -> c) -> [a] -> [b] -> [c]
zipWith' _ [] _ = []
zipWith' _ _ [] = []
zipWith' f (x:xs) (y:ys) = f x y : zipWith' f xs ys


--app2ToFunctions :: Num a => [a -> b] -> [b]
--app2ToFunctions l = [($ 2) | f <- l]
--app2ToFunctions l = [(f $ 2) | f <- l]
--app2ToFunctions l = map ($ 2) l