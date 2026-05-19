<?php

namespace Database\Seeders;

use App\Models\Membership;
use App\Models\User;
use App\Models\Book;
use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;

class DatabaseSeeder extends Seeder
{
    use WithoutModelEvents;

    /**
     * Seed the application's database.
     */
    public function run(): void
    {
        Membership::create(['name' => 'Adminisztrátor', 'max_reservations' => 9999, 'max_loans' => 9999 ]);
        Membership::factory(3)->create();
        $users = User::factory(10)->create();
        User::factory()->create(['email' => 'admin@szerveroldali.hu', 'is_admin' => true, 'membership_id' => 1, 'member_until_date' => null]);
        $books = Book::factory(20)->create();

        foreach ($users as $user) {
            $numberOfBooks = rand(1, 3);
            
            for ($i = 0; $i < $numberOfBooks; $i++) {
                $book = $books->pop();
                
                if (!$book) {
                    break;
                }

                $isReturned = fake()->boolean(30);
                $isReserved = !$isReturned && fake()->boolean(20);
                $startDate = now()->subDays(rand(10, 30));
                $deadlineDate = $startDate->toImmutable()->addDays(rand(14, 21));
                $endDate = null;
                if ($isReturned) {
                    $endDate = $startDate->toImmutable()->addDays(rand(1, 10)); 
                }

                $user->books()->attach($book->id, [
                    'start_date' => $startDate,
                    'deadline_date' => $deadlineDate,
                    'end_date' => $endDate,
                    'is_extended' => fake()->boolean(20),
                    'is_reserved' => $isReserved,
                    'payed_total_fee' => $isReturned ? 0 : fake()->randomElement([0, 0, 500, 1000]),
                ]);
            }
        }
    }
}
