# github-access-report

# GitHub Access Report API

Spring Boot backend service that generates a report of which users have access to which repositories in a GitHub organization.

## Endpoint

GET /api/github-access-report/{org}

Example:

http://localhost:8080/api/github-access-report/google

## How It Works

1. Fetch repositories of the organization using GitHub API
2. Fetch contributors for each repository
3. Aggregate results into User → Repositories mapping

## Tech Stack

Java  
Spring Boot  
Maven  
GitHub REST API

## Setup

1. Create the resources folder

src/main/resources

2. Inside the resources folder create a file:

application.properties

3. Add GitHub token in application.properties

github.token=YOUR_GITHUB_TOKEN

4. Run the application

5. Call the endpoint

/api/github-access-report/{org}

## Note

The `application.properties` file is not included in the repository to avoid exposing sensitive credentials.  
Users must create the file locally and provide their own GitHub Personal Access Token.
