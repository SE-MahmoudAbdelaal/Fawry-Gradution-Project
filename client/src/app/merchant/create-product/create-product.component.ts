import { Component } from '@angular/core';
import bootstrap from '../../../main.server';
import { FormBuilder, FormGroup, FormsModule ,ReactiveFormsModule, Validators } from '@angular/forms';
import { NgIf } from '@angular/common';
import { MerchantSidebarComponent } from '../merchant-sidebar/merchant-sidebar.component';
import { ProductserviceService } from '../services/productservice.service';
import {Router} from '@angular/router'
import { response } from 'express';

@Component({
  selector: 'app-create-product',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    NgIf,
    MerchantSidebarComponent,
    FormsModule
  ],
  templateUrl: './create-product.component.html',
  styleUrl: './create-product.component.css'
})
export class CreateProductComponent {
  productForm!: FormGroup;
  userid!:any

  constructor(private fb: FormBuilder,private ProductService:ProductserviceService,private router:Router) {}

  ngOnInit(): void {

    this.productForm = this.fb.group({
      image_url: ['', Validators.required],
      name: ['', Validators.required],
      price: [0, [Validators.required, Validators.min(0)]],
      description: ['', Validators.required],
      stock_quantity: [0, [Validators.required, Validators.min(0)]]
    });

    //this.addProduct();
  }

 /* addProduct():void {
    console.log("4")
    if (this.productForm.valid) {
      const newProduct = this.productForm.value;
      console.log('Product Added:', newProduct);
      this.ProductService.createProduct(this.productForm.value).subscribe((response)=>{
        console.log('product added');
        this.router.navigate(['/merchent-products']);
      },(error)=>{
        console.log('product added');
      });
      // Handle the API call to add the product here
    }

  }*/


  addProducts():void {
    console.log("4")
    if (this.productForm.valid) {
      const newProduct = this.productForm.value;
      this.userid = Number( localStorage.getItem("userId"))
      console.log('Product Added:', newProduct);
      this.ProductService.createProduct(this.productForm.value,this.userid).subscribe((response)=>{
        console.log('product added');
        this.router.navigate(['/merchent-products']);
      },(error)=>{
        console.log('product added');
      });



     
    }

  }
}
