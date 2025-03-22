import { Component } from '@angular/core';
import { MerchantSidebarComponent } from "../merchant-sidebar/merchant-sidebar.component";
import { NgFor,NgClass } from '@angular/common';
import { Router } from '@angular/router';
import { CreateProductService } from '../services/create-product.service';
import { FormsModule } from '@angular/forms';
import { ProductserviceService } from '../services/productservice.service';
import { Product } from '../../product';
import { response } from 'express';

@Component({
  selector: 'app-merchant-products',
  standalone: true,
  imports: [MerchantSidebarComponent,
    NgFor,
    NgClass,
    FormsModule,

  ],
  templateUrl: './merchant-products.component.html',
  styleUrl: './merchant-products.component.css'
})
export class MerchantProductsComponent {

  //products:Product[]=[];
  constructor(private router:Router , private productService:CreateProductService,private theproductservice:ProductserviceService){}
  products:Product[]=[]
  page:number = 0;
  size:number = 2;
  totalPages:number = 0;
  totalElements:number = 0;
  userId:number=8;

  ngOnInit(){
    this.getallproducts();
  }
  getallproducts(){
      console.log(1)
      /*this.theproductservice.getProducts().subscribe((res:any)=>{
         this.products=res.data;
      });*/
      /*this.theproductservice.getProducts().subscribe(
        (res: Product[]) => {
          this.products = res;
        },
        (error) => {
          console.error('Error fetching products:', error);
        }
      );*/
      this.theproductservice.getProducts(this.page,this.size,this.userId).subscribe(
        (/*products: Product[]*/res:any) => {
          //console.log(products);
          // Do something with the list of products
          //this.products = products; // For example, assign it to a component property
          console.log(res);
          this.products=res.content;
          this.totalPages = res.totalPages; // Total pages
        this.totalElements = res.totalElements;
        },
        (error) => {
          console.error('Error fetching products:', error);
        }
      );
      
      //console.log(this.products);
      //console.log('Products:', this.products); // Ensure it's an array
      //this.nextPage();
  }

  nextPage() {
    console.log(2)
    if (this.page < this.totalPages - 1) {
      this.page++;
      this.getallproducts();
      
    }
    
  }

  previousPage() {
    console.log("h");
    if (this.page > 0) {
      this.page--;
      this.getallproducts();
      console.log(this.page)
    }
  }

  deleteproduct(id:number):void{
    this.theproductservice.deleteProduct(id).subscribe(
       (/*products: Product[]*/res:any) => {
          //console.log(products);
          // Do something with the list of products
          //this.products = products; // For example, assign it to a component property
        //this.router.navigateByUrl(['/merchent-products']).then(()=>window.location.reload);
           window.location.reload();
           //this.router.navigateByUrl('/merchant-products');
        },
        (error) => {
          console.error('Error fetching products:', error);
        }
    );
  }
   

  /*products = [
    {
      id:4,
      image: 'https://example.com/product1.jpg',
      name: 'Product 1',
      price: 29.99,
      description: 'This is the description for Product 1.',
      stockQuantity: 50
    },
    {
      id:5,

      image: 'https://example.com/product2.jpg',
      name: 'Product 2',
      price: 49.99,
      description: 'This is the description for Product 2.',
      stockQuantity: 30
    },
    {
      id:7,

      image: 'https://example.com/product3.jpg',
      name: 'Product 3',
      price: 19.99,
      description: 'This is the description for Product 3.',
      stockQuantity: 100
    }
  ];*/


  createNewProduct() {
    this.router.navigate(['/create-product'])
    }
    selectedProduct = { id:0 ,name: '', price: 0, description: '', stock_quantity: 0,image_url:'',userId:0 };
     
    selectProduct(product: Product) {
      this.selectedProduct = {
        id: product.id,  // Assign the actual product ID here
        name: product.name,
        price: product.price,
        description: product.description,
        stock_quantity: product.stock_quantity,
        image_url: product.image_url,
        userId: product.userId
      };
    }

    update(product:Product,id:number) {
      console.log('Selected product before update:', this.selectedProduct.id);
      
      this.theproductservice.updateProduct(id,product).subscribe(
      
        (response:any) => {
          console.log('Product updated:', response);
          console.log(this.selectedProduct);
          // this.loadProducts();
          // Reload products after update
          //this.router.navigate(['/merchant-dashboard'])
          //window.location.reload();
          this.router.navigateByUrl(this.router.url)
          

        },
        (error:any) => {
          console.error('Error updating product:', error);
        }
      );
      //window.location.reload()
    }

    getproductbyid(id:number){
      this.theproductservice.getProduct(id).subscribe(
         (response:any)=>{
          this.selectedProduct=response;
          this.selectedProduct.id=id;
          console.log(response);
          this.update(this.selectedProduct,this.selectedProduct.id);
         }
      );
    }



}
