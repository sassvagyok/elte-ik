module Hazi3 where
import Data.List

isSingleton :: [a] -> Bool
isSingleton [x] = True
isSingleton _ = False

exactly2OrAtLeast4 :: [a] -> Bool
exactly2OrAtLeast4 [_,_,_,_] = True
exactly2OrAtLeast4 [_,_] = True
exactly2OrAtLeast4 _ = False

firstTwoElements :: [a] -> [a]
firstTwoElements (x:y:_) = [x,y]
firstTwoElements _ = []

withoutThird :: [a] -> [a]
withoutThird (x:y:z:tobbi) = x : y : tobbi
withoutThird (x:y) = x : y

onlySingletons :: [[a]] -> [[a]]
onlySingletons y = [x | x <- y, length x == 1]

compress :: (Eq a, Num b) => [a] -> [(a,b)]
compress x = [(head y, fromIntegral (length y)) | y <- group x]

decompress :: Integral b => [(a,b)] -> [a]
decompress x = [y | (y, n) <- x, _ <- [1..n]]