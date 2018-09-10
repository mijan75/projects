import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { EmployeeService } from './employee.service';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { AdminAddComponent } from './admin/admin-add/admin-add.component';
import { AdminEditComponent } from './admin/admin-edit/admin-edit.component';
import { RouterModule, Route } from '@angular/router';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
   
    AdminAddComponent,
    AdminEditComponent,
   
  ],
  imports: [
    BrowserModule,
    HttpModule,
    FormsModule,
    RouterModule.forRoot([
      { path: '', component: HomeComponent},
      { path: 'home', component: HomeComponent},
      { path: 'admin/add', component: AdminAddComponent},
      { path: 'admin/edit', component: AdminEditComponent},
      { path: 'admin/edit/:id', component: AdminEditComponent}
     
    ])
  ],
  
  providers: [EmployeeService],
  bootstrap: [AppComponent]
})
export class AppModule { }
