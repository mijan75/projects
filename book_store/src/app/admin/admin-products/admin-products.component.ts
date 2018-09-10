import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../product.service';
import { OnDestroy } from '@angular/core/';
import { Router, ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { Product } from '../../models/product';



@Component({
  selector: 'admin-prducts',
  templateUrl: './admin-products.component.html',
  styleUrls: ['./admin-products.component.css']
})
export class AdminProductsComponent implements OnInit, OnDestroy {
  products: Product[];
  filteredProducts: any[];
  author: {};
  //items: Product[] = [];
  //itemCount: number;
  subscription: Subscription;
  id;
  


  constructor(private productService: ProductService,
    private route: ActivatedRoute,
    private router: Router,
   ) {
    this.subscription = this.productService.getAll()
    .subscribe(products => {
      this.filteredProducts = this.products = products ;
     
    } );

   
   
   }

  

   filter(query: string) {
    this.filteredProducts = (query) ?
    this.products.filter(p => 
      p.title.toLocaleLowerCase().includes(query.toLocaleLowerCase())
      || p.author.toLocaleLowerCase().includes(query.toLocaleLowerCase())
    ):
    this.products;
   }
   ngOnDestroy() {
   this.subscription.unsubscribe();
   }

  ngOnInit() {
  }

}
