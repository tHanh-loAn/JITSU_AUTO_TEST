package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.util.*;

public class GitHubApiTest {

    private static final String BASE_URL = "https://api.github.com/orgs/SeleniumHQ/repos";
    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testGitHubRepositoryData() throws Exception {
        List<JsonNode> allRepos = new ArrayList<>();
        int page = 1;
        int totalOpenIssues = 0;

        while (true) {
            Response response = RestAssured.given()
                    .queryParam("per_page", 100)
                    .queryParam("page", page)
                    .get(BASE_URL)
                    .then()
                    .statusCode(200)
                    .extract().response();

            JsonNode reposPage = mapper.readTree(response.getBody().asString());
            if (!reposPage.isArray() || reposPage.size() == 0) break;

            for (JsonNode repo : reposPage) {
                allRepos.add(repo);
                totalOpenIssues += repo.get("open_issues_count").asInt();
            }

            page++;
        }

        // 1. Total open issues across all repositories
        System.out.println("Total open issues: " + totalOpenIssues);

        // 2. Sort repos by updated_at (descending)
        allRepos.sort((a, b) -> b.get("updated_at").asText().compareTo(a.get("updated_at").asText()));
        System.out.println("Most recently updated repository: " + allRepos.get(0).get("name").asText());

        // 3. Repository with most watchers
        JsonNode maxWatchersRepo = Collections.max(allRepos, Comparator.comparingInt(r -> r.get("watchers_count").asInt()));
        System.out.println("Repo with most watchers: " + maxWatchersRepo.get("name").asText() + " (" + maxWatchersRepo.get("watchers_count").asInt() + ")");
    }
}
