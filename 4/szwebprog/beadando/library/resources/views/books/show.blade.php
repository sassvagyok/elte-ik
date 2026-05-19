@extends('librarylayout')

@section('title', $book->title)

@section('content')
    <h2 class="text-xl">{{ $book->title }} ({{ $book->year }})</h2>
    <ul>
        <li>
            Író: {{ $book->writer }}
        </li>
        <li>
            Típus: {{ $book->type }}
        </li>
        <li>
            Nyelv: {{ $book->language }}
        </li>
        <li>
            ISBN: {{ $book->isbn }}
        </li>
        <li>
            Állapot: 
            @if ($book->isCurrentlyBorrowed())
                elérhető ekkor: {{ $book->activeBorrowing()->pivot->deadline_date }}
            @else
                Kikölcsönözhető
            @endif
        </li>
        <li>
            @if (Auth::check() && Auth::user()->is_admin)
                Előjegyzés: {{ $book->hasReservation() ? 'Van előjegyzés' : 'Nincs előjegyzés' }}
            @endif
        </li>
    </ul>

    @if ($book->borítókép !== null)
        <img src="{{ Storage::disk('public')->url('images/' . $book->borítókép) }}" alt="">
    @else
        <img src="{{ asset('book_placeholder.png') }}" alt="">
    @endif

    <br>
    {{ $book->content }}
    
    @can('update', $book)
        <a href="{{ route('books.edit', ['book' => $book]) }}">Szerkesztés</a>
    @endcan
    @can('delete', $book)
        <form action="{{ route('books.destroy', ['book' => $book]) }}" method="POST">
            @csrf
            @method('DELETE')
            <a href="#" onclick="this.closest('form').submit()">Törlés (Soft)</a>
        </form>

        <form action="{{ route('books.forceDestroy', ['book' => $book]) }}" method="POST">
            @csrf
            @method('DELETE')
            <a href="#" onclick="this.closest('form').submit()">Törlés (Hard)</a>
        </form>
    @endcan

@endsection
