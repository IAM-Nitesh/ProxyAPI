1. Start with creating a new project with java17 and maven and select the dependencies needed for the project
2. to go src/main/java/org.springboot.project and create an mvc architecture
3. Start with creating controller and defining the end point
    -> @RestController
    -> @RequestMapping(/"name of controller") - ( eg: /product )
    -> define all the CRUD operations for the entity - ( eg: getProductById)
       @GetMapping()  - ( )
       @GetMapping(/path variable) - (/id)
    -> inject service variable via constructor
    -> define the function with return type as the model class for the entity
       eg : Product getProductById(@PathVariable("id") Long id)
        it would return the service class method
4. Create the services - product service ( if there are multiple implementations then use interface)
    -> @Service
    public Product getProductById( Long id) -> here the business implementation would lie
5. Create all the models and use @getter @setter
    -> eg product
    -> eg category

---- Run up till now and check now if code is build successfully.

---- we would make necessary changes here onwards.
1. we would hit the 3 party api
    - there are two ways of doing this
    a. create a http request and make a call
    b. a better way to do that is via RestTemplate provided by spring.
2. we would create a db of our own

---- RestTemplate implementation to connect to fakestore api --

1. Create an object of rest template in FakeStoreService and inject it via constructor

when we created an object of product service ( fakestore service had annotation service)
then when object of product controller was created it already had an object of product service available
this is called auto wiring - it auto wires the dependency whenever its required

-> use the resttemplateobj.getForObject( url , return type )

2. But in the case of rest template we haven't told if its object needs to be created
- this is done with the help of configs
- Config is a place where we define objects that needs to be created as bean
- we create a bean and put in configs in laymen terms
eg - create config folder with RestTemplateConfig file having the bean getRestTemplate


----- data massage would happen here as there might be things irrelevant coming from fakestore api to what product we want to send back to customer ---

1. DTO ( DATA TRANSFER OBJECT ) - whenever there is a contract btw two services its controlled by DTO
ie whenever i will call your api - will get data in this format

2. FakeStoreProductDTO fakestoreproductdto = resttemplate.getObjectFor(url, FakeStoreProductDTO.class)

using above we are doing deserialization of fakestoreapi json response to DTO object.

3. now finally we want to return product obj - so write a convertFakeStoreDTOToProduct





