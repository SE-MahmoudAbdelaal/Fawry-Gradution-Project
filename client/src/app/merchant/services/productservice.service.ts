import { Injectable } from '@angular/core';

import { BehaviorSubject,Observable, tap } from 'rxjs';

import { HttpClient, HttpParams } from '@angular/common/http';
import { Product } from '../../product';
import { ProductDto } from '../../ProductDto';
import {PaginatedResponse} from '../../models/PaginatedResponse';

@Injectable({
  providedIn: 'root'
})
export class ProductserviceService {
  private apiurl='http://localhost:8081/products';

  private productsSubject: BehaviorSubject<Product[]> = new BehaviorSubject<Product[]>([]);
  public products$: Observable<Product[]> = this.productsSubject.asObservable();
  constructor(private http: HttpClient) { }
  getProducts(page: number=0, size: number=5,userId:number):Observable<PaginatedResponse<Product>>{
    const params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());
    return this.http.get<PaginatedResponse<Product>>(`${this.apiurl}/user/${userId}`,{params}).pipe(
      tap(res => {
        this.productsSubject.next(res.content);
      }));
  }

  getProduct(id:number):Observable<Product>{
    return this.http.get<Product>(`${this.apiurl}/${id}`)
  }

  createProduct(product:Product,userId:number):Observable<Product>{
    return this.http.post<Product>(`${this.apiurl}/addproduct/${userId}`,product);
  }

  deleteProduct(id:number):Observable<void>{
    return this.http.delete<void>(`${this.apiurl}/${id}`).pipe(
      tap(() => {
        this.updateProductsList(id);

      })
    );

  }



  updateProduct(id:number,product:Product):Observable<Product>{
    return this.http.put<Product>(`${this.apiurl}/updateproduct/${id}`,product).pipe(
      tap((product) => {
        // Get the current list of products
        const products = this.productsSubject.value;

        // Find the index of the updated product
        const index = products.findIndex(p => p.id === id);

        // Replace the updated product in the list
        if (index > -1) {
          products[index] = product;
          this.productsSubject.next(products);
        }
      })
    );

  }

  private updateProductsList(deletedProductId: number) {
    const currentProducts = this.productsSubject.getValue();
    const updatedProducts = currentProducts.filter(product => product.id !== deletedProductId);
    this.productsSubject.next(updatedProducts);
  }




}
