@Mobile
Feature: Performing of visual testing for Home Page on mobile device
  As a user I would like to see that all the elements on the home page are aligned properly
  and all the elements have correct sizes and styles

  Scenario: start new session
    Given new session of Chrome driver for mobile device 'Apple iPhone 6'
    When user opens the home page

  Scenario: validate that top slider element has correct alignment
    Then top slider element has correct alignment and width between 300 and 350 px

  Scenario: validate that top text block element has correct alignment
    Then top text block element has correct alignment

  Scenario: validate that grid elements are aligned in a grid view
    Then then grid elements have the same sizes, not overlapped and aligned in a grid view 1 x 12

  Scenario: validate that every element of grid view hav correct style
    Then every element of grid view have correct background color '#f8f8f8'

  Scenario: generate html report
    Then html report is going to be generated

  Scenario: close session
    Then web driver is going to be closed