<?php

namespace App\Http\Controllers;

use App\Models\User;
use App\Models\Book;
use App\Http\Requests\BookStoreOrUpdateRequest;
use Illuminate\Support\Facades\Gate;
use Illuminate\Support\Facades\Storage;
use Illuminate\Support\Str;
use Illuminate\Http\Request;

class BookController extends Controller
{
    public function index(Request $request)
    {
        // a keresés módját AI-tól vettem át
        $query = Book::query();

        if ($request->filled('writer')) {
            $query->where('writer', 'like', '%' . $request->input('writer') . '%');
        }

        if ($request->filled('title')) {
            $query->where('title', 'like', '%' . $request->input('title') . '%');
        }

        if ($request->filled('year')) {
            $query->where('year', $request->input('year'));
        }

        if ($request->filled('type')) {
            $query->where('type', $request->input('type'));
        }

        if ($request->filled('language')) {
            $query->where('language', 'like', '%' . $request->input('language') . '%');
        }

        $books = $query->orderBy('writer', 'asc')->orderBy('title', 'asc')->paginate(10);

        return view('books.index', ['books' => $books]);
    }

    public function show(Book $book)
    {
        return view('books.show', ['book' => $book]);
    }

    public function create()
    {
        Gate::authorize('create', Book::class);
        return view('books.create', [
            'users' => User::all()
        ]);
    }

    public function store(BookStoreOrUpdateRequest $request) {
        Gate::authorize('create', Book::class);
        $validated = $request->validated();

        if ($request->hasFile('borítókép')) {
            $file = $request->file('borítókép');
            $fileName = Str::uuid() . "." . $file->getClientOriginalExtension();
            Storage::disk('public')->put("images/" . $fileName, $file->getContent());
            $validated['borítókép'] = $fileName;
        }

        $book = Book::create($validated);
        return redirect()->route('books.index');
    }

    public function edit(Book $book)
    {
        Gate::authorize('update', $book);
        return view('books.edit', [
            'users' => User::all(),
            'book' => $book
        ]);
    }

    public function update(BookStoreOrUpdateRequest $request, Book $book)
    {
        Gate::authorize('update', $book);
        $validated = $request->validated();

        if ($request->hasFile('borítókép')) {
            $file = $request->file('borítókép');
            $fileName = Str::uuid() . "." . $file->getClientOriginalExtension();
            Storage::disk('public')->put("images/" . $fileName, $file->getContent());
            $validated['borítókép'] = $fileName;

            if ($book->borítókép) {
                Storage::disk('public')->delete("images/" . $book->borítókép);
            }
        }

        $book->update($validated);
        return redirect()->route('books.index');
    }

    public function destroy(Book $book)
    {
        Gate::authorize('delete', $book);
        $title = $book->title;
        $book->delete();
        return redirect()->route('books.index');
    }

    public function forceDestroy(Book $book)
    {
        Gate::authorize('delete', $book);
        
        if ($book->borítókép) {
            Storage::disk('public')->delete("images/" . $book->borítókép);
        }

        $book->forceDelete();
        return redirect()->route('books.index');
    }
}
