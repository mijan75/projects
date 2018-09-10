import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';
import { EmployeeService } from '../employee.service';

@Component({
  selector: 'home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
 
  employees: any;
  constructor(private employeeService: EmployeeService) { }

  ngOnInit() {
    this.getEmployess();
  }

  getEmployess(){
    this.employeeService.getAllEmployees()
    .subscribe(emp => this.employees = emp);
  }

  deleteEmployee(id){
    this.employeeService
      .deleteEmployee(id)
      .subscribe(() => {
      this.getEmployess();
    } )
}

}
