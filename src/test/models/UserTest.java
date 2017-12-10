package test.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import test.models.Models;
import models.User;

public class UserTest
{
  User homer = new User ("homer", "simpson", "homer@simpson.com",  "secret", "default");

  @Test
  public void testCreate()
  {
    assertEquals ("homer",               homer.firstName);
    assertEquals ("simpson",             homer.lastName);
    assertEquals ("homer@simpson.com",   homer.email);   
    assertEquals ("secret",              homer.password); 
    assertEquals ("default",              homer.role); 
  }


  @Test
  public void testToString()
  {
    assertEquals ("User" + homer.id + ", homer, simpson, homer@simpson.com, secret, default", homer.toString());
  }
  
  @Test
  public void testEquals()
  {
    User homer2 = new User ("homer", "simpson", "homer@simpson.com",  "secret", "default"); 
    User bart   = new User ("bart", "simpson", "bartr@simpson.com",  "secret", "default"); 

    assertEquals(homer, homer);
    assertEquals(homer, homer2);
    assertNotEquals(homer, bart);
  } 
  
}