import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-my-first-component',
  templateUrl: './my-first.component.html',
  styleUrls: ['./my-first.component.scss']
})
export class MyFirstComponent {

  @Input()
  inputValue: string = 'Hello';
  @Output()
  childClicked: EventEmitter<void> = new EventEmitter<void>();

  @Output()
  elementCreated: EventEmitter<string> = new EventEmitter<string>();
  displayMsg = false;
  msgList: Array<string> = [];

  clickMe(): void {
    this.msgList.push(this.inputValue);
    console.log(this.msgList);
    this.childClicked.emit();
    this.elementCreated.emit(this.inputValue);
    this.inputValue = '';
  }
}
