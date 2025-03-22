import { Component, OnInit } from '@angular/core';
import { ProductComponent } from "../product/product.component";
import { ProductsService } from '../../services/product.service';
import { ActivatedRoute } from '@angular/router';
import { FooterComponent } from "../../shared/components/footer/footer/footer.component";
import { NavbarComponent } from "../../shared/components/navbar/navbar.component";

@Component({
  selector: 'app-search-list-component',
  standalone: true,
  imports: [ProductComponent, FooterComponent, NavbarComponent],
  templateUrl: './search-list-component.component.html',
  styleUrl: './search-list-component.component.css'
})
export class SearchListComponentComponent implements OnInit {
  products?: any[];
  searchValue!: string;

  constructor(private getapilistservice: ProductsService, private activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    // Initial setup based on the current route params
    this.activatedRoute.params.subscribe(params => {
      this.searchValue = params['searchValue'];
      this. fetchproducts();
    });

    // Listen for changes in route params
    this.activatedRoute.params.subscribe(params => {
      this.searchValue = params['searchValue'];
      this.fetchproducts();
    });
  }

  fetchproducts() {
    this.getapilistservice.searchItem(this.searchValue).subscribe((response: any) => {
      this.products = response.results;
    });
  }
}
