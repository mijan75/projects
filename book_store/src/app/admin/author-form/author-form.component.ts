import { AuthorService } from './../../author.service';
import { ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CategoryService } from './../../category.service';
import { ProductService } from './../../product.service';
import { Author } from './../../models/author';
import 'rxjs/add/operator/take';


@Component({
  selector: 'author-form',
  templateUrl: './author-form.component.html',
  styleUrls: ['./author-form.component.css']
})
export class AuthorFormComponent implements OnInit {
  author = {};
  authors$;
  id;
  constructor(
    
    private route: ActivatedRoute,
    private router: Router,
    private authorService: AuthorService) 
    {
     

     this.id =  this.route.snapshot.paramMap.get('id');
     if( this.id ) this.authorService.get(this.id).take(1).subscribe(a => this.author = a);
   }

  ngOnInit() {
  }

  save(author) {
  if(this.id) this.authorService.update(this.id, author);
  else this.authorService.create(author);

  
  this.router.navigate(['/admin/authors']);
  }

  delete() {
    if(!confirm('Are you sure you want to delete this author?')) return;

      this.authorService.delete(this.id);
      this.router.navigate(['/admin/authors']);
   
  }

  

  

}
