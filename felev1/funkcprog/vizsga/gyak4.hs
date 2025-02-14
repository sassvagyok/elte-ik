import Data.Char
import Data.List
import Data.Maybe
import Data.Either

-- 3.
isSingleton :: [a] -> Bool
isSingleton l = length l == 1

exactly2OrAtLeast4 :: [a] -> Bool
exactly2OrAtLeast4 l
    | length l == 2 = True
    | length l >= 4 = True
    | otherwise = False

firstTwoElements :: [a] -> [a]
firstTwoElements l
    | length l < 2 = []
    | otherwise = take 2 l

withoutThird :: [a] -> [a]
withoutThird l
    | length l < 3 = l
    | otherwise = take 2 l ++ drop 3 l

onlySingletons :: [[a]] -> [[a]]
onlySingletons ls = [l | l <- ls, length l == 1]

compress :: (Eq a, Num b) => [a] -> [(a,b)]
compress xs = [(head g, fromIntegral (length g)) | g <- group xs]

decompress :: Integral b => [(a,b)] -> [a]
decompress xs = concat [replicate (fromIntegral n) x | (x, n) <- xs]

-- 4.
mountain :: Integral i => i -> String
mountain 0 = ""
mountain n = mountain (n - 1) ++ replicate (fromIntegral n) '#' ++ "\n"

countAChars :: Num i => String -> i
countAChars [] = 0
countAChars ('a':xs) = 1 + countAChars xs
countAChars (_:xs) = countAChars xs

lucas :: (Integral a, Num b) => a -> b
lucas 0 = 2
lucas 1 = 1
lucas n = lucas (n - 1) + lucas (n - 2)

longerThan :: Integral i => [a] -> i -> Bool
longerThan _ n | n < 0 = True
longerThan [] n = n < 0
longerThan (_:xs) n = n <= 0 || longerThan xs (n - 1)

merge :: [a] -> [a] -> [a]
merge [] [] = []
merge (x:xs) (y:ys) = x : y : merge xs ys
merge (x:xs) [] = x : xs
merge [] (y:ys) = y : ys

-- 5.
toUpperThird :: String -> String
toUpperThird (x:y:z:zs) = x : y : toUpper z : zs
toUpperThird l = l

isSorted :: Ord a => [a] -> Bool
isSorted [] = True
isSorted [x] = True
isSorted (x:y:ys) = x <= y && isSorted (y:ys)

(!!!) :: Integral b => [a] -> b -> a
l !!! n
    | n >= 0 = helper l n
    | otherwise = helper (reverse l) (-n - 1)
    where
        helper (x:_) 0 = x
        helper (_:xs) i = helper xs (i - 1)

mightyGale :: (Num a, Ord b, Num b, Integral c) => [(String, a, b, c)] -> String
mightyGale [] = ""
mightyGale ((a,b,c,d):ls)
    | c > 110 = a
    | otherwise = mightyGale ls

doubleElements :: [a] -> [a]
doubleElements [] = []
doubleElements (x:xs) = x : x : doubleElements xs

deleteDuplicateSpaces :: String -> String
deleteDuplicateSpaces l = unwords $ words l

-- 6.
splitOn :: Eq a => a -> [a] -> [[a]]
splitOn _ [] = [[]]
splitOn e (x:xs)
    | x == e = [] : splitOn e xs
    | otherwise = (x : head (splitOn e xs)) : tail (splitOn e xs)

emptyLines :: Num a => String -> [a]
emptyLines l = helper l 1
    where
        helper [] _ = []
        helper (x:xs) ind
            | x == '\n' = ind : helper xs (ind + 1)
            | otherwise = helper xs (ind + 1)

csv :: String -> [[String]]
csv input = map (splitOn ',') (splitOn '\n' input)

-- 7.
data TriBool = Yes | Maybe | No
    deriving(Eq, Show)

instance Ord TriBool where
    compare Yes Yes = EQ
    compare Yes _ = GT
    compare _ Yes = LT
    compare Maybe Maybe = EQ
    compare Maybe No = GT
    compare No Maybe = LT
    compare No No = EQ

