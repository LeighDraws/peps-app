import { Component, inject } from '@angular/core';
import { RouterLink } from '@angular/router';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { faCaretLeft } from '@fortawesome/free-solid-svg-icons/faCaretLeft';
import { faCaretRight } from '@fortawesome/free-solid-svg-icons/faCaretRight';
import { faSearch } from '@fortawesome/free-solid-svg-icons/faSearch';
import { AuthService } from 'src/entities/User/service/auth.service';

@Component({
  selector: 'app-header',
  imports: [FontAwesomeModule, RouterLink],
  templateUrl: './header.html',
  styleUrl: './header.css',
})
export class Header {
  faSearch = faSearch;
  faCarretRight = faCaretRight;
  faCaretLeft = faCaretLeft;

  protected readonly authService = inject(AuthService);
}
