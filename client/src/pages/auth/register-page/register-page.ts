import { Component } from '@angular/core';
import { Register } from 'src/features/auth/register/register';

@Component({
  selector: 'app-register-page',
  imports: [Register],
  templateUrl: './register-page.html',
  styleUrl: './register-page.css',
})
export class RegisterPage {}
