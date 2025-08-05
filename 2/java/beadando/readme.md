<h1>Programozási nyelvek Java 2024-25/2 Beadandó feladat</h1>
<h2>Feltételek</h2>
<ul>
<li>A megoldást <strong>a gyakorlat TMS-csoportjába kell feltölteni</strong>.
<ul>
<li>A leírás viszont nem ott, hanem az előadás TMS-csoportjában olvasható. <strong>Nem ott kell beadni</strong> a megoldást.</li>
</ul>
</li>
<li>Beadás formátuma: <strong>zip</strong>.
<ul>
<li>Ez csak a megoldás forrásfájljait tartalmazza, a megfelelő könyvtárszerkezetben.
<ul>
<li>Más fájlokat (pl. <code>.class</code>) ne tartalmazzon a zip.</li>
<li>Ha a megoldás működtetéséhez szükségesek további (pl. bemeneti) fájlok, azok a zip gyökerében legyenek.</li>
<li>⚠️ <strong>A megoldás ne tartalmazzon szükségtelen könyvtárakat.</strong> Ha például a megoldás fájljai bekerülnek egy <code>beadandó</code> könyvtárba, amit a feladat egyáltalán nem kért, az össze fogja zavarni az automatikus tesztelőt: elutasítja a megoldást arra hivatkozva, hogy az elvárt fájlok nem találhatók meg.</li>
</ul>
</li>
<li>Amennyiben a <code>7Zip</code> program telepítve van, a <code>check.cmd</code> előkészíti a feltölthető fájlt <code>solution.zip</code> néven.</li>
<li>Feltöltés után pár perccel egy autotesztelő fut le. Ha ez hibát jelez, akkor a megoldás javítandó.</li>
</ul>
</li>
<li>A beadandó általános feltételei az előadás Canvasében, a Tematika oldalon olvashatók.
<ul>
<li><strong>Csalni tilos.</strong> Aki mégis megteszi, és kiderül, elesik a tárgy teljesítésétől.</li>
<li>További részletekért lásd a Tematika oldal megfelelő aloldalát.</li>
</ul>
</li>
<li>A megoldás legyen a lehető legjobb minőségű.
<ul>
<li>A feladatban megadott neveket betűre pontosan úgy kell használni, ahogy meg vannak adva.</li>
<li>A Java nyelv szokásos konvencióit követni kell.</li>
<li>A kód szerkezete legyen világos, a változók nevei legyenek megfelelők.</li>
<li>A kód szerkezete legyen világos, a változók nevei legyenek megfelelők.</li>
<li>A tanult koncepciókat, eszközöket helyesen kell használni.</li>
</ul>
</li>
<li>Beadás határideje.
<ul>
<li>A megoldás a határidőn belül <strong>többször is beadható</strong>.
<ul>
<li><strong>Aki egyáltalán nem tölt fel megoldást, elesik a tárgy teljesítésétől.</strong></li>
<li>Az utoljára beadott megoldás kerül értékelésre.</li>
</ul>
</li>
<li><strong>A határidő éles.</strong>
<ul>
<li>Nem célszerű kicentizni az időt. Aki mégis megteszi, és lekésik akár pár perccel, csak magára vethet.</li>
</ul>
</li>
</ul>
</li>
</ul>
<h2>Parkolóház feladat</h2>
<p>Készítsünk egy egyszerűsített parkolóház szimulációt Java nyelven, amely olyan alapvető funkciókat tartalmaz, mint például járművek regisztrációja, parkolóhelyek kiosztása és autók visszakeresése. Ez egy egyszerűsített változat lesz; egy valódi rendszer fejlettebb funkciókat és optimalizációkat tartalmazna.</p>
<h2>Parkolóhelyek</h2>
<p>A <code>Space</code> osztály segítségével hozható létre a <code>ParkingLot</code>, amely a hozzárendelt <code>Car</code> objektum hivatkozását tárolja.</p>
<h3><code>Space</code> osztály műveletei</h3>
<ul>
<li><code>addOccupyingCar()</code>: A paraméterként kapott <code>Car</code> objektum hivatkozását rendeli hozzá a parkolóhelyhez, másolat készítése nélkül.</li>
<li><code>removeOccupyingCar()</code>: A tárolt <code>Car</code> hivatkozást <code>null</code>-ra állítja, ezzel eltávolítva az ott parkoló autót.</li>
<li><code>isTaken()</code>: Ellenőrzi, hogy tartozik-e már a helyhez egy <code>Car</code> hivatkozás, azaz foglalt-e az adott parkolóhely. Ha igen, igaz értéket ad vissza.</li>
</ul>
<p>Fontos, hogy ha egy nagyméretű autó érkezik, az két szomszédos helyet foglal el, és mindkét helyen ugyanaz a <code>Car</code> hivatkozás tárolódik.</p>
<h2>Jármű</h2>
<p>A <code>Car</code> osztály a parkolóházba érkező járműveket ábrázolja. A <code>ticketId</code>-t csak a kapu állíthatja be, jelezve, hogy az autó be lett engedve. A további funkciókat a strukturális tesztekben leírtak szerint kell megvalósítani.</p>
<p>A <code>Size</code> enum csak két értéket tartalmazzon: <code>SMALL</code> és <code>LARGE</code>.</p>
<p><code>ParkingLotTestSuite</code>: Tartalmazza az egész feladat tesztjeit. Győződjünk meg arról, hogy minden teszt sikeresen lefut, beleértve a strukturális és funkcionális teszteket is. A funkcionális tesztek célja annak ellenőrzése, hogy a program valóban a kívánt módon működik, nem pedig egy olyan tesztkód futtatása, amely nem teszteli érdemben az implementált algoritmusokat.</p>
<h2>A kapu</h2>
<p>A <code>Gate</code> osztály az autók regisztrálásának és eltávolításának fő kezelője, a parkolási műveleteket a megadott <code>ParkingLot</code> segítségével végzi.</p>
<h3><code>Gate</code> osztály műveletei</h3>
<ul>
<li><code>findTakenSpaceByCar()</code>: Segédfüggvény, visszaadja az adott autó által foglalt első helyet.</li>
<li><code>findAvailableSpaceOnFloor()</code>: Segédfüggvény, visszaadja az első elérhető helyet az adott autó számára. Nagyméretű autó esetén a második helyet adja vissza, biztosítva, hogy az első hely is üres legyen.</li>
<li><code>findAnyAvailableSpaceForCar()</code>: Visszaad egy tetszőleges elérhető helyet, amely alkalmas az autó befogadására, a segédfüggvény használatával.</li>
<li><code>findPreferredAvailableSpaceForCar()</code>: Visszaad egy olyan elérhető helyet, amely alkalmas az autó befogadására, a segédfüggvény használatával. Ha a preferált emeleten nincs hely, a keresés lefelé terjed a következő emeletre. Ha az is tele van, a keresés felfelé folytatódik. Ez a váltakozó minta addig tart, amíg egy elérhető helyet nem találunk, vagy amíg minden emeletet átnéztünk.</li>
<li><code>registerCar()</code>: Egy autó hozzáadása a parkolóhoz, akár a preferált helyére, akár a következő legjobb szabad helyre. Sikeres hozzáadás esetén igaz értéket ad vissza.</li>
<li><code>registerCars()</code>: Több autó regisztrációja preferenciák figyelmen kívül hagyásával, megpróbál minden autó számára szabad helyet találni. Ha egy adott autó számára nem található hely, hibaüzenetet ír ki a hiba kimenetre és áttér a következő autóra.</li>
<li><code>deRegisterCar()</code>: Ellenőrzi az autó által megadott jegyet, és eltávolítja az autó hivatkozását a parkolóhelyéről.</li>
</ul>
<h3>JUnit 5 tesztek</h3>
<p>Megjegyzés: A következő teszteknek minden járműtípusra (azaz nagy és kis autókra) ellenőrizniük kell a függvények működését.</p>
<ul>
<li><code>testFindAnyAvailableSpaceForCar()</code>: Ellenőrzi, hogy a tetszőleges hely keresése megfelelően működik.</li>
<li><code>testFindPreferredAvailableSpaceForCar()</code>: A preferált hely keresést teszteli. Paraméteres teszteset vizsgálja a különböző autótípusokat.</li>
<li><code>testRegisterCar()</code>: Az autó regisztrációjának tesztelése. Itt is paraméterezett teszteset vizsgálja, hogy a különböző autók regisztrációja megfelelően működjön.</li>
<li><code>testDeRegisterCar()</code>: Az autók eltávolításának tesztelése. Paraméterezett teszteset ellenőrzi, hogy mind a nagyméretű, mind a kisméretű autók megfelelően eltávolíthatók.</li>
</ul>
<h2>Parkolás</h2>
<p>A <code>ParkingLot</code> osztály egy mátrix formájában ábrázolja szintek alaprajzát, hasonlóan egy többszintes parkolóházhoz. A mátrix minden egyes eleme egy <code>Space</code>, ahol egy autó parkolhat.</p>
<p>A <code>ParkingLot</code> inicializálásakor, <code>IllegalArgumentException</code>-t dob ha az emeletek vagy parkolóhelyek száma kisebb mint 1. A 0. emelet a földszintnek számít, és mind a 0. emelet, mind a 0. hely úgy funkcionál, mint az összes többi; nem alkalmazunk rájuk speciális feltételeket.</p>
<h3><code>ParkingLot</code> osztály műveletei</h3>
<ul>
<li>
<p><code>getFloorPlan</code>: Visszaadja a teljes emelet alaprajzát, egyszerű getter függvényként.</p>
</li>
<li>
<p>Az alaprajz szöveges ábrázolásában az <code>X</code> a szabad helyeket, az <code>S</code> a kis autók által foglalt helyeket és az <code>L</code> a nagy autók által foglalt helyeket jelöli.</p>
<pre><code>X X X S X
X S S L L
S X S L L
L L L L X
X S S X X
</code></pre>
<ul>
<li>Figyeljünk arra, hogy az <code>L L</code>, azaz két egymás melletti <code>L</code> betű egyetlen nagy autót jelöl, mivel a nagy autók mindig két helyet foglalnak el.</li>
<li>A fenti példában az 1. emeleten az <code>L L L L</code> azt jelenti, hogy két nagy autó parkol egymás mellett.</li>
<li>A 4. (legfelső) emeleten csak egy kis autó parkol.</li>
<li>A 0. (földszinti) emeleten két kis autó parkol.</li>
</ul>
</li>
</ul>
<h3>JUnit 5 tesztek</h3>
<ul>
<li><code>testConstructorWithInvalidValues()</code>: Azt ellenőrzi, hogy a konstruktor megfelelően működik.</li>
<li><code>testTextualRepresentation()</code>: A szöveges ábrázolás megfelelő működését ellenőrzi. Készítsünk egy hasonló alaprajzot (<code>floorPlan</code>-t), mint a fenti példa: Regisztráljunk és távolítsunk el néhány autót, majd ellenőrizzük az eredményt.</li>
</ul>