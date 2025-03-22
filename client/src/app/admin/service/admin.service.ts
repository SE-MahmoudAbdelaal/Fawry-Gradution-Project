import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from '../../services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class AdminService {
  private apiUrl = 'http://localhost:8000/users/admin/';

  constructor(private http: HttpClient , private authService: AuthService) { }

  getAllUser(): Observable<any> {
    const token = this.authService.getToken()
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.get<any>(`${this.apiUrl}?role=ADMIN`, { headers });
  }
  createAdmin(adminData: any): Observable<any> {
    const token = this.authService.getToken()
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.post(this.apiUrl, adminData, { headers });
  }
  deleteAdmin(adminId:string){
    const token = this.authService.getToken()
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.delete(this.apiUrl+`${adminId}`, { headers });
  }
  changeRole(userId: string, newRole: string): Observable<void> {
    const url = `${this.apiUrl}/${userId}/role`;
    return this.http.patch<void>(url, { role: newRole });
  }
}
