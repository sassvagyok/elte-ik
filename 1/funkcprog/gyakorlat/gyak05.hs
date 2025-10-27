module Test where

pl2 :: Integer -> Integer -> Bool
-- pl2 = y // nincs scope-ban a z (nem elérhető)
pl2 x y = even z && z `mod` 3 == 0
    where
        z = x + y -- lokális scope

-- let dec in expr

pl3 :: Integer -> Integer -> Bool
pl3 x y = let z = x + y in even z && z `mod` 3 == 0

pl4 :: Integer -> Integer
pl4 x = (let z = x + 1 in z + z) + (let y = 3 in y + x) -- a másodikban már nem lehet a z-t használni, mert lokális scope-ban van

-- if-et soha nem használunk

pl5 :: (Integer, Integer) -> Integer
pl5 x = y + z
    where  
        (y, z) = x

pl5' :: (Integer, Integer) -> Integer
pl5' x = let (y, z) = x in y + z

numberWords' :: [String] -> [(Int, String)]
-- ["alma", "korte"] ->> [(1, "alma"), (2, "korte")]
numberWords' words = numberWordsHelper words 1
    where
        numberWordsHelper [] _ = []
        numberWordsHelper (x:xs) n = (n, x) : numberWordsHelper xs (n+1)

intersperse' :: a -> [a] -> [a]
intersperse' _ [] = []
intersperse' _ [x] = [x]
intersperse' element (x:xs) = x : intersperseHelper xs
    where
        intersperseHelper [] = []
        intersperseHelper (y:ys) = element : y : intersperseHelper ys

reverse' :: [a] -> [a]
reverse' lista = reverseHelper lista []
    where
        reverseHelper [] acc = acc
        reverseHelper (x:xs) acc = reverseHelper xs (x:acc)

{- rekurzio változatai

--normal rekurzio:

sum [] = 0
sum (x:xs) = x + sum xs

sum [] = 0
sum (x:xs) = xs + sum x


--végrekurzió:

sum xs = sumACC 0 xs
    where
        sumAcc acc [] = acc
        sumAcc acc (x:xs) = sumAcc (acc + x) xs

sum xs = sumACC 0 xs
    where
        sumAcc acc [] = acc
        sumAcc acc (x:xs) = sumAcc (x + acc) xs

-}

fact n
    | n <= 0 = 1
    | otherwise = n * fact (n-1) -- az "otherwise" egy fuggveny (lehetne helyette True-t is irni)

replicate' :: Int -> Char -> [Char] -- String
replicate' n c
    | n > 0 = c : replicate' (n-1) c
    | otherwise = []