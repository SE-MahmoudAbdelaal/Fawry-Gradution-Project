import { Component } from '@angular/core';
import { NavbarComponent } from "../../shared/components/navbar/navbar.component";
import { HeaderComponent } from "../header/header.component";
import { OurBrandComponent } from "../our-brand/our-brand.component";
import { FooterComponent } from "../../shared/components/footer/footer/footer.component";
import { AllProductComponent } from "../all-product/all-product.component";
import { RouterOutlet } from '@angular/router';
import { NgClass } from '@angular/common';
import { SliderComponent } from "../slider/slider.component";


@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    NavbarComponent,
    HeaderComponent,
    OurBrandComponent,
    FooterComponent,
    AllProductComponent,
    RouterOutlet,
    NgClass,
    SliderComponent
],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

  isVisible = false;
  animationClass = '';
  delay = 0;

  ngOnInit() {
    // Initialize the animation after the delay
    setTimeout(() => {
      this.animationClass = 'animate__fadeInDown'; // Assign the animation class
      this.isVisible = true; // Make the element visible
    }, this.delay);
  }
}
