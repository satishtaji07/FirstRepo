AspireAppQAChallenge
This is an hybrid framework. Please refer to config.properties file for data

Statement A credit company requests to have software to help them decide how to reject or approve a money loan request from their customers. This system will help to keep track of all the loan information and help to faster proceed all the requests.

Tests The tests that candidate should handle:

Login to web application
Navigate to Inventory feature
From the top-menu bar, select Products -> Products item, then create a new product
Update the quantity of new product is more than 10
From top-left page, click on Application icon
Navigate to Manufacturing feature, then create a new Manufacturing Order item for the created Product on step #3
Update the status of new Orders to “Done” successfully
Validate the new Manufacturing Order is created with corrected information.
Structure

pom.xml --> addition of dependencies
testng.xml --> execution of testcases
packages: i. com.qa.PageLayer --> contains pagewise test scripts. ii. com.qa.TestBase --> contains base class for execution of test case which is parent class for all other classes iii. com.qa.TestLayer --> contains test cases for execution
Execution:

1: read config.properties file 2: update product_name; product_quantity and barcode; 3: Go to TestCase.java and Run test OR 3: Open **testng.xml ** and then run

Please refer to applicationLogs.log file logs
