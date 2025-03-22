import { Component, OnInit } from '@angular/core';
import { SideBarComponent } from '../side-bar/side-bar.component';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { NgClass } from '@angular/common';

@Component({
  selector: 'app-vendor-products',
  standalone: true,
  imports: [ SideBarComponent, RouterLink, CommonModule,NgClass],
  templateUrl: './vendor-products.component.html',
  styleUrl: './vendor-products.component.css',
})
export class VendorProductsComponent implements OnInit {
  // items = new Array(15).fill(0);

  products: any[] = [];

  constructor() {}

  ngOnInit(): void {
    this.fetchProducts();
  }

  fetchProducts() {
    const vendorToken = localStorage.getItem('vendorToken');
    // if (vendorToken) {
    //   // not working coz its returning an object not array
    //   this._vendorDashboardService.getAllProducts(vendorToken).subscribe(
    //     (response) => {
    //       // this.products = response.products;
    //       this.products = Object.values(response.products);

    //       // console.log(response.products);
    //     },
    //     (error) => {
    //       console.error('Error fetching products:', error);
    //     }
    //   );
    // } else {
    //   console.error('Vendor token not found.');
    // }
  }
}
