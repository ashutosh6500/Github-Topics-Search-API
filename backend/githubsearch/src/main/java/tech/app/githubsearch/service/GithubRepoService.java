package tech.app.githubsearch.service;
import tech.app.githubsearch.helper.APIResponse;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.app.githubsearch.models.GithubRepository;
import tech.app.githubsearch.models.Person;
import tech.app.githubsearch.repository.GithubRepoRepository;
import tech.app.githubsearch.repository.UserRepository;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

@Service
public class GithubRepoService {
    static class CustomSortingBasedonStars implements Comparator<GithubRepository> {
        public int compare(GithubRepository a,GithubRepository b){
            return (int)(b.getStargazersCount() - a.getStargazersCount());
        }
    }
    static class CustomSortingBasedonForks implements Comparator<GithubRepository> {
        public int compare(GithubRepository a,GithubRepository b){
            return (int)(b.getForkCount() - a.getForkCount());
        }
    }
    static class CustomSortingBasedonUpdatedTime implements Comparator<GithubRepository> {
        public int compare(GithubRepository a,GithubRepository b){
            //sorting based on last updated time
            //pending
            return b.getRecentupdateTime().compareTo(a.getRecentupdateTime());
        }
    }
    @Autowired
    private GithubRepoRepository githubRepoRepository;
    @Autowired
    private UserRepository userRepository;

    public GithubRepository saveRepo(GithubRepository repository){
        return githubRepoRepository.save(repository);
    }
    //get repositories for specific user
    public Set<GithubRepository> getReposofUser(String userId){
        Set<GithubRepository> repos = new HashSet<>();
        for(Person p : userRepository.findAll()){
            if(p.getUserId().equals(userId))
                return p.getRepos();
        }
        return null;
    }
    //delete repository
    public void deleteRepo(GithubRepository githubRepository){
        Person p = githubRepository.getUsers().iterator().next();
        for(GithubRepository g : p.getRepos()){
            if(g.getRepoId().equals(githubRepository.getRepoId())){
                p.getRepos().remove(g);
            }
        }
    }
    //get repositories for topics
    public List<GithubRepository> getReposBasedonTopics(String sortBy,List<String> topic_list1) throws IOException {
        List<GithubRepository> repos = new ArrayList<>();
        //https://api.github.com/search/repositories?q=topic:java+topic:azure+topic:aws+topic:python
        StringBuilder url = new StringBuilder("https://api.github.com/search/repositories?q=");
        int index= 0;
        List<String> topics = topic_list1;
        for(String topic:topics){
            url.append("topic:"+topic);
            if(index <topics.size()-1)
                url.append("+");
            index++;
        }
        URL urlObj = new URL(url.toString());
        URLConnection request = urlObj.openConnection();
        request.connect();
        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonObject rootObj = root.getAsJsonObject();

        for(var e:rootObj.entrySet()){
            if(e.getKey().toString().equals("items"))
            {
                for(var repo : e.getValue().getAsJsonArray()){
                    try {
                        GithubRepository githubRepository = new GithubRepository();
                        Map<String, JsonElement> mp = repo.getAsJsonObject().asMap();
                        //setting repo id
                        githubRepository.setRepoId(mp.get("id").toString());
                        //setting description
                        githubRepository.setDescription(mp.get("description").toString());
                        //setting topic list
                        List<String> topic_list = new ArrayList<>();
                        for (var topic : mp.get("topics").getAsJsonArray()) {
                            topic_list.add(topic.toString());
                        }
                        githubRepository.setTopicList(topic_list);
                        //setting clone url
                        githubRepository.setCloneUrl(mp.get("clone_url").toString());
                        //setting project url
                        githubRepository.setProjectUrl(mp.get("html_url").toString());
                        //setting stars
                        githubRepository.setStargazersCount(Long.parseLong(mp.get("stargazers_count").toString()));
                        //setting forks
                        githubRepository.setForkCount(Long.parseLong(mp.get("forks_count").toString()));
                        //setting recent updated time
                        githubRepository.setRecentupdateTime(mp.get("updated_at").toString());
                        //setting name
                        githubRepository.setName(mp.get("name").toString());
                        repos.add(githubRepository);
                    }
                    catch (Exception exception){
                        exception.printStackTrace();
                    }
                }
            }
        }
        if(sortBy.equals("fork_count"))
            Collections.sort(repos,new CustomSortingBasedonForks());
        else if(sortBy.equals("star_count"))
            Collections.sort(repos,new CustomSortingBasedonStars());
        else if(sortBy.equals("updatedTime"))
            Collections.sort(repos,new CustomSortingBasedonUpdatedTime());

        return repos;
    }
    public void deleteRepo(String userId,String repoId){
        for(var user : userRepository.findAll()){
            if(user.getUserId().equals(userId)){
                for(var repo : user.getRepos()){
                    if(repo.getRepoId().equals(repoId)){
                        user.getRepos().remove(repo);
                        break;
                    }
                }
            }
        }
    }
}
