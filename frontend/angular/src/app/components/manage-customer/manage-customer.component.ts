import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Customer } from '../../services/models/customer';
import { CustomerComponent } from '../customer/customer.component';

@Component({
  selector: 'app-manage-customer',
  templateUrl: './manage-customer.component.html',
  styleUrls: ['./manage-customer.component.scss']
})
export class ManageCustomerComponent {

  @Input()
  customer: Customer = {};
  @Output()
  save: EventEmitter<Customer> = new EventEmitter<Customer>();
  @Output()
  cancel: EventEmitter<boolean> = new EventEmitter<boolean>();
  genders = [
    {
      name: 'Male',
      code: 'MALE'
    },
    {
      name: 'Female',
      code: 'FEMALE'
    }
  ];

  onCancel() {
    this.cancel.emit(true);
  }

  onSaveCustomer() {
    this.save.emit(this.customer);
  }

  get isCustomerValid(): boolean {
    return this.hasLength(this.customer.name) &&
      this.hasLength(this.customer.email) &&
      this.hasLength(this.customer.gender) &&
      this.customer.age !== undefined && this.customer.age > 0;
  }

  private hasLength(input: string | undefined): boolean {
    return  input !== null && input !== undefined && input.length > 0;
  }
}
