import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { response } from 'express';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiUrl = 'http://localhost:8900/users/auth';
  private tokenKey = 'auth-token';
  userId!:string;
  role!:string;
  user: BehaviorSubject<string | null> = new BehaviorSubject<string | null>(null);

  constructor(private http: HttpClient) {}

  // Signup method
  signup(user: any): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post(`${this.apiUrl}/register`, user, { headers });
  }

  // Login method
  login(credentials: { email: string; password: string }): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http
      .post(`${this.apiUrl}/login`, credentials, {
        headers,
        observe: 'response',
      })
      .pipe(
        map((response) => {
          const token = response.headers.get('Authorization');
          console.log('from login in service', response);
          // if (token) {
          //   this.saveToken(token);
          // }
          return response.body;
        })
      );
  }

  // Save token to localStorage
  saveToken(token: string) {
    localStorage.setItem(this.tokenKey, token);


  }

  // Get token from localStorage
  getToken(): string | null {

      if (typeof window !== 'undefined') {
    return localStorage.getItem(this.tokenKey);
  }
  return null
}

  // Check if user is logged in
  isLoggedIn(): boolean {
    return this.getToken() !== null;
  }

  // Logout method
  logout() {
    localStorage.removeItem(this.tokenKey);
  }
}
