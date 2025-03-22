import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowAllUsersComponent } from './show-all-users.component';

describe('ShowAllUsersComponent', () => {
  let component: ShowAllUsersComponent;
  let fixture: ComponentFixture<ShowAllUsersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ShowAllUsersComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShowAllUsersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
