import { Component } from '@angular/core';
import { NgIf } from '@angular/common';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { SidebarComponent } from "../sidebar/sidebar.component";
import { AdminService } from '../service/admin.service';

@Component({
  selector: 'app-create-admin',
  standalone: true,
  imports: [
    NgIf,
    ReactiveFormsModule,
    SidebarComponent
],
  templateUrl: './create-admin.component.html',
  styleUrl: './create-admin.component.css'
})
export class CreateAdminComponent {


      constructor(private adminService:AdminService,private router:Router){}
      adminForm: FormGroup<any> = new FormGroup(
        {firstName:new FormControl(null,[Validators.required,Validators.minLength(3),Validators.maxLength(30)]),
          lastName:new FormControl(null,[Validators.required,Validators.minLength(3),Validators.maxLength(30)]),
          email:new FormControl(null,[Validators.required,Validators.email]),
          phone:new FormControl(null,[Validators.required]),
          gender:new FormControl(null,[Validators.required]),
          role:new FormControl("ADMIN"),
         password:new FormControl(null,[Validators.required,Validators.pattern(/^[A-Z]/)]),

              });
            // }
            submitadminForm() {
              if (this.adminForm.valid) {
                console.log(this.adminForm.value);
                localStorage.setItem('userValue', JSON.stringify(this.adminForm.value));
                
                this.adminService.createAdmin(this.adminForm.value).subscribe(
                  response => {
                    console.log('Admin created successfully', response);
                    this.router.navigate(['/show-admins']);
                    // Handle success response, maybe redirect or show a success message
                  },
                  error => {
                    console.error('Error creating admin', error);
                    // Handle error response, show an error message
                  }
                );
              }
            }
          }



