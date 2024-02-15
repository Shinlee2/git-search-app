package gitSearch.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BranchSerialized {
    @JsonProperty("Branch Name")
    private String name;
    @JsonProperty("Last Commit Sha")
    private String sha;

    public BranchSerialized(String name, String sha) {
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
