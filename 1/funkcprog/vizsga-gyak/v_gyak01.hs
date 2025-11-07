type Apple = (Bool, Int)
type Tree = [Apple]
type Garden = [Tree]

ryuksApples :: Garden -> Int
ryuksApples garden = sum (map countApplesInTree garden)
  where
    countApplesInTree tree = length (filter isReachableAndRipe tree)
    isReachableAndRipe (ripe, height) = ripe && height <= 3

doesContain :: String -> String -> Bool
doesContain [] _ = True
doesContain _ [] = False
doesContain (x:xs) (y:ys)
    | x == y = doesContain xs ys
    | otherwise = doesContain (x:xs) ys

barbie :: [String] -> String
barbie szoknyak = szoknyaKeres szoknyak 1
    where
        szoknyaKeres [] _ = "farmer"
        szoknyaKeres (x:xs) ind
            | x == "rozsaszin" = "rozsaszin"
            | even ind && x /= "fekete" = x
            | otherwise = szoknyaKeres xs  (ind + 1)

firstValid :: [a -> Bool] -> a -> Maybe Int
firstValid p v = findIndex p v 0
    where
        findIndex [] _ _ = Nothing
        findIndex (p:ps) v index
            | p v = Just index
            | otherwise = findIndex ps v (index + 1)