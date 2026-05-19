@extends('librarylayout')

@section('title', 'Kezdőlap')

@section('content')
    
    <form action="{{ route('books.index') }}" method="GET" class="mb-4">
        <div>
            <label for="writer">Szerző:</label>
            <br>
            <input type="text" name="writer" id="writer" value="{{ request('writer') }}">
        </div>
        <div>
            <label for="title">Cím:</label>
            <br>
            <input type="text" name="title" id="title" value="{{ request('title') }}">
        </div>
        <div>
            <label for="year">Évszám:</label>
            <br>
            <input type="number" name="year" id="year" value="{{ request('year') }}">
        </div>
        <div>
            <label for="type">Típus:</label>
            <br>
            <select name="type" id="type">
                <option value="">Mindegy</option>
                <option value="könyv" {{ request('type') == 'könyv' ? 'selected' : '' }}>Könyv</option>
                <option value="folyóirat" {{ request('type') == 'folyóirat' ? 'selected' : '' }}>Folyóirat</option>
                <option value="kotta" {{ request('type') == 'kotta' ? 'selected' : '' }}>Kotta</option>
                <option value="térkép" {{ request('type') == 'térkép' ? 'selected' : '' }}>Térkép</option>
                <option value="képregény" {{ request('type') == 'képregény' ? 'selected' : '' }}>Képregény</option>
            </select>
        </div>
        <div>
            <label for="language">Nyelv:</label>
            <br>
            <input type="text" name="language" id="language" value="{{ request('language') }}">
        </div>
        <button type="submit">Szűrés</button>
        <a href="{{ route('books.index') }}">Szűrők törlése</a>
    </form>

    <ul>
        @foreach ($books as $book)
            <li><a href="{{ route('books.show', ['book' => $book]) }}">
                     {{ $book->writer }}: {{ $book->title }}</a>, {{ $book->type }} ({{ $book->year }})</li>
        @endforeach

    </ul>
    {{ $books->withQueryString()->links() }}
@endsection
