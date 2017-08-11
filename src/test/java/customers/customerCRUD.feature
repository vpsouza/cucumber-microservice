Feature: Customer CRUD Test

Background: 
	* url demoBaseUrl

Scenario: fetch some customers

    Given path 'customers'
    When method get
	Then status 200
	And assert response.size() === 0
	
Scenario: add a customer, update it and then delete it

	def newCustomerPayload = {firstName: 'Vinicius', surName: 'Piedade', lastName: 'de Souza', age: 31}
	def newCustomer = call read('addCustomer.feature') {payload: newCustomerPayload}
	match newCustomer contains { id: '#number', firstName: 'Vinicius', surName: 'Piedade', lastName: 'de Souza', age: 31}

	set newCustomer.firstName = 'Michel'
	set newCustomer.surName = null
	set newCustomer.lastName = 'Temer'
	def patchCustomerResult = call read('updateCustomer.feature') newCustomer
	match patchCustomerResult contains {firstName: 'Michel', surName: '#null', lastName: 'Temer'}
	
	def resultDelete = call read('deleteCustomer.feature') patchCustomerResult