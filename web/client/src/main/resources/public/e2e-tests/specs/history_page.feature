Feature: Viewing the competition history page
  As a user
  I should be able to view the competition history home page
  to create new competitions

  Scenario: View  history page
    Given I go to "/index.html#/history"
    Then the title should equal "Photo Competition"
    And the page heading should equal "All Competitions"

  Scenario: Create new competition
      Given I go to "/index.html#/history"
      And I enter "This week's topic" into the "vm.new_topic" field
      And I click the button with id "submit-new-competition-form"
      Then the history table should contain a row with topic "This week's topic"
