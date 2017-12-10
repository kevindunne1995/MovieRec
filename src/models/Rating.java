package models;

import static com.google.common.base.MoreObjects.toStringHelper;

import com.google.common.base.Objects;

public class Rating
{
  static Long   counter = 0l;
  
  public Long  id;
  public int rating;
  public String date;
 
  
  public Rating()
  {
  }
  
  public Rating (int rating, String date)
  {
    this.id        = counter++;
    this.rating  = rating;
    this.date = date;
    
  }
  
  @Override
  public String toString()
  {
    return toStringHelper(this).addValue(id)
                               .addValue(rating)
                               .addValue(date)           
                               .toString();
  }
  
  @Override  
  public int hashCode()  
  {  
     return Objects.hashCode(this.id, this.rating, this.date);  
  } 
  
  @Override
  public boolean equals(final Object obj)
  {
    if (obj instanceof Rating)
    {
      final Rating other = (Rating) obj;
      return Objects.equal(rating, other.rating) 
          && Objects.equal(date, other.date);
    }
    else
    {
      return false;
    }
  }
}