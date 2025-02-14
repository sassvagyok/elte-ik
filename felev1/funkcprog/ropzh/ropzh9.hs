data Pair a b = Pair a b
    deriving(Show, Eq)

swap :: Pair a b -> Pair b a
swap (Pair x y) = (Pair y x)