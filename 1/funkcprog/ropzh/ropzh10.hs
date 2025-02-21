l = \(x, y) -> x * y

mapt :: (Num a) => ((a, a) -> a) -> [(a, a)] -> [a]
mapt f [] = []
mapt f ((x, y):xs) = f (x, y) : mapt f xs