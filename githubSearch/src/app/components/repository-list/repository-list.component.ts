import { Component, Input } from '@angular/core';
import { GithubRepository } from 'src/app/entities/GithubRepo';
import { RepoInput } from 'src/app/entities/RepoInput';
import { User } from 'src/app/entities/User';
import { RepositoryService } from 'src/app/services/repository.service';

@Component({
  selector: 'app-repository-list',
  templateUrl: './repository-list.component.html',
  styleUrls: ['./repository-list.component.scss']
})
export class RepositoryListComponent {
  repoList : GithubRepository[] = [];
  clone = "git clone";
  sortBy : string = "";
  topics : string[] = [];
  @Input() repoInput  = new RepoInput("",[]);
  constructor(private repoService : RepositoryService){
    //this.sortBy = repoService.sortBy;
    //this.topics = repoService.topics;
    console.log(this.sortBy,this.topics);
  }
  ngOnInit() {
    this.repoService.aClickedEvent
    .subscribe((data:RepoInput) => {
      this.sortBy = data.sortBy;
      this.topics = data.topicList;
      console.log(this.sortBy,this.topics);
      if((this.sortBy.length !=0) && (this.topics.length != 0)){
        console.log("here");
      this.repoService.getReposFromTopics(this.sortBy,this.topics).subscribe(
        (response : GithubRepository[]) => {
          this.repoList = response;
          console.log("repos are ",this.repoList);
        }
      );
      }

    });
   
  }
  
 

}
