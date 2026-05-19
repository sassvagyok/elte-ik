<?php

namespace App\Http\Requests;

use Illuminate\Contracts\Validation\ValidationRule;
use Illuminate\Foundation\Http\FormRequest;

class BookUserStoreOrUpdateRequest extends FormRequest
{
    /**
     * Determine if the user is authorized to make this request.
     */
    public function authorize(): bool
    {
        return true;
    }

    /**
     * Get the validation rules that apply to the request.
     *
     * @return array<string, ValidationRule|array<mixed>|string>
     */
    public function rules(): array
    {
        // az ehhez tartozó feladatokat nem oldottam meg, lehetséges, hogy nem jól működik
        return [
            'user_id' => 'required|integer',
            'book_id' => 'required|integer',
            'start_date' => 'nullable|date|before_or_equal:today',
            'deadline_date' => 'nullable|date|after_or_equal:start_date',
            'end_date' => 'nullable|date|after_or_equal:start_date',
            'is_extended' => 'nullable|boolean',
            'is_reserved' => 'nullable|boolean',
            'payed_total_fee' => 'nullable|integer|min:0',
        ];
    }
}
