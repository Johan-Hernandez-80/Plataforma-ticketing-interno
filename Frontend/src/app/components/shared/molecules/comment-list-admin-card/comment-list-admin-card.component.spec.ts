import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CommentListAdminCardComponent } from './comment-list-admin-card.component';

describe('CommentListAdminCardComponent', () => {
  let component: CommentListAdminCardComponent;
  let fixture: ComponentFixture<CommentListAdminCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CommentListAdminCardComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CommentListAdminCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
