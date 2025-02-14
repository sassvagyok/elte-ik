module Lesson2 where

-- f (g x)
-- f . g x
-- ((+1) . (*10)) 10 == 101

id' :: a -> a
id' x = x

-- id 10 == 10

const' :: a -> b -> a
const' x _ = x

-- const2 2 "alma" == "alma"
const2 :: a -> b -> b
--const2 _ x = x
const2 = const' id'

-- const5 2 == 5
const5 :: b -> Int
const5 = const' 5

flip' :: (a -> b -> c) -> b -> a -> c
flip' f x y = f y x

const2' :: a -> b -> b
const2' = flip' const'

curry' :: ((a, b) -> c) -> a -> b -> c
curry' f x y = f (x, y)

uncurry' :: (a -> b -> c) -> (a, b) -> c
uncurry' f (x, y) = f x y


-- take 10 (iterate' (+1) 0) == [0,1,2,3,4,5,6,7,8,9]

-- iterate' (+1) 0
iterate' :: (a -> a) -> a -> [a]
iterate' f x = x : iterate' f (f x)

repeat' :: a -> [a]
repeat' x = iterate' id' x


-- ((+1) ° (*10)) 10 == 101
(°) :: (b -> c) -> (a -> b) -> a -> c
(°) f g x = f (g x)
infixr 9 °

-- [1,11,111,1111,11111,...]
numbersMadeOfOnes :: [Integer]
--numbersMadeOfOnes = iterate' (\x -> x * 10 + 1) 1
numbersMadeOfOnes = iterate' ((+1) . (*10)) 1

times2plus1 :: Num a => a -> a
--times2plus1 x = 2 * x + 1
times2plus1 = (+1) . (*2)


any' :: (a -> Bool) -> [a] -> Bool
any' p xs = or (map p xs)

any'' :: (a -> Bool) -> [a] -> Bool
any'' p = or . map p

any''' :: (a -> Bool) -> [a] -> Bool
any''' = (or .) . map --ilyet ne, tul bonyolult

(|>) :: a -> (a -> b) -> b
(|>) x f = f x
infixl 9 |>

dropSpace :: String -> String
dropSpace s = dropWhile (\c -> c == ' ') s

trim' :: String -> String
--trim' s = reverse (dropSpace (reverse (dropSpace) s))
--trim' s = (reverse . dropSpace . reverse . dropSpace) s
trim' = reverse . dropWhile (== ' ') . reverse . dropWhile (== ' ')

maximumOfMinimums :: (Ord a) => [[a]] -> a
maximumOfMinimums = maximum . map minimum