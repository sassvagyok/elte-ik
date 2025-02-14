orFoldr :: [Bool] -> Bool
orFoldr = foldr (||) False