import { User } from "./User";
export class GithubRepository{
    repoId : string;
    stargazersCount : number;
    forkCount:number;
    recentupdateTime:string;
    projectUrl:string;
    description:string;
    cloneUrl:string;
    topicList:string[];
    users:User[];
    constructor(repoId : string,stargazersCount : number,forkCount:number,recentupdateTime:string,projectUrl:string,description:string,cloneUrl:string,topicList:string[],users:User[]){
        this.repoId = repoId;
        this.stargazersCount =stargazersCount;
        this.cloneUrl = cloneUrl;
        this.description = description;
        this.forkCount = forkCount;
        this.projectUrl = projectUrl;
        this.recentupdateTime= recentupdateTime;
        this.users = users;
        this.topicList = topicList;
    }
}