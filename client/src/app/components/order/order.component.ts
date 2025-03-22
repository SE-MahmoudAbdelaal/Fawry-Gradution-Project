import { Component, OnInit } from '@angular/core';
import { OrderService } from '../../services/order.service';
import { error } from 'console';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
@Component({
  selector: 'app-order',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './order.component.html',
  styleUrl: './order.component.css',
})
export class OrderComponent implements OnInit {
  orders: any[] = [];
  startDate = '';
  endDate = '';
  errorMessage = '';
  userId = localStorage.getItem('userId') || '';

  constructor(private orderService: OrderService) {}
  ngOnInit(): void {
    this.getAllOrders();
  }

  getAllOrders() {
    this.orderService.getAllOrders(this.userId).subscribe(
      (response) => {
        this.orders = response;
      },
      (error) => {
        this.errorMessage = 'Failed to load orders.';
        console.log('Error fetching orders: ', error);
      }
    );
  }
  searchOrders() {
    if (this.startDate && this.endDate) {
      console.log('startDate :>> ', this.startDate);
      console.log('this.endDate :>> ', this.endDate);
      this.orderService
        .searchOrdersByDate(this.userId, this.startDate, this.endDate)
        .subscribe(
          (response) => {
            this.orders = response;
            console.log('response :>> ', response);
          },
          (error) => {
            console.log('error :>> ', error);
            this.errorMessage = 'Failed to load orders.';
          }
        );
    } else {
      this.errorMessage = 'Please select both start and end dates.';
    }
  }
}
