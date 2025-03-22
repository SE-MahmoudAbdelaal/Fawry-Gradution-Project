import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MerchantProductsComponent } from './merchant-products.component';

describe('MerchantProductsComponent', () => {
  let component: MerchantProductsComponent;
  let fixture: ComponentFixture<MerchantProductsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MerchantProductsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MerchantProductsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
