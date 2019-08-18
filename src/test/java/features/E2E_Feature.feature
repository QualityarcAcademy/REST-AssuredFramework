# This is an integrated tests for API and Selenium
Feature: Integrated tests for Servicenow application

  Scenario Outline: Title of your scenario outline
    Given Create an Incident using API and verify <StatusCode>
    When Login servicenow using given <Username> and <Password>
    Then Search the incident using <searchkeyword> and enter <incidentNum>

    Examples: 
      | Username | Password     | searchkeyword | incidentNum | StatusCode |
      | admin    | ErMiWa0E0mlP | Incident      | INC0010125  |        201 |
