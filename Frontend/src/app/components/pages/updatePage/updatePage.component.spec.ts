import { ComponentFixture, TestBed } from '@angular/core/testing';
import { UpdatePageComponent } from './updatePage.component';

describe('UpdatePageComponent', () => {
  let component: UpdatePageComponent;
  let fixture: ComponentFixture<UpdatePageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UpdatePageComponent]
    }).compileComponents();

    fixture = TestBed.createComponent(UpdatePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should open modal on request confirm', () => {
    component.onRequestConfirm({ type: 'email', payload: { newEmail: 'test@example.com' } });
    expect(component.showModal).toBeTrue();
    expect(component.modalType).toEqual('email');
  });
});
