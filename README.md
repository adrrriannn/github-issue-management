# Issue manager

RESTful API that shows issues from projects. This API acts as a gateway for a external version control API.

Technologies used are:

 - Spring Boot
 - Docker
 - Junit
 - Maven
 - Usage

## Usage
To build the application we need to run this command :
```
  cd local && sh build.sh
```
This will generate the following Docker image for the project. This image is also available in Docker Hub with the stable tag.
```
adrrriannn/issue-manager-api
```
To run the application in a containerized fashion with Docker, we need to run this command:
```
docker run -ti adrrriannn/issue-manager-api
```

For simplicity, this manager will be read only. For further implementations create and edit operations might be added. Thiw would require authentication with the external API, in the case of GitHub. The RESTful API has two endpoints:

### View all issues for a given owner and repository:

  - user : Owner user

  - repo : Repository name

   - Using cURL:
   ```sh
   curl -X GET 'http://localhost:8080/issues?user=adrrriannn&repo=issue-manager'
   ```
   - Response for the above request:
   ```sh
  [
     {
        "id":"1",
        "title":"Test issue",
        "description":"This is an issue for testing",
        "assignees":[],
        "labels":[]
      }
  ]
  ```
### View single issue:

- id: Issue identifier

- user : Owner user

- repo : Repository name

  - Using cURL:
  ```sh
    curl -X GET 'http://localhost:8080/issues/1?user=adrrriannn&repo=issue-manager'
  ```
  - Response for the above request:
  ```sh 
  {
      "id":"1",
      "title":"Test issue",
      "description":"This is an issue for testing",
      "assignees":[],
      "labels":[]
  }
  ```
