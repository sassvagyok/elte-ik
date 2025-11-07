import Data.List

add' :: Num a => [a] -> a
add' [] = 0
add' (x:xs) = x + add' xs

add2 :: Num a => [a] -> [a]
add2 [] = []
add2 (x:xs) = x + 1 : add2 xs

fact :: Integer -> Integer
fact 0 = 1
fact n = n * fact (n - 1)

product' :: Num a => [a] -> a
product' [] = 1
product' (x:xs) = x * product' xs

-- 2 [1, 2, 3]
-- 2 == 1 || 2 == 2 || 2 == 3
-- False || True || False
elem' :: Eq a => a -> [a] -> Bool
elem' _ [] = False
elem' y (x:xs) = y == x || elem' y xs

genericLength' :: Num b => [a] -> b
genericLength' [] = 0
genericLength' (_:xs) = 1 + genericLength' xs

factorial :: Integral a => a -> a
factorial n = product [1..n]

--rep :: a -> a -> [a]
--rep n x = [x | _ <- [1..n]]

rep :: Integer -> a -> [a]
rep 0 _ = []
rep n x = x : rep (n - 1) x

replicateFact' :: [a] -> b -> [b]
replicateFact' l x = rep (factorial (fromIntegral (length l))) x

-- [] [1, 2] == [1, 2]
-- [1, 2] [2] == 1:2:[2]
-- x1 : x2 : xn : l2 [2, 3]
(+++) :: [a] -> [a] -> [a]
(+++) [] l2 = l2
(+++) (x:xs) l2 = x : (xs +++ l2) -- x : (+++) xs l2
infixr 5 +++

--hf
--zip' [] _ = []
--zip' _ [] = []
--zip' = undefined

--listaszámozás kövi oran!!