import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UsuarioCreateCardComponent } from './usuario-create-card.component';

describe('UsuarioCreateCardComponent', () => {
  let component: UsuarioCreateCardComponent;
  let fixture: ComponentFixture<UsuarioCreateCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UsuarioCreateCardComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UsuarioCreateCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
