import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, retry, throwError } from 'rxjs';
const tkey='cd521cd9-771c-4f2b-a2ff-d477fd689328'
const key='c1ed9fe5-1a48-434d-a1d4-30d6a50accce'
const URL_COUNTRY = 'http://api.airvisual.com/v2/countries?key='+key;
const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
  }),
};

@Injectable({
  providedIn: 'root'
})
export class IqairService {
  
  constructor(private http: HttpClient) { }

  getCountries(){
    return this.http
    .get<any>(URL_COUNTRY)
    .pipe(retry(1), catchError(this.handleError));
  }

  getStates(country: string) {
    return this.http.get<any>('http://api.airvisual.com/v2/states?country='+country+'&key='+key).pipe(retry(0), catchError(this.handleError));
  }

  getCities(country:string,state:string){
    return this.http.get<any>('http://api.airvisual.com/v2/cities?state='+state+'&country='+country+'&key='+key).pipe(retry(0),catchError(this.handleError));
  }

  
  getStations(country:string,state:string,city:string){
    return this.http.get<any>('http://api.airvisual.com/v2/stations?city='+city+'&state='+state+'&country='+country+'&key='+key).pipe(retry(0),catchError(this.handleError));
  }


  getDataStation(country:string,state:string,city:string,station:string){
    return this.http.get<any>('http://api.airvisual.com/v2/city?city='+city+'&state='+state+'&country='+country+'&key='+key).pipe(retry(0),catchError(this.handleError));
  }


  private handleError(error: HttpErrorResponse) {
    if (error.status === 0) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong.
      console.error(
        `Backend returned code ${error.status}, body was: `,
        error.error
      );
    }
    // Return an observable with a user-facing error message.
    return throwError(
      () => new Error('Something bad happened; please try again later.')
    );
  }

}
