import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CommentViewAdminPageComponent } from './comment-view-admin-page.component';

describe('CommentViewAdminPageComponent', () => {
  let component: CommentViewAdminPageComponent;
  let fixture: ComponentFixture<CommentViewAdminPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CommentViewAdminPageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CommentViewAdminPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
