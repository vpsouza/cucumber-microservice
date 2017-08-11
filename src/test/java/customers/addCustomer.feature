@ignore
Feature: Add Customer

Background: 
	* url demoBaseUrl

Scenario: add a customer

	Given path 'customers'
	And def payload = {firstName: '#(firstName)', surName: '#(surName)', lastName: '#(lastName)'}
	And set newCustomer.age = 
	And request payload
	When method post
	Then status 200