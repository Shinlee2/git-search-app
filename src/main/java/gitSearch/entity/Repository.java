package gitSearch.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Repository {
    @JsonProperty("Repository Name")
    private String name;
    @JsonProperty("Owner Login")
    private String owner;
    @JsonProperty("Branch List")
    private List<Branch> branches;

    public Repository(String name, String owner, List<Branch> branches) {
        this.name = name;
        this.owner = owner;
        this.branches = branches;
    }

    public Repository() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }
}
