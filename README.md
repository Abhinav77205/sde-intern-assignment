SDE intern assignment – Entry Management System

An android app for entry management.

Approach:

I have approached the problem unconventionally where there is a reception handles the entry. Rather the visitor can check himself/herself in without involving a third person. To improve productivity I have given the solution an app interface. Being an MVP, the UI is simple and self-explanatory but it can be turned into something very productive.

What it can do:

    • Saves the name, email address, phone no of the visitor and same 	information 	is captured for the host.
    • Once the data is captured, it is stored in the backend.
    • Visitor details are sent to host via SMS and email.
    • On check-out, the details of the meeting are emailed to the visitor.
	(Email and SMS functionality doesn’t work because it needs premium services. 	The 	mock API call has been indicated in code)
    • Check-in time and check-out time are  automatically captured and are not 	required for input.

Technologies & Languages:

Android, Firebase Firestore, Kotlin, XML

Supported Platforms:

It works on every Android phone with API>=22.

How to run?

    • Firebase projects needs to be linked with the app to get the backend working.
    • Connect a virtual or physical device run the project. 

How does it work?

The visitor and host details are stored in the firebase firestore NoSQL database. The check-in time is not shown as field and is generated using the system time. As the details is pushed to the database, the screen changes and displays all the entered details. When the check-out button is pressed the time is automatically generated and pushed to the database. Mock API calls for SMS and email have been indicated and they are needed to be implemented.
