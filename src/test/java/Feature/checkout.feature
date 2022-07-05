Feature: User can select and book the maximum rating property
  @Tag1
  Scenario: User should select maximum property location and should book 3 rooms  for 2 adults and 2 children in the highest rating property
    Given User opens "chrome" browser
    And launch "https://booking.com" URL
    Then User should see HomePage
    When click on the maximum property location
    And select checkin date and checkout date for 6 days
    And add 2 adult
    And add 1 child with 15 years age
    And add 1 child with 12 years age
    And add 2 rooms
    And click on search button
    Then User should see the property result page
    When User apply 5 star rating filter and click maximum rated property
    Then new window should open
    And user should select the amount
    And take the screenshot and close browser

    @Tag2
  Scenario: User should select maximum property location and should book 3 rooms  for 2 adults and 2 children in the highest rating property
    Given User opens "edge" browser
    And launch "https://booking.com" URL
    Then User should see HomePage
    When click on the maximum property location
    And select checkin date and checkout date for 6 days
    And add 4 adult
    And add 1 child with 15 years age
    And add 1 child with 12 years age
    And add 3 rooms
    And click on search button
    Then User should see the property result page
    When User apply 5 star rating filter and click maximum rated property
    Then new window should open
    And user should select the amount
    And take the screenshot and close browser

