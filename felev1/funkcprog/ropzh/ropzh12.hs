removeSpaces :: String -> String
removeSpaces = reverse . dropWhile (== ' ') . reverse