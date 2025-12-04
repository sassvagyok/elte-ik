from functools import reduce

# 1.
print("1:")
st = "Eurt si em tuoba yas yeht gnihtyrevE"
print(st[::-1])

# 2.
print("2:")
d = { "Monday" : 8, "Tuesday" : 8, "Wednesday" : 12, "Thursday" : 0, "Friday" : 0}
l = []
for k, v in d.items():
    if v < 8:
        l.append(k)
print(l)

# 3.
print("3:")
lst = [33, 22, 33, 21, 33, 44, 33, 11, 11, 2, 1]
s = {33, 22, 45, 47, 42, 52}
lst_set = set(lst)
halmaz = lst_set & s
unio = lst_set | s
kulonbseg = lst_set - s
print(halmaz, unio, kulonbseg)

# 4.
print("4:")
def joinLoo(*str):
    final = ""
    for s in str:
        if "lo" in s.lower():
            final += s

    return final
print(joinLoo("lovacska", "lovas", "asdlo", "asd"))

joinLo = lambda *str : "".join(s for s in str if "lo" in s.lower())
print(joinLo("lovacska", "lovas", "asdlo", "asd"))

# 5.
print("5:")
countHm = lambda str : str.lower().count("hm")
print(countHm("hmhmhmhm"))

# 6.
print("6:")
shorterThan5 = lambda lst : [x for x in lst if len(x) < 5]
print(shorterThan5(["macska", "kutya", "asd", "a"]))

# 7.
print("7:")
sortD = lambda d : dict(sorted(d.items(), key=lambda x : x[1]))
print(sortD({"a": 10, "b": 2, "c": 5}))

# 9.
print("9:")
osszetett = lambda *str : ", ".join(s[3] * 3 for s in str if len(s) > 5)
print(osszetett("othosszu", "nem", "ezishosszu"))

# 10.
class Song:
    def __init__(self, artist, title, genre, length):
        if not isinstance(artist, str):
            raise TypeError("Artist: string")
        self.artist = artist
        if not isinstance(title, str):
            raise TypeError("Title: string")
        self.title = title
        if not isinstance(genre, str):
            raise TypeError("Genre: string")
        self.genre = genre
        if not isinstance(length, int):
            raise TypeError("Length: int")
        self.length = length

    def __eq__(self, other):
        if not isinstance(other, Song):
            raise TypeError("Other: Song")
        
        return self.length == other.length
    
    def __lt__(self, other):
        if not isinstance(other, Song):
            raise TypeError("Other: Song")
        
        return self.length < other.length
    
    def __str__(self):
        perc = 0
        length = self.length
        while (True):
            if length - 60 >= 0:
                perc += 1
                length -= 60
            else:
                break
        
        mp = self.length - perc * 60

        return f"{self.artist} - {self.title} ({self.genre}) ({perc}:{mp})"
    
class Playlist:
    genre = None
    songs = []

    def __init__(self, genre):
        if not genre:
            genre = None
            return
        if not isinstance(genre, str):
            raise TypeError("genre: string")
        self.genre = genre

    def __str__(self):
        ordered_songs = sorted(self.songs)

        final = ""
        for s in ordered_songs:
            final += str(s) + "\n"

        return final
    
    def add_songs(self, *songs):
        for song in songs:
            if song.genre == self.genre or self.genre == None:
                self.songs.append(song)

song1 = Song("A", "B", "C", 120)
song2 = Song("D", "E", "F", 178)
pl = Playlist(None)
pl.add_songs(song1, song2)
print(pl)