import { Component } from '@angular/core';
import { SideBarComponent } from '../side-bar/side-bar.component';
import { CommonModule } from '@angular/common';
import { NgClass } from '@angular/common';

@Component({
  selector: 'app-ratings',
  standalone: true,
  imports: [ SideBarComponent, CommonModule, NgClass],
  templateUrl: './ratings.component.html',
  styleUrl: './ratings.component.css',
})
export class RatingsComponent {

  reviews: any[] = [];

  constructor(
  ) {
  //   const vendorToken = sessionStorage.getItem('vendorToken');
  //   if (vendorToken) {
  //     this._vendorDashboardService.getReviews(vendorToken).subscribe(
  //       (res) => {
  //         this.reviews = res.data;
  //       },
  //       (error) => {
  //         console.error('message:', error);
  //       }
  //     );
    // }
  }
}
