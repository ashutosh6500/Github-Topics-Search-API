import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Globals } from 'src/app/globals';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss']
})
export class LoginPageComponent {
  userId : string ="";
  password : string ="";
  constructor(private userservice : UserService,private router : Router,private globals : Globals){}
  login(){
    if(this.userId == ""){
      alert("Enter Email Id");
      return;
    }
    if(this.password == ""){
      alert("Enter Password");
      return;
    }
    //validate in backend
    
    this.globals.login = true;
    this.globals.register = false;
    this.router.navigate(['/GithubSearchEngine']);

  }
}
