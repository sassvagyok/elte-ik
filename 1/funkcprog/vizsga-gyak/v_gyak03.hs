import Data.Char

concatTripleString :: ([Char], [Char], [Char]) -> [Char]
concatTripleString (x,y,z) = x ++ y ++ z

mods :: Integral a => a -> a -> Maybe (a, a)
mods _ 0 = Nothing
mods 0 _ = Nothing
mods x y = Just (x `mod` y, y `mod` x)

dropEmpties :: Eq a => [[a]] -> [[a]]
dropEmpties [] = []
dropEmpties (x:xs)
    | null x = dropEmpties xs
    | otherwise = x : dropEmpties xs

createChain :: Integer -> String
createChain = helper
    where
        helper n
            | n <= 0 = ""
            | otherwise = createChain (n - 1)  ++ "(" ++ show n ++ ")"

createChain' :: Integer -> String
createChain' n
    | n <= 0 = ""
    | otherwise = createChain (n - 1)  ++ "(" ++ show n ++ ")"

aLtErNaTiNgCaPs :: String -> String
aLtErNaTiNgCaPs str = helper str 1
    where
        helper [] _ = []
        helper (x:xs) ind
            | even ind = toUpper x : helper xs (ind + 1)
            | otherwise = toLower x : helper xs (ind + 1)

result :: [Maybe Bool] -> Int -> Bool
result l min = length (helper l) >= min
    where
        helper [] = []
        helper (x:xs)
            | x == Just True = x : helper xs
            | x == Just False = init (helper xs)
            | otherwise = helper xs

maximumIF :: Ord a => (a -> Bool) -> [a] -> Maybe a
maximumIF f l = helper l Nothing
    where
        helper [] ind = ind
        helper (x:xs) ind
            | f x = helper xs (Just (maybe x (max x) ind))
            | otherwise = helper xs ind

fillBlanks :: String -> String -> String
fillBlanks [] [] = []
fillBlanks [] _ = []
fillBlanks _ [] = []
fillBlanks (x:xs) (y:ys)
    | x == '_' = y : fillBlanks xs ys
    | otherwise = x : fillBlanks xs (y:ys)