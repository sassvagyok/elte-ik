<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Factories\HasFactory;

#[Fillable(['name', 'max_reservations', 'max_loans'])]
class Membership extends Model
{
    use HasFactory;
    
    public function users(): HasMany
    {
        return $this->hasMany(User::class, 'membership_id');
    }
}