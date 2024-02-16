package gitSearch.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class GithubRepository {

    @JsonProperty("Repository Name")
    private String name;
    @JsonProperty("Owner Login")
    private String login;
    private boolean fork;

    @JsonProperty("owner")
    private void unpackFromNestedObject(Map<String, String> owner) {
        login = owner.get("login");
    }

    public GithubRepository(String name, String login, Boolean fork) {
        this.name = name;
        this.login = login;
        this.fork = fork;
    }

    public boolean isFork() {
        return fork;
    }

    public void setFork(boolean fork) {
        this.fork = fork;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
