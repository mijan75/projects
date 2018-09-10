import { Author } from './../../models/author';
import { ActivatedRoute } from '@angular/router';
import { AuthorService } from './../../author.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';

@Component({
  selector: 'admin-authors',
  templateUrl: './admin-authors.component.html',
  styleUrls: ['./admin-authors.component.css']
})
export class AdminAuthorsComponent implements OnInit {
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
