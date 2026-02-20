import { Component, inject, OnInit } from '@angular/core';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import {
  faHome,
  faBook,
  faCalendarDays,
  faUser,
  faPowerOff,
  faArrowRightToBracket,
} from '@fortawesome/free-solid-svg-icons';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { AuthService } from 'src/entities/User/service/auth.service';

@Component({
  selector: 'app-sidenav',
  imports: [FontAwesomeModule, RouterLink, RouterLinkActive],
  templateUrl: './sidenav.html',
  styleUrl: './sidenav.css',
})
export class Sidenav implements OnInit {
  faHome = faHome;
  faBook = faBook;
  faCalendarDays = faCalendarDays;
  faUser = faUser;
  faPowerOff = faPowerOff;
  faArrowRightToBracket = faArrowRightToBracket;

  protected authService = inject(AuthService);

  ngOnInit(): void {
    console.log(this.authService.isLoaded());
  }
}
