import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MyFirstComponent } from './my-first-component/my-first.component';
import { FormsModule } from '@angular/forms';
import { MyCalculatorService } from './services/my-calculator.service';

@NgModule({
  declarations: [
    AppComponent,
    MyFirstComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [
    MyCalculatorService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
