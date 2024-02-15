package gitSearch.controller;

import gitSearch.entity.BranchEntityDeserialized;
import gitSearch.entity.BranchSerialized;
import gitSearch.entity.SerializedEntity;
import gitSearch.entity.UserEntityDeserialized;
import gitSearch.service.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;

//api key = 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


@RestController
public class Controller {
    @Autowired
    private RestService restService;

    @RequestMapping(value = "/{username}", method = RequestMethod.GET, headers = "Accept=application/json")
    public List<SerializedEntity> getRepositoriesByUsername(@PathVariable String username) {

        List<UserEntityDeserialized> repositoryList = restService.deserializeUsers(username);
        List<SerializedEntity> serializedEntityList = new ArrayList<>();

        for (int i = 0; i < repositoryList.size(); i++) {
            String repository = repositoryList.get(i).getName();
            List<BranchEntityDeserialized> deserializedBranchList = restService.deserializeBranches(username, repository);

            List<BranchSerialized> branchSerializedList = new ArrayList<>();
            for (int j = 0; j < deserializedBranchList.size(); j++) {
                BranchSerialized branchSerialized = new BranchSerialized(deserializedBranchList.get(j).getName(), deserializedBranchList.get(j).getSha());
                branchSerializedList.add(branchSerialized);
                SerializedEntity serializedEntity = new SerializedEntity(repository, username, branchSerializedList);
                serializedEntityList.add(serializedEntity);
            }
        }
        return serializedEntityList;
    }
}
