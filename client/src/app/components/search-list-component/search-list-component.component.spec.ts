import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchListComponentComponent } from './search-list-component.component';

describe('SearchListComponentComponent', () => {
  let component: SearchListComponentComponent;
  let fixture: ComponentFixture<SearchListComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SearchListComponentComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SearchListComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
