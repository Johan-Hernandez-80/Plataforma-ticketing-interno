import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CommentAdminCardComponent } from './comment-admin-card.component';

describe('CommentAdminCardComponent', () => {
  let component: CommentAdminCardComponent;
  let fixture: ComponentFixture<CommentAdminCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CommentAdminCardComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CommentAdminCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
