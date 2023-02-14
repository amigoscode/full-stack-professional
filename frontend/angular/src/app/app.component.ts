import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'angular';
  clickCount = 0;
  lastCreatedElement = '';

  handleChildClick() {
    this.clickCount ++;
  }

  displayLastCreatedElement(element: string) {
    this.lastCreatedElement = element;
  }
}
