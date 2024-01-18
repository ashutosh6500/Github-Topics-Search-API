import { EventEmitter, Injectable, Output } from '@angular/core';
import { RepoInput } from '../entities/RepoInput';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { GithubRepository } from '../entities/GithubRepo';

@Injectable({
  providedIn: 'root'
})
export class GlobalsService {
 login : boolean ;
 logout : boolean ;
 register : boolean;
 constructor(){
    this.login = true;
    this.register = true;
    this.logout = false;
 }
 getLogin(){
    return this.login;
 }
 getLogout(){
    return this.logout;
 }
 getRegister(){
    return this.register;
 }
 setLogin(login : boolean){
    this.login = login;
 }
 setLogout(logout : boolean){
    this.logout = logout;
 }
 setRegister(register : boolean){
    this.register = register;
 }
 

 
}
