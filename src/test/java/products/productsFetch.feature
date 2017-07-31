Feature: Products Fetch Test

Background:
* url demoBaseUrl

Scenario: fetch some products

    Given path 'products'
    When method get
	Then status 200
	And assert response.size() === 6
	
Scenario: add a product

	Given path 'products'
	And request {name: 'Iphone 7 Plus 128GB', description: 'Iphone 7 Plus Space Gray 128GB' }
	When method post
	Then status 200
	And match response contains { id: '#number', name: 'Iphone 7 Plus 128GB', description: 'Iphone 7 Plus Space Gray 128GB'}