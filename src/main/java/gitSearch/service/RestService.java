package gitSearch.service;

import gitSearch.entity.BranchEntityDeserialized;
import gitSearch.entity.UserEntityDeserialized;
import gitSearch.handler.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class RestService {
    RestClient restClient = RestClient.create();

    @Value("${apiKey}")
    private String apiKey;

    public List<UserEntityDeserialized> deserializeUsers(String username) {

        List<UserEntityDeserialized> deserializedRepositoryNames = restClient
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

    public List<BranchEntityDeserialized> deserializeBranches(String username, String repositoryName) {

        List<BranchEntityDeserialized> deserializedBranches = restClient
                .get()
                .uri("https://api.github.com/repos/" + username + "/" + repositoryName + "/branches")
                .header("Authorization", "Bearer " + apiKey)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });

        return deserializedBranches;
    }
}
