import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(private fb: FormBuilder ,private router:Router) { }
  registerForm = this.fb.group({
    userName: ['', [Validators.required, Validators.minLength(6)]],
    email: ['', [Validators.required,Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")]],
    password: ['', [Validators.required, Validators.minLength(5)]],
    confirmPassword: ['', [Validators.required, Validators.minLength(5)]],
  })

  ngOnInit(): void {
  }

  register(){
    console.log(this.registerForm.value);
    this.router.navigateByUrl("/login");
  }

}
