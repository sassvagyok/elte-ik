import Data.Char
import Data.List
import Data.Maybe

squareSum :: Num a => (a, a) -> (a, a, a)
squareSum (a,b) = (a, b, (a*a) + (b*b))

names :: [String] -> [String] -> [String]
names [] _= []
names _ [] = []
names (x:xs) (y:ys) = (x ++ " " ++ y) : names xs ys

triangleArea :: (Double, Double, Double) -> Maybe Double
triangleArea (a,b,c)
    | a > 0 && b > 0 && c > 0 && (a + b >= c || a + c >= b || c + b >= a) && ((a * a) + (b * b) == (c * c)) = Just ((a * b) / 2)
    | otherwise = Nothing

doubleIdxs:: Eq a  => [(a,a)] -> Maybe [Int]
doubleIdxs [] = Nothing
doubleIdxs l
    | null $ helper l 1 = Nothing
    | otherwise = Just $ helper l 1
    where
        helper [] _ = []
        helper ((a,b):ls) i
            | a == b = i : helper ls (i + 1)
            | otherwise = helper ls (i + 1)

snakeToCamel :: String -> String
snakeToCamel [] = ""
snakeToCamel [x] = [x]
snakeToCamel (x1:x2:xs)
    | x1 == '_' = toUpper x2 : snakeToCamel xs
    | otherwise = x1 : snakeToCamel (x2:xs)

removeExtremes :: Ord a => [a] -> [a]
removeExtremes [] = []
removeExtremes l = filter (/= maximum l) (filter (/= minimum l) l)

anagram :: String -> String -> Bool
anagram x1 x2 = sort x1 == sort x2

sumWithLenghtN :: Num a => Int -> [[a]] -> a
sumWithLenghtN _ [] = 0
sumWithLenghtN n (x:ls)
    | n < 0 = 0
    | length (take (n + 1) x) == n = sum (take n x) + sumWithLenghtN n ls
    | otherwise = sumWithLenghtN n ls

isSteady :: Eq b => (a -> b) -> [a] -> Bool
isSteady _ [] = True
isSteady f l = all (== (head $ map f l)) (tail $ map f l)

data Parcell = P String Double Int
    deriving(Eq, Show)

deliveryFee :: Parcell -> Maybe Double
deliveryFee (P v kg _)
    | v == "Asgard" = Just (kg * 100)
    | v == "Midgard" = Just (kg * 10)
    | v == "Vanaheim" = Just (kg * 80)
    | v == "Alfheim" = Just (kg * 50)
    | otherwise = Nothing

delivery :: [Parcell] -> Double
delivery [] = 0
delivery ((P v kg uv):ls)
    | isJust (deliveryFee (P v kg uv)) = fromJust (deliveryFee (P v kg uv)) + fromIntegral uv + delivery ls
    | otherwise = delivery ls