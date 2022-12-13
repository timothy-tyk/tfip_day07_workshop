package day07;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {
  //Calculate Avg Rating for any particular category
  public static void main(String[] args) {
    String fileName = args[0];
    String category = args[1];

    List<Float> ratingList = new LinkedList<Float>();

    try {
      File f = new File(fileName);
      BufferedReader bfr = new BufferedReader(new FileReader(f));
      bfr.readLine();// Read past header line
      //Initialize List of List of Strings to store data
      List<String[]> appInfo = new ArrayList<String[]>();
      String line;
      while((line = bfr.readLine())!=null){
        appInfo.add(line.split(","));
      }
      //Filter to only category type, filter out the NaN ratings
      List<String[]> categoryList = appInfo.stream().filter(i -> i[1].equalsIgnoreCase(category)).filter(i->!Float.isNaN(Float.parseFloat(i[2]))).toList();
      List<Float> ratings = categoryList.stream().map(i -> Float.parseFloat(i[2])).toList();
      System.out.println(ratings.toString());
     
      //Reduce to get average rating`
      Optional<Float> totalRating;
      totalRating = ratings.stream().collect(Collectors.reducing((acc,rating) -> acc + rating ));
      if(totalRating.isPresent()){
        Float averageRating = totalRating.get()/ratings.size();
        System.out.println(averageRating);
      }
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
