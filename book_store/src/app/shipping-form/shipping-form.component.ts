import { Component, OnInit, Input } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { Router } from '@angular/router';
import { AuthService } from './../auth.service';
import { ShoppingCartService } from './../shopping-cart.service';
import { OrderService } from './../order.service';
import { Order } from './../models/order';
import { OnDestroy } from '@angular/core/';
import { ShoppingCart } from './../models/shopping-cart';

@Component({
  selector: 'shipping-form',
  templateUrl: './shipping-form.component.html',
  styleUrls: ['./shipping-form.component.css']
})


export class ShippingFormComponent implements OnInit, OnDestroy {
  shipping = {};
  @Input('cart') cart: ShoppingCart;
  userSubscription : Subscription;
  userId: string;

 constructor(
  private router: Router,
  private authService: AuthService,
  private shoppingCartService: ShoppingCartService,
private orderService: OrderService) {

}

  ngOnInit() {
    this.userSubscription = this.authService.user$.subscribe(user => this.userId = user.uid)

  }

  ngOnDestroy() {
    this.userSubscription.unsubscribe();
    }

  async placeOrder() {
    let order = new Order(this.userId, this.shipping, this.cart);
    let result = await this.orderService.placeOrder(order);

    this.router.navigate(['/order-success', result.key]);
   }
  
}
