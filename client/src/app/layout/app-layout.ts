import { Component, computed, inject } from '@angular/core';
import { NavigationEnd, Router, RouterOutlet } from '@angular/router';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { Sidenav } from 'src/shared/components/sidenav/sidenav';
import { faSearch, faCaretRight, faCaretLeft } from '@fortawesome/free-solid-svg-icons';
import { AuthService } from 'src/entities/User/service/auth.service';
import { Header } from 'src/shared/components/header/header';
import { toSignal } from '@angular/core/rxjs-interop';
import { filter } from 'rxjs/internal/operators/filter';
import { map } from 'rxjs/internal/operators/map';

@Component({
  selector: 'app-app-layout',
  imports: [RouterOutlet, Sidenav, FontAwesomeModule, Header],
  templateUrl: './app-layout.html',
  styleUrl: './app-layout.css',
})
export class AppLayout {
  faSearch = faSearch;
  faCarretRight = faCaretRight;
  faCaretLeft = faCaretLeft;

  protected readonly authService = inject(AuthService);
  private router = inject(Router);

  private currentUrl = toSignal(
    this.router.events.pipe(
      filter((event) => event instanceof NavigationEnd),
      map((event) => (event as NavigationEnd).urlAfterRedirects),
    ),
    { initialValue: this.router.url },
  );

  public showHeader = computed(() => {
    const url = this.currentUrl();
    const hiddenRoutes = ['/login', '/register', '/menus'];

    return !hiddenRoutes.some((route) => url.includes(route));
  });
}
