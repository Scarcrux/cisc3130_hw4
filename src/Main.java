/* Class: CISC 3130
 * Section: TY9
 * EmplId: 23975673
 * Name: Jonathan Scarpelli
 */

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Comparator;

/* Hash map for movies */
class Hash {
  private Map<String, Integer> map = new HashMap<String, Integer>();

  /* Calculates the frequency of elements */
  public void countFrequencies(List<String> list) {
    for (String i : list) {
      Integer j = map.get(i);
      map.put(i, (j == null) ? 1 : j + 1);
    }
  }

  // Converts hash map to list
  public void sortByValue() {
      List<Map.Entry<String, Integer> > list =
              new LinkedList<Map.Entry<String, Integer> >(map.entrySet());

      // Sorts the list by descending order
      Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
          public int compare(Map.Entry<String, Integer> o1,
                              Map.Entry<String, Integer> o2)
          {
              return (o2.getValue()).compareTo(o1.getValue());
          }
      });

      // Reverts list back to hash map format
      HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
      for (Map.Entry<String, Integer> aa : list) {
          temp.put(aa.getKey(), aa.getValue());
      }
      map = temp;
  }

  // Prints overall movie data
  public void printOverall() {
    List<String> artistList = new ArrayList<String>();;
    StringBuilder sb = new StringBuilder();
    sb.append("Genre");
    sb.append(',');
    sb.append("Count");
    sb.append('\n');

    // Displays the frequency of elements in the list
    for (Map.Entry<String, Integer> val : map.entrySet()) {
      artistList.add(val.getKey());
      sb.append(val.getKey());
      sb.append(',');
      sb.append(val.getValue());
      sb.append('\n');
    }

    // Writes the string to an external CSV
    File csvOutputFile = new File("../data/reports/Movies Released by Genre - Overall.csv");
    try (PrintWriter  writer= new PrintWriter(csvOutputFile)) {
      writer.write(sb.toString());
    } catch (FileNotFoundException error) {
      System.out.println(error);
    }
  }

  // Prints data for the past 5 years
  public void printPastFiveYears() {
    List<String> artistList = new ArrayList<String>();;
    StringBuilder sb = new StringBuilder();
    sb.append("Genre");
    sb.append(',');
    sb.append("Count");
    sb.append('\n');

    // Displays the frequency of elements in the list
    for (Map.Entry<String, Integer> val : map.entrySet()) {
      artistList.add(val.getKey());
      sb.append(val.getKey());
      sb.append(',');
      sb.append(val.getValue());
      sb.append('\n');
    }

    // Writes the string to an external CSV
    File csvOutputFile = new File("../data/reports/Movies Released by Genre - Past 5 Years.csv");
    try (PrintWriter  writer= new PrintWriter(csvOutputFile)) {
      writer.write(sb.toString());
    } catch (FileNotFoundException error) {
      System.out.println(error);
    }
  }

  /* Prints data for a given year */
  public void printYear(Integer year) {
    List<String> artistList = new ArrayList<String>();;
    StringBuilder sb = new StringBuilder();
    sb.append("Genre");
    sb.append(',');
    sb.append("Count");
    sb.append('\n');

    // Displays the frequency of elements in the list
    for (Map.Entry<String, Integer> val : map.entrySet()) {
      artistList.add(val.getKey());
      sb.append(val.getKey());
      sb.append(',');
      sb.append(val.getValue());
      sb.append('\n');
    }

    // Writes the string to an external CSV
    File csvOutputFile = new File("../data/reports/Movies Released by Genre - Annual/" + year + ".csv");
    try (PrintWriter  writer= new PrintWriter(csvOutputFile)) {
      writer.write(sb.toString());
    } catch (FileNotFoundException error) {
      System.out.println(error);
    }
  }
}

/* Test class */
public class Main {

  public static void main(String[] args) {
    // Creates an array that stores the names of files and directories
    String[] myFiles;

    // Creates a new File instance by converting the given pathname string
    // into an abstract pathname
    String path = "../data/";
    File f = new File(path);

      // Filters filename extensions to only include CSV
    FilenameFilter filter = new FilenameFilter() {
      @Override
      public boolean accept(File f, String name) {
          return name.endsWith(".csv");
      }
    };
    myFiles = f.list(filter);

    // Appends the path to the filename
    for (int i = 0; i < myFiles.length; i++) {
      myFiles[i] = path + myFiles[i];
    }

    // Variables for reading the source CSV movie data
    String csvFile = myFiles[0];
    BufferedReader br = null;
    String line = "";
    String cvsSplitBy = ",";
    List<String> values = new ArrayList<String>();
    List<String> valuesForPastFiveYears = new ArrayList<String>();

    // Creates ArrayLists corresponding to years from 1900-2020
    @SuppressWarnings("unchecked")
    ArrayList<String> Year[] = new ArrayList[2021];
    for( int i = 0; i < 2021; i++) {
        Year[i] = new ArrayList<String>();
    }

    try {
      br = new BufferedReader(new FileReader(csvFile));
      while ((line = br.readLine()) != null) {
        // Uses comma as a delimiter
        String[] movies = line.split(cvsSplitBy);
        String[] genres = movies[2].split("\\|");
        String temp = movies[1];

        // RegEx patterns for (year) and movie title up to (
        Pattern p = Pattern.compile("\\((\\d{4}?)\\)$");
        Matcher m = p.matcher(temp);

        while(m.find()) {
          if (movies[1].equals("title")) {
          } else if (movies[1] == null) {
          } else {
            // Populates arrays representing movie genre data for past 5 years
            int year = Integer.parseInt(m.group(0).replace("(", "").replace(")", ""));
            if (year == 2018 || year == 2017 || year == 2016 || year == 2015 || year == 2014) {
              for (String movie: genres) {
                valuesForPastFiveYears.add(movie);
              }
           }

            // Populates arrays representing annual movie genre data
            if (year < 2021) {
              for (String movie: genres) {
                Year[year].add(movie);
              }
            }

            // Populates array representing overall movie genre data
            for (String movie: genres) {
              values.add(movie);
            }
          }
        }

        // Generates reports
        Hash count = new Hash();
        count.countFrequencies(values);
        count.sortByValue();
        count.printOverall();

        Hash fiveYears = new Hash();
        fiveYears.countFrequencies(valuesForPastFiveYears);
        fiveYears.sortByValue();
        fiveYears.printPastFiveYears();

        // Annual data reports
        for (int i = 1900; i < 2021; i++) {
          Hash twenty = new Hash();
          twenty.countFrequencies(Year[i]);
          twenty.sortByValue();
          twenty.printYear(i);
        }
      }
    } catch (FileNotFoundException error) {
      error.printStackTrace();
    } catch (IOException error) {
      error.printStackTrace();
    } finally {
      if (br != null) {
        try {
          br.close();
        } catch (IOException error) {
          error.printStackTrace();
        }
      }
    }
  }
}
