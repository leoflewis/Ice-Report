# Ice-Report 
The Ice-Report is a system used to keep track of local outdoor ice sheets and their quality.
The minimal requirements needed for an IceSheet are the name, address, ice quality score and date skated. However other information can be added as well.
Run Controller.java to use the application. A GUI form will appear, fill out the information about the ice sheet to the best of your ability and add it to the database. 
The Ice-Report will ensure the user input is valid. 
The Ice-Report does not allow duplicates. The Ice-Report does not allow the user to edit an entry, it must be deleted an re-created.
The Ice-Report sends entries from the database to a Twitter Account upon their addition. 
The Ice-Report sorts the Ice Sheets the user enters by score with 10 being the best and displays them in a list.


The Ice-Report has these features.
* It has an IceSheet object
* It stores IceSheets in a sorted database
* It has an intuitive user interface that can add, delete and save
* It has insightful comments
* It tweets out ice sheet information