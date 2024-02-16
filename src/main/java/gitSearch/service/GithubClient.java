package gitSearch.service;

import gitSearch.entity.GithubBranch;
import gitSearch.entity.GithubRepository;
import gitSearch.handler.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class GithubClient {

    private final RestClient restClient;

    @Value("${apiKey}")
    private String apiKey;

    public GithubClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public List<GithubRepository> getRepositories(String username) {

        List<GithubRepository> repositoryList = restClient
                .get()
                .uri("https://api.github.com/users/" + username + "/repos")
                .header("Authorization", "Bearer " + apiKey)
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
                .uri("https://api.github.com/repos/" + username + "/" + repositoryName + "/branches")
                .header("Authorization", "Bearer " + apiKey)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });

        return branchList;
    }
}
