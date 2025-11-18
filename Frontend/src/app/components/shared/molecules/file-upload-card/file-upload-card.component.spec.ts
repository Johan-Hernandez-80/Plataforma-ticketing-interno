import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FileUploadCardComponent } from './file-upload-card.component';

describe('FileUploadCardComponent', () => {
    let component: FileUploadCardComponent;
    let fixture: ComponentFixture<FileUploadCardComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [FileUploadCardComponent]
        }).compileComponents();

        fixture = TestBed.createComponent(FileUploadCardComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
