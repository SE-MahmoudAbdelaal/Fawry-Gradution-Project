import { Component } from '@angular/core';
import { RouterLink, Router } from '@angular/router';
import {
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
  FormsModule,
  ValidatorFn,
  AbstractControl,
} from '@angular/forms';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { NgClass } from '@angular/common';
import { HeaderComponent } from '../../header/header.component';

@Component({
  selector: 'app-add-product',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    FormsModule,
    CommonModule,
    RouterLink,
    FormsModule,
    CommonModule,
    HttpClientModule,
    NgClass,
  ],
  templateUrl: './add-product.component.html',
  styleUrl: './add-product.component.css',
})
export class AddProductComponent {
  constructor(
    private router: Router,
    private _http: HttpClient,
  ) {}

  error: string = '';
  imageOneFile!: File;
  imageTwoFile!: File;
  imageThreeFile!: File;
  imageFourFile!: File;

  addImageOne(event: any) {
    if (event.target.files.length > 0) {
      this.imageOneFile = event.target.files[0];

      this.addProductVendor.patchValue({
        image: this.imageOneFile,
      });
    }
  }
  addImageTwo(event: any) {
    if (event.target.files.length > 0) {
      this.imageTwoFile = event.target.files[0];

      this.addProductVendor.patchValue({
        image: this.imageTwoFile,
      });
    }
  }
  addImageThree(event: any) {
    if (event.target.files.length > 0) {
      this.imageThreeFile = event.target.files[0];

      this.addProductVendor.patchValue({
        image: this.imageThreeFile,
      });
    }
  }
  addImageFour(event: any) {
    if (event.target.files.length > 0) {
      this.imageFourFile = event.target.files[0];

      this.addProductVendor.patchValue({
        image: this.imageFourFile,
      });
    }
  }

  addProductVendor: FormGroup = new FormGroup({
    product_name: new FormControl(null, Validators.required),
    descreption: new FormControl(null, Validators.required),
    category_id: new FormControl(null, Validators.required),
    price: new FormControl(null, Validators.required),
    stock: new FormControl(null, Validators.required),
    discount: new FormControl(null, Validators.required),
    is_on_sale: new FormControl(null, Validators.required),
    productImage1: new FormControl(null, Validators.required),
  });

  ngAddProduct() {
    const formdata = new FormData();

    formdata.append(
      'product_name',
      this.addProductVendor.get('product_name')?.value
    );
    formdata.append(
      'descreption',
      this.addProductVendor.get('descreption')?.value
    );

    formdata.append(
      'category_id',
      this.addProductVendor.get('category_id')?.value
    );
    formdata.append('price', this.addProductVendor.get('price')?.value);
    formdata.append('stock', this.addProductVendor.get('stock')?.value);
    formdata.append('discount', this.addProductVendor.get('discount')?.value);
    formdata.append(
      'is_on_sale',
      this.addProductVendor.get('is_on_sale')?.value
    );
    formdata.append('image1', this.imageOneFile);
    formdata.append('image2', this.imageTwoFile);
    formdata.append('image3', this.imageThreeFile);
    formdata.append('image4', this.imageFourFile);

    const vendorToken = localStorage.getItem('vendorToken');

    // if (vendorToken) {
    //   this._vendorDashboardService
    //     .createProduct(formdata, vendorToken)
    //     .subscribe({
    //       next: (res: any) => {
    //         console.log(res);
    //         this.addProductVendor.reset();
    //         this.router.navigate(['/vendor/dashboard/products']); // Redirect to desired route
    //       },
    //       error: (error) => {
    //         console.log(error.error, 'errors');
    //         alert(error.error.errors);
    //       },
    //     });
    // }
  }
}
