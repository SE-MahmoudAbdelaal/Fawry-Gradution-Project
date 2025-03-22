import { Component } from '@angular/core';
import { ProductsService } from '../../services/product.service';
import { NgFor, NgIf } from '@angular/common';
import { SpinnerComponent } from '../../shared/components/spinner/spinner.component';
import { ProductComponent } from '../product/product.component';
import { RouterLink } from '@angular/router';
import { Product } from '../../product';
import { HttpClient, HttpHandler, HttpHeaders } from '@angular/common/http';
@Component({
  // providers:,
  selector: 'app-all-product',
  standalone: true,
  imports: [NgFor, SpinnerComponent, NgIf, ProductComponent, RouterLink],
  templateUrl: './all-product.component.html',
  styleUrl: './all-product.component.scss',
})
export class AllProductComponent {
  products: Product[] = [];
  categoeies: string[] = [];
  loading: boolean = false;
  cartProduct: any[] = [];

  constructor(
    private getProductServices: ProductsService,
    private http: HttpClient
  ) {}

  ngOnInit(): void {
    this.getProducts();
  }

  getProducts() {
    this.loading = true;
    this.getProductServices.getAllProducts().subscribe(
      (res: any) => {
        this.products = res.content;
        this.loading = false;
      },
      (error) => {
        this.loading = false;
        alert('error');
      }
    );
  }

  addToCart(event: any) {
    let cartId = this.getCartId(); 

    if (!cartId) {
      cartId = this.generateCartId(); 
      localStorage.setItem('cartId', localStorage.getItem('userId')!); 
    }

    if ('cart' in localStorage) {
      this.cartProduct = JSON.parse(localStorage.getItem('cart')!);

      let exist = this.cartProduct.find(
        (item: any) => item.product.id == event.product.id 
      );
console.log('item :>> ', this.cartProduct);
console.log('event :>> ', event);

      if (exist) {
        exist.quantity = event.quantity;
        localStorage.setItem('cart', JSON.stringify(this.cartProduct));
        this.sendToBackend(cartId, event.product.id, exist.quantity);
      } else {
        this.cartProduct.push(event);
        localStorage.setItem('cart', JSON.stringify(this.cartProduct));
        this.sendToBackend(cartId, event.product.id, event.quantity);
      }
    } else {
      this.cartProduct.push(event);
      localStorage.setItem('cart', JSON.stringify(this.cartProduct));
      this.sendToBackend(cartId, event.product.id, event.quantity); //
    }
  }

  sendToBackend(cartId: string, productId: number, quantity: number) {
    this.http
      .post('http://localhost:8900/shop/cart', { productId, quantity, cartId })
      .subscribe(
        (response) => {
          console.log('تمت إضافة المنتج إلى السلة بنجاح:', response);
        },
        (error) => {
          console.error('حدث خطأ أثناء إضافة المنتج إلى السلة:', error);
        }
      );
  }

  getCartId(): string | null {
    return localStorage.getItem('cartId');
  }

  generateCartId() {
    return localStorage.getItem('userId')!;
  }
}
