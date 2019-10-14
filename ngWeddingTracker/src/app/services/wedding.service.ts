import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Wedding } from '../models/wedding';
import { throwError } from 'rxjs/internal/observable/throwError';
import { map, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class WeddingService {

  private baseUrl = 'http://localhost:8084/';
  private url = this.baseUrl + 'api/weddings';

  constructor(private http: HttpClient) { }

  index() {
    return this.http.get<Wedding[]>(this.url).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Error in WeddingService.index()');
      })
    );
  }

  getWeddingById(id: number) {
    return this.http.get<Wedding>(this.url + '/' + id).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Error in WeddingService.getWeddingById()');
      })
    );
  }

  create(wedding: Wedding) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
        // Authorization: 'my-auth-token'
      })
    };
    return this.http.post(this.url, wedding, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Error posting new Wedding in WeddingService.create()');
      })
    );
  }

  update(id: number, wedding: Wedding) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
        // Authorization: 'my-auth-token'
      })
    };
    return this.http.put(this.url + '/' + id, wedding, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Error editing a Wedding');
      })
    );
  }

  destroy(id: number) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
        // Authorization: 'my-auth-token'
      })
    };
    return this.http.delete(this.url + '/' + id, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Error deleting a Wedding in WeddingService.destroy()');
      })
    );
  }
}
