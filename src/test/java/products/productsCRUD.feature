Feature: Products CRUD Test

Background: 
	* url demoBaseUrl

Scenario: fetch some products

    Given path 'products'
    When method get
	Then status 200
	And assert response.size() === 6
	
Scenario: add a product, update it and then delete it

	Given path 'products'
	And request {name: 'Iphone 7 Plus 128GB', description: 'Iphone 7 Plus Space Gray 128GB' }
	When method post
	Then status 200
	And match response contains { id: '#number', name: 'Iphone 7 Plus 128GB', description: 'Iphone 7 Plus Space Gray 128GB'}
	And def newProduct = response

	Given path 'products'
	And set newProduct.name = 'New Product Iphone 7'
	And request newProduct
	When method patch
	Then status 200
	And match response contains {name: 'New Product Iphone 71'}
	And def idToBeDeleted = response.id
	
	Given path 'products', idToBeDeleted
	When method delete
	Then status 200