import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { InputTextModule } from 'primeng/inputtext';
import { SidebarModule } from 'primeng/sidebar';
import { ButtonModule } from 'primeng/button';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CustomerComponent } from './components/customer/customer.component';
import { AvatarModule } from 'primeng/avatar';
import { MenuItemComponent } from './components/menu-item/menu-item.component';
import { MenuBarComponent } from './components/menu-bar/menu-bar.component';
import { HeaderBarComponent } from './components/header-bar/header-bar.component';
import { RippleModule } from 'primeng/ripple';
import { MenuModule } from 'primeng/menu';
import { CustomerCardComponent } from './components/customer-card/customer-card.component';
import { CardModule } from 'primeng/card';
import { BadgeModule } from 'primeng/badge';
import { ManageCustomerComponent } from './components/manage-customer/manage-customer.component';
import { DropdownModule } from 'primeng/dropdown';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { ConfirmationService, MessageService } from 'primeng/api';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { AutoFocusModule } from 'primeng/autofocus';
import { ToastModule } from 'primeng/toast';

@NgModule({
  declarations: [
    AppComponent,
    CustomerComponent,
    MenuItemComponent,
    MenuBarComponent,
    HeaderBarComponent,
    CustomerCardComponent,
    ManageCustomerComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    InputTextModule,
    SidebarModule,
    ButtonModule,
    AvatarModule,
    RippleModule,
    MenuModule,
    CardModule,
    BadgeModule,
    DropdownModule,
    ConfirmDialogModule,
    HttpClientModule,
    FormsModule,
    AutoFocusModule,
    ToastModule
  ],
  providers: [
    ConfirmationService,
    HttpClient,
    MessageService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
