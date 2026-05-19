<?php

namespace Database\Factories;

use App\Models\Membership;
use Illuminate\Database\Eloquent\Factories\Factory;

/**
 * @extends Factory<Membership>
 */
class MembershipFactory extends Factory
{
    /**
     * Define the model's default state.
     *
     * @return array<string, mixed>
     */
    public function definition(): array
    {
        return [
            'name' => fake()->unique()->randomElement(['Bronz', 'Ezüst', 'Arany']),
            'max_reservations' => fake()->numberBetween(1, 10),
            'max_loans' => fake()->numberBetween(2, 20),
        ];
    }
}
