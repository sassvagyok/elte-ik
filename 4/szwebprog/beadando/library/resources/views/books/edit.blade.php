@extends('librarylayout')

@section('title', $book->title . ' szerkesztése')

@section('content')

    <h2 class="text-2xl">{{ $book->title }} szerkesztése</h2>

    <form action="{{ route('books.update', ['book' => $book]) }}" method="POST" enctype="multipart/form-data">
        @csrf
        @method('PATCH')

        <h3 class="text-xl mt-4">Cím:</h3>
        @error('title')
            <span class="error">{{ $message }}</span>
        @enderror
        <input type="text" name="title" value="{{ old('title', $book->title) }}" class="w-full"><br>

        <h3 class="text-xl mt-4">Író:</h3>
        @error('writer')
            <span class="error">{{ $message }}</span>
        @enderror
        <input type="text" name="writer" value="{{ old('writer', $book->writer) }}" class="w-full"><br>

        <h3 class="text-xl mt-4">Kiadás éve:</h3>
        @error('year')
            <span class="error">{{ $message }}</span>
        @enderror
        <input type="number" name="year" value="{{ old('year', $book->year) }}" class="w-full"><br>

        <h3 class="text-xl mt-4">Típus:</h3>
        @error('type')
            <span class="error">{{ $message }}</span>
        @enderror
        <input type="radio" class="mr-2" name="type" id="könyv" value="könyv" {{ old('type', $book->type) == 'könyv' ? 'checked' : '' }}>
        <label for="könyv">könyv</label><br>
        <input type="radio" class="mr-2" name="type" id="folyóirat" value="folyóirat" {{ old('type', $book->type) == 'folyóirat' ? 'checked' : '' }}>
        <label for="folyóirat">folyóirat</label><br>
        <input type="radio" class="mr-2" name="type" id="kotta" value="kotta" {{ old('type', $book->type) == 'kotta' ? 'checked' : '' }}>
        <label for="kotta">kotta</label><br>
        <input type="radio" class="mr-2" name="type" id="térkép" value="térkép" {{ old('type', $book->type) == 'térkép' ? 'checked' : '' }}>
        <label for="térkép">térkép</label><br>
        <input type="radio" class="mr-2" name="type" id="képregény" value="képregény" {{ old('type', $book->type) == 'képregény' ? 'checked' : '' }}>
        <label for="képregény">képregény</label><br>

        <h3 class="text-xl mt-4">Nyelv:</h3>
        @error('language')
            <span class="error">{{ $message }}</span>
        @enderror
        <input type="radio" class="mr-2" name="language" id="hu" value="hu" {{ old('language', $book->language) == 'hu' ? 'checked' : '' }}>
        <label for="hu">hu</label><br>
        <input type="radio" class="mr-2" name="language" id="en" value="en" {{ old('language', $book->language) == 'en' ? 'checked' : '' }}>
        <label for="en">en</label><br>
        <input type="radio" class="mr-2" name="language" id="de" value="de" {{ old('language', $book->language) == 'de' ? 'checked' : '' }}>
        <label for="de">de</label><br>
        <input type="radio" class="mr-2" name="language" id="fr" value="fr" {{ old('language', $book->language) == 'fr' ? 'checked' : '' }}>
        <label for="fr">fr</label><br>
        <input type="radio" class="mr-2" name="language" id="it" value="it" {{ old('language', $book->language) == 'it' ? 'checked' : '' }}>
        <label for="it">it</label><br>
        <input type="radio" class="mr-2" name="language" id="ru" value="ru" {{ old('language', $book->language) == 'ru' ? 'checked' : '' }}>
        <label for="ru">ru</label><br>
        <input type="radio" class="mr-2" name="language" id="es" value="es" {{ old('language', $book->language) == 'es' ? 'checked' : '' }}>
        <label for="es">es</label><br>
        <input type="radio" class="mr-2" name="language" id="la" value="la" {{ old('language', $book->language) == 'la' ? 'checked' : '' }}>
        <label for="la">la</label><br>
        <input type="radio" class="mr-2" name="language" id="pl" value="pl" {{ old('language', $book->language) == 'pl' ? 'checked' : '' }}>
        <label for="pl">pl</label><br>

        ISBN: @error('isbn')
            <span class="error">{{ $message }}</span>
        @enderror
        <br>
        <input type="text" name="isbn" value="{{ old('isbn', $book->isbn) }}" class="w-full"><br>

        <h3 class="text-xl mt-4">Borítókép:</h3>
        @error('borítókép')
            <span class="error">{{ $message }}</span>
        @enderror
        <input type="file" name="borítókép"><br><br>

        <button class="mt-2 p-2 bg-sky-500 hover:bg-sky-400 rounded rounded-lg" type="submit">Mentés</button>
    </form>

@endsection
