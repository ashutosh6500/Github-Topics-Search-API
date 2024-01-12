import { GithubRepository } from "./GithubRepo";
export class User{
    userId : string;
    password : string;
    repos : GithubRepository[];
    constructor(userId:string,password:string,repos:GithubRepository[]){
        this.userId = userId;
        this.password =password;
        this.repos = repos;
    }
}
