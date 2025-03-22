import { Component } from '@angular/core';

import { RouterLink } from '@angular/router';
import { NgClass } from '@angular/common';
import { FooterComponent } from '../../shared/components/footer/footer/footer.component';
import { NavbarComponent } from '../../shared/components/navbar/navbar.component';

@Component({
  selector: 'app-user-profile',
  standalone: true,
  imports: [FooterComponent,NavbarComponent,NgClass,RouterLink],
  templateUrl: './user-profile.component.html',
  styleUrl: './user-profile.component.css'
})
export class UserProfileComponent {

  constructor(){}
userInfo:any



}
