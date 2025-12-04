import { Component } from '@angular/core';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import {
  faHome,
  faBook,
  faCalendarDays,
  faGears,
  faArrowRightFromBracket,
} from '@fortawesome/free-solid-svg-icons';
import { RouterLink, RouterLinkActive } from '@angular/router';

@Component({
  selector: 'app-sidenav',
  imports: [FontAwesomeModule, RouterLink, RouterLinkActive],
  templateUrl: './sidenav.html',
  styleUrl: './sidenav.css',
})
export class Sidenav {
  faHome = faHome;
  faBook = faBook;
  faCalendarDays = faCalendarDays;
  faGears = faGears;
  faArrowRightFromBracket = faArrowRightFromBracket;
}
