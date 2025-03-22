import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  Validators,
  ReactiveFormsModule,
  FormsModule,
} from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { CartService } from '../../services/cart.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-payment',
  standalone: true,
  imports: [ReactiveFormsModule, FormsModule, RouterLink, CommonModule],
  templateUrl: './payment.component.html',
  styleUrl: './payment.component.css',
})
export class PaymentComponent {
  checkoutForm: FormGroup;
  cartProduct: any[] = [];
  errorMessage: string = '';
  constructor(
    private fb: FormBuilder,
    private http: HttpClient,
    private cartService: CartService,
    private router: Router
  ) {
    this.checkoutForm = this.fb.group({
      cardNumber: ['', Validators.required],
      cvv: ['', Validators.required],
      coupon: [null],
    });
  }

  onSubmit() {
    if (this.checkoutForm.valid) {
      const cartId = localStorage.getItem('cartId');
      const token = localStorage.getItem('auth-token')!;
      const userId = localStorage.getItem('userId');
      if (cartId) {
        const paymentData = {
          ...this.checkoutForm.value,
        };
        console.log(this.checkoutForm.value);
        this.http
          .post(`http://localhost:8900/shop/order/${userId}`, paymentData, {
            headers: {
              'Content-Type': 'application/json',
              cartid: cartId,
              Authorization: `Bearer ${token}`,
            },
          })
          .subscribe(
            (response) => {
              console.log('Payment successful', response);
              localStorage.setItem('cart', JSON.stringify(this.cartProduct));
              alert("Payment completed successfully")
              this.errorMessage = '';
              this.router.navigate(['/home']);
            },
            (error) => {
              console.error('Payment failed', error);
              this.errorMessage = 'Payment failed. Please try again.';
            }
          );
      } else {
        this.errorMessage = 'Cart ID is not available in localStorage.';
        console.error('Cart ID is not available in localStorage.');
      }
    } else {
      this.errorMessage =
        'Form is invalid. Please fill out all required fields.';
      console.log('Form is invalid');
    }
  }
}
