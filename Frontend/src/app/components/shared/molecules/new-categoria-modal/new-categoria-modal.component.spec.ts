import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewCategoriaModalComponent } from './new-categoria-modal.component';

describe('NewCategoriaModalComponent', () => {
  let component: NewCategoriaModalComponent;
  let fixture: ComponentFixture<NewCategoriaModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NewCategoriaModalComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(NewCategoriaModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
