import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MyFirstComponent } from './my-first-component/my-first.component';
import { FormsModule } from '@angular/forms';
import { MyCalculatorService } from './services/my-calculator.service';
import { Page1Component } from './pages/page1/page1.component';
import { Page2Component } from './pages/page2/page2.component';
import { Page3Component } from './pages/page3/page3.component';

@NgModule({
  declarations: [
    AppComponent,
    MyFirstComponent,
    Page1Component,
    Page2Component,
    Page3Component
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
