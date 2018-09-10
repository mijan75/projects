import { AngularFireDatabase } from 'angularfire2/database-deprecated';
import { Injectable } from '@angular/core';

@Injectable()
export class AuthorService {

  constructor(private db: AngularFireDatabase) { }

  create(author) {
    return this.db.list('/authors').push(author);
  }
  getAll() {
    return this.db.list('/authors');
  }

  get(authorId) {
    return this.db.object('/authors/'  + authorId);
  }

  update(authorId, author) {
   return this.db.object('/authors/' + authorId).update(author);
  }
  delete(authorId) {
    return this.db.object('/authors/' + authorId).remove();
  }

}
