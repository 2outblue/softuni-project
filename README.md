
-----------------------
The project consists of 3 modules - app-main, event-service, email-service. Two of those are required - the app-main and event-service. The email-service is optional - more details on how to run it are included below. 
On the latest commit i have mostly addded more data to the DBInit - more full data to better demonstrate functionalities, cleaned up some code and the default settings will work without the email-service.

The app-main folder contains the main application:
 - you can run it through an IDE (Intellij) - 
1. Go in the folder and open the project through Intellij for example (OR File -> Open -> find the directory and open)
2. Configure the application.yaml for the database settings - the email-service is disabled by default - keep it that way until you read the below section for it.
3. Run the app-main
4. Do the same for the event-service
The order in which you run the modules doesn't matter, but app-main and event-service need to be running for the web app to work.

------
Admin account credentials: username: admin@cc.com, password: parola
User account credentials: username: user@cc.com, password: parola

Main app works by default on localhost:8080, event-service: localhost:8090, email-service: localhost:8099

-------------
The email-service

It can be disabled thorugh the application.yaml file in the app-main - on the latest commit it is disabled by default. 

You need google credentials in order for this to work. The service uses the GMAIL API - you need to generate your own credentials (https://console.cloud.google.com/apis/credentials). The credentials are 
OAuth Client ID. You need to select at least the 'Send Emails' option when going through the process. Then you need to download the JSON file (secrets file) - this needs to be placed in the resources folder 
in the email-service. Then you need to go to the Gmailer Configuration class - line 41 and replace the path (name) to your secrets file. My secrets file is not included in this repository for obvious reasons..

When you first start-up the app, the program will print a link on the console, you need to follow that link and give it permission (like logging in with google to some website) - its going to ask you to 
use your email - you need to give it permission. After that the email-service should be working correctly and sending emails through the account that you gave it permission to.


