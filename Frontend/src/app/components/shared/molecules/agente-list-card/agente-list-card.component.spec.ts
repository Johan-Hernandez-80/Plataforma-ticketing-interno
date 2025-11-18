import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AgenteListCardComponent } from './agente-list-card.component';

describe('AgenteListCardComponent', () => {
  let component: AgenteListCardComponent;
  let fixture: ComponentFixture<AgenteListCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AgenteListCardComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AgenteListCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
