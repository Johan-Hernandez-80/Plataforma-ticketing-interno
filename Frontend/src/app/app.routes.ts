import { Routes } from "@angular/router";
import { HomePageComponent } from "./components/pages/homePage/homePage.component";
import { LoginPageComponent } from "./components/pages/loginPage/loginPage.component";
import { NotificationListPageComponent } from "./components/pages/notification-list-page/notification-list-page.component";

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
];
