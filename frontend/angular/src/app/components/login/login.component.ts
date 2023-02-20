import { Component } from '@angular/core';
import { AuthenticationRequest } from '../../models/authentication-request';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  authenticationRequest: AuthenticationRequest = {};
}
