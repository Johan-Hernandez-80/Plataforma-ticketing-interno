import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CommentManagementPageComponent } from './comment-management-page.component';

describe('CommentManagementPageComponent', () => {
  let component: CommentManagementPageComponent;
  let fixture: ComponentFixture<CommentManagementPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CommentManagementPageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CommentManagementPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
