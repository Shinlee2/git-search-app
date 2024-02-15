package gitSearch.service;

import gitSearch.entity.BranchEntityDeserialized;
import gitSearch.entity.UserEntityDeserialized;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class RestService {
    RestClient restClient = RestClient.create();

    public List<UserEntityDeserialized> deserializeUsers(String username) {

        List<UserEntityDeserialized> deserializedRepositoryNames = restClient
                .get()
                .uri("https://api.github.com/users/" + username + "/repos")
                .header("Authorization", "Bearer<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>")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });

        return deserializedRepositoryNames;
    }

    public List<BranchEntityDeserialized> deserializeBranches(String username, String repositoryName) {

        List<BranchEntityDeserialized> deserializedBranches = restClient
                .get()
                .uri("https://api.github.com/repos/" + username + "/" + repositoryName + "/branches")
                .header("Authorization", "Bearer<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>")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });

        return deserializedBranches;
    }
}
