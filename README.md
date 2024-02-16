# Github Search App
### This application aims to resolve following recruitment task:

As an api consumer, given username and header “Accept: application/json”, I would like to list all his github repositories, which are not forks. Information, which I require in the response, is:

Repository Name

Owner Login

For each branch it’s name and last commit sha

As an api consumer, given not existing github user, I would like to receive 404 response in such a format:

    {
    “status”: ${responseCode}
    “message”: ${whyHasItHappened}
    }

---

### Endpoint:
    /{username}

returns Json based on given "username":

    [
        {
        "Repository Name": ${repositoryName},
        "Owner Login": ${ownerLogin},
        "Branch and Sha":
            [
                {
                   "Branch Name": ${branch},
                    "Last Commit SHA": ${sha}
                }
            ]  
        }
    ]

in case given "username" does not exist return value will be changed to:

    {
        "status": ${statusCode}
        "message": ${message}
    }

---

### Technologies used:
- Java 21
- Spring Boot 3