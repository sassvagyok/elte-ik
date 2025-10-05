const library = [
    {
        szerzo: "J. K. Rowling",
        cim: "Harry Potter és a bölcsek köve",
        ev: 1997,
        kiado: "Bloomsbury",
        isbn: "978-963-07-3456-0"
    },
    {
        szerzo: "George Orwell",
        cim: "1984",
        ev: 1949,
        kiado: "Secker & Warburg",
        isbn: "978-0-452-28423-4"
    },
    {
        szerzo: "Neil Gaiman",
        cim: "Neverwhere",
        ev: 1997,
        kiado: "BBC Books",
        isbn: "978-0-7475-3270-8"
    }
];

function getBooksByYear(ev) {
    return library.filter(x => x.ev === ev);
}

const form = document.querySelector("form");
const lista = document.querySelector("#lista");
const evszam = document.querySelector("#evszam");

form.addEventListener("submit", onSubmit);

function onSubmit(e) {
    e.preventDefault();
    const ev = Number(evszam.value);

    if (isNaN(ev)) {
        return lista.innerHTML = "Kérlek érvényes évszámot adj meg!";
    } else {
        const talalatok = getBooksByYear(ev);

        lista.innerHTML = talalatok.length ? `<ul>${genList(talalatok)}</ul>`: "Nincs találat!";
    }
}

function genList(talalatok) {
    return talalatok.map(x => `<li>${x.cim}</li>`).join("");
}

//+feladatok
//A 1990 után megjelent könyvek címei nagybetűsen consol-ra kiírva
const after1990 = library.filter(x => x.ev > 1990).map(x => x.cim.toUpperCase()).join("\n");
console.log(after1990);

//Van-e olyan könyv, aminek a címe 10 karakternél hosszabb, és „9”-essel kezdődik az ISBN-je?
const isbn9 = library.some(x => x.cim.length > 10 && x.isbn[0] === "9");
console.log(isbn9);