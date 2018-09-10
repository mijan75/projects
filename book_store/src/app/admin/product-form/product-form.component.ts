import { AuthorService } from './../../author.service';
import { Component, OnInit } from '@angular/core';
import { CategoryService } from '../../category.service';
import { ProductService } from '../../product.service';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import 'rxjs/add/operator/take';

@Component({
  selector: 'product-form',
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.css']
})
export class ProductFormComponent implements OnInit {
  categories$;
  authors$;
  product= {};
  id;
  constructor(
    
    private route: ActivatedRoute,
    private authorService: AuthorService,
    private router: Router,
    private categoryService: CategoryService,
    private productService: ProductService) 
    {
    this.categories$ = categoryService.getCategories();
    this.authors$ = authorService.getAll();
    
    this.id =  this.route.snapshot.paramMap.get('id');
    if( this.id ) this.productService.get(this.id).take(1).subscribe(p => this.product = p);
    
   }

  ngOnInit() {
  }

  save(product) {
  if(this.id) this.productService.update(this.id, product);
  else this.productService.create(product);

  
  this.router.navigate(['/admin/products']);
  }

  delete() {
    if(!confirm('Are you sure you want to delete this product?')) return;

      this.productService.delete(this.id);
      this.router.navigate(['/admin/products']);
   
  }

  

}
