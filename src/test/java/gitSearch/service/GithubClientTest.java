package gitSearch.service;

import gitSearch.entity.GithubBranch;
import gitSearch.entity.GithubRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class GithubClientTest {

    @Autowired
    GithubClient githubClient;

    @Test
    void givenUsernameAndRepositoryName_whenDeserializingRepositoryBranches_thenFirstBranchNameIsAsExpected() {
        //Given
        String username = "Shinlee2";
        String repositoryName = "git-search-app";
        String expectedString = "master";

        //When
        List<GithubBranch> branchEntityList = githubClient.getBranches(username, repositoryName);
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
        List<GithubRepository> repositoryDeserializedList = githubClient.getRepositories(username);
        String responseString = repositoryDeserializedList.get(1).getName();

        //Then
        assertEquals(expectedString, responseString);
    }
}