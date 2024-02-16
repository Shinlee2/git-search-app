package gitSearch.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class GithubBranch {

    @JsonProperty("Branch name")
    private String name;
    @JsonProperty("Last Commit Sha")
    private String sha;

    @JsonProperty("commit")
    private void unpackFromNestedObject(Map<String, String> commit) {
        sha = commit.get("sha");
    }

    public GithubBranch(String name, String sha) {
        this.name = name;
        this.sha = sha;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }
}
