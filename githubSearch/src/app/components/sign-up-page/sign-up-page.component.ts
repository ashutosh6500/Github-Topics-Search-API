import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { GithubRepository } from 'src/app/entities/GithubRepo';
import { User } from 'src/app/entities/User';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-sign-up-page',
  templateUrl: './sign-up-page.component.html',
  styleUrls: ['./sign-up-page.component.scss']
})
export class SignUpPageComponent {
  
  userId : string="";
  password : string="";
  repos : GithubRepository[] = [];
  user : any;
  message :any;
  emptyPassword : boolean= false;
  
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
  constructor(private service:UserService,private router : Router){

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
    let response = this.service.saveUser(new User(this.userId,this.password,this.repos));
    console.log("response is ",response);
    
    let res = response.subscribe((data)=> this.message=data);
    console.log("msg is",this.message);

    //On successfull sign up,navigate to login:
    //this.router.navigate(['/']);
  }
  ngOnInit(){

  }
}
