import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Coupon } from '../interfac/coupon';

@Injectable({
  providedIn: 'root'
})
export class CouponService {
  private apiUrl = 'http://localhost:8080/coupons'; // Replace with your API endpoint

  constructor(private http: HttpClient) {}

  addCoupon(coupon: Coupon): Observable<Coupon> {
    return this.http.post<Coupon>( `${this.apiUrl}/add`, coupon);
  }
 
  

  getCoupons(): Observable<Coupon[]> {
    return this.http.get<Coupon[]>(this.apiUrl);
  }

  // Get coupon by ID
  getCouponByCode(code: string): Observable<any> {
    return this.http.get(`${this.apiUrl}/${code}`);
  }

  // Delete coupon by ID
  deleteCoupon(code: string): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${code}`);
  } 


  updateCoupon(id: number, coupon: any): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/${id}`, coupon);
  }
}
