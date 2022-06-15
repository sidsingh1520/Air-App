import {
  HttpClient,
  HttpErrorResponse,
  HttpHeaders,
} from '@angular/common/http'
import { Injectable } from '@angular/core'
import { BehaviorSubject, catchError, throwError, retry } from 'rxjs'

@Injectable({
  providedIn: 'root',
})
export class UserService {
  baseAuthUrl = 'http://localhost:8100/api/v1'
  httpOptions: any = {
    headers: new HttpHeaders({
      Authorization: `Bearer ${this.getToken()}`,
    }),
  }
  httpregOptions: any = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
  }
  isLogged = new BehaviorSubject<boolean>(this.isLoggedIn())

  constructor(private http: HttpClient) {}

  register(user: any) {
    return this.http
      .post(`${this.baseAuthUrl}/register`, user, this.httpregOptions)
      .pipe(retry(1), catchError(this.handleError))
  }

  doLogin(user: any) {
    return this.http.post(`${this.baseAuthUrl}/login`, user)
  }

  validateToken(user: any) {
    return this.http.post(
      `${this.baseAuthUrl}/authentication`,
      this.httpOptions,
    )
  }

  //for login user
  loginUser(token: string, email: string, userName: string) {
    this.isLogged.next(true)
    localStorage.setItem('isLogged', '1')
    localStorage.setItem('token', token)
    localStorage.setItem('email', email)
    localStorage.setItem('userName', userName)
    return true
  }

  isLoggedIn() {
    let token = localStorage.getItem('token')
    if (token == undefined || token == null || token == '') {
      return false
    } else {
      return true
    }
  }

  logout() {
    this.isLogged.next(false)
    localStorage.setItem('isLogged', '0')
    localStorage.removeItem('token')
    localStorage.removeItem('email')
    localStorage.removeItem('userName')
    return true
  }

  getToken() {
    return localStorage.getItem('token')
  }

  get loggedInStatus() {
    return this.isLogged.asObservable()
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
    if (error.status === 409) {
      return throwError(
        () => new Error('This email is already registered, Try signing in'),
      )
    } else {
      return throwError(
        () => new Error('Something went bad ! Please try again after sometime'),
      )
    }
  }
}
