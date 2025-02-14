module Test where

a :: Int
a = 2

f :: Int -> Int -> Int
f a b = a + b

greater :: Int -> Int -> Bool
greater a b = (>) a b
--greater a b = a > b
--greater = (>)