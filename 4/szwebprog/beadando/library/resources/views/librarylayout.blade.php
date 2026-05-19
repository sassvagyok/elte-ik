<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document- @yield('title')</title>
    @vite(['resources/css/app.css', 'resources/js/app.js'])
</head>

<body>
    <div class="mx-auto container">
        <div class="grid grid-cols-3">

            <div class="col-span-3">
                <h1>Library</h1>
            </div>

            <div class="col-span-2">
                @yield('content')
            </div>

            <div class="col-span-1">
                @guest
                    <a href="{{ route('login') }}">Bejelentkezés</a><br>
                    <a href="{{ route('register') }}">Regisztráció</a>
                @endguest

                @auth
                    Szia, {{ Auth::user()->name }}!
                    <form action="{{ route('logout') }}" method="POST">
                        @csrf
                        <a href="#" onclick="this.closest('form').submit()">Kijelentkezés</a>
                    </form>
                @endauth

                @can('create', App\Models\Book::class)
                    <a href="{{ route('books.create') }}">Új könyv létrehozása</a>
                @endcan
            </div>

        </div>
    </div>

</body>

</html>
