import { HttpClient, HttpHeaders } from '@angular/common/http'
import { Injectable } from '@angular/core'
import { BehaviorSubject } from 'rxjs'

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
    return this.http.post(
      `${this.baseAuthUrl}/register`,
      user,
      this.httpregOptions,
    )
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
  loginUser(token: string, email: string) {
    this.isLogged.next(true)
    localStorage.setItem('isLogged', '1')
    localStorage.setItem('token', token)
    localStorage.setItem('email', email)
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
    return true
  }

  getToken() {
    return localStorage.getItem('token')
  }

  get loggedInStatus() {
    return this.isLogged.asObservable()
  }
}
