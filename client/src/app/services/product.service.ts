import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductsService {

  constructor(private _http:HttpClient) { }


  getAllProducts(){
  return this._http.get('http://localhost:8900/products');
  }

  searchItem(itemName: string): Observable<any>{
    return this._http.get(`https://api.themoviedb.org/3/search/movie?api_key=6795618303e502bcf5f108ef8fb77131&query=${itemName}`)
  }
}
