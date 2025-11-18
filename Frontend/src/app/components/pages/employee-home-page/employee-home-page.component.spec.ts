import { ComponentFixture, TestBed } from "@angular/core/testing";

import { EmployeeHomePageComponent } from "./agent-home-page.component";

describe("EmployeeHomePageComponent", () => {
  let component: EmployeeHomePageComponent;
  let fixture: ComponentFixture<EmployeeHomePageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EmployeeHomePageComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(EmployeeHomePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });
});
