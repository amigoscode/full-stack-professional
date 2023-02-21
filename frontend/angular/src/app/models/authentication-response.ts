import { CustomerDTO } from './customer-dto';

export interface AuthenticationResponse {
  token?: string;
  customerDTO: CustomerDTO;
}
