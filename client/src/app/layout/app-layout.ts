import { Component, inject } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { Sidenav } from 'src/shared/components/sidenav/sidenav';
import { faSearch, faCaretRight, faCaretLeft } from '@fortawesome/free-solid-svg-icons';
import { AuthService } from 'src/entities/User/service/auth.service';

@Component({
  selector: 'app-app-layout',
  imports: [RouterOutlet, Sidenav, FontAwesomeModule, RouterLink],
  templateUrl: './app-layout.html',
  styleUrl: './app-layout.css',
})
export class AppLayout {
  faSearch = faSearch;
  faCarretRight = faCaretRight;
  faCaretLeft = faCaretLeft;

  protected readonly authService = inject(AuthService);
}
