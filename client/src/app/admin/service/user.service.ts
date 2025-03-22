import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {


  private apiUrl = 'http://localhost:8000/users/admin/';

  // userId = localStorage.getItem('userId')
  constructor(private http: HttpClient , private authService: AuthService) { }

  getAllUserS(): Observable<any> {
    const token =  this.authService.getToken();
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.get<any>(`${this.apiUrl}?role=USER`, { headers });
  }

  deleteUser(userId:string){
    const token = this.authService.getToken()
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.delete(this.apiUrl+`${userId}`, { headers });
  }
}


