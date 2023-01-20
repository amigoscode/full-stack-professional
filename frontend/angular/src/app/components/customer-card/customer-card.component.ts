import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Customer } from '../../services/models/customer';

@Component({
  selector: 'app-customer-card',
  templateUrl: './customer-card.component.html',
  styleUrls: ['./customer-card.component.scss']
})
export class CustomerCardComponent {

  @Input()
  customer: Customer = {};
  @Input()
  customerIndex = 0;
  @Output()
  delete: EventEmitter<Customer> = new EventEmitter<Customer>();
  @Output()
  update: EventEmitter<Customer> = new EventEmitter<Customer>();

  onDelete() {
    this.delete.emit(this.customer);
  }

  onUpdate() {
    this.update.emit(this.customer);
  }

  get customerImage(): string {
    const gender = this.customer.gender === 'MALE' ? 'men' : 'women';
    return `https://randomuser.me/api/portraits/${gender}/${this.customerIndex}.jpg`;
  }

}
