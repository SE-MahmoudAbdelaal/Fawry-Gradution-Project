import { Component } from '@angular/core';
import { SidebarComponent } from "../../admin/sidebar/sidebar.component";
import { MerchantsComponent } from "../../admin/merchants/merchants.component";
import { MerchantSidebarComponent } from "../merchant-sidebar/merchant-sidebar.component";
import { MerchantProductsComponent } from "../merchant-products/merchant-products.component";
import { CreateCouponModelComponent } from "../create-coupon-model/create-coupon-model.component";
// import { CouponsComponent } from "../coupons/coupons.component";
import { CreateProductComponent } from "../create-product/create-product.component";

@Component({
  selector: 'app-merchant-dashboard',
  standalone: true,
  imports: [SidebarComponent, MerchantsComponent, MerchantSidebarComponent, MerchantProductsComponent, CreateCouponModelComponent, CreateProductComponent],
  templateUrl: './merchant-dashboard.component.html',
  styleUrl: './merchant-dashboard.component.css'
})
export class MerchantDashboardComponent {

}
