import { Component, OnInit } from '@angular/core';
import { SidebarComponent } from "../sidebar/sidebar.component";
import { Merchant } from '../../models/merchant';
import { NgFor } from '@angular/common';
import { MerchantService } from '../service/merchant.service';

@Component({
  selector: 'app-merchants',
  standalone: true,
  imports: [SidebarComponent,
    NgFor,
  ],
  templateUrl: './merchants.component.html',
  styleUrl: './merchants.component.css'
})
export class MerchantsComponent implements OnInit {

  constructor(private merchantservise :MerchantService){}

  ngOnInit(): void {
 this.getAllMerchants()
  }
    merchants: any[] = []



    getAllMerchants(){
      this.merchantservise.getAllUserS().subscribe((res)=>{
        this.merchants = res
      })
    }

    deletMerchant(merchantId:any){
      this.merchantservise.deleteUser(merchantId).subscribe((res)=>{
        this.merchants = this.merchants.filter(merchant => merchant.id !== merchantId);
        this.getAllMerchants()
      })
    }
  }


