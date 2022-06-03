import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm = this.fb.group({
    username: ['', [Validators.required, Validators.minLength(6)]],
    password: ['', [Validators.required, Validators.minLength(5)]],
  });

  constructor(private fb: FormBuilder ,private router:Router) {}

  ngOnInit(): void {}

  login(){
    console.log(this.loginForm.value);
    this.router.navigateByUrl("/upload");
  }

}
