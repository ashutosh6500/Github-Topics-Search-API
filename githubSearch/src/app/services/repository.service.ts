import { EventEmitter, Injectable, Output } from '@angular/core';
import { RepoInput } from '../entities/RepoInput';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { GithubRepository } from '../entities/GithubRepo';

@Injectable({
  providedIn: 'root'
})
export class RepositoryService {
  eventEmitter1 = new EventEmitter<string>();
  eventEmitter2 = new EventEmitter<string[]>();
  sortBy : string = "";
  topics : string[] = [];
  private url = "http://localhost:8080";

  @Output() aClickedEvent = new EventEmitter<RepoInput>();
  emitEventToRepoList(repoInput: RepoInput){
    console.log("emitting a event");
    this.aClickedEvent.emit(repoInput);
  }
  constructor(private http : HttpClient) { }
  //get
  public getReposFromTopics(sortBy : string,topics : string[]) : Observable<GithubRepository[]> {
    console.log("sortby is",sortBy,"topis are ",topics);
    let response = this.http.get<GithubRepository[]>(`${this.url}/getReposWithTopics/${sortBy}/${topics}`);
    console.log("repoinput service response is ",response);
    return response;
  }
  public getReposForUser(userId : string) : Observable<GithubRepository[]>{
    return this.http.get<GithubRepository[]>(`http://localhost:8080/getRepos/${userId}`);
  }
 
}
