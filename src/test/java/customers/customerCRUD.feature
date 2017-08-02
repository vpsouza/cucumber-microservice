Feature: Customer CRUD Test

Background: 
	* url demoBaseUrl

Scenario: fetch some customers

    Given path 'customers'
    When method get
	Then status 200
	And assert response.size() === 0
	
Scenario: add a customer, update it and then delete it

	Given path 'customers'
	And request {firstName: 'Vinicius', surName: 'Piedade', lastName: 'de Souza', age: 31}
	When method post
	Then status 200
	And match response contains { id: '#number', firstName: 'Vinicius', surName: 'Piedade', lastName: 'de Souza', age: 31}
	And def newCustomer = response

	Given path 'customers'
	And set newCustomer.firstName = 'Michel'
	And set newCustomer.surName = null
	And set newCustomer.lastName = 'Temer'
	And request newCustomer
	When method patch
	Then status 200
	And match response contains {firstName: 'Michel', surName: '#null', lastName: 'Temer'}
	And def idToBeDeleted = response.id
	
	Given path 'customers', idToBeDeleted
	When method delete
	Then status 200