triAnd :: TriBool -> TriBool -> TriBool
triAnd No _ = No
triAnd _ No = No
triAnd Maybe _ = Maybe
triAnd _ Maybe = Maybe
triAnd Yes Yes = Yes

triOr :: TriBool -> TriBool -> TriBool
triOr Yes _ = Yes
triOr _ Yes = Yes
triOr Maybe _ = Maybe
triOr _ Maybe = Maybe
triOr No No = No

incMonotonityTest :: (Integral i, Ord a) => i -> [a] -> TriBool
incMonotonityTest n l
    | n <= 0 = Yes
    | null l = Yes
    | otherwise = helper n l
    where
        helper _ [] = Yes
        helper 1 [_] = Yes
        helper n (x1:x2:xs)
            | x1 > x2 = No
            | n == 1 && null xs = Yes
            | n == 1 && not (null xs) = Maybe
            | otherwise = helper (n - 1) (x2:xs)

data GolfScore = Ace | Albatross | Eagle | Birdie | Par | Bogey Integer
    deriving(Eq, Show)

score :: Integer -> Integer -> GolfScore
score _ 1 = Ace
score l u
    | l < u = Bogey (u - l)
    | l == u = Par
    | (l - 3) >= u = Albatross
    | l - u == 2 = Eagle
    | l - u == 1 = Birdie

data Time = T Int Int
    deriving(Eq, Show)

t :: Int -> Int -> Time
t ora perc
    | ora < 24 && ora > -1 && perc < 60 && perc > -1 = T ora perc
    | otherwise = error "Bakker"

-- 8.
dropMaybes :: [Maybe a] -> [a]
dropMaybes [] = []
dropMaybes (x:xs)
    | isNothing x = dropMaybes xs
    | otherwise = fromJust x : dropMaybes xs

divHead :: Integral a => [a] -> a -> Maybe a
divHead [] _ = Nothing
divHead (x:xs) d
    | d == 0 = Nothing
    | otherwise = Just (x `div` d)

type ErrorOr a = Either String a

divHead' :: Integral a => [a] -> a -> ErrorOr a
divHead' [] _ = Left "ures"
divHead' (x:xs) d
    | d == 0 = Left "0 osztas"
    | otherwise = Right (x `div` d)

data Error = ErrorEmptyList | ErrorDivideByZero
    deriving(Show, Eq)

divHead'' :: Integral a => [a] -> a -> Either Error a
divHead'' [] _ = Left ErrorEmptyList
divHead'' (x:xs) d
    | d == 0 = Left ErrorDivideByZero
    | otherwise = Right (x `div` d)

elemIndex' :: (Eq a, Num b) => a -> [a] -> Maybe b
elemIndex' c str = helper c str 0
    where
        helper _ [] _ = Nothing
        helper c (x:xs) i
            | c == x = Just i
            | otherwise = helper c xs (i + 1)

add1OrNot :: Num a => Either a Bool -> Either a Bool
add1OrNot (Left x) = Left (x + 1)
add1OrNot (Right b) = Right (not b)

splitEither :: [Either a b] -> ([a],[b])
splitEither [] = ([],[])
splitEither (Left a:xs) = let (as, bs) = splitEither xs in (a: as, bs)
splitEither (Right b:xs) = let (as, bs) = splitEither xs in (as, b:bs)

-- 12.
dropEveryNth :: Integral i => i -> [a] -> [a]
dropEveryNth _ [] = []
dropEveryNth n l = helper n l 1
    where
        helper _ [] _ = []
        helper n (x:xs) i
            | n == i = helper n xs 1
            | otherwise = x : helper n xs (i + 1)

positiveIntegers ((Left n):xs)
    | n > 0 = Just (round n) : positiveIntegers xs
    | otherwise = Nothing : positiveIntegers xs
positiveIntegers ((Right n):xs)
    | n > 0 = Just n : positiveIntegers xs
    | otherwise = Nothing : positiveIntegers xs
positiveIntegers [] = []