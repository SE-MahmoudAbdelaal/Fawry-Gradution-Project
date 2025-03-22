import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { HeaderComponent } from '../header/header.component';
import { FooterComponent } from '../footer/footer.component';
import { withLatestFrom } from 'rxjs';
import { NgClass } from '@angular/common';
import { ThemeService } from '../services/theme.service';

@Component({
  selector: 'app-contact-us',
  standalone: true,
  imports: [RouterLink, HeaderComponent, FooterComponent,NgClass],
  templateUrl: './contact-us.component.html',
  styleUrl: './contact-us.component.css',
})
export class ContactUsComponent {
  constructor(public _themeservice:ThemeService){}

  // this is a placeholder page can change later
  // need to see if we can send the form info to the back end to store that data
}
