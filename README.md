data
====

This project uses data about [baby names from the US Social Security Administration](https://www.ssa.gov/oact/babynames/limits.html) to answer specific questions. 


Name: Connor Penny

### Timeline

Start Date: 1/12/2020

Finish Date: 1/26/2020

Hours Spent: ~40

### Resources Used
https://www.geeksforgeeks.org/different-ways-reading-text-file-java/

https://stackoverflow.com/questions/30564462/read-data-from-a-text-file-and-create-an-object

https://stackoverflow.com/questions/30564462/read-data-from-a-text-file-and-create-an-object

https://stackoverflow.com/questions/11871520/how-can-i-read-input-from-the-console-using-the-scanner-class-in-java

https://stackoverflow.com/questions/10960213/how-can-i-read-comma-separated-values-from-a-text-file-in-java

https://www.geeksforgeeks.org/iterate-map-java/

https://beginnersbook.com/2014/08/convert-hashset-to-a-list-arraylist/

https://stackoverflow.com/questions/1330288/how-to-make-a-separated-copy-of-an-arraylist

https://docs.oracle.com/javase/tutorial/networking/urls/readingURL.html

https://www.tutorialspoint.com/how-to-convert-inputstream-object-to-a-string-in-java

https://www.geeksforgeeks.org/check-if-url-is-valid-or-not-in-java/

Also referenced a lot of Java API documentation on the internet to utilize methods for different classes/packages
### Running the Program

Main class: BabyData

Test classes: BabyDataTest, EmptyDataSetTest, InvalidNameTest, InvalidRangeOfYearsTest

Errors Handled: See "Known Bugs"

Data files needed: NA

Key/Mouse inputs: NA

Cheat keys: NA

Known Bugs:

I could not figure out how to handle errors without simply printing
an error message and exiting the program. All error cases except for
invalid gender input are handled this way. With gender input, I was
able return null for invalid gender inputs for all methods that answered a question and thus
took a gender input. This also means I was unable to write correct JUnit tests for most error
cases because they simply exit the program without returning any values. I still wrote JUnit
tests for these situations, and their functionality can be verified by running the test and seeing
a relevant error message printed in the console.

When running LargestChangeInRankInRangeOfYears on the complete dataset, the code will always
print an error statement and exit the program. This is because startYear will almost always
contain a name that the endYear doesn't contain, and when the method tries to access the rank
of the name that doesn't exist in the endYear, it prints an error message and exits the code.

Extra credit:

None


### Notes/Assumptions
Changing Data Source:

You can change the data source by creating new BabyData objects and specifying the new 
path for the data source as a parameter. You must also pass whether the file is a Directory
(true) or a URL (false) as a parameter.

Data Sources (At Least 3):

I assumed that my test_data folders were adequate as one of the  other data sources that could be used.
My code can also process URL's.

Basic Implementation:

First, I assumed that ties in rankings of name frequency were
broken by alphabetical ranking.  This significantly simplified the code
that had to generate rankings for names, as all the names that are
tied in frequency for a given year are already in alphabetical order.
For the third question, I assumed again that any ties in names that were
ranked number one most frequently for a range of years would be broken by
alphabetical rankings. Thus, for the third question, my program returns only
the first name lexicographically, even though there may be multiple names 
tied for most number one rankings. For the fourth question, I assumed that
"most popular letter that names started with" meant the letter with the most
aggregate instances of people beginning with that letter.

Complete Implementation:

For question 2, I assumed that "first" year was not necessarily the earliest year and "last"
year was not necessarily the latest year. This made it possible to find the difference in rankings
from an earlier year to a later year or vice versa (these differences would be negations of each other).
For any question involving an average ranking, I assumed that this average would be a double.
For question 8 and 9, I broke ties between names that held a rank most often alphabetically, and
then by female name first.

Tests:

Assumed that each assertEquals represents a test, so 3 assertEquals statements for a given method
represents the three necessary tests for that method (method answers a question).

### Impressions

This assignment was extremely time consuming because I simply do not have a good
handle on the design principles necessary to write a program from scratch. The
guidelines gave me some idea of where to go with my design, and I felt
that mine was pretty solid after a lot of time spent thinking and refactoring.
None of my methods were extremely long, I was using multiple classes and helper methods
within each, and I didn't have much duplicate code. But when it came time to handle errors 
in the data and include a URL as a data source, I felt like my code lost a bit of its quality.
I simply did not have the foresight or design skills to design my code in such a way that 
could smoothly adapt to these new specifications. However, I feel pretty proud of my program
overall, as I think I followed the design principles specified pretty well.

