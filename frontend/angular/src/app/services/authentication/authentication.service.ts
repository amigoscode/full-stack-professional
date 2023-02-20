import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AuthenticationRequest } from '../../models/authentication-request';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(
    private http: HttpClient
  ) { }

  authenticate(authRequest: AuthenticationRequest): Observable<any> {
    return this.http.post('', authRequest);
  }
}
