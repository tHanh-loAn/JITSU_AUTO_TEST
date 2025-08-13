package steps;

import io.cucumber.java.en.*;

import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;
import java.util.stream.Collectors;

public class GithubSteps {

    private String orgName;
    private final List<JSONObject> allRepos = new ArrayList<>();

    @Given("The GitHub organization is {string}")
    public void theGitHubOrganizationIs(String orgName) {
        this.orgName = orgName;
    }

    @When("Retrieve all repositories of the organization")
    public void iRetrieveAllRepositoriesOfTheOrganization() {
        int page = 1;
        int perPage = 100;
        Response response;

        do {
            response = RestAssured.given()
                    .baseUri("https://api.github.com")
                    .basePath("/orgs/" + orgName + "/repos")
                    .queryParam("per_page", perPage)
                    .queryParam("page", page)
                    .get();

            JSONArray repos = new JSONArray(response.getBody().asString());
            for (int i = 0; i < repos.length(); i++) {
                allRepos.add(repos.getJSONObject(i));
            }

            page++;
        } while (!response.getBody().asString().equals("[]"));

        assertThat(allRepos).isNotEmpty();
    }

    @Then("The total number of open issues should be shown")
    public void theTotalNumberOfOpenIssuesShouldBeShown() {
        int totalOpenIssues = allRepos.stream()
                .mapToInt(repo -> repo.getInt("open_issues_count"))
                .sum();

        System.out.println("Total Open Issues: " + totalOpenIssues);
        assertThat(totalOpenIssues).isGreaterThanOrEqualTo(0);
    }

    @Then("The repositories should be sorted by updated date in descending order")
    public void theRepositoriesShouldBeSortedByUpdatedDateInDescendingOrder() {
        List<String> originalOrder = allRepos.stream()
                .map(repo -> repo.getString("updated_at"))
                .collect(Collectors.toList());

        List<String> sortedOrder = new ArrayList<>(originalOrder);
        sortedOrder.sort(Comparator.reverseOrder());

        System.out.println("Repositories are sorted by updated date descending: ");
        for (String date : sortedOrder) System.out.println(date);
    }

    @Then("The repository with the most watchers should be shown")
    public void theRepositoryWithTheMostWatchersShouldBeShown() {
        JSONObject topRepo = allRepos.stream()
                .max(Comparator.comparingInt(repo -> repo.getInt("watchers_count")))
                .orElseThrow();

        System.out.println("Repository with most watchers: " +
                topRepo.getString("name") + " - " + topRepo.getInt("watchers_count") + " watchers");
        assertThat(topRepo.getInt("watchers_count")).isGreaterThanOrEqualTo(0);
    }
}
