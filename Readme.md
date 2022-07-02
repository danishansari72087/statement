Take a pull of both security and statement service
security service is responsible for authentication and generating the jwt token
statement service is responsible for providing the statements for authenticated users



security service flow :
use /authenticate api of security service for authentication
use any credentials (username=admin password=admin , username=user password=user )
if credentials is correct then in response you will get jwt token, use that token in header for consuming statement service api


statements  service flow :
use /statement/search api of statement service to get statements (use postman collection given in doc folder)
provide accountsdb.accdb file path in application.properties file
Forward Authorization in header with  jwt token
To get statement by date forward searchType parameter with  BY_DATE value,also forward accId,  toDate and fromDate parameters
To get statement by amount forward searchType parameter with BY_AMOUNT , also forward accId,  fromAmount ,toAmount parameters
For last 3 months statements value of searchType is null, only forward accId parameter
