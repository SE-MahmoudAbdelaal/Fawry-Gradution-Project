import { Component } from '@angular/core';
import { MerchantSidebarComponent } from "../merchant-sidebar/merchant-sidebar.component";
import { NgFor } from '@angular/common';
import { CreateCouponModelComponent } from "../create-coupon-model/create-coupon-model.component";
import { Router } from '@angular/router';
import { CouponService } from '../services/coupon.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-coupons',
  standalone: true,
  imports: [MerchantSidebarComponent,
    NgFor, CreateCouponModelComponent,
    FormsModule
  ],
  templateUrl: './coupons.component.html',
  styleUrl: './coupons.component.css'
})
export class CouponsComponent {



  constructor(private router:Router,private couponService:CouponService) {


  }

coupons = [
  { code: 'SAVE20', usageCount: 10, expireDate: '2024-12-31', fxidValue: 123, percentageValue: 20 },
  { code: 'HOLIDAY50', usageCount: 5, expireDate: '2024-11-30', fxidValue: 456, percentageValue: 50 },
  { code: 'NEWYEAR10', usageCount: 8, expireDate: '2025-01-15', fxidValue: 789, percentageValue: 10 },
];


createNewCoupon() {
  this.router.navigate(['/create-coupon'])
  }
  selectedCoupon = { id:0 ,code: '', usageCount: 0, expireDate: '', fxidValue: 0,percentageValue:0 };


  onSubmit() {
    this.couponService.updateCoupon(this.selectedCoupon.id, this.selectedCoupon).subscribe(
      (response:any) => {
        console.log('Coupon updated:', response);
        // this.loadProducts();
        // Reload products after update

      },
      (error:any) => {
        console.error('Error updating Coupon:', error);
      }
    );
  }
}
