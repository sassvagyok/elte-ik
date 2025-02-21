module NagyBead where

import Data.Either
import Data.Maybe
import Text.Read (readMaybe)
import Data.Char (isSpace, isDigit)


-- Feladat: Megvalósítási terv

data Dir = InfixL | InfixR
    deriving(Show, Eq, Ord)  

data Tok a = BrckOpen | BrckClose | TokLit a | TokBinOp (a -> a -> a) Char  Int  Dir | TokFun (a -> a) String

instance Show a => Show (Tok a) where
    show BrckOpen = "BrckOpen"
    show BrckClose = "BrckClose"
    show (TokLit a) = "TokLit " ++ show a
    show (TokBinOp _ ch kot dir) = "TokBinOp " ++ show ch ++ " " ++ show kot ++ " " ++ show dir
    show (TokFun _ f1) = "TokFun " ++ f1

instance Eq a => Eq (Tok a) where
    BrckOpen == BrckOpen = True
    BrckClose == BrckClose = True
    (TokLit a) == (TokLit b) = a == b
    (TokBinOp _ ch1 kot1 dir1) == (TokBinOp _ ch2 kot2 dir2) = ch1 == ch2 && kot1 == kot2 && dir1 == dir2
    (TokFun _ f1) == (TokFun _ f2) = f1 == f2
    _ == _ = False

basicInstances = 0 -- Mágikus tesztelőnek kell ez, NE TÖRÖLD!

type OperatorTable a = [(Char, (a -> a -> a, Int, Dir))]

tAdd, tMinus, tMul, tDiv, tPow :: (Floating a) => Tok a
tAdd = TokBinOp (+) '+' 6 InfixL
tMinus = TokBinOp (-) '-' 6 InfixL
tMul = TokBinOp (*) '*' 7 InfixL
tDiv = TokBinOp (/) '/' 7 InfixL
tPow = TokBinOp (**) '^' 8 InfixR

operatorTable :: (Floating a) => OperatorTable a
operatorTable =
    [ ('+', ((+), 6, InfixL))
    , ('-', ((-), 6, InfixL))
    , ('*', ((*), 7, InfixL))
    , ('/', ((/), 7, InfixL))
    , ('^', ((**), 8, InfixR))
    ]


-- Feladat: Operátorrá alakítás

operatorFromChar :: OperatorTable a -> Char -> Maybe (Tok a)
operatorFromChar [] _ = Nothing
operatorFromChar ((c1, (x, y, z)):ls) c2
    | c1 == c2 = Just (TokBinOp x c1 y z)
    | otherwise = operatorFromChar ls c2


getOp :: (Floating a) => Char -> Maybe (Tok a)
getOp = operatorFromChar operatorTable


-- Feladat: String-ek tokenizálása

parseTokens :: Read a => OperatorTable a -> String -> Maybe [Tok a]
parseTokens table str
  | not (validFormat str) = Nothing
  | otherwise = fmap concat . traverse parseToken $ parseBrackets str
  where
    parseBrackets [] = []
    parseBrackets (x:xs)
      | x == '(' || x == ')' = [x] : parseBrackets xs
      | isSpace x = parseBrackets xs
      | otherwise = let word = x : collectTokens xs
                        rest = drop (length word) (x:xs)
                    in word : parseBrackets rest

    collectTokens [] = []
    collectTokens (x:xs)
      | isSpace x || x == '(' || x == ')' = []
      | otherwise = x : collectTokens xs

    parseToken word
      | all (== '(') word = Just (replicate (length word) BrckOpen)
      | all (== ')') word = Just (replicate (length word) BrckClose)
      | [c] <- word, Just op <- operatorFromChar table c = Just [op]
      | Just lit <- readMaybe word = Just [TokLit lit]
      | otherwise = Nothing

    validFormat [] = True
    validFormat [_] = True
    validFormat (x:y:xs)
      | x == '(' && isDigit y = False
      | isDigit x && y == ')' = False
      | otherwise = validFormat (y:xs)

parse :: String -> Maybe [Tok Double] 
parse = parseTokens operatorTable


-- Feladat: Alap Shunting Yard algoritmus

shuntingYardBasic :: Eq a => [Tok a] -> ([a], [Tok a])
shuntingYardBasic = shuntingBasicHelper [] []
  where
    shuntingBasicHelper lits ops [] = (lits, ops)
    shuntingBasicHelper lits ops (TokLit x : xs) = shuntingBasicHelper (x : lits) ops xs
    shuntingBasicHelper lits ops (BrckOpen : xs) = shuntingBasicHelper lits (BrckOpen : ops) xs
    shuntingBasicHelper lits ops (TokBinOp f c p d : xs) = shuntingBasicHelper lits (TokBinOp f c p d : ops) xs
    shuntingBasicHelper lits ops (BrckClose : xs) =
      let (beforeOpen, afterOpen) = break (== BrckOpen) ops
          newLits = foldl (flip applyOperator) lits beforeOpen
      in shuntingBasicHelper newLits (tail afterOpen) xs

    applyOperator (TokBinOp f _ _ _) (x:y:ys) = f y x : ys

