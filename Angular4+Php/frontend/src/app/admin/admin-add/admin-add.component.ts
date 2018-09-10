import { Employee } from './../../../../../front-end/src/app/employee';
import { EmployeeService } from './../../employee.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-add',
  templateUrl: './admin-add.component.html',
  styleUrls: ['./admin-add.component.css']
})
export class AdminAddComponent implements OnInit {

  constructor(private employeeService: EmployeeService, private router: Router) { }

  ngOnInit() {

  }

  model = new Employee();
  addEmployee(){
   this.employeeService.addEmployee(this.model)
   .subscribe(() => this.goBack());
  }

  goBack(){
    this.router.navigate(['/home']);
  }

}
