import { Routes } from "@angular/router";
import { AgentHomePageComponent } from "./components/pages/agent-home-page/agent-home-page.component";
import { AdminHomePageComponent } from "./components/pages/admin-home-page/admin-home-page.component";
import { EmployeeHomePageComponent } from "./components/pages/employee-home-page/employee-home-page.component";
import { LoginPageComponent } from "./components/pages/login-page/login-page.component";
import { ProfilePageComponent } from "./components/pages/profilePage/profilePage.component";
import { UpdatePageComponent } from "./components/pages/updatePage/updatePage.component";
import { NotificationListPageComponent } from "./components/pages/notification-list-page/notification-list-page.component";
import { TicketManagementPageComponent } from "./components/pages/ticket-management-page/ticket-management-page.component";
import { CommentManagementPageComponent } from "./components/pages/comment-management-page/comment-management-page.component";
import { CommentViewPageComponent } from "./components/pages/comment-view-page/comment-view-page.component";
import { TestEndpointsComponent } from "./test-endpoints/test-endpoints.component";

export const routes: Routes = [
  {
    path: "",
    component: LoginPageComponent,
  },
  {
    path: "agent/home",
    component: AgentHomePageComponent,
  },
  {
    path: "admin/home",
    component: AdminHomePageComponent,
  },
  {
    path: "home",
    component: EmployeeHomePageComponent,
  },
  {
    path: "profile",
    component: ProfilePageComponent,
  },
  {
    path: "profile/update",
    component: UpdatePageComponent,
  },
  {
    path: "notifications",
    component: NotificationListPageComponent,
  },
  {
    path: "ticket/management",
    component: TicketManagementPageComponent,
  },
  {
    path: "ticket/management/comments",
    component: CommentManagementPageComponent,
  },
  {
    path: "ticket/comments/view",
    component: CommentViewPageComponent,
  },
  {
    path: "test",
    component: TestEndpointsComponent,
  },
];
