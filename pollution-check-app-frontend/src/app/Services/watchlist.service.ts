import {
  HttpClient,
  HttpErrorResponse,
  HttpHeaders,
} from '@angular/common/http'
import { Injectable } from '@angular/core'
import { catchError, retry, throwError } from 'rxjs'
import { CityData } from '../Models/city-data'

const URL_WATCHLIST = 'http://localhost:8200/api/v1/watchlist'
const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
  }),
}
@Injectable({
  providedIn: 'root',
})
export class WatchlistService {
  constructor(private http: HttpClient) {}

  getWatchListByEmail(userEmail: string) {
    return this.http
      .get<any>(URL_WATCHLIST + userEmail)
      .pipe(retry(0), catchError(this.handleError))
  }

  addToWatchList(cityData: CityData) {
    return this.http
      .post<CityData>(URL_WATCHLIST, cityData, httpOptions)
      .pipe(retry(1), catchError(this.handleError))
  }

  private handleError(error: HttpErrorResponse) {
    if (error.status === 0) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error)
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong.
      console.error(
        `Backend returned code ${error.status}, body was: `,
        error.error,
      )
    }
    // Return an observable with a user-facing error message.
    if(error.status===409){
      return throwError(
        () => new Error('You have already added this city to watchlist'),
      )
    }
    else {
      return throwError(
        () => new Error('Something went bad ! Please try again after sometime'),
      )
    }
  }
}
