import { Routes } from "@angular/router";
import { HomePageComponent } from "./components/pages/homePage/homePage.component";
import { LoginPageComponent } from "./components/pages/loginPage/loginPage.component";
import { ProfilePageComponent } from "./components/pages/profilePage/profilePage.component";
import { UpdatePageComponent } from "./components/pages/updatePage/updatePage.component";
import { NotificationListPageComponent } from "./components/pages/notification-list-page/notification-list-page.component";
import { TicketManagementPageComponent } from "./components/pages/ticket-management-page/ticket-management-page.component";
import { UserTicketManagementPageComponent } from "./components/pages/user-ticket-management-page/user-ticket-management-page.component";
import { TicketCreatePageComponent } from "./components/pages/ticket-create-page/ticket-create-page.component";
import { CommentManagementPageComponent } from "./components/pages/comment-management-page/comment-management-page.component";
import { CommentViewPageComponent } from "./components/pages/comment-view-page/comment-view-page.component";
import { TestEndpointsComponent } from "./test-endpoints/test-endpoints.component";
import { UserHomePageComponent } from "./components/pages/userHomePage/userHomePage.component";

export const routes: Routes = [
  {
    path: "",
    component: LoginPageComponent,
  },
  {
    path: "home",
    component: HomePageComponent,
  },
  {
    path: "home/user",
    component: UserHomePageComponent,
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
    path: "ticket/management/user",
    component: UserTicketManagementPageComponent,
  },
  {
    path: "ticket/create",
    component: TicketCreatePageComponent,
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
    path: 'test',
    component: TestEndpointsComponent,
  },
];
