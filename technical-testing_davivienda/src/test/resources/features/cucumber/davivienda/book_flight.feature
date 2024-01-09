@atrapalo
Feature: User must enter the catch it page
  book a flight and if you do not have enough data
  show an error message within the execution report

  Scenario Outline: Enter the catch it page and book a flight
    Given that andres open the browser of atrapalo
    When andres search for a flight from "<origin>" to "<destination>" on "<date_start>" and "<date_end>"
    And andres waits for the results page to load
    Then andres selects the second available flight



    Examples:
      | origin | destination | date_start | date_end |
      ##@externaldata@./src/test/resources/data_test_case.xlsx@davivienda@
   |Bogota   |Medellin   |20   |29|

