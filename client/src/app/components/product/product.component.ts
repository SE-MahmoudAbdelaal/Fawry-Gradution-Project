import { NgIf } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { RouterLink } from '@angular/router';
import { Product } from '../../product';
import { CartService } from '../../services/cart.service';

@Component({
  selector: 'app-product',
  standalone: true,
  imports: [
    NgIf,
    FormsModule,

    RouterLink,
  ],
  templateUrl: './product.component.html',
  styleUrl: './product.component.scss'
})
export class ProductComponent {
@Input() data!:any
@Output() item = new EventEmitter
// @Input() product: any;
addButton:boolean =false
amount:number = 1

// add(){
// this.item.emit({item:this.data,quantity:this.amount})
// }
cartamount: number = 1;
// addButton: boolean = false;

constructor(private cartService: CartService) {}
updatedCount!:number

add() {

this.item.emit({product:this.data,quantity:this.amount})
}

  // Method to handle the "Add to Cart" button click
  // onAddToCart() {
  //   this.cartService.addToCart();
  //   this.updatedCount = this.cartamount.value + 1;
  //   if (!isNaN(this.updatedCount)) {
  //     this.cartCountSubject.next(updatedCount);
  //     localStorage.setItem('cartCount', updatedCount.toString());
  //   }
  // }


}


