import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MerchantSidebarComponent } from './merchant-sidebar.component';

describe('MerchantSidebarComponent', () => {
  let component: MerchantSidebarComponent;
  let fixture: ComponentFixture<MerchantSidebarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MerchantSidebarComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MerchantSidebarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
