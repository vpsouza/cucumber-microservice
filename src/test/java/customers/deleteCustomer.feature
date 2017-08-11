@ignore
Feature: Add Customer

Background: 
	* url demoBaseUrl

Scenario: delete a customer

	Given path 'customers', id
	When method delete
	Then status 200