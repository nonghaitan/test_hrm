package api.steps;

import commons.base.GlobalConstants;
import commons.report.APIExtentTestManager;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

public class GitHubAPISteps {

    private Response response;
    private List<Map<String, Object>> repos;

    // Helpers
    private Map<String, Object> getTopRepoByStars() {
        return repos.stream()
                .max((r1, r2) -> ((Integer) r1.get("stargazers_count"))
                        .compareTo((Integer) r2.get("stargazers_count")))
                .orElseThrow();
    }

    private int getTotalOpenIssues() {
        return repos.stream()
                .mapToInt(repo -> (int) repo.get("open_issues_count"))
                .sum();
    }

    private void assertAndLog(boolean condition, String message) {
        APIExtentTestManager.getTest().info(message);
        Assert.assertTrue(condition, message);
    }

    @Given("I fetch all repositories of {string}")
    public void fetchRepositories(String org) {
        APIExtentTestManager.getTest().info("Fetching repositories of " + org);

        response = RestAssured
                .given()
                .baseUri(GlobalConstants.API_GITHUB_BASE_URI)
                .get(String.format(GlobalConstants.API_GITHUB_ORG_REPOS_ENDPOINT, org))
                .then().statusCode(200)
                .extract().response();

        repos = response.jsonPath().getList("");
        APIExtentTestManager.getTest().info("Total repositories fetched: " + repos.size());

        assertAndLog(repos.size() > 0, "Repositories fetched > 0");
    }

    @When("I calculate the total number of open issues")
    public void calculateTotalOpenIssues() {
        int totalOpenIssues = getTotalOpenIssues();
        APIExtentTestManager.getTest().info("Total open issues: " + totalOpenIssues);
        assertAndLog(totalOpenIssues >= 0, "Total open issues >= 0");
    }

    @When("I determine the repository with the highest stars")
    public void findHighestStarRepo() {
        Map<String, Object> topRepo = getTopRepoByStars();
        APIExtentTestManager.getTest().info("Highest-star repo: " + topRepo.get("name") +
                " with stars: " + topRepo.get("stargazers_count"));

        assertAndLog(topRepo.get("name") != null, "Top repo name is not null");
        assertAndLog((Integer) topRepo.get("stargazers_count") >= 0, "Top repo stars >= 0");
    }

    @Then("The total number of open issues should be greater than or equal to 0")
    public void assertTotalOpenIssues() {
        int totalOpenIssues = getTotalOpenIssues();
        assertAndLog(totalOpenIssues >= 0, "Total open issues >= 0: " + totalOpenIssues);
    }

    @Then("The repository name should not be empty")
    public void assertRepoNameNotEmpty() {
        String repoName = (String) getTopRepoByStars().get("name");
        assertAndLog(repoName != null && !repoName.isEmpty(), "Top repo name: " + repoName);
    }

    @Then("The number of stars should be greater than or equal to 0")
    public void assertStarsNonNegative() {
        int stars = (int) getTopRepoByStars().get("stargazers_count");
        assertAndLog(stars >= 0, "Top repo stars: " + stars);
    }
}
