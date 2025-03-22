import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root',
})
export class OrderService {
  private baseUrl = 'http://localhost:8900/shop/order';

  constructor(private http: HttpClient) {}
  getAllOrders(userId: string) {
    return this.http.get<any[]>(`${this.baseUrl}/searchById/${userId}`);
  }
  searchOrdersByDate(userId: string, startDate: string, endDate: string) {
    console.log('startDate :>> ', startDate);
    console.log('this.endDate :>> ', endDate);
    return this.http.get<any[]>(
      `${this.baseUrl}/search/${userId}?startDate=${startDate}&endDate=${endDate}`
    );
  }
}
