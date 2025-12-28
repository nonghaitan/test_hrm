package steps.api;

import commons.ExtentTestManager;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

public class GitHubAPISteps {

    private Response response;
    private List<Map<String, Object>> repos;

    @Given("I fetch all repositories of {string}")
    public void fetchRepositories(String org) {
        ExtentTestManager.getTest().info("Fetching repositories of " + org);
        response = RestAssured
                .given()
                .baseUri("https://api.github.com")
                .get("/orgs/" + org + "/repos")
                .then().statusCode(200)
                .extract().response();

        repos = response.jsonPath().getList("");
        ExtentTestManager.getTest().info("Total repositories fetched: " + repos.size());
        Assert.assertTrue(repos.size() > 0, "No repositories fetched!");
    }

    @When("I calculate the total number of open issues")
    public void calculateTotalOpenIssues() {
        int totalOpenIssues = repos.stream()
                .mapToInt(repo -> (int) repo.get("open_issues_count"))
                .sum();

        ExtentTestManager.getTest().info("Total open issues: " + totalOpenIssues);
        Assert.assertTrue(totalOpenIssues >= 0);
    }

    @When("I determine the repository with the highest stars")
    public void findHighestStarRepo() {
        Map<String, Object> topRepo = repos.stream()
                .max((r1, r2) -> ((Integer) r1.get("stargazers_count")).compareTo((Integer) r2.get("stargazers_count")))
                .orElseThrow();

        ExtentTestManager.getTest().info("Highest-star repo: " + topRepo.get("name") +
                " with stars: " + topRepo.get("stargazers_count"));

        Assert.assertNotNull(topRepo.get("name"));
        Assert.assertTrue((Integer) topRepo.get("stargazers_count") >= 0);
    }

    @Then("The total number of open issues should be greater than or equal to 0")
    public void assertTotalOpenIssues() {
        int totalOpenIssues = repos.stream()
                .mapToInt(repo -> (int) repo.get("open_issues_count"))
                .sum();
        ExtentTestManager.getTest().info("Asserting total open issues: " + totalOpenIssues);
        Assert.assertTrue(totalOpenIssues >= 0, "Total open issues is negative!");
    }

    @Then("The repository name should not be empty")
    public void assertRepoNameNotEmpty() {
        Map<String, Object> topRepo = repos.stream()
                .max((r1, r2) -> ((Integer) r1.get("stargazers_count")).compareTo((Integer) r2.get("stargazers_count")))
                .orElseThrow();
        String repoName = (String) topRepo.get("name");
        ExtentTestManager.getTest().info("Asserting top repo name is not empty: " + repoName);
        Assert.assertNotNull(repoName, "Top repository name is null!");
        Assert.assertFalse(repoName.isEmpty(), "Top repository name is empty!");
    }

    @Then("The number of stars should be greater than or equal to 0")
    public void assertStarsNonNegative() {
        Map<String, Object> topRepo = repos.stream()
                .max((r1, r2) -> ((Integer) r1.get("stargazers_count")).compareTo((Integer) r2.get("stargazers_count")))
                .orElseThrow();
        int stars = (int) topRepo.get("stargazers_count");
        ExtentTestManager.getTest().info("Asserting top repo stars >= 0: " + stars);
        Assert.assertTrue(stars >= 0, "Top repository stars are negative!");
    }
}
