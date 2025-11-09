import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CommentViewPageComponent } from './comment-view-page.component';

describe('CommentViewPageComponent', () => {
  let component: CommentViewPageComponent;
  let fixture: ComponentFixture<CommentViewPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CommentViewPageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CommentViewPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
