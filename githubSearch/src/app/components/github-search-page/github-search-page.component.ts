import { Component } from '@angular/core';
import { GithubRepository } from 'src/app/entities/GithubRepo';
import { RepoInput } from 'src/app/entities/RepoInput';
import { RepositoryService } from 'src/app/services/repository.service';

@Component({
  selector: 'app-github-search-page',
  templateUrl: './github-search-page.component.html',
  styleUrls: ['./github-search-page.component.scss']
})
export class GithubSearchPageComponent {
  constructor(private repoService : RepositoryService){
  }
  ngOnInit(){
    this.noContent =true;

  
  }
  sortBy : string = "";
  noContent = true;
  topicList : string[] = [];
  repoInput : RepoInput = new RepoInput(this.sortBy,this.topicList);
  githubRepositories : any= [];
  
  public onClick() : void{
    this.noContent = false;
    this.repoInput.sortBy = this.sortBy;
    this.repoInput.topicList = this.topicList;
    this.repoService.emitEventToRepoList(this.repoInput);

  }
}
