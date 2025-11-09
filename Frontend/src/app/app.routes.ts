import { Routes } from "@angular/router";
import { HomePageComponent } from "./components/pages/homePage/homePage.component";
import { LoginPageComponent } from "./components/pages/loginPage/loginPage.component";
import { ProfilePageComponent } from "./components/pages/profilePage/profilePage.component";
import { UpdatePageComponent } from "./components/pages/updatePage/updatePage.component";

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
  {
    path: "profile/update",
    component: UpdatePageComponent,
  },
];
