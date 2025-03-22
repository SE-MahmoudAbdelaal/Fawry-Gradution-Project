import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { SearchListComponentComponent } from '../search-list-component/search-list-component.component';

@Component({
  selector: 'app-search',
  standalone: true,
  imports:  [FormsModule,SearchListComponentComponent],
  templateUrl: './search.component.html',
  styleUrl: './search.component.css'
})


export class SearchComponent implements OnInit{
  searchValue !: string;
  constructor (private router :Router){}
  ngOnInit(): void {

  }


  goToSearchComponent() {
  this.router.navigate(['/searchlist', this.searchValue],);

  //update the search

  }
}
