module Hazi2 where

addV :: (Double,Double) -> (Double,Double) -> (Double,Double)
addV (x1, y1) (x2, y2) = (x2 + x1, y2 + y1)

subV :: (Double,Double) -> (Double,Double) -> (Double,Double)
subV (x1, y1) (x2, y2) = (x2 - x1, y2 - y1)

scaleV :: Double -> (Double,Double) -> (Double,Double)
scaleV s (x, y) = (s * x, s * y)

scalar :: (Double,Double) -> (Double,Double) -> Double
scalar (x1, y1) (x2, y2) = x1 * x2 + y1 * y2

divides :: Integral a => a -> a -> Bool
divides a b = (b `mod` a) == 0

add :: (Integral a, Integral b, Num c) => a -> b -> c
add a b = fromIntegral a + fromIntegral b