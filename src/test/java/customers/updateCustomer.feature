@ignore
Feature: Add Customer

Background: 
	* url demoBaseUrl

Scenario: update a customer

	Given path 'customers'
	And request newCustomer
	When method patch
	Then status 200