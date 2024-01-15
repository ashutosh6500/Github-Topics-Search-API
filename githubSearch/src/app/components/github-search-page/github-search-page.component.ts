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
    this.noContent =true;
  }
  ngOnInit(){
    
  
  }
  sortBy : string = "";
  noContent = true;
  topicList : string[] = [];
  repoInput : RepoInput = new RepoInput(this.sortBy,this.topicList);
  githubRepositories : any= [];
  
  public onClick() : void{
    this.repoInput.sortBy = this.sortBy;
    this.repoInput.topicList = this.topicList;
    this.repoService.emitEventToRepoList(this.repoInput);
    //sending sortBy and Topic list to github repo list component
    // this.repoService.sortBy = this.sortBy;
    // this.repoService.topics = this.topicList;
    // this.repoService.eventEmitter1.emit(this.sortBy);
    // this.repoService.eventEmitter2.emit(this.topicList);
    
   

    
    // this.repoService.getReposForUser("mail").subscribe(
    //   (response : GithubRepository[]) => {
    //     this.githubRepositories = response;
    //     console.log("user are",this.githubRepositories);
    //   }
    // );
  }

  


}
