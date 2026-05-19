<?php

namespace App\Http\Controllers;

use App\Models\Book;
use App\Models\User;
use Illuminate\Http\Request;
use App\Http\Requests\BookUserStoreOrUpdateRequest;
use Illuminate\Support\Facades\Auth;

class BookUserController extends Controller
{
    // az ehhez tartozó feladatokat nem oldottam meg, lehetséges, hogy nem jól működik
    // tartalmaz AI-generált kódot
    public function store(BookUserStoreOrUpdateRequest $request)
    {
        $validated = $request->validated();
        $userId = Auth::user()->id;
        $user = User::findOrFail($userId);
        
        $user->books()->attach($validated['book_id'], [
            'start_date' => $validated['start_date'] ?? now(),
            'deadline_date' => $validated['deadline_date'] ?? null,
            'end_date' => $validated['end_date'] ?? null,
            'is_extended' => $validated['is_extended'] ?? false,
            'is_reserved' => $validated['is_reserved'] ?? false,
            'payed_total_fee' => $validated['payed_total_fee'] ?? 0,
        ]);

        return redirect()->route('books.index');
    }
}
