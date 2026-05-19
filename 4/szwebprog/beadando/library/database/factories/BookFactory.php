<?php

namespace Database\Factories;

use App\Models\Book;
use Illuminate\Database\Eloquent\Factories\Factory;

/**
 * @extends Factory<Book>
 */
class BookFactory extends Factory
{
    /**
     * Define the model's default state.
     *
     * @return array<string, mixed>
     */
    public function definition(): array
    {
        return [
            'title' => ucwords(fake()->words(3, true)),
            'writer' => fake()->name(),
            'type' => fake()->randomElement(['könyv', 'folyóirat', 'kotta', 'térkép', 'képregény']),
            'year' => fake()->numberBetween(1900, 2026),
            'language' => fake()->randomElement(['hu', 'en', 'de', 'fr', 'it', 'ru', 'es', 'la', 'pl']),
            'isbn' => fake()->isbn13()
        ];
    }
}
