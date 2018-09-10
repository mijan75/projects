import { EmployeeService } from './../../employee.service';
import { Employee } from './../../employee';
import { Component, OnInit } from '@angular/core';
import { Router, Params, ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-admin-edit',
  templateUrl: './admin-edit.component.html',
  styleUrls: ['./admin-edit.component.css']
})
export class AdminEditComponent implements OnInit {

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private empService: EmployeeService 
  ) { }

  ngOnInit() {
    this.getOneEmployee();
  }

  model = new Employee();
  id = this.route.snapshot.params['id'];

  getOneEmployee() {
   this.empService.getEmployee(this.id)
   .subscribe(employee => {
     this.model = employee[0];
   })
  };

  updateEmployee(){
    this.empService.updateEmployee(this.model)
    .subscribe(()=> this.goBack());
  }

  goBack(){
    this.router.navigate(['/home']);
  }
}
