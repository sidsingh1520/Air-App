import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { UserService } from '../Services/user.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private user:UserService,private router:Router){}
  result:any;
  valid:boolean=false;
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      if(this.user.getToken()==null){
        this.router.navigate(['accounts/login']);
        return false;
      }
  
      this.user.validateToken().subscribe((response)=>{
        console.log(response);
        this.result=response;
        if(this.result.message=="Valid token"){
          this.valid=true;
        }
      })
      
      if(this.valid==true){
        return true;
      }
      else{
        this.router.navigate(['accounts/login']);
        return false;
      }
    // if(this.user.isLoggedIn()){
    //   return true;
    // }
    // else{
    //   this.router.navigate(['accounts/login']);
    //   return false;
    // }
  }
  
}
