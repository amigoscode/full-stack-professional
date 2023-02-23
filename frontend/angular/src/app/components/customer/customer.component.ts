import { Component, OnInit } from '@angular/core';
import { CustomerDTO } from '../../models/customer-dto';
import { CustomerService } from '../../services/customer/customer.service';
import { CustomerRegistrationRequest } from '../../models/customer-registration-request';
import { ConfirmationService, MessageService } from 'primeng/api';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.scss']
})
export class CustomerComponent implements OnInit {

  display = false;
  customers: Array<CustomerDTO> = [];
  customer: CustomerRegistrationRequest = {};

  constructor(
    private customerService: CustomerService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService
  ) {}

  ngOnInit(): void {
    this.findAllCustomers();
  }


  private findAllCustomers() {
    this.customerService.findAll()
    .subscribe({
      next: (data) => {
        this.customers = data;
        console.log(data);
      }
    })
  }

  save(customer: CustomerRegistrationRequest) {
    if (customer) {
      this.customerService.registerCustomer(customer)
      .subscribe({
        next: () => {
          this.findAllCustomers();
          this.display = false;
          this.customer = {};
          this.messageService.add(
            {severity:'success',
              summary: 'Customer saved',
              detail: `Customer ${customer.name} was successfully saved`
            }
          );
        }
      });
    }
  }

  deleteCustomer(customer: CustomerDTO) {
    this.confirmationService.confirm({
      header: 'Delete customer',
      message: `Are you sure you want to delete ${customer.name}? You can\'t undo this action afterwords`,
      accept: () => {
        this.customerService.deleteCustomer(customer.id)
        .subscribe({
          next: () => {
            this.findAllCustomers();
            this.messageService.add(
              {
                severity:'success',
                summary: 'Customer deleted',
                detail: `Customer ${customer.name} was successfully deleted`
              }
            );
          }
        });
      }
    });
  }
}
