import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Tag } from '../model/tag';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class TagService {
  private readonly API_URL: string = environment.apiUrl;
  private readonly ENDPOINT = '/tags';

  private http = inject(HttpClient);

  getAllTags(): Observable<Tag[]> {
    return this.http.get<Tag[]>(`${this.API_URL}${this.ENDPOINT}`);
  }

  getTagById(id: number): Observable<Tag> {
    return this.http.get<Tag>(`${this.API_URL}${this.ENDPOINT}/${id}`);
  }

  createTag(tag: Omit<Tag, 'id'>): Observable<Tag> {
    return this.http.post<Tag>(`${this.API_URL}${this.ENDPOINT}`, tag);
  }

  updateTag(id: number, tag: Tag): Observable<Tag> {
    return this.http.put<Tag>(`${this.API_URL}${this.ENDPOINT}/${id}`, tag);
  }

  deleteTag(id: number): Observable<void> {
    return this.http.delete<void>(`${this.API_URL}${this.ENDPOINT}/${id}`);
  }
}
