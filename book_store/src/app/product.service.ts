import { Product } from './models/product';
import { Injectable } from '@angular/core';
import { AngularFireDatabase } from 'angularfire2/database-deprecated';
import { Observable } from 'rxjs/Observable';


@Injectable()
export class ProductService {

  constructor(private db: AngularFireDatabase) { }


 
   public create(product) {
     return this.db.list('/products').push(product);
   }
   getAll() {
     return this.db.list('/products');
   }

   get(productId) {
     return this.db.object('/products/'  + productId);
   }

   update(productId, product) {
    return this.db.object('/products/' + productId).update(product);
   }
   delete(productId) {
     return this.db.object('/products/' + productId).remove();
   }

}
