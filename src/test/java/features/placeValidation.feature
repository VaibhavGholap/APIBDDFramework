Feature: Validating place API's

Scenario Outline: Verify if place is being Successfully added using AddPlaceAPI

Given Add Place Payload "<name>","<language>","<address>"
When user call "AddPlaceAPI" with Post http request
Then the API call got success with status status code 200
And "status" in responce is "OK"
And "scope" in responce is "APP"


Examples:
|name   |language|address          |
|AAhouse|English |Word Cross Center|
|BBhouse|Marathi |Sea Cross Center |
