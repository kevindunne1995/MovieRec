package test.models;

import models.Movie;
import models.Rating;
import models.User;

public class Models
{
  public static User[] users =
  {
    new User ("marge", "simpson", "marge@simpson.com","secret","default"),
    new User ("lisa",  "simpson", "lisa@simpson.com", "secret","default"),
  };

  public static Movie[] movies =
  {
    new Movie ("JonWick","2014","imdb2014"),
    new Movie ("ForestGump","1994","imdb1995"),
  };

  public static Rating[]ratings =
  {
		  
  };
}