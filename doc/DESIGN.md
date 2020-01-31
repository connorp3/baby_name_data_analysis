# Planning Design

###Contributor

Connor Penny: Designed program, coded classes, implemented tests, commented all methods and classes

###High Level Design & Design Goals

The goal of this design was first and foremost to allow a user to manipulate baby name data (Name, Gender
Number of babies born with that name) for a certain number of years. It was designed with 3 main classes
for this manipulation: BabyEntry, BabyFile, and BabyData. BabyEntry represents a single entry within
a file of baby name data for a specific year with the attributes Name, Gender, and Number of babies
born with that name. BabyFile holds all the BabyEntry objects for a specific year, so it represents
a year's worth of baby data. BabyData holds all the BabyFile objects for a given dataset, so
 it represents the dataset as a whole over the entire range of years provided.
 
 
This design was meant to break data manipulation down into more manageable chunks. For example,
any task that was specific to a certain year could be implemented as a method in BabyFile (finding
a name/gender pair's rank for a certain year). And because BabyFile objects consisted of a list of 
BabyEntry objects, specific data about a baby name could be easily accessed using BabyEntry. Additionally,
any task that was required multiple years of data or a range of years of data could be implemented as a 
 method in BabyData. These methods in BabyData would then loop through BabyFile objects and use the
 methods from BabyFile to manipulate relevant data within that year.

This design keeps methods relatively short (max 30 lines), and helps with code readability, as all the
methods are named after their functionality in regards how they manipulate data. Additionally, this
design is meant to allow the user to implement new data manipulation tasks in smaller steps, first by using already
existing or implementing new BabyFile methods to manipulate data within a year. Then, by using these
BabyFile methods to implement that task at hand over a range of years. I found that as I implemented
more and more questions from the project specifications, I built up more and more methods in BabyFile
that made implementation of the entire question easier. Unfortunately, my design was not robust in implementing
new types of data sources (URLs, ZIP files, etc) or for handling errors (as far as I could tell). 

###Assumptions that Affected Design

#####Data Sources (At Least 3):

I assumed that my test_data folders were adequate as one of the  other data sources that could be used.
My code can also process URL's.

This was an assumption that simply made it easier to keep track of the datasets I was using, as they
were all located in one folder, and it required less changing of the path to switch between datasets.

#####Basic Implementation:

First, I assumed that ties in rankings of name frequency were
broken by alphabetical ranking. For the third question, I assumed again that any ties in names that were
ranked number one most frequently for a range of years would be broken by
alphabetical rankings, and then by female gender first. Thus, for the third question, 
my program returns only
the first name lexicographically, even though there may be multiple names 
tied for most number one rankings. For the fourth question, I assumed that
"most popular letter that names started with" meant the letter with the most
aggregate instances of people beginning with that letter.

These significantly simplified the code that had to generate rankings for names, as all the names 
that are tied in frequency for a given year are already in alphabetical order in the data set.
It also made it so that I did not have to return multiple names and frequencies for names that were tied
in frequency at a certain rank.

#####Complete Implementation:

For question 2, I assumed that "first" year was not necessarily the earliest year and "last"
year was not necessarily the latest year. This made it possible to find the difference in rankings
from an earlier year to a later year or vice versa (these differences would be negations of each other).

This assumption simply added the functionality of being able to see change in ranking both up and down
in years depending on the order start and end year were entered.

For any question involving an average ranking, I assumed that this average would be a double.

I feel that this assumption was reasonable, but it didn't really simplify my code.

#####Tests:

Assumed that each assertEquals represents a test, so 3 assertEquals statements for a given method
represents the three necessary tests for that method (method answers a question).

This simplified test writing significantly, as a 3 different test methods did not have to be written for
each question.

###Adding New Features in Project:

I covered this a bit in "Design Goals", but I'll describe specifically how to add number 10 (the extra credit
feature) to the project. 

First, a method in BabyFile would need to be implemented that, given a gender, loops over all the BabyEntry objects in a given
BabyFile object, generating a HashMap of prefixes with their frequencies of names that have this frequency.
It would utilize GetBabyEntriesForGender to first separate the BabyFile into the relevant gender.
It would then require a nested for loop that adds a name to the HashMap, then loops over all names below it,
checking if the latter name contains the former name. The inner loop can then be refactored and extracted as
a helper method to simplify code. This will also require logic that, after the HashMap is generated, removes 
the entries with a value of 0 (as these are not prefixes). This can also be extracted as a helper method 
to simplify code.

Once these methods in BabyFile have been generated, a method in BabyData can be created that loops over
all BabyFiles in the specified range of years and compiles all the HashMaps into one. A helper method would
then be necessary that loops over all the hashmap keys (prefixes) and finds the one with the highest value,
breaking ties if necessary. I believe this last step has already been coded in a method in BabyData, but
this method may be too specific to a different task and may need to be refactored before it can be used.
