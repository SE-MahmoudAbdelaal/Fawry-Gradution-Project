import { Component } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { SidebarComponent } from "../sidebar/sidebar.component";
import { MerchantsComponent } from "../merchants/merchants.component";
import { ShowAdminsComponent } from "../show-admins/show-admins.component";

@Component({
  selector: 'app-admindashboard',
  standalone: true,
  imports: [
    RouterOutlet,
    RouterLink,
    SidebarComponent,
    MerchantsComponent,
    ShowAdminsComponent
],
  templateUrl: './admindashboard.component.html',
  styleUrl: './admindashboard.component.css'
})
export class AdmindashboardComponent {

}
