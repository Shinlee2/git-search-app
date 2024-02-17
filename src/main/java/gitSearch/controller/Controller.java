package gitSearch.controller;

import gitSearch.entity.*;
import gitSearch.handler.UsernameNotFoundException;
import gitSearch.service.GithubApiClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {

    private final GithubApiClient githubApiClient;

    public Controller(GithubApiClient githubApiClient) {
        this.githubApiClient = githubApiClient;
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET, headers = "Accept=application/json")
    public List<Repository> getRepositoriesByUsername(@PathVariable String username) {

        List<GithubRepository> githubRepositoryList = githubApiClient.getRepositories(username);

        List<Repository> repositoryList = new ArrayList<>();

        for (GithubRepository githubRepository : githubRepositoryList) {
            if (!githubRepository.isFork()) {
                String repositoryName = githubRepository.getName();
                List<GithubBranch> githubBranchList = githubApiClient.getBranches(username, repositoryName);

                List<Branch> branchList = new ArrayList<>();
                for (GithubBranch githubBranch : githubBranchList) {
                    Branch branch = new Branch(githubBranch.getName(), githubBranch.getSha());
                    branchList.add(branch);
                }

                Repository repository = new Repository();
                repository.setName(repositoryName);
                repository.setOwner(username);
                repository.setBranches(branchList);
                repositoryList.add(repository);
            }
        }
        return repositoryList;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UsernameNotFoundException.class)
    public ExceptionHandingEntity handleException(@RequestBody UsernameNotFoundException usernameNotFoundException) {
        ExceptionHandingEntity exceptionHandingEntity = new ExceptionHandingEntity();
        exceptionHandingEntity.setStatusCode(usernameNotFoundException.getStatusCode());
        exceptionHandingEntity.setStatusMessage(usernameNotFoundException.getMessage());
        return exceptionHandingEntity;
    }
}
