import { Component, OnInit } from '@angular/core';
import { SidebarComponent } from '../sidebar/sidebar.component';
import { NgFor } from '@angular/common';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-show-all-users',
  standalone: true,
  imports: [
    SidebarComponent,
    NgFor
  ],
  templateUrl: './show-all-users.component.html',
  styleUrl: './show-all-users.component.css'
})
export class ShowAllUsersComponent implements OnInit {

  users: any[] = []

  constructor(private userService :UserService){}

  ngOnInit(): void {
 this.getAllUsers()
  }




    getAllUsers(){
      this.userService.getAllUserS().subscribe((res)=>{
        this.users = res
      })
    }

    deletUser(userId:any){
      this.userService.deleteUser(userId).subscribe((res)=>{
        this.users = this.users.filter(user => user.id !== userId)
        this.getAllUsers()
      })
    }
}
