import { Component, OnInit } from '@angular/core';
import { ConfirmationService, MessageService } from 'primeng/api';
import { Customer } from '../../services/models/customer';
import { CustomerService } from '../../services/http/customer.service';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.scss']
})
export class CustomerComponent implements OnInit {

  display = false;
  customers: Array<Customer> = [];
  customerModel: Customer = {};

  constructor(
    private confirmationService: ConfirmationService,
    private customerService: CustomerService,
    private messageService: MessageService
  ) {
  }


  ngOnInit(): void {
    this.findAllCustomers();
  }

  private findAllCustomers() {
    this.customerService.findAll()
    .subscribe({
      next: (data) => {
        this.customers = data;
      }
    });
  }

  deleteCustomer(customer: Customer) {
    this.delete(customer);
  }

  updateCustomer(customer: Customer) {
    this.display = true;
    this.customerModel = customer;
  }

  private delete(customer: Customer) {
    this.confirmationService.confirm({
      header: 'Delete customer',
      message: `Are you sure you want to delete ${customer.name} You can\'t undo this action afterwards.`,
      accept: () => {
        this.customerService.deleteCustomer(customer.id)
        .subscribe({
          next: () => {
            this.findAllCustomers();
            this.showSuccess('Customer deleted', `Customer ${customer.name} was successfully deleted`);
          }
        });
      }
    });
  }

  saveCustomer(customer: Customer) {
    if (customer.id) {
      this.customerService.updateCustomer(customer)
      .subscribe({
        next: () => {
          this.findAllCustomers();
          this.showSuccess('Customer updated', `Customer ${customer.name} was successfully updated`);
        },
        error: err => {
          this.messageService.add({severity:'warn', summary: 'No changes detected', detail: err.error.message});
        }
      });
    } else {
      this.customerService.registerCustomer(customer)
      .subscribe({
        next: () => {
          this.findAllCustomers();
          this.showSuccess('Customer saved', `Customer ${customer.name} was successfully saved`);
        },
        error: err => {
          this.messageService.add({severity:'error', summary: 'Error', detail: err.error.message});
        }
      });
    }
    this.reset();
  }

  cancel(cancelEvt: boolean) {
    if (cancelEvt) {
      this.customerModel = {};
      this.display = false;
    }
  }

  onHideSidebar() {
    this.reset();
  }

  private reset() {
    this.customerModel = {};
    this.display = false;
  }

  private showSuccess(summary: string, message: string) {
    this.messageService.add({severity:'success', summary, detail: message});
  }
}
