#comment goes here
Feature: sellers related test


  @seller
  Scenario: get all sellers and their company name and their phone number
    Given user hits get all sellers api with "/api/myaccount/sellers" and parameters: isArchived false, page 1, size 10
    Then user gets all sellers and their phone number


  Scenario Outline: Seller creation, seller info update, reading seller's data
    Given data "<company_name>", "<seller_name>", "<email>", "<phone_number>" and "<address>" to build request body
    Then hit seller API endpoint "/api/myaccount/sellers" and seller's id
    Then update email "jureanicolae@gmail.com" and use the same rest of data "<company name>", "<seller name>", "<phone number>" and "<address>"
    Then hit update seller API endpoint "/api/myaccount/sellers/" concatenated by above seller_id
    Then verify that seller's email got updates succesfully to "11jackson15@gmail.com"
    Then hit get seller API endpoint "/api/myaccount/sellers" concatenated by above seller_id
    And verify that id in updated seller API and id in get seller API is the same

    Examples:
      | company_name    | seller_name | email          | phone_number | address     |
      | myed dfs vsdvcompssany | im am coco  | coshvvsddsla@gmail.com | 121314156     | 2250 ashton |

