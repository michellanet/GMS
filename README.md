# GYM Management System and Scheduler
# For test runs use the following username and passwords
  Admin Username: prekshya password: canada
  
  Member Username: sajitkhadka password:canada
  
  Trainer Username: michael password: java
  
  Member and trainer can be created by admin as well. So member username and trainer username can be generated as required.
  
# Entity RelationShip Diagram
![ER Diagram](https://github.com/2019-summer-db-java-itc5201-b/project-gms/blob/master/er-diagram.png)

# Project Description
Gym management and Scheduler (GMS) is an app designed to ease the schedule and payment of a gym member. It encompasses features that a person in a gym could use. 

The concept is that the application can generate exerciese schedule for a member and tracks each day’s attendance and and payment history. It has a feature to check each month’s payments and will show payment due for each member.

# Work BreakDown
a)	Interface
-	It will have three interfaces (one for user, one for gym administration and one for trainer).
-	Sajit Khadka will develop member interface, Prekshya Aryal will develop Admin interface and Michael will develop trainer interface.

b)	Login (Sajit Khadka)
-	User have to be registered and log in in order to use this system. There is UI forms to register and login.
-	There will be only one login form for gym admin, member and trainer. The system will find out who is the user (admin, member or trainer) and gets the corresponding form.
-	Sajit Khadka developed user login system and member registration system.

c)	Admin (Prekshya Aryal)
-	Prekshya Aryal is responsible designing, developing and modelling this part.
-	Gym admin can register and delete a new user, and trainer. So she created UI to add new member, trainer and admin
- She will be responsible creating ui to dislay list of all users which have table to show all the members and their detail such as payment information, profile detail etc., in admin dashboard.
-	
d)	Gym Member (Every of the following feature was created by Sajit Khadka)
-	Sajit Khadka is responsible designing, developing and modelling this part.
- Sajit is responsible creating auto generation of gym schedule(computer generates the daily exercieses for members using computer AI).
-	Member have option to choose different options at gym(swimming, gym, cardio) and trainer for them. If member choose these member's monthly fee is increased. This system was created by Sajit Khadka 
-	The gym has one month credit system which means member can get one month credit time to pay the bill. Also member can pay amount due and view the amount due and history of payment.
-	Member has option to select his/her ideal body type(such as bulky, slim or fit) which helps computer AI generate ideal gym schedule for each member.
-	Member can choose shift.
-	Member can view and edit profile.

e)	Trainer
-	Michael Anetor is responsible designing, developing and modelling this part.
-	There will be a trainer dashboard where trainer can view the list of his trainees and their schedule.
-	Trainer can add his experience and other description about himself such as a bio.
-	Trainer can add their profile picture as well.
-	Trainer also have profile where he can view or edit personal information.


