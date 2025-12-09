import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { Sidenav } from 'src/shared/components/sidenav/sidenav';
import { faSearch, faCaretRight, faCaretLeft } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-app-layout',
  imports: [RouterOutlet, Sidenav, FontAwesomeModule],
  templateUrl: './app-layout.html',
  styleUrl: './app-layout.css',
})
export class AppLayout {
  faSearch = faSearch;
  faCarretRight = faCaretRight;
  faCaretLeft = faCaretLeft
}
