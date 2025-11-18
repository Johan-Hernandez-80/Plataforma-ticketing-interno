import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AgenteListItemComponent } from './agente-list-item.component';

describe('AgenteListItemComponent', () => {
  let component: AgenteListItemComponent;
  let fixture: ComponentFixture<AgenteListItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AgenteListItemComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AgenteListItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
