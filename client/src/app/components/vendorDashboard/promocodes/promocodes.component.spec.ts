import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PromocodesComponent } from './promocodes.component';

describe('PromocodesComponent', () => {
  let component: PromocodesComponent;
  let fixture: ComponentFixture<PromocodesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PromocodesComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PromocodesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