parseAndEval :: (String -> Maybe [Tok a]) -> ([Tok a] -> ([a], [Tok a])) -> String -> Maybe ([a], [Tok a])
parseAndEval parse eval input = maybe Nothing (Just . eval) (parse input)

syNoEval :: String -> Maybe ([Double], [Tok Double])
syNoEval = parseAndEval parse shuntingYardBasic

syEvalBasic :: String -> Maybe ([Double], [Tok Double])
syEvalBasic = parseAndEval parse (\t -> shuntingYardBasic $ BrckOpen : (t ++ [BrckClose]))


-- Feladat: Az algoritmus javítása - kötési erősségek és irányok figyelembevétele

shuntingYardPrecedence :: Eq a => [Tok a] -> ([a], [Tok a])
shuntingYardPrecedence = shuntingPrecedenceHelper [] []
  where
    shuntingPrecedenceHelper lits ops [] = (lits, ops)
    shuntingPrecedenceHelper lits ops (TokLit x : xs) = shuntingPrecedenceHelper (x : lits) ops xs
    shuntingPrecedenceHelper lits ops (BrckOpen : xs) = shuntingPrecedenceHelper lits (BrckOpen : ops) xs
    shuntingPrecedenceHelper lits ops (TokBinOp f c p d : xs) =
      let op = TokBinOp f c p d
          (toEval, remainingOps) = span (shouldEval op) ops
          newLits = foldl (flip applyOp) lits toEval
      in shuntingPrecedenceHelper newLits (op : remainingOps) xs
      
    shuntingPrecedenceHelper lits ops (BrckClose : xs) =
      let (beforeOpen, afterOpen) = break (== BrckOpen) ops
          newLits = foldl (flip applyOp) lits beforeOpen
      in shuntingPrecedenceHelper newLits (tail afterOpen) xs

    shouldEval (TokBinOp _ _ p1 d1) (TokBinOp _ _ p2 _) = p2 > p1 || (d1 == InfixL && p2 == p1)
    shouldEval _ _ = False

    applyOp (TokBinOp f _ _ _) (x:y:ys) = f y x : ys

syEvalPrecedence :: String -> Maybe ([Double], [Tok Double])
syEvalPrecedence = parseAndEval parse (\t -> shuntingYardPrecedence $ BrckOpen : (t ++ [BrckClose]))


-- Feladat: Hibatípus definiálása

data ShuntingYardError = OperatorOrClosingParenExpected
  | LiteralOrOpeningParenExpected
  | NoClosingParen
  | NoOpeningParen
  | ParseError
  deriving(Eq, Show)

type ShuntingYardResult a = Either ShuntingYardError a

-- eqError-t vedd ki a kommentből, ha megcsináltad az 1 pontos "Hibatípus definiálása" feladatot
eqError = 0 -- Mágikus tesztelőnek szüksége van rá, NE TÖRÖLD!

{-
-- Ezt akkor vedd ki a kommentblokkból, ha a 3 pontos "A parser és az algoritmus újradefiniálása" feladatot megcsináltad.
parseAndEvalSafe ::
    (String -> ShuntingYardResult [Tok a]) ->
    ([Tok a] -> ShuntingYardResult ([a], [Tok a])) ->
    String -> ShuntingYardResult ([a], [Tok a])
parseAndEvalSafe parse eval input = either Left eval (parse input)

sySafe :: String -> ShuntingYardResult ([Double], [Tok Double])
sySafe = parseAndEvalSafe
  (parseSafe operatorTable)
  (\ts -> shuntingYardSafe (BrckOpen : ts ++ [BrckClose]))
-}


-- Feladat: Függvénytábla és a típus kiegészítése

type FunctionTable a = [(String, a -> a)]

-- Ezt akkor vedd ki a kommentblokkból, ha az 1 pontos "Függvénytábla és a típus kiegészítése" feladatot megcsináltad.
tSin, tCos, tLog, tExp, tSqrt :: Floating a => Tok a
tSin = TokFun sin "sin"
tCos = TokFun cos "cos"
tLog = TokFun log "log"
tExp = TokFun exp "exp"
tSqrt = TokFun sqrt "sqrt"

functionTable :: (RealFrac a, Floating a) => FunctionTable a
functionTable =
    [ ("sin", sin)
    , ("cos", cos)
    , ("log", log)
    , ("exp", exp)
    , ("sqrt", sqrt)
    , ("round", (\x -> fromIntegral (round x :: Integer)))
    ]

{-
-- Ezt akkor vedd ki a kommentblokkból, ha a 2 pontos "Függvények parse-olása és kiértékelése" feladatot megcsináltad.
syFun :: String -> Maybe ([Double], [Tok Double])
syFun = parseAndEval
  (parseWithFunctions operatorTable functionTable)
  (\t -> shuntingYardWithFunctions $ BrckOpen : (t ++ [BrckClose]))
-}

{-
-- Ezt akkor vedd ki a kommentblokkból, ha minden más feladatot megcsináltál ez előtt.
syComplete :: String -> ShuntingYardResult ([Double], [Tok Double])
syComplete = parseAndEvalSafe
  (parseComplete operatorTable functionTable)
  (\ts -> shuntingYardComplete (BrckOpen : ts ++ [BrckClose]))
-}