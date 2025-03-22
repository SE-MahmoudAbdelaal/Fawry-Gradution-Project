import { Component } from '@angular/core';
import { CouponService } from '../services/coupon.service';
import bootstrap from '../../../main.server';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { NgIf } from '@angular/common';
import { MerchantSidebarComponent } from "../merchant-sidebar/merchant-sidebar.component";

@Component({
  selector: 'app-create-coupon-model',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    NgIf,
    MerchantSidebarComponent
],
  templateUrl: './create-coupon-model.component.html',
  styleUrl: './create-coupon-model.component.css'
})
export class CreateCouponModelComponent {
  addCoupon() {
    if (this.couponForm.valid) {
      this.couponService.addCoupon(this.couponForm.value).subscribe(
        response => {
          console.log('Coupon added successfully', response);
        },
        error => {
          
          console.error('Error adding coupon', error);
        }
      );
    }
  }
  
  coupons = [
    { code: 'SAVE20', usageCount: 10, expireDate: '2024-12-31', fxidValue: 123, percentageValue: 20 },
    { code: 'HOLIDAY50', usageCount: 5, expireDate: '2024-11-30', fxidValue: 456, percentageValue: 50 },
    { code: 'NEWYEAR10', usageCount: 8, expireDate: '2025-01-15', fxidValue: 789, percentageValue: 10 },
  ];
  newCoupon = {
    code: '',
    usageCount: 0,
    expireDate: '',
    fxidValue: 0,
    percentageValue: 0
  };


  couponForm!: FormGroup;

  constructor(private fb: FormBuilder, private couponService: CouponService) {}

  ngOnInit(): void {
    this.couponForm = this.fb.group({
      code: ['', Validators.required],
      usageCount: [0, [Validators.required, Validators.min(1)]],
      maxUsages: [0, [Validators.required, Validators.min(1)]],
      expiryDate: ['', Validators.required],
      value: [0, Validators.required],
      valueType: ['', Validators.required]
    });
  }
}