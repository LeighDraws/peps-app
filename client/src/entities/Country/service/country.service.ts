import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Country } from '../model/country';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class CountryService {
  private readonly API_URL: string = environment.apiUrl;
  private readonly ENDPOINT = '/countries';

  private http = inject(HttpClient);

  getAllCountries(): Observable<Country[]> {
    return this.http.get<Country[]>(`${this.API_URL}${this.ENDPOINT}`);
  }

  getCountryById(id: number): Observable<Country> {
    return this.http.get<Country>(`${this.API_URL}${this.ENDPOINT}/${id}`);
  }

  createCountry(country: Omit<Country, 'id'>): Observable<Country> {
    return this.http.post<Country>(`${this.API_URL}${this.ENDPOINT}`, country);
  }

  updateCountry(id: number, country: Country): Observable<Country> {
    return this.http.put<Country>(`${this.API_URL}${this.ENDPOINT}/${id}`, country);
  }

  deleteCountry(id: number): Observable<void> {
    return this.http.delete<void>(`${this.API_URL}${this.ENDPOINT}/${id}`);
  }
}
