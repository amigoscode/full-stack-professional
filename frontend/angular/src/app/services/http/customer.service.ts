import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Customer } from '../models/customer';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  private readonly customerEndpoint = `${environment.api.baseUrl}/${environment.api.customerUrl}`;
  constructor(
    private http: HttpClient
  ) { }

  findAll(): Observable<Customer[]> {
    return this.http.get<Customer[]>(`${this.customerEndpoint}`);
  }

  registerCustomer(customer: Customer): Observable<void> {
    return this.http.post<void>(`${this.customerEndpoint}`, customer);
  }

  updateCustomer(customer: Customer): Observable<void> {
    return this.http.put<void>(`${this.customerEndpoint}/${customer.id}`, customer);
  }

  deleteCustomer(id: number | undefined): Observable<void> {
    return this.http.delete<void>(`${this.customerEndpoint}/${id}`);
  }
}
