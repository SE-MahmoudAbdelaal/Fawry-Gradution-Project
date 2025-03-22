import { CartService } from './../../../services/cart.service';
import { AuthService } from './../../../services/auth.service';
import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { RegisterComponent } from '../../../components/register/register.component';
import { SearchComponent } from '../../../components/search/search.component';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [
    RouterLink,
    RegisterComponent,
    
    SearchComponent,
    NgIf,
  ],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css',
})
export class NavbarComponent implements OnInit {
  cartCount: number = 0;
  isAuthenticated: boolean = false;
  constructor(
    private cartService: CartService,
    private authService: AuthService
  ) {}

  ngOnInit() {
    this.authService.user.subscribe({
      next: () => {
        if (this.authService.getToken() != null) {
          this.isAuthenticated = true;
        } else {
          this.isAuthenticated = false;
        }
      },
    });
   this.getCartCount();
  }
  getCartCount() {
    return this.cartService.getCartCount();
  }

  logout() {
    this.authService.logout();
    localStorage.removeItem('role');
    localStorage.removeItem('userId');
    localStorage.removeItem("cart");
    localStorage.removeItem("cartId")
    this.isAuthenticated = false;
  }
}
