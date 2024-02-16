package gitSearch.controller;

import gitSearch.entity.*;
import gitSearch.handler.UsernameNotFoundException;
import gitSearch.service.GithubClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {

    @Autowired
    private GithubClient githubClient;

    @RequestMapping(value = "/{username}", method = RequestMethod.GET, headers = "Accept=application/json")
    public List<Repository> getRepositoriesByUsername(@PathVariable String username) {

        List<GithubRepository> githubRepositoryList = githubClient.getRepositories(username);

        List<Repository> repositoryList = new ArrayList<>();

        for (GithubRepository githubRepository : githubRepositoryList) {
            if (!githubRepository.isFork()) {
                String repositoryName = githubRepository.getName();
                List<GithubBranch> githubBranchList = githubClient.getBranches(username, repositoryName);

                List<Branch> branchList = new ArrayList<>();
                Repository repository = new Repository();

                for (GithubBranch githubBranch : githubBranchList) {
                    Branch branch = new Branch(githubBranch.getName(), githubBranch.getSha());
                    branchList.add(branch);
                }

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
