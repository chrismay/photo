Feature: Viewing the competition home page
  As a user
  I should be able to view the competition home page

  Scenario: View  homepage
    Given I go to "/index.html"
    Then the title should equal "Photo Competition"

  Scenario: View the upload link
    Given I go to "/index.html"
    Then I should see a link with id "upload-link" with text "Submit a photo" 
