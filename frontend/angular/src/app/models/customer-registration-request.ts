export  interface CustomerRegistrationRequest {
  id?: number;
  name?: string;
  email?: string;
  password?: string;
  age?: number;
  gender?: 'MALE' | 'FEMALE';
}
