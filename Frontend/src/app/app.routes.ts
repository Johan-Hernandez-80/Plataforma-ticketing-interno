import { Routes } from "@angular/router";
import { AgentHomePageComponent } from "./components/pages/agent-home-page/agent-home-page.component";
import { AdminHomePageComponent } from "./components/pages/admin-home-page/admin-home-page.component";
import { EmployeeHomePageComponent } from "./components/pages/employee-home-page/employee-home-page.component";
import { LoginPageComponent } from "./components/pages/login-page/login-page.component";
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
import { TestComponent } from "./components/shared/molecules/test/test.component";
import { TicketManagementAdminPageComponent } from "./components/pages/ticket-management-admin-page/ticket-management-admin-page.component";
import { CommentManagementAdminPageComponent } from "./components/pages/comment-management-admin-page/comment-management-admin-page.component";
import { AsignarAgentePageComponent } from "./components/pages/asignar-agente-page/asignar-agente-page.component";
import { CommentViewAdminPageComponent } from "./components/pages/comment-view-admin-page/comment-view-admin-page.component";
import { UsuarioManagementPageComponent } from "./components/pages/usuario-management-page/usuario-management-page.component";
import { UsuarioDetailPageComponent } from "./components/pages/usuario-detail-page/usuario-detail-page.component";
import { UpdateAdminPageComponent } from "./components/pages/update-admin-page/update-admin-page.component";

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
    path: "admin/profile/update",
    component: UpdateAdminPageComponent,
  },
  {
    path: "notifications",
    component: NotificationListPageComponent,
  },
  {
    path: "ticket/management/:id",
    component: TicketManagementPageComponent,
  },
  {
    path: "admin/ticket/management/:id",
    component: TicketManagementAdminPageComponent,
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
    path: "ticket/management/:id/comments",
    component: CommentManagementPageComponent,
  },
  {
    path: "admin/ticket/management/:id/comments/:comentarioId",
    component: CommentViewAdminPageComponent,
  },
  {
    path: "admin/ticket/management/:id/comments",
    component: CommentManagementAdminPageComponent,
  },
  {
    path: "ticket/management/:id/comments/:comentarioId",
    component: CommentViewPageComponent,
  },
  {
    path: 'admin/ticket/management/:id/asignar',
    component: AsignarAgentePageComponent,
  },
  {
    path: 'admin/usuario/management',
    component: UsuarioManagementPageComponent,
  },
  {
    path: 'admin/usuario/management/:id',
    component: UsuarioDetailPageComponent,
  },
  {
    path: 'admin/usuario/create',
    component: UsuarioManagementPageComponent,
  },
  {
    path: "test-endpoints",
    component: TestEndpointsComponent,
  },
  {
    path: "test",
    component: TestComponent,
  },
];
