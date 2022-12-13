package day07;

public class App {
  private String name;
  private String category;
  private Float rating;

  public App(String name, String category, Float rating){
    this.name = name;
    this.category = category;
    this.rating = rating;
  }
// Getters and Setters
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getCategory() {
    return category;
  }
  public void setCategory(String category) {
    this.category = category;
  }
  public Float getRating() {
    return rating;
  }
  public void setRating(Float rating) {
    this.rating = rating;
  }

  
}
