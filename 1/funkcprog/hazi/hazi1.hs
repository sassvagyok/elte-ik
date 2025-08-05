module Hazi1 where

intExpr1 :: Int
intExpr1 = 0
intExpr2 :: Int
intExpr2 = 1
intExpr3 :: Int
intExpr3 = 2

charExpr1 :: Char
charExpr1 = 'a'
charExpr2 :: Char
charExpr2 = 'b'
charExpr3 :: Char
charExpr3 = 'c'

boolExpr1 :: Bool
boolExpr1 = True
boolExpr2 :: Bool
boolExpr2 = False
boolExpr3 :: Bool
boolExpr3 = True

inc :: Int -> Int
inc a = a + 1

triple :: Int -> Int
triple a = a * 3

nulla :: Int
nulla = 0

thirteen1 ::  Int
thirteen1 = (inc (triple (inc (inc (inc (inc nulla))))))

thirteen2 ::  Int
thirteen2 = (inc (inc (inc (inc (triple (triple (inc nulla)))))))

thirteen3 ::  Int
thirteen3 = (inc (inc (inc (inc (triple (inc (inc (inc nulla))))))))

cmpRem5Rem7 :: Int -> Bool
cmpRem5Rem7 a = (a `mod` 5) > (a `mod` 7)

foo :: Int -> Bool -> Bool
foo a b = if (a `mod` 2) > 0 then not b else b

bar :: Bool -> Int -> Bool
bar a b = foo b a