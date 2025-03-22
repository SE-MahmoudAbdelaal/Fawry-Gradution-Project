import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchCouponComponent } from './search-coupon.component';

describe('SearchCouponComponent', () => {
  let component: SearchCouponComponent;
  let fixture: ComponentFixture<SearchCouponComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SearchCouponComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SearchCouponComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
