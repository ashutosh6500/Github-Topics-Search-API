import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../entities/User';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private url = "http://localhost:8080";

  constructor(private http : HttpClient) { }

  //post
  public saveUser(user : User) : Observable<User>{
    console.log("user is" ,user);
    let response = this.http.post<User>(`${this.url}/api/saveUser`,user);
    console.log("service response is ",response);
    return response;
  }
}
