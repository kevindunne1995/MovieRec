package models;

import static com.google.common.base.MoreObjects.toStringHelper;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Objects;

public class Movie 
{ 
  static Long   counter = 0l;
  
  public Long   id;
  
  public String title;
  public String release;
  public String imdb;
  
  public List<Rating> rate = new ArrayList<>();
 
  public Movie()
  {
  }
  
  public Movie(String title, String release, String imdb)
  {
    this.id        = counter++;
    this.title      = title;
    this.release  = release;
    this.imdb  = imdb;
  }
  
  @Override
  public String toString()
  {
    return toStringHelper(this).addValue(id)
                               .addValue(title)
                               .addValue(release)
                               .addValue(imdb)
                               .addValue(rate)
                               .toString();
  }
  
  @Override  
  public int hashCode()  
  {  
     return Objects.hashCode(this.id, this.title, this.release, this.imdb);  
  } 
  
  @Override
  public boolean equals(final Object obj)
  {
    if (obj instanceof Movie)
    {
      final Movie other = (Movie) obj;
      return Objects.equal(title, other.title) 
          && Objects.equal(release,  other.release)
          && Objects.equal(imdb,  other.imdb)
          && Objects.equal(rate,     other.rate);    
    }
    else
    {
      return false;
    }
  }
}