# THE TAYLOR SWIFT LYRIC ACRONYM DECODER
#### Video Demo:  <URL HERE>
#### Description:

I created a full stack web application that can decipher a given acronym into any matches found in Taylor Swift's lyrics. This year, Taylor Swift has embarked on an international stadium tour titled The Eras Tour. Taylor's fans, known as Swifties, have started a tradition of making and bringing beaded friendship bracelets to the concerts to exchange with other fans. The bracelets typically use letter beads to spell out album titles, song titles, pieces of Taylor Swift lore, and acronyms. There are currently many programs that decode song acronyms into the correct song title. However many Swifties have included acronyms of their favorite lyrics on the bracelets. This can be challenging for the bracelet receiver to decode, because it involves scanning the lyrics of over 200 songs to find a match, if you aren't lucky enough to figure it out on your own. For example, "IWBYILMF" stands for "I wanna brainwash you into loving me forever" from the song "Paris" off of Taylor's 2022 album Midnights (3am Version). I've seen many fans online this year asking for help with decoding acronyms on bracelets they received, which gave me the idea for this program.

For the tech stack of my program, I've used Java, Spring and Spring Boot, and a CSV file of 225 Taylor Swift songs for the backend. The CSV file (source: https://github.com/shaynak/taylor-swift-lyrics/) contains lyrics for all 225 songs. For the frontend, I've used JavaScript, React, HTML, and CSS. I used Git and GitHub as my version control system. I chose Java, Spring, and Spring Boot because I'm currently an Application Developer Apprentice, and this is the language and the framework that we are requested to learn and get experience in. 

My directory is set up as a typical Spring Boot directory. The src folder contains 2 relevant folders: frontend and main. The frontend folder contains my React web application. For my web application, I've used a simple one page React application. I used React to gain experience with it and to simplify the development of my frontend. 

The main folder contains the Java/Spring Boot code and resources. In the resources folder, there is an application.properties file that can be used to configure properties such as the credentials for a database. To access the neccessary data, I chose to use a CSV file that another Swiftie created for other developers to use (linked above, credit to GitHub user shaynak). This file contains three columns, all being String data types: song titles, the associated album names, and the lyrics of each song. This CSV file is also in my main/resources folder.

The actual Java and Spring Boot code is in this filepath of the main folder: main/java/com/atwtmvtvftv/acronyms. This is the package name of the code. This /acronyms folder has the following subfolders: config, controller, entity, repository, service, and util. The core Spring Boot application file is in the /acronyms folder (not in a subfolder) titles AcronymsApplication.java. This file runs the application and uses the bean @SpringBootApplication.

The cors folder contains a file called CorsConfig.java. The purpose of this Java class is to allow my React frontend access to my Spring Boot backend.

The controller folder contains three controller classes. The HomeContorller class allows my frontend and backend to interact. The ApiController is not currently in use. At the start of this final project, I set up an ApiController class (and an ApiService class) to make calls to an external API of Taylor Swift's song data that another Swiftie developer created. While it did work well, I've since switched to the CSV file, because the API was missing about 50 songs. The AcronymController Java class is how the frontend makes requests to the backend. 

AcronymController uses @RestController to annotate that it is a Spring Boot controller. It also uses @RequestMapping("/lyric-finder") for mapping web requests. I've used constructor injection to inject the AcronymService class as a dependency to the controller class. The controller class has one handler method called findLyrics(). This method is annotated with @PostMapping because it maps HTTP post requests. The acronym that the user enters into the React frontend form is accepted as a parameter. The @PathVariable annotation is used to get the acronym string from the URL path. The handler method calls on a method in the AcronymService class which is also titled findLyrics().

The service class in Spring Boot is the class that handles logic. My AcronymService class has multiple methods. The first one, titled findLyrics() takes the acronym as a String parameter and returns a Java Set of lyric matches. A Set is a collection that contains no duplicate elements. It uses the CsvHandler object to read the data from the CSV file. It uses a for loop to look at the lyrics in each row of the CSV file. It calls on a method called findMatchInLyrics(); the parameters are the acronym string and the lyrics string. 

The findMatchInLyrics() method does the following: First it converts both parameters to lowercase. It uses Java's StringBuilder to create variables called wholeSongAcronym and foundLyrics. A for loop is used to go through each word in the given lyrics, which I've put into an array of Strings. This uses a helper method called firstLetter() that takes a word String as a parameter. The first letter of each word is appended to the wholeSongAcronym string. We now have the entire lyrics of a given song converted into one very long acronym. Java's String object has a method called indexOf that returns the index within a string of the first occurrence of the specified substring. If the substring isn't found, it returns -1. I've used this method to find out if the user's acronym (substring) is found in the long wholeSongAcronym string. If it is, the start index and end index are used to find the words represented from the words array. A for loop uses the start and end index to append the represented words to the foundLyrics StringBuilder with a space in between them, and this is then converted to a String and returned. If the substring is not found within the string, null is returned. 

The first method findLyrics() continues to go through each song's lyrics to check for matches in all of them. Every time a match is found, it's added to the Set of Strings, which is called matches. After going through every song, the Set of lyric matches is returned. If the Set is empty, a string "no matches found" is returned with a frowning emoticon. 

For the React frontend, the index.js file renders an AcronymSearch component. The AcronymSearch component sends a post request to the AcronymController class on the backend. This component returns what will be shown on the user's browser. It maps through the Set of lyric matches returned by the backend, and displays them as a list. 

I used CSS to style the frontend React app. I created a grid out of divs that's inspired by Taylor Swift's Eras Tour poster. It's a 3x4 grid that represents the colors of each of her eras. And I chose the colors of the app based on this aesthetic. 

I plan to add more features to my full stack application in future iterations. For now, the list of acronym matches do not give any further details, such as which song and album contain the found lyrics. For my first iteration, this was an intentional choice. There is a culture of Easter-egg hunting and sleuthing/investigating among the Swiftie fandom. So the choice to offer only the necessary information (just the lyric excerpt represented by the given acronym) gives the fan the opportunity to then guess which song the lyric is from. It essentially offers a hint and allows them to continue sleuthing on their own. However for future iterations, I would like to add the option to click on the lyric to reveal more information only if they choose to view it.  





Song and lyric data from: https://github.com/shaynak/taylor-swift-lyrics/