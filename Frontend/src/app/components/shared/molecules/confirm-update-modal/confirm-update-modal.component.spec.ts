import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ConfirmUpdateModalComponent } from './confirm-update-modal.component';

describe('ConfirmUpdateModalComponent', () => {
  let component: ConfirmUpdateModalComponent;
  let fixture: ComponentFixture<ConfirmUpdateModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ConfirmUpdateModalComponent]
    }).compileComponents();

    fixture = TestBed.createComponent(ConfirmUpdateModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
