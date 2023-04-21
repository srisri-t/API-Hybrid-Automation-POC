Feature: Demo Rest API Functionality Scenarios


  @QuickTest
  Scenario: Verify that the API returns the expected status code
    Given the API endpoint is "https://jsonplaceholder.typicode.com/posts/1"
    When I send a GET request to the API
    Then the response status code should be 200

  @QuickTest
  Scenario: Verify that the API returns the expected response body
    Given the API endpoint is "https://jsonplaceholder.typicode.com/posts/1"
    When I send a GET request to the API
    Then the response should contain "sunt aut facere repellat provident occaecati excepturi optio reprehenderit"

  @QuickTest
  Scenario: Verify User details fetching
    Given the API endpoint is "https://reqres.in/api/users/1"
    When I send a GET request to the API
    Then the response status code should be 200


#
#  Scenario Outline:  Verify Code
#    Given Get Call to "<url>"
#    Then Response  is array total "<total>"
#
#    Examples:
#      | url      | total |
#      | /student | 18    |
#
#  Scenario Outline:  Fetch user Data
#    Given Get Call to "<url>"
#    Then Response Code "<responseMessage>" is validated
#    Examples:
#      | url       | responseMessage |
#      | /users/:1 | 404             |
#
#
#  Scenario Outline:  Login successful validation
#    Given Get Call to "<url>"
#    Then Response Code "<responseMessage>" is validated
#    Examples:
#      | url        | responseMessage |
#      | /api/login | 404               |