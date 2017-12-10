package test.models;

import static org.junit.Assert.*;
import org.junit.Test;

import models.Movie;

public class MovieTest
{ 
  Movie test = new Movie ("JonWick","2014","imdb202");

  @Test
  public void testCreate()
  {
    assertEquals ("JonWick",          test.title);
    assertEquals ("2014",        test.release);
    assertEquals ("imdb202",   test.imdb);    
  }

  @Test
  public void testToString()
  {
    assertEquals ("Movie{" + test.id + ", JonWick, 2014, imdb202}", test.toString());
  }
}