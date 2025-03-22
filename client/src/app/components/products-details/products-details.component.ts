import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProductsService } from '../../services/product.service';
import { SpinnerComponent } from '../../shared/components/spinner/spinner.component';
import { NgIf } from '@angular/common';
import { error } from 'console';
import { NavbarComponent } from "../../shared/components/navbar/navbar.component";
import { FooterComponent } from "../../shared/components/footer/footer/footer.component";

@Component({
  selector: 'app-products-details',
  standalone: true,
  imports: [
    SpinnerComponent,
    NgIf,
    NavbarComponent,
    FooterComponent
],
  templateUrl: './products-details.component.html',
  styleUrl: './products-details.component.scss'
})
export class ProductsDetailsComponent {
  id:any
  productData:any ={}
  loading:boolean= false
  constructor(private route:ActivatedRoute,private _productService:ProductsService){
this.id = this.route.snapshot.paramMap.get("id")
console.log(this.id);}

ngOnInit(): void {
  //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
  //Add 'implements OnInit' to the class.
  // this.getDetailsProduct(this.id)
}

// getDetailsProduct(id:any){
// this.loading = true
//   this._productService.getDetailsProduct(this.id).subscribe((res:any)=>{
//   this.loading = false
//     this.productData=res
//   },error => {this.loading = true
//   alert(error)
//   }
// )}}

}