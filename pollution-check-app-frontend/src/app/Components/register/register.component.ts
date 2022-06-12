import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { UserService } from 'src/app/Services/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(private fb: FormBuilder ,private router:Router,private _snackBar: MatSnackBar,private userService:UserService) { }
  registerForm = this.fb.group({
    name: ['', [Validators.required, Validators.minLength(6)]],
    email: ['', [Validators.required,Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")]],
    password: ['', [Validators.required, Validators.minLength(5)]],
    confirmPassword: ['', [Validators.required, Validators.minLength(5)]],
  })
  result:any;
  ngOnInit(): void {
  }

  register(){
    console.log(this.registerForm.value);
    this.userService.register(this.registerForm.value).subscribe((response)=>{
      this.result=response;
      console.log(this.result);
      this.openSnackBar("Registered Successfully","Ok");
      this.router.navigate(['accounts/login'])
    },
    error=>{
      this.openSnackBar(error.error.message,"Ok")
    }
    )
  }
  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action,{
      duration:2000
    });
  }
}
