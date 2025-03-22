import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateCouponModelComponent } from './create-coupon-model.component';

describe('CreateCouponModelComponent', () => {
  let component: CreateCouponModelComponent;
  let fixture: ComponentFixture<CreateCouponModelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreateCouponModelComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreateCouponModelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
