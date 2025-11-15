import { ComponentFixture, TestBed } from '@angular/core/testing';
import { UserHomePageComponent } from './userHomePage.component';

describe('UserHomePageComponent', () => {
  let component: UserHomePageComponent;
  let fixture: ComponentFixture<UserHomePageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UserHomePageComponent]
    }).compileComponents();

    fixture = TestBed.createComponent(UserHomePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
