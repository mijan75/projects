import { AuthorService } from './../author.service';
import { ActivatedRoute } from '@angular/router';
import { Author } from './../models/author';
import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { Observable } from 'rxjs/Observable';
import { ShoppingCart } from './../models/shopping-cart';
import { ShoppingCartService } from './../shopping-cart.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-authors',
  templateUrl: './authors.component.html',
  styleUrls: ['./authors.component.css']
})
export class AuthorsComponent implements OnInit {

 
  authors$;
  


  constructor(private authorService: AuthorService,
    private route: ActivatedRoute,
    private router: Router,
   ) {
    this.authors$ = this.authorService.getAll()
    
   }
ngOnInit() {
  
}
}
