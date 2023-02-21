export interface CustomerDTO {

  id?: number;
  name?: string;
  email?: string;
  gender?: 'MALE' | 'FEMALE';
  age?: number;
  roles?: string[],
  username?: string;
}
