package day07;
// 2nd way to do - use App class

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main2 {
  public static void main(String[] args) {
    String fileName = args[0];
    String category = args[1];

    List<App> appList = new LinkedList<App>();
    List<Float> ratingList = new LinkedList<Float>();
    try {
      File f = new File(fileName);
      BufferedReader bfr = new BufferedReader(new FileReader(f));
      String line = bfr.readLine(); // Read Headers
      while((line=bfr.readLine())!=null){
        String[] appInfo = line.split(",");
        // if(!appInfo[1].matches("^[A-Z]+(?:_[A-Z]+)*$") || appInfo[2].equalsIgnoreCase("sticker")|| Float.isNaN(Float.parseFloat(appInfo[2]))){
        //   continue;
        // }
        if(appInfo.length>14)continue;
        else{
          String name = appInfo[0];
          String cat = appInfo[1];
          Float rating = Float.parseFloat(appInfo[2]);
          
          App app = new App(name, category, rating);
          appList.add(app);
        }
      }
      //Get Ratings for desired category
      List<App> listByCategory;
      listByCategory = appList.stream().filter(app -> app.getCategory().equalsIgnoreCase(category)).filter(app->!Float.isNaN(app.getRating())).toList();
      ratingList = listByCategory.stream().map((i)->i.getRating()).toList();
      System.out.println(ratingList);

      Optional<Float> total = ratingList.stream().collect(Collectors.reducing((totals,i)->totals+i));
      if(total.isPresent()){
        Float averageRating = total.get()/ratingList.size();
        System.out.println(averageRating);
      }

      Map<String,List<App>> groupByCategory = appList.stream().collect(Collectors.groupingBy(app->app.getCategory()));
      
      for(String c: groupByCategory.keySet()){
        List<App> apps = groupByCategory.get(c);
        System.out.printf("Category: %s - Size = %d\n",c,apps.size());
      }
      


    } catch (Exception e) {
      e.printStackTrace();
    }
    
  }
}
