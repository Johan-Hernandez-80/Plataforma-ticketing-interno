import { Routes } from "@angular/router";
import { HomePageComponent } from "./components/pages/homePage/homePage.component";
import { LoginPageComponent } from "./components/pages/loginPage/loginPage.component";

export const routes: Routes = [
  {
    path: "login",
    component: LoginPageComponent,
  },
  {
    path: "home",
    component: HomePageComponent,
  },
];
