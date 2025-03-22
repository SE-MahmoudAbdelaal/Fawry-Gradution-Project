import { Component } from '@angular/core';
import { Routes } from '@angular/router';
import path from 'path';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { CartComponent } from './components/cart/cart.component';
import { HomeComponent } from './components/home/home.component';
import { ProductsDetailsComponent } from './components/products-details/products-details.component';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { PaymentComponent } from './components/payment/payment.component';
import { SearchListComponentComponent } from './components/search-list-component/search-list-component.component';
import { AccountSettingsComponent } from './components/vendorDashboard/account-settings/account-settings.component';
import { AdmindashboardComponent } from './admin/admindashboard/admindashboard.component';
import { MerchantsComponent } from './admin/merchants/merchants.component';
import { ShowAllUsersComponent } from './admin/show-all-users/show-all-users.component';
import { ShowAdminsComponent } from './admin/show-admins/show-admins.component';
import { CreateAdminComponent } from './admin/create-admin/create-admin.component';
import { MerchantDashboardComponent } from './merchant/merchant-dashboard/merchant-dashboard.component';
// import { CouponsComponent } from './merchant/coupons/coupons.component';
import { MerchantSidebarComponent } from './merchant/merchant-sidebar/merchant-sidebar.component';
import { MerchantProductsComponent } from './merchant/merchant-products/merchant-products.component';
import { CreateCouponModelComponent } from './merchant/create-coupon-model/create-coupon-model.component';
import { CreateProductComponent } from './merchant/create-product/create-product.component';
import { SearchCouponComponent } from './merchant/search-coupon/search-coupon.component';
import { authGuard } from './guard/auth.guard';
import { OrderComponent } from './components/order/order.component';
// import { AdminDashboardComponent } from './components/admin/AdminDashboardComponent';
// import { CategoriesComponent } from './components/admin/categories/categories.component';
// import { LoginAdminComponent } from './components/admin/login-admin/login-admin.component';
// import { PaymentMethodsComponent } from './components/admin/payment-methods/payment-methods.component';
// import { PromoCodesComponent } from './components/admin/promo-codes/promo-codes.component';
// import { VendorsComponent } from './components/admin/vendors/vendors.component';
// import { VendorDashboardComponent } from './components/vendorDashboard/vendor-dashboard/vendor-dashboard.component';
// import { AddProductComponent } from './components/vendorDashboard/add-product/add-product.component';
// import { EditProductComponent } from './components/vendorDashboard/edit-product/edit-product.component';
// import { PromocodesComponent } from './components/vendorDashboard/promocodes/promocodes.component';
// import { RatingsComponent } from './components/vendorDashboard/ratings/ratings.component';
// import { SideBarComponent } from './components/vendorDashboard/side-bar/side-bar.component';
// import { VendorOrdersComponent } from './components/vendorDashboard/vendor-orders/vendor-orders.component';
// import { VendorProductsComponent } from './components/vendorDashboard/vendor-products/vendor-products.component';

export const routes: Routes = [
  {path:"home", component:HomeComponent , canActivate: [authGuard] },
  {path:"details/:id", component:ProductsDetailsComponent},
  {path:"profile", component:UserProfileComponent},
  {path:"register",component:RegisterComponent},
  {path:"cart",component:CartComponent},
  {path:"payment",component:PaymentComponent},
  {path:"searchlist",component:SearchListComponentComponent},
  {path:"login", component:LoginComponent},
{path:"orders",component:OrderComponent},

  // {path:"admin", component:AdminDashboardComponent},
  {path:"admin/dashboard", component:AdmindashboardComponent , canActivate: [authGuard]},
  {path:"show-admins", component:ShowAdminsComponent},
  {path:"create-admins", component:CreateAdminComponent},
  {path:"showallusers", component:ShowAllUsersComponent},
  {path:"merchents", component:AdmindashboardComponent},



  {path:"merchent-dashboard", component:MerchantDashboardComponent , canActivate: [authGuard]},
  {path:"merchent-products", component:MerchantProductsComponent},
  {path:"merchent-sidebar", component:MerchantSidebarComponent},
  {path:"search-coupones", component:SearchCouponComponent},
  {path:"create-coupon", component:CreateCouponModelComponent},
  {path:"create-product", component:CreateProductComponent},



    {path:"**",redirectTo:"home",pathMatch:"full"},

];
