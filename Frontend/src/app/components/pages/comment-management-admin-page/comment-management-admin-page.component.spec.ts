import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CommentManagementAdminPageComponent } from './comment-management-admin-page.component';

describe('CommentManagementAdminPageComponent', () => {
  let component: CommentManagementAdminPageComponent;
  let fixture: ComponentFixture<CommentManagementAdminPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CommentManagementAdminPageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CommentManagementAdminPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
