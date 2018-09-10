import { ProductService } from './../product.service';
import { Component, OnInit } from '@angular/core';
import { AuthService } from './../auth.service';
import { OrderService } from './../order.service';
import { auth } from 'firebase';

@Component({
  selector: 'my-orders',
  templateUrl: './my-orders.component.html',
  styleUrls: ['./my-orders.component.css']
})
export class MyOrdersComponent  {
orders$;
products$;
  constructor(private authService: AuthService,
              private orderService: OrderService
             
  ) {
    this.orders$ = authService.user$.switchMap(u => orderService.getOrdersByUser(u.uid),
   
  );
   }

 

}
