package gitSearch.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SerializedEntity {
    @JsonProperty("Repository Name")
    private String name;
    @JsonProperty("Repository Owner")
    private String owner;
    @JsonProperty("Branches")
    private List<BranchSerialized> branches;

    public SerializedEntity(String name, String owner, List<BranchSerialized> branches) {
        this.name = name;
        this.owner = owner;
        this.branches = branches;
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

    public List<BranchSerialized> getBranches() {
        return branches;
    }

    public void setBranches(List<BranchSerialized> branches) {
        this.branches = branches;
    }
}
