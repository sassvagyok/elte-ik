module Test where

data MaybeInt = Nothing0 | Just0 Int
    deriving(Show)

head' :: [Int] -> Int
head' (x:_) = x

safeHead' :: [Int] -> MaybeInt
safeHead'[] = Nothing0
safeHead' (x:_) = Just0 x


-- minden típusra működik
data Maybe' x = Nothing' | Just' x
    deriving(Show)

safeHead'' :: [Char] -> Maybe' Char
safeHead'' [] = Nothing'
safeHead'' (x:_) = Just' x

-- minden típusra működik
safeHead''' :: [a] -> Maybe' a
safeHead''' [] = Nothing'
safeHead''' (x:_) = Just' x


one :: Int
one = 1

m :: Maybe' Int
m = Just' 10

m' :: Maybe' Int
m' = Nothing'

m'' :: Maybe' Int
m'' = m''


saveDiv :: Int -> Int -> Maybe Int
saveDiv _ 0 = Nothing
saveDiv x y = Just (x `div` y)

add :: Maybe Int -> Maybe Int -> Maybe Int
add (Just x) (Just y) = Just (x + y)
add _ _ = Nothing

divHead :: [Int] -> Int -> Maybe Int
divHead [] _ = Nothing
divHead (x:_) y
    | y == 0 = Nothing
    | otherwise = Just (x `div` y)


-- data Maybe'' a b = Nothing'' b | Just'' a
--     deriving(Show)

-- data Either a b = Left a | Right b

divHead' :: [Int] -> Int -> Either String Int
divHead' [] _ = Left "Empty list"
divHead' (x:_) y
    | y == 0 = Left "Div by 0"
    | otherwise = Right (x `div` y)


data BinTree a = Leaf a | Node a (BinTree a) (BinTree a)
    deriving(Show)

t :: Num a => BinTree a
t = Node 4
    (Node 3
        (Node 1 (Leaf 10) (Leaf 20))
        (Leaf 0))
    (Node 2
        (Leaf 3)
        (Node 7 (Leaf 5) (Leaf 9)))

add1Tree :: Num a => BinTree a -> BinTree a
add1Tree (Leaf x) = Leaf (x + 1)
add1Tree (Node x left right) = Node (x + 1) (add1Tree left) (add1Tree right)


-- data List_ a = Empty | Cons a (List_ a)

-- list1 :: List_ Int
-- list1 = Cons 1 ()