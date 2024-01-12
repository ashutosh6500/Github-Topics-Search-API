export class RepoInput{
    sortBy : string;
    topicList : string[];
    constructor(sortBy:string,topics : string[]){
        this.sortBy = sortBy;
        this.topicList = topics;
    }
}