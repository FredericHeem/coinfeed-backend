## Introduction
**coinfeed-backend** grabs bitcoin market data from different exchanges and store the data into databases.
See [coinfeed](https://github.com/FredericHeem/coinfeed) for the web frontend.

## Getting started
	
### Get source and build it

    git clone git@github.com:FredericHeem/coinfeed-backend.git
    cd coinfeed-backend
    mvn install	

## Development

**coinfeed-backend** is built with maven, developed with eclipse, tested with junit, statically analyzed, code covered, and continuously integrated: [![Build Status](https://travis-ci.org/FredericHeem/coinfeed-backend.png)](https://travis-ci.org/FredericHeem/coinfeed-backend)

The state machine code generator [StateForge](http://www.stateforge.com) is used to implement this backend. By the way, I'm the author of these state machine tools, developers need to eat their own dog food. 

Mongodb is used to store the data.


### Generate the eclipse project

    mvn eclipse:eclipse

### Run the unitest

    mvn test

### Run static analysis with [pmd] (http://pmd.sourceforge.net/)

    mvn pmd:pmd   
     
View the report at target/site/pmd.html

### Run static analysis [findbugs] (http://findbugs.sourceforge.net/)

    mvn site:site
    
View the report at target/site/findbugs.html    

### Generate the code coverage report with [cobertura] (http://cobertura.sourceforge.net/)

    mvn cobertura:cobertura
    
View report at target/site/cobertura/index.html 
	                                                         
## Contributors

[FredericHeem](https://github.com/FredericHeem)



	

	
	






