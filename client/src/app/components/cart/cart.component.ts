import { NgFor, NgIf } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule, NgModel } from '@angular/forms';
import { CartService } from '../../services/cart.service';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterLink } from '@angular/router';
import { NavbarComponent } from "../../shared/components/navbar/navbar.component";
import { FooterComponent } from "../../shared/components/footer/footer/footer.component";
import {HttpClient, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [
    NgIf,
    NgFor,
    FormsModule,
    CommonModule,
    NavbarComponent,
    FooterComponent,
    RouterLink
  ],
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.scss',
})
export class CartComponent {
  cartProduct: any[] = [];
  total: number = 0;
  success: boolean = false;
  
  constructor(private cartService: CartService, private router: Router,     private http: HttpClient) {}

  ngOnInit(): void {
    this.getCartProduct();
  }
  // get data from localstorage
  // getCartProduct() {
  //   if ('cart' in localStorage) {
  //     this.cartProduct = JSON.parse(localStorage.getItem('cart')!);
  //     console.log(this.cartProduct);
  //   }
  //   this.getTotalProducts();
  // }
    getCartProduct() {
    const cartId = localStorage.getItem('userId')!;
    const headers = new HttpHeaders({
      cartid: cartId,
    });
    this.http
      .get<any[]>('http://localhost:8900/shop/cart',{headers})
      .subscribe(
        (response) => {
          this.cartProduct = response;
          console.log('this.cartProduct :>> ', this.cartProduct);
           localStorage.setItem('cart',JSON.stringify(this.cartProduct));
          console.log(this.cartProduct);
          this.getTotalProducts();
        },
        (error) => {
          console.error('Error fetching cart data:', error);
        }
      );
  }
  detectChange() {
    this.getTotalProducts();
    localStorage.setItem('cart', JSON.stringify(this.cartProduct));
  }

  deleteProduct(index: number) {
    const productId = this.cartProduct[index].product.id;
    console.log('cartProduct[index] :>> ', this.cartProduct[index].product.id);
    const cartId = localStorage.getItem("userId")!;
    this.cartService.removeProductFromCart(productId, cartId).subscribe(
      () => {
        this.cartProduct.splice(index, 1);
        this.getTotalProducts();
      },
      (error) => {
        console.error('حدث خطأ أثناء حذف المنتج من السلة', error);
      }
    );
    this.cartProduct.splice(index, 1);
    this.getTotalProducts();

    localStorage.setItem('cart', JSON.stringify(this.cartProduct));
  }

  // clear All Products
  clearProducts() {
    this.cartProduct = [];
    this.getTotalProducts();
    localStorage.setItem('cart', JSON.stringify(this.cartProduct));
  }

  // Send Cart Data To Backend
  addCartToBackend() {
    // let products = this.cartProduct.map((item) => {
    //   return { productId: item.item.id, quantity: item.quantity };
    // });

    // let model = {
    //   userId: 5,
    //   date: new Date(),
    //   products: products,
    // };

    this.router.navigate(['/payment']);
  }
  // to calculate Total products
  getTotalProducts() {
    this.total = 0;
    for (let x in this.cartProduct) {
      this.total +=
        this.cartProduct[x].product.price * this.cartProduct[x].quantity;
    }
  }



}
