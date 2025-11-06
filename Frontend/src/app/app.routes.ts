import { Routes } from "@angular/router";
import { HomePageComponent } from "./components/pages/homePage/homePage.component";
import { LoginPageComponent } from "./components/pages/loginPage/loginPage.component";
import { NotificationListPageComponent } from "./components/pages/notification-list-page/notification-list-page.component";
import { TicketManagementPageComponent } from "./components/pages/ticket-management-page/ticket-management-page.component";
import { CommentManagementPageComponent } from "./components/pages/comment-management-page/comment-management-page.component";

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
];
