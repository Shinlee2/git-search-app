package gitSearch.controller;

import gitSearch.entity.BranchEntityDeserialized;
import gitSearch.entity.UserEntityDeserialized;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {
    @RequestMapping(value = "/{username}", method = RequestMethod.GET, headers = {"Accept=application/json", "key=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"})
    public List<UserEntityDeserialized> getRepositoriesByUsername(@PathVariable String username) {
        RestClient restClient = RestClient.create();

        List<UserEntityDeserialized> deserializedRepositoryNames = restClient
                .get()
                .uri("https://api.github.com/users/" + username + "/repos")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
        List<BranchEntityDeserialized> deserializedBranches = new ArrayList<>();

        for (int i = 0; i < deserializedRepositoryNames.size(); i++) {
            String repositoryName = deserializedRepositoryNames.get(i).getName();

            deserializedBranches = restClient
                    .get()
                    .uri("https://api.github.com/repos/" + username + "/" + repositoryName + "/branches")
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {});
        }



        return deserializedRepositoryNames;
    }
}
