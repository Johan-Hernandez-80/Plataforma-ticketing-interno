import { COMPILER_OPTIONS, Component } from "@angular/core";
import { RouterOutlet } from "@angular/router";
import { TextInputComponent } from "./components/shared/atoms/text-input/text-input.component";
import { MainButtonComponent } from "./components/shared/atoms/main-button/main-button.component";
import { CardComponent } from "./components/shared/atoms/card/card.component";
import { LoginCardComponent } from "./components/shared/molecules/login-card/login-card.component";
import { LoginPageComponent } from "./components/pages/loginPage/loginPage.component";
import { HeaderComponent } from "./components/shared/molecules/header/header.component";
import { LogOutButtonComponent } from "./components/shared/atoms/log-out-button/log-out-button.component";
import { NotificationListItemComponent } from "./components/shared/atoms/notification-list-item/notification-list-item.component";
import { SmallNotificationListComponent } from "./components/shared/molecules/small-notification-list/small-notification-list.component";
import { ValidationCardComponent } from "./components/shared/molecules/validation-card/validation-card.component";
import { TextAreaComponent } from "./components/shared/molecules/text-area/text-area.component";
import { TextBoxComponent } from "./components/shared/atoms/text-box/text-box.component";
import { CommentCardComponent } from "./components/shared/molecules/comment-card/comment-card.component";
import { ComboBoxComponent } from "./components/shared/atoms/combo-box/combo-box.component";
import { TicketManageCardComponent } from "./components/shared/molecules/ticket-manage-card/ticket-manage-card.component";
import { CommentListItemComponent } from "./components/shared/atoms/comment-list-item/comment-list-item.component";
import { CommentListCardComponent } from "./components/shared/molecules/comment-list-card/comment-list-card.component";
import { LargeNotificationListCardComponent } from "./components/shared/molecules/large-notification-list-card/large-notification-list-card.component";

@Component({
  selector: "app-root",
  standalone: true,
  imports: [
    RouterOutlet,
    TextInputComponent,
    MainButtonComponent,
    CardComponent,
    LoginCardComponent,
    LoginPageComponent,
    HeaderComponent,
    LogOutButtonComponent,
    NotificationListItemComponent,
    SmallNotificationListComponent,
    ValidationCardComponent,
    TextAreaComponent,
    TextBoxComponent,
    CommentCardComponent,
    ComboBoxComponent,
    TicketManageCardComponent,
    CommentListItemComponent,
    CommentListCardComponent,
    LargeNotificationListCardComponent,
  ],
  templateUrl: "./app.component.html",
  styleUrl: "./app.component.css",
})
export class AppComponent {
  title = "Frontend";
  message = `

      "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod
      tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim
      veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea
      commodo consequat. Duis aute irure dolor in reprehenderit in voluptate
      velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat
      cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id
      est laborum." Sección 1.10.32 de "de Finibus Bonorum et Malorum", escrito
      por Cicero en el 45 antes de Cristo "Sed ut perspiciatis unde omnis iste
      natus error sit voluptatem accusantium doloremque laudantium, totam rem
      aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto
      beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia
      voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni
      dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam
      est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit,
      sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam
      aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum
      exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea
      commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea
      voluptate velit esse quam nihil molestiae consequatur, vel illum qui
      dolorem eum fugiat quo voluptas nulla pariatur?" Traducci�n hecha por H.
      Rackham en 1914 "But I must explain to you how all this mistaken idea of
      denouncing pleasure and praising pain was born and I will give you a
      complete account of the system, and expound the actual teachings of the
      great explorer of the truth, the master-builder of human happiness. No one
      rejects, dislikes, or avoids pleasure itself, because it is pleasure, but
      because those who do not know how to pursue pleasure rationally encounter
      consequences that are extremely painful. Nor again is there anyone who
      loves or pursues or desires to obtain pain of itself, because it is pain,
      but because occasionally circumstances occur in which toil and pain can
      procure him some great pleasure. To take a trivial example, which of us
      ever undertakes laborious physical exercise, except to obtain some
      advantage from it? But who has any right to find fault with a man who
      chooses to enjoy a pleasure that has no annoying consequences, or one who
      avoids a pain that produces no resultant pleasure?" Sección 1.10.33 de "de
      Finibus Bonorum et Malorum", escrito por Cicero en el 45 antes de Cristo
      "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis
      praesentium voluptatum deleniti atque corrupti quos dolores et quas
      molestias excepturi sint occaecati cupiditate non provident, similique
      sunt in culpa qui officia deserunt mollitia animi, id est laborum et
      dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio.
      Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil
      impedit quo minus id quod maxime placeat facere possimus, omnis voluptas
      assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut
      officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates
      repudiandae sint et molestiae non recusandae. Itaque earum rerum hic
      tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias
      consequatur aut perferendis doloribus asperiores repellat."
`;
}
