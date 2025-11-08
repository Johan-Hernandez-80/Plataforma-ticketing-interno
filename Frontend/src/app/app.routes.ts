import { Routes } from "@angular/router";
import { HomePageComponent } from "./components/pages/homePage/homePage.component";
import { LoginPageComponent } from "./components/pages/loginPage/loginPage.component";
import { ProfilePageComponent } from "./components/pages/profilePage/profilePage.component";

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
    path: "profile",
    component: ProfilePageComponent,
  },
];
