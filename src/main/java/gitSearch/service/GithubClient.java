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
    RestClient restClient = RestClient.create();

    @Value("${apiKey}")
    private String apiKey;

    public List<GithubRepository> getRepositories(String username) {

        List<GithubRepository> deserializedRepositoryNames = restClient
                .get()
                .uri("https://api.github.com/users/" + username + "/repos")
                .header("Authorization", "Bearer " + apiKey)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    throw new UsernameNotFoundException(response.getStatusCode(), response.getStatusText());
                })
                .body(new ParameterizedTypeReference<>() {
                });

        return deserializedRepositoryNames;
    }

    public List<GithubBranch> getBranches(String username, String repositoryName) {

        List<GithubBranch> deserializedBranches = restClient
                .get()
                .uri("https://api.github.com/repos/" + username + "/" + repositoryName + "/branches")
                .header("Authorization", "Bearer " + apiKey)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });

        return deserializedBranches;
    }
}
