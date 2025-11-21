import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReportePageComponent } from './reporte-page.component';

describe('ReportePageComponent', () => {
  let component: ReportePageComponent;
  let fixture: ComponentFixture<ReportePageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReportePageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ReportePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
