# cisc3130_hw4

Clone this repository and run Main.java.

Learning Objectives

- Continue working with input files
- Storing data as key-value pairs using the HashMap data structure
- Contrast the List and Map Interfaces in Java

Problem Description

There is a business owner who wants to know which movie genres are available out there, in order to know which categories of movies to have at his online storefront. In order to do so he needs to know more about movies produced and has a copy of data from the MovieLens project.

The data comes from MovieLens https://grouplens.org/datasets/movielens/ - any of the data samples would be fine however for the purposes of prototyping it would make the most sense to use the latest dataset (small, 1MB zip file) found at this permalink https://grouplens.org/datasets/movielens/latest/

We learned about the dictionary ADT that allows using key value pairs to store information for insertion and search.

How many movies are classified under each genre? How many for the whole data set versus the most recent 5 years?

Pick the correct file from the ones available in the zip file to answer this question. Parse the file so that you can extract the genre names, and then be able to quickly see the genres and how many times it was mentioned in the file.

Ideally, it would be great to show the report sorted by descending order, largest values first. It would also be great to show the average number of movies per genre and split the data into more than average and less than average.

Parse out the release year for each movie. How many movies of each genre came out each year?

Pick the correct file from the ones available to answer this question. Parse the file so that you can extract the release year, it will be the last substring of the format (YYYY) of the movie title.
