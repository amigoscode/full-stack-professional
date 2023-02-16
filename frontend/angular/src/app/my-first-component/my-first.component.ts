import { Component, EventEmitter, Input, Output } from '@angular/core';
import { MyCalculatorService } from '../services/my-calculator.service';

@Component({
  selector: 'app-my-first-component',
  templateUrl: './my-first.component.html',
  styleUrls: ['./my-first.component.scss']
})
export class MyFirstComponent {
  value1 = 0;
  value2 = 0;
  result = 0;

  constructor(
    private readonly calculator: MyCalculatorService
  ) {}

  sum() {
    this.result = this.calculator.sum(this.value1, this.value2);
  }

  sub() {
    this.result = this.calculator.sub(this.value1, this.value2);
  }

  multiply() {
    this.result = this.calculator.multiply(this.value1, this.value2);
  }

  divide() {
    this.result = this.calculator.divide(this.value1, this.value2);
  }
}
