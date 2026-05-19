<?php

namespace App\Http\Requests;

use Illuminate\Contracts\Validation\ValidationRule;
use Illuminate\Foundation\Http\FormRequest;

class BookStoreOrUpdateRequest extends FormRequest
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
        return [
            'title' => 'required|string|max:255',
            'writer' => 'required|string|max:255',
            'type' => 'required|in:könyv,folyóirat,kotta,térkép,képregény',
            'year' => 'required|integer|max:2026',
            'language' => 'required|in:hu,en,de,fr,it,ru,es,la,pl',
            'isbn' => 'required|string|regex:/^(?=(?:\D*\d){13}\D*$)[0-9-]+$/', // ez a regex AI generált
            'borítókép' => 'nullable|image|mimetypes:image/jpeg,image/png,image/bmp,image/webp|max:1024'
        ];
    }

    public function messages()
    {

        return [
            'title.max' => 'A cím maximum 255 karakter lehet!',
            'writer.max' => 'Az író neve maximum 255 karakter lehet!',
            'year.max' => 'A kiadás éve maximum 2026 lehet!',
            'borítókép.image' => 'Csak képfájl tölthető fel!',
            'borítókép.mimetypes' => 'Csak JPG/JPEG, PNG, BMP vagy WEBP formátum engedélyezett!',
            'borítókép.max' => 'A képfájl maximum 1 MB lehet!'
        ];
    }
}
