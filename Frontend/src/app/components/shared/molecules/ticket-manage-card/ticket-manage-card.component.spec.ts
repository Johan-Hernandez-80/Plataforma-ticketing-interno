import { ComponentFixture, TestBed } from "@angular/core/testing";
import { TicketManageCardComponent } from "./ticket-manage-card.component";

describe("TicketManageCardComponent", () => {
  let component: TicketManageCardComponent;
  let fixture: ComponentFixture<TicketManageCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TicketManageCardComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(TicketManageCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });
});
