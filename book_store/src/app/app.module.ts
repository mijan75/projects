import { AuthorService } from './author.service';

import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AngularFireModule } from 'angularfire2';
import { AngularFireDatabaseModule } from 'angularfire2/database-deprecated';
import { AngularFireAuthModule } from 'angularfire2/auth';
import { RouterModule } from '@angular/router';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AuthService } from './auth.service';
import { AuthGuardService } from './auth-guard.service';
import { UserService } from './user.service';
import { AdminAuthGuardService } from './admin-auth-guard.service';
import { CategoryService } from './category.service';
import { FormsModule } from '@angular/forms';
import { ProductService } from './product.service';
import { CustomFormsModule } from 'ng2-validation';
import { ShoppingCartService } from './shopping-cart.service';
import { OrderService }  from './order.service';


import { AppComponent } from './app.component';
import { environment } from '../environments/environment';
import { BsNavbarComponent } from './bs-navbar/bs-navbar.component';
import { HomeComponent } from './home/home.component';
import { ShoppingCartComponent } from './shopping-cart/shopping-cart.component';
import { OrderSuccessComponent } from './order-success/order-success.component';
import { ProductsComponent } from './products/products.component';
import { CheckOutComponent } from './check-out/check-out.component';
import { MyOrdersComponent } from './my-orders/my-orders.component';
import { LoginComponent } from './login/login.component';
import { AdminProductsComponent } from './admin/admin-products/admin-products.component';
import { AdminOrdersComponent } from './admin/admin-orders/admin-orders.component';
import { ProductFormComponent } from './admin/product-form/product-form.component';
import { ProductFilterComponent } from './products/product-filter/product-filter.component';
import { ProductCardComponent } from './product-card/product-card.component';
import { ProductQuantityComponent } from './product-quantity/product-quantity.component';
import { ShoppingCartSummaryComponent } from './shopping-cart-summary/shopping-cart-summary.component';
import { ShippingFormComponent } from './shipping-form/shipping-form.component';
import { OrderViewComponent } from './order-view/order-view.component';
import { AuthorFormComponent } from './admin/author-form/author-form.component';
import { AdminAuthorsComponent } from './admin/admin-authors/admin-authors.component';
import { AuthorCardComponent } from './author-card/author-card.component';
import { AuthorsComponent } from './authors/authors.component';


@NgModule({
  declarations: [
    AppComponent,
    BsNavbarComponent,
    HomeComponent,
    ShoppingCartComponent,
    OrderSuccessComponent,
    ProductsComponent,
    CheckOutComponent,
    MyOrdersComponent,
    LoginComponent,
    AdminProductsComponent,
    AdminOrdersComponent,
    ProductFormComponent,
    ProductFilterComponent,
    ProductCardComponent,
    ProductQuantityComponent,
    ShoppingCartSummaryComponent,
    ShippingFormComponent,
    OrderViewComponent,
    AuthorFormComponent,
    AdminAuthorsComponent,
    AuthorCardComponent,
    AuthorsComponent
    
  ],
  imports: [
    BrowserModule,
    
    AngularFireModule.initializeApp(environment.firebase),
    AngularFireDatabaseModule,
    AngularFireAuthModule,
    CustomFormsModule,
    FormsModule,
    NgbModule.forRoot(),
    
    RouterModule.forRoot([
      { path: '', component: ProductsComponent},
      { path: 'products', component: ProductsComponent},
      { path: 'authors', component: AuthorsComponent},
      { path: 'check-out', component: CheckOutComponent, canActivate: [AuthGuardService]},
      { path: 'my/orders', component: MyOrdersComponent, canActivate: [AuthGuardService]},
      { path: 'login', component: LoginComponent},
      { path: 'order-success/:id', component:OrderSuccessComponent, canActivate: [AuthGuardService]},


      { path: 'shopping-cart', 
       component: ShoppingCartComponent,
       },

       { path: 'order/view', 
       component: OrderViewComponent,
       },

       {  path: 'admin/products',
         component: AdminProductsComponent,
         canActivate: [AuthGuardService, AdminAuthGuardService]},

         {  path: 'admin/authors',
         component: AdminAuthorsComponent,
         canActivate: [AuthGuardService, AdminAuthGuardService]},


         {  path: 'admin/authors/new',
         component: AuthorFormComponent,
         canActivate: [AuthGuardService, AdminAuthGuardService]},


        { path: 'admin/products/new',
          component: ProductFormComponent,
          canActivate: [AuthGuardService, AdminAuthGuardService]},

        { path: 'admin/products/:id',
          component: ProductFormComponent,
          canActivate: [AuthGuardService, AdminAuthGuardService]},
 

          { path: 'admin/authors/:id',
          component: AuthorFormComponent,
          canActivate: [AuthGuardService, AdminAuthGuardService]},


       {  path: 'admin/orders',
          component: AdminOrdersComponent,
          canActivate: [AuthGuardService, AdminAuthGuardService]}
  
    ])



  ],
  providers: [
    AuthService,
    AuthGuardService,
    UserService,
    AdminAuthGuardService,
    CategoryService,
    ProductService,
    ShoppingCartService,
    OrderService,
    AuthorService
   
    
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
