@scenarioTest 
Feature: Product Comparison. 
	Choose product to compare from Product List ,Add to basket, change quantity, remove from basket

Scenario Outline: 
	Should Add product from Comparison Page to basket 
	Given I navigate to "https://en.wikipedia.org" using "<browser>"
    And I set "Poland" into search input
    When I click on search button
    Then I should see "Poland" article

	Examples: 
		| browser |
		| CHROME  |
		| FIREFOX |

Scenario Outline:
    Should Add product from Comparison Page to basket
    Given I navigate to "https://en.wikipedia.org" using "<browser>"
    And I set "Poland" into search input
    When I click on search button
    Then I should see "Poland" article
    And I open main page

    Examples:
        | browser |
        | CHROME  |
        | FIREFOX |