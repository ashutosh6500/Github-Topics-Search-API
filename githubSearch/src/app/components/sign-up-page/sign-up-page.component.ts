import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { GithubRepository } from 'src/app/entities/GithubRepo';
import { User } from 'src/app/entities/User';
import { GlobalsService } from 'src/app/services/globals.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-sign-up-page',
  templateUrl: './sign-up-page.component.html',
  styleUrls: ['./sign-up-page.component.scss']
})
export class SignUpPageComponent {

change() {
 this.error = "";
  console.log("here");
}
  expression: RegExp = /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i;

  userId : string="";
  password : string="";
  repos : GithubRepository[] = [];
  user : any;
  message :any;
  emptyPassword : boolean= false;
  showError : boolean = false;
  error :string ="";
  validateForm(){
    console.log("called");

    console.log(this.userId,this.password);
    if(this.password == "")
    {
      console.log("input password");
      this.emptyPassword=true;
      return false;
    }
    return true;
  }
  constructor(private service:UserService,private router : Router,private globals : GlobalsService){
    
  }
  public getUsers() : void{
    this.service.getUser().subscribe(
      (response : User[]) => {
        this.user = response;
        console.log("user are",this.user);
      }
    );
  }
  public register(){
  //add code for checking password constraints
  if(this.expression.test(this.userId) == false){
    alert("Enter Valid Email Id");
    return;
  }
  if(this.password == "")
  {
    alert("Password can't be empty!")
    return;
  }
  if(this.userId == "")
  {
    alert("UserId can't be empty!")
    return;
  }
  
  //add logic for reading response Entiry coming from backend part!
  this.service.saveUser(new User(this.userId,this.password,this.repos)).subscribe((user1) => {
    console.log("saved user is",user1);
    //On successfull sign up,navigate to login:
    this.globals.setLogin(true);
    this.globals.setLogout(false);
    this.globals.setRegister(false);
    alert("Signup Successfull!");
    this.router.navigate(['/']);
  },
  error => {
  
    //conflict case
    if(error.status === 409){
      this.error = "Failed ! User with this Email already exist!";
      this.showError = true;
       setTimeout(this.change, 5000);

    }
    
    return;
  }
  );

  }
  ngOnInit(){

  }
}
