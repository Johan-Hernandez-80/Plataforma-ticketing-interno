import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CommentListAdminItemComponent } from './comment-list-admin-item.component';

describe('CommentListAdminItemComponent', () => {
  let component: CommentListAdminItemComponent;
  let fixture: ComponentFixture<CommentListAdminItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CommentListAdminItemComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CommentListAdminItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
