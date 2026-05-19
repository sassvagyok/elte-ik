<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\SoftDeletes;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Attributes\Fillable;

#[Fillable(['title', 'writer', 'type', 'year', 'language', 'isbn', 'borítókép'])]
class Book extends Model
{
    use HasFactory, SoftDeletes;

    public function borrowers()
    {
        return $this->belongsToMany(User::class)->withTimestamps();
    }

    public function isCurrentlyBorrowed()
    {
        return $this->borrowers()->whereNull('book_user.end_date')->exists();
    }

    public function activeBorrowing()
    {
        return $this->borrowers()->whereNull('book_user.end_date')->withPivot('deadline_date')->first();
    }

    public function hasReservation()
    {
        return $this->borrowers()->where('book_user.is_reserved', true)->whereNull('book_user.end_date')->exists();
    }
}
