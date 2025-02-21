module Ora2 where
import Data.List
import Data.Ord

on' :: (b -> b -> c) -> (a -> b) -> a -> a -> c
on' f g x y = f (g x) (g y)

add :: Int -> Int -> Int
add x y = x + y

square :: Int -> Int
square x = x * x

-- compare :: Orda a => a -> a -> Ordering
-- data Odering = LT | EQ | GT

minimumBy' :: (a -> a -> Ordering) -> [a] -> a
-- minimumBy' cp (x:xs) = foldl minBy x xs
minimumBy' cp = foldl1 minBy
    where
        minBy x y 
            | cp x y == GT = y
            | otherwise = x

minimum' :: Ord a => [a] -> a
minimum' = minimumBy' compare


sortBy' :: (a -> a -> Ordering) -> [a] -> [a]
sortBy' cp = foldr insert []
    where
        insert x [] = [x]
        insert x (y:ys)
            | cp x y == GT = y : insert x ys
            | otherwise = x : y : ys

sort' :: Ord a => [a] -> [a]
sort' = sortBy' compare


sortWords' :: String -> String
sortWords' = unwords . sortBy' (\x y -> compare (length x) (length y)) . words --ez kell a parseTokenhez + segedfuggvenyek minden esethez (kb 40. percnel)


minIndex :: (Ord a, Num b, Enum b) => [a] -> b
minIndex l = fst $ minimumBy' (comparing snd) (zip [0..] l)