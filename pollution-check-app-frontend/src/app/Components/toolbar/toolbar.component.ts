import { Component } from '@angular/core'
import { Router } from '@angular/router'
import { Observable } from 'rxjs'
import { UserService } from 'src/app/Services/user.service'

@Component({
  selector: 'app-toolbar',
  templateUrl: './toolbar.component.html',
  styleUrls: ['./toolbar.component.css'],
})
export class ToolbarComponent {
  titleFlag = true
  userName: string | null = ''
  isLoggedIn$: any
  constructor(private user: UserService, private router: Router) {}

  ngOnInit(): void {
    this.isLoggedIn$ = this.user.loggedInStatus
    this.userName = localStorage.getItem('userName')
  }
  logout() {
    this.user.logout()
    this.ngOnInit()
    this.router.navigate(['accounts/login'])
  }
}
