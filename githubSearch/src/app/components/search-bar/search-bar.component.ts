import { Component } from '@angular/core';
import { RepoInput } from 'src/app/entities/RepoInput';
import { RepositoryService } from 'src/app/services/repository.service';

interface Food {
  value: string;
  viewValue: string;
}
@Component({
  selector: 'app-search-bar',
  templateUrl: './search-bar.component.html',
  styleUrls: ['./search-bar.component.scss']
})
export class SearchBarComponent {
  constructor(private repoService: RepositoryService){

  }
  sortBy : string = "fork_count";
  topic_list : string[] =["java,aws"];
  
  
  
  //console.log(this.sortBy);
  
  
}
