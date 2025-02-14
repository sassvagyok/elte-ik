type Apple = (Bool, Int)
type Tree = [Apple]
type Garden = [Tree]

ryuksApples :: Garden -> Int
ryuksApples garden = sum (map helper garden)
    where
        helper tree = length (filter ripeReach tree)
        ripeReach (r,m) = r && m <= 3

ryuksApples' :: Garden -> Int
ryuksApples' [] = 0
ryuksApples' (x:xs) = helper x + ryuksApples' xs
    where
        helper [] = 0
        helper ((r, m):xs)
            | r && m <= 3 = 1 + helper xs
            | otherwise = helper xs

doesContain :: String -> String -> Bool
doesContain _ [] = False
doesContain [] _ = True
doesContain (x:xs) (y:ys)
    | x == y = doesContain xs ys
    | otherwise = doesContain (x:xs) ys

barbie :: [String] -> String
barbie szoknya = helper szoknya 1
    where
        helper [] _ = "farmer"
        helper (x:xs) ind
            | even ind && x /= "fekete" = x
            | x == "rozsaszin" = "rozsaszin"
            | otherwise = helper xs (ind + 1)

firstValid :: [a -> Bool] -> a -> Maybe Int
firstValid p v = helper p v 0
    where
        helper [] _ _ = Nothing
        helper (x:xs) v ind
            | x v = Just ind
            | otherwise = helper xs v (ind + 1)

combineListsIf :: (a -> b -> Bool) -> (a -> b -> c) -> [a] -> [b] -> [c]
combineListsIf _ _ [] _ = []
combineListsIf _ _ _ [] = []
combineListsIf p f (x:xs) (y:ys)
    | p x y = f x y : combineListsIf p f xs ys
    | otherwise = combineListsIf p f xs ys

data Line = Tram Integer [String] | Bus Integer [String]
    deriving(Eq, Show)

whichBusStop :: String -> [Line] -> [Integer]
whichBusStop _ [] = []
whichBusStop str (x:xs)
    | helper str x = vehichleId x : whichBusStop str xs
    | otherwise = whichBusStop str xs
    where
        helper str (Bus _ stop) = str `elem` stop
        helper _ _ = False

        vehichleId (Bus id _) = id