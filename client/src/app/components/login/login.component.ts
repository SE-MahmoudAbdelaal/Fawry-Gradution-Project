import { Component, OnInit } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import {
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
  FormsModule,
} from '@angular/forms';
import { CommonModule, NgClass } from '@angular/common';

import { HttpClientModule, HttpHeaders } from '@angular/common/http';
import { HttpClient } from '@angular/common/http';

import { AuthService } from './../../services/auth.service';
import { NavbarComponent } from "../../shared/components/navbar/navbar.component";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule,
    RouterLink,
    HttpClientModule,
    NgClass,
    NavbarComponent
],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent {
  // backerror: string = '';
  error: string = '';
  isuserLogin: boolean = false;

  constructor(
    private _Router: Router,
    private http: HttpClient,
    private authService: AuthService
  ) {}

  loginform: FormGroup = new FormGroup({
    email: new FormControl(null, [Validators.email, Validators.required]),
    password: new FormControl(null, [
      Validators.required,
      Validators.minLength(8),
    ]),
  });

  submitlogin(loginform: FormGroup){
    this.authService.login(this.loginform.value).subscribe({
      next: (response) => {
         if (response) {
          console.log(response)
          const token = response.token;
          const userId = response.user.userId
          const role = response.user.role
          console.log(userId)
          console.log(role)
          localStorage.setItem("userId", userId);
          localStorage.setItem("role", role);
          this.authService.saveToken(token)

          switch (role) {
            case 'ADMIN':
              this._Router.navigate(['/admin-dashboard']);
              break;
            case 'USER':
              this._Router.navigate(['/home']);
              break;
            case 'MERCHANT':
              this._Router.navigate(['/merchent-dashboard']);
              break;
            default:
             
              this.error = 'Unknown role';
              break;
          }
        } else {
          this.error = response.message;
        }

          //  this._Router.navigate(['/home']);
          },
          error: (err) => {
            console.error(err);
            this.error = 'Login failed. Please try again.';
          }


    });

  }
}

