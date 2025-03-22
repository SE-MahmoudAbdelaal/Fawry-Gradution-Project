import { EventEmitter, Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CartService {

  constructor(private http: HttpClient) {
  }
  getCartCount() {
    let cart =  JSON.parse(localStorage.getItem('cart')!)
    if(cart == null) {
    return 0;
    }else{
    return cart.length;
  }}

  removeProductFromCart(productId: number, cartId: string): Observable<void> {
    const headers = new HttpHeaders({
      cartId: cartId,
    });
    console.log('headers :>> ', headers);
    return this.http.delete<void>(
      `http://localhost:8900/shop/cart/${productId}`,
      { headers }
    );
  }


}
