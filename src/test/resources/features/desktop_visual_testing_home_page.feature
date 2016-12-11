@Desktop
Feature: Performing of visual testing for Home Page on desktop
  As a user I would like to see that all the elements on the home page are aligned properly
  and all the elements have correct sizes and styles

  Scenario: start new session
    Given new session of Chrome driver for desktop
    When user opens the home page

  Scenario: validate that top slider element has correct alignment
    Then top slider element has correct alignment and width between 750 and 770 px

  Scenario: validate that top text block element has correct alignment
    Then top text block element has correct alignment

  Scenario: validate that grid elements are aligned in a grid view
    Then then grid elements have the same sizes, not overlapped and aligned in a grid view 4 x 3

  Scenario: validate that every element of grid view hav correct style
    Then every element of grid view have correct background color '#f8f8f8'

  Scenario Outline: validate that main container and grid are aligned in a center
    Then main container and grid are aligned in a center horizontally with equal left and right offset when windows size is <width> x <height>
    Examples:
      | width | height |
      | 1200  | 900    |
      | 800   | 600    |

  Scenario Outline: validate that main container and grid are aligned in a center
    Then main container and grid are aligned in a center horizontally with equal left and right offset when window zoom is <zoom>
    Examples:
      | zoom |
      | 50   |
      | 70   |
      | 100  |
      | 120  |
      | 150  |

  Scenario: generate html report
    Then html report is going to be generated

  Scenario: close session
    Then web driver is going to be closed