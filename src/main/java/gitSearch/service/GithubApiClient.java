package gitSearch.service;

import gitSearch.entity.GithubBranch;
import gitSearch.entity.GithubRepository;
import gitSearch.handler.UsernameNotFoundException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class GithubApiClient {

    private final RestClient restClient;

    public GithubApiClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public List<GithubRepository> getRepositories(String username) {

        List<GithubRepository> repositoryList = restClient
                .get()
                .uri("/users/" + username + "/repos")
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    throw new UsernameNotFoundException(response.getStatusCode(), response.getStatusText());
                })
                .body(new ParameterizedTypeReference<>() {
                });

        return repositoryList;
    }

    public List<GithubBranch> getBranches(String username, String repositoryName) {

        List<GithubBranch> branchList = restClient
                .get()
                .uri("/repos/" + username + "/" + repositoryName + "/branches")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });

        return branchList;
    }
}
