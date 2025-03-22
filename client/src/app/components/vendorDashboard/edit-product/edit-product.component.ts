import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import {
  RouterLink,
  RouterModule,
  Router,
  ActivatedRoute,
} from '@angular/router';
import { SideBarComponent } from '../side-bar/side-bar.component';

import { NgClass } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
@Component({
  selector: 'app-edit-product',
  standalone: true,
  imports: [
    CommonModule,
    RouterLink,
    NgClass,
    SideBarComponent,
    HttpClientModule,
    RouterModule,
    FormsModule,
  ],
  templateUrl: './edit-product.component.html',
  styleUrl: './edit-product.component.css',
})
export class EditProductComponent implements OnInit {
  productId: any;
  product: any;
  // updateProductForm: FormGroup;

  constructor(
    private route: ActivatedRoute,
    private _http: HttpClient,
    private _Router: Router
  ) {}

  ngOnInit(): void {
    this.productId = this.route.snapshot.paramMap.get('id');

    // console.log(this.productId);
    // this.fetchProductDetails();
    // fetchProductDetails() {
    const vendorToken = localStorage.getItem('vendorToken');
    // console.log(vendorToken);

    // if (vendorToken) {
    //   this._vendorDashboardService
    //     .getSingleProduct(vendorToken, this.productId)
    //     .subscribe(
    //       (res) => {
    //         this.product = res.product;
    //         // console.log(res.product);
    //       },
    //       (error) => {
    //         console.error('Error fetching product details:', error);
    //       }
    //     );
      // }
    }
  }

  // onEditProduct() {
    // const vendorToken = localStorage.getItem('vendorToken');
    // if (vendorToken) {
    //   const inputData = {
    //     product_name: this.product.product_name,
    //     price: this.product.price,
    //     discount: this.product.discount,
    //     stock: this.product.stock,
    //     // description: this.product.description,
    //     // to be added later with the onsale true or false
    //   };
    //   this._vendorDashboardService
    //     .editProduct(this.product.id, inputData, vendorToken)
    //     .subscribe(
    //       (res) => {
    //         this._Router.navigate(['/vendor/dashboard/products']);
    //         console.log(vendorToken);
    //         console.log(inputData);
    //       },
    //       (error) => {
    //         console.error('Error fetching product details: ', error);
    //       }
    //     );
    // }
  // }

  // onDelete() {
  //   this.productId = this.route.snapshot.paramMap.get('id');
  //   const vendorToken = localStorage.getItem('vendorToken');
  //   if (vendorToken) {
  //     this._vendorDashboardService
  //       .deleteProduct(vendorToken, this.productId)
  //       .subscribe(
  //         (response) => {
  //           this._Router.navigate(['/vendor/dashboard/products']);
  //         },
  //         (error) => {
  //           console.error('Error fetching products:', error);
  //         }
  //       );
  //   } else {
  //     console.error('Vendor token not found.');
  //   }
  // }
// }
