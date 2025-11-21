import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReporteCardComponent } from './reporte-card.component';

describe('ReporteCardComponent', () => {
  let component: ReporteCardComponent;
  let fixture: ComponentFixture<ReporteCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReporteCardComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ReporteCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
