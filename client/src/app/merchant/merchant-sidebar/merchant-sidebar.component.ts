import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-merchant-sidebar',
  standalone: true,
  imports: [
    RouterLink
  ],
  templateUrl: './merchant-sidebar.component.html',
  styleUrl: './merchant-sidebar.component.css'
})
export class MerchantSidebarComponent {
  constructor(private authService :AuthService){}
  logout(){
    this.authService.logout()
  localStorage.removeItem("role");
  localStorage.removeItem("userId");

  }
}
