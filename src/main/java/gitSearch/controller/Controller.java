package gitSearch.controller;

import gitSearch.entity.*;
import gitSearch.handler.UsernameNotFoundException;
import gitSearch.service.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {
    @Autowired
    private RestService restService;

    @RequestMapping(value = "/{username}", method = RequestMethod.GET, headers = {"Accept=application/json"})
    public List<SerializedEntity> getRepositoriesByUsername(@PathVariable String username) {

        List<UserEntityDeserialized> repositoryList = restService.deserializeUsers(username);

        List<SerializedEntity> serializedEntityList = new ArrayList<>();

        for (int i = 0; i < repositoryList.size(); i++) {
            if (!repositoryList.get(i).isFork()) {
                String repository = repositoryList.get(i).getName();
                List<BranchEntityDeserialized> deserializedBranchList = restService.deserializeBranches(username, repository);

                List<BranchSerialized> branchSerializedList = new ArrayList<>();
                SerializedEntity serializedEntity = new SerializedEntity();
                for (int j = 0; j < deserializedBranchList.size(); j++) {
                    BranchSerialized branchSerialized = new BranchSerialized(deserializedBranchList.get(j).getName(), deserializedBranchList.get(j).getSha());
                    branchSerializedList.add(branchSerialized);
                    serializedEntity.setName(repository);
                    serializedEntity.setOwner(username);
                    serializedEntity.setBranches(branchSerializedList);
                }
                serializedEntityList.add(serializedEntity);
            }
        }
        return serializedEntityList;
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
