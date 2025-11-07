--module Test3 where

one :: Int --32 v 64bit
one = 1

three :: Integer --amennyi memoria van
three = 3

four :: Integer
four = fromIntegral one + three

inc :: Int -> Int 
--inc = (+1)
--inc :: Num a => a -> a
inc x = x + 1

add :: Int -> Int -> Int
add x y = x + y
--infix[l/r] 6 `add`
infixl 6 `add`

(*+) :: Integer -> Integer -> Integer
x *+ y = 2 * x + y
--infixl 6 *+

--Double -> Integer
doubleToInteger :: Double -> Integer
doubleToInteger = round

not' :: Bool -> Bool
not' False = True
not' _ = False

(&&&) :: Bool -> Bool -> Bool
(&&&) True True = True
(&&&) _ _ = False

(|||) :: Bool -> Bool -> Bool
(|||) False False = False
(|||) _ _ = True

isZero :: Integer -> Bool
isZero 0 = True
isZero _ = False

--data (a,b) = (a,b)

--fst
--snd

incBoth :: (Integer, Double) -> (Integer, Double)
incBoth pr = (fst pr + 1, snd pr + 1)

incBoth' :: (Integer, Double) -> (Integer, Double)
incBoth' (x, y) = (x + 1, y + 1)

p (x, _) = x -- (1, (1 (2, 3))) == (1, _)
addp (x, y) = x


--g :: (a,b) -> a

fn :: Eq a => a -> a -> Bool
fn x y = x == y

add2 :: Num a => a -> a -> a
add2 a b = a + b