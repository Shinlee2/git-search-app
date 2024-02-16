package gitSearch.service;

import gitSearch.entity.BranchEntityDeserialized;
import gitSearch.entity.UserEntityDeserialized;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RestServiceTest {

    @Autowired
    RestService restService;

    @Test
    void givenUsernameAndRepositoryName_whenDeserializingRepositoryBranches_thenFirstBranchNameIsAsExpected() {
        //Given
        String username = "Shinlee2";
        String repositoryName = "git-search-app";
        String expectedString = "master";

        //When
        List<BranchEntityDeserialized> branchEntityList = restService.deserializeBranches(username, repositoryName);
        String responseString = branchEntityList.get(0).getName();

        //Then
        assertEquals(expectedString, responseString);
    }

    @Test
    void givenUsername_whenDeserializingRepository_thenRepositoryNameIsAsExpected() {
        //Given
        String username = "Shinlee2";
        String expectedString = "git-search-app";

        //When
        List<UserEntityDeserialized> repositoryDeserializedList = restService.deserializeUsers(username);
        String responseString = repositoryDeserializedList.get(1).getName();

        //Then
        assertEquals(expectedString, responseString);
    }
}