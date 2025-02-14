data Point = Point Int Int
  deriving(Eq)

create' :: Int -> Int -> Point
create' x y = Point x y