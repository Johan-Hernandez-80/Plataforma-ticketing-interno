import { ComponentFixture, TestBed } from '@angular/core/testing';
import { UserTicketManagementPageComponent } from './user-ticket-management-page.component';

describe('UserTicketManagementPageComponent', () => {
    let component: UserTicketManagementPageComponent;
    let fixture: ComponentFixture<UserTicketManagementPageComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [UserTicketManagementPageComponent]
        }).compileComponents();

        fixture = TestBed.createComponent(UserTicketManagementPageComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
