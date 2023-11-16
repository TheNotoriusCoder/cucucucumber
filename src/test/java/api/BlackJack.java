package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class BlackJack {

    @Test
    public void blackJack(){
        //hit new deck API to get deck_id
        baseURI = "https://www.deckofcardsapi.com";
        Response response = given().get("/api/deck/new/");
        response.prettyPrint();
        System.out.println("status code: " + response.statusCode());

        //using JSON path

        String deckId = response.jsonPath().getString("deck_id");
        System.out.println("deck id: " + deckId);

        //assert that our deck is not shuffled
        boolean shuffled = response.jsonPath().getBoolean("shuffled");
        Assert.assertFalse(shuffled);

        //shuffle the cards

        response = given().get("/api/deck/" + deckId + "/shuffle/");
        response.prettyPrint();
        System.out.println("status code: " + response.statusCode());
        shuffled = response.jsonPath().getBoolean("shuffled");

        Assert.assertTrue(shuffled);

        //deal 3 cards to each of 2 students

        Map<String, Object> params = new HashMap<>();
        params.put("count",3);
        response = given().params(params).get("/api/deck/"+ deckId + "/draw/");
        response.prettyPrint();
        System.out.println("first player cards: ");
        System.out.println(response.jsonPath().getString("cards[0].value"));
        System.out.println(response.jsonPath().getString("cards[1].value"));
        System.out.println(response.jsonPath().getString("cards[2].value"));


        response = given().params(params).get("/api/deck/"+ deckId + "/draw/");
        response.prettyPrint();
        System.out.println("second player cards: ");
        System.out.println(response.jsonPath().getString("cards[0].value"));
        System.out.println(response.jsonPath().getString("cards[1].value"));
        System.out.println(response.jsonPath().getString("cards[2].value"));


        //verify that we have less than 52 cards
        int remaining = response.jsonPath().getInt("remaining");

        Assert.assertEquals(remaining,46);





//        String deckID = given()
//                .baseUri("https://www.deckofcardsapi.com/")   // Set the base URI for the API request.
//                .when()                                        // Define that an HTTP request is about to be made.
//                .get("api/deck/new/")                          // Perform an HTTP GET request to "api/deck/new/".
//                .then()                                        // Define that response assertions are about to be made.
//                .log().all()                                   // Log the entire response (headers and body) to the console.
//                .statusCode(200)                               // Assert that the HTTP response status code is 200 (OK).
//                .extract()                                     // Extract details from the response.
//                .jsonPath()                                   // Use JSONPath to work with the JSON response.
//                .getString("deck_id");                         // Extract the value of "deck_id" from the JSON response.
//
//        System.out.println("deck id: " + deckID);            // Print the extracted deck ID to the console.
    }

}
