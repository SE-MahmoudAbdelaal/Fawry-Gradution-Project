import { Component } from '@angular/core';
import { CopyRightComponent } from "../copy-right/copy-right.component";

@Component({
  selector: 'app-footer',
  standalone: true,
  imports: [CopyRightComponent],
  templateUrl: './footer.component.html',
  styleUrl: './footer.component.css'
})
export class FooterComponent {

}
