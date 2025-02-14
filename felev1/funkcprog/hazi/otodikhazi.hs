module Hazi5 where
import Data.Char

toUpperThird :: String -> String
toUpperThird [] = []
toUpperThird [x] = [x]
toUpperThird [x, y] = [x, y]
toUpperThird (x:y:z:zs) = x : y : toUpper z : zs

isSorted :: Ord a => [a] -> Bool
isSorted [] = True
isSorted [_] = True
isSorted (x:y:ys) = x <= y && isSorted (y:ys)

(!!!) :: Integral b => [a] -> b -> a
(!!!) xs n
    | n >= 0 = fwdIndex xs n
    | otherwise = fwdIndex (reverse xs) (abs n - 1)
    where
        fwdIndex (x:_) 0 = x
        fwdIndex (_:xs) n = fwdIndex xs (n - 1)

format :: Integral i => i -> String -> String
format n str
    | n < 0 = format' 0 str
    | otherwise = format' (fromIntegral n) str
    where
        format' 0 xs = xs
        format' n [] = ' ' : format' (n - 1) []
        format' n (x:xs) = x : format' (n - 1) xs

mightyGale :: (Num a, Ord b, Num b, Integral c) => [(String, a, b, c)] -> String
mightyGale [] = ""
mightyGale ((nev, _, seb, _):xs)
    | seb > 110 = nev
    | otherwise = mightyGale xs

cipher :: String -> String
cipher (x:y:z:zs)
    | isDigit z = [x, y, z]
    | otherwise = cipher (y:z:zs)
cipher _ = ""

doubleElements :: [a] -> [a]
doubleElements [] = []
doubleElements (x:xs) = x : x : doubleElements xs

deleteDuplicateSpaces :: String -> String
deleteDuplicateSpaces = unwords . words