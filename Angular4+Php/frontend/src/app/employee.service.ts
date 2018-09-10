import { Http,  Response } from '@angular/http';
import { Injectable } from '@angular/core';
import 'rxjs/add/operator/map';
import { } from '@angular/http';

@Injectable()
export class EmployeeService {
 
  private url = 'http://localhost/php/select.php';
  constructor(private http: Http) { }

  getAllEmployees() {
    return this.http.get(this.url)
    .map(response => response.json());
  }

  addEmployee(emp){
    return this.http.post("http://localhost/php/insert.php",emp)
      .map(()=>"");
  }

  getEmployee(id){
    return this.http.post("http://localhost/php/selectone.php/",{'id':id})
      .map(res=>res.json());
  }

  deleteEmployee(id){
    return this.http.post("http://localhost/php/delete.php/",{'id':id})
      .map(()=>this.getAllEmployees());
  }
  updateEmployee(id){
    return this.http.post("http://localhost/php/update.php/", id)
      .map(()=>"");
  }

}
