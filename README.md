# FinancialLogger

Backend application that allows a user to log their day to day trades for better accessability and readability.

# Features

Application is secured using spring security. <br/>
Role based authentication is implemented using JWT<br/><br/>
The application makes http call to open nse(National Stock Exchange) url using Java 11 - HttpClient API, to retrive basic stock data including current-stock-price,
stock-code, etc. <br/>
After every one of this http call is made, the Stock table of the application is updated or inserted depending on whether or not the fetch is for a new stock.
<br/><br/>
User can enter new trades. For each trade added, the percentage of capital employed for each of teh other trades of that user is automatically updated accordingly.<br/>
This is achieved using observer pattern 

![image](https://user-images.githubusercontent.com/79571862/189483609-3a8fb9b8-ec12-490e-8476-1401bd39c988.png)

![image](https://user-images.githubusercontent.com/79571862/189483553-e07d15ab-f655-4b09-94f8-0dcfeabafc67.png)

<br/>
View Trade API retrieves the stock data of the currently logged in user based on the JWT token
<br/><br/>
UI application for this service is developed using Angular - github url https://github.com/NaveenJoyGit/FinancialLoggerUI

<br/>
