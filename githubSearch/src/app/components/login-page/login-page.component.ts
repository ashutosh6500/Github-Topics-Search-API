import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Globals } from 'src/app/globals';
import { GlobalsService } from 'src/app/services/globals.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss']
})
export class LoginPageComponent {
  userId : string ="";
  password : string ="";
  expression: RegExp = /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i;
  response : any;
  error : string = "";
  showError : boolean = false;
  change(){
    this.showError = false;
  }
  constructor(private service : UserService,private router : Router,private globalService : GlobalsService){}
  login(){
    if(this.userId == ""){
      alert("Enter Email Id");
      return;
    }
    if(this.expression.test(this.userId) == false){
      alert("Enter Valid Email Id");
      return;
    }
    if(this.password == ""){
      alert("Enter Password");
      return;
    }
    //validate in backend
    this.service.checkUser(this.userId,this.password).subscribe((user) => {
      this.globalService.login = false;
      this.globalService.logout = true;
      this.globalService.register = false;
      this.router.navigate(['/GithubSearchEngine']);
    },
    error => {
    
      //if Email or Password is wrong
      if(error.status === 404 || error.status === 400){
        this.error = "Failed ! Username and/or Password is wrong !";
        this.showError = true;
         setTimeout(this.change, 5000);
  
      }
      
      return;
    }
    );
   

  }
}
