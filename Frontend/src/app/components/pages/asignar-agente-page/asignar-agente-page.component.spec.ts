import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AsignarAgentePageComponent } from './asignar-agente-page.component';

describe('AsignarAgentePageComponent', () => {
  let component: AsignarAgentePageComponent;
  let fixture: ComponentFixture<AsignarAgentePageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AsignarAgentePageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AsignarAgentePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
