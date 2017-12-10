package controllers;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import com.google.common.base.Optional;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.util.HashMap;
import java.util.Map;
import models.Movie;
import models.Rating;
import models.User;
import utils.FileLogger;
import utils.Serializer;

public class MovieRecAPI
{
  private Map<Long,   User>   userIndex       = new HashMap<>();
  private Map<String, User>   emailIndex      = new HashMap<>();
  private Map<Long, Movie> moviesIndex = new HashMap<>();
      
  public MovieRecAPI()
  {
  }
  
  public Collection<User> getUsers ()
  {
    return userIndex.values();
  }
  
  public Collection<Movie> getMovies ()
  {
    return moviesIndex.values();
  }
  
  
  public  void deleteUsers() 
  {
    userIndex.clear();
    emailIndex.clear();
  }
  
  public User createUser(String firstName, String lastName, String email, String password, String role) 
  {
    User user = new User (firstName, lastName, email, password, role);
    userIndex.put(user.id, user);
    emailIndex.put(email, user);
    return user;
  }
  
public User getUserByEmail(String email) 
  {  
    return emailIndex.get(email);
  }

  public User getUser(Long id) 
  {
    return userIndex.get(id);
  }

  public void deleteUser(Long id) 
  {
    User user = userIndex.remove(id);
    emailIndex.remove(user.email);
  }
  
  public Movie createMovieListing(String title, String release, String imdb) 
  {
    Movie movies = new Movie (title, release, imdb);
   moviesIndex.put(movies.id, movies);
    return movies;
  }
  
  public Movie addMovie(Long id, String title, String release, String imdb)
  {
    Movie movie = null;
    Optional<User> user = Optional.fromNullable(userIndex.get(id));
    if (user.isPresent())
    {
    	movie = new Movie (title,release,imdb);
      user.get().movies.put(movie.id, movie);
      moviesIndex.put(movie.id, movie);
    }
    return movie;
  }
  
  public Movie getMovie (Long id)
  {
    return moviesIndex.get(id);
  }
  
  public Movie getMoviebytitle(String title) 
  {  
    return moviesIndex.get(title);
  }
  
  public Movie getMoviebyrelease(String release) 
  {  
    return moviesIndex.get(release);
  }

  
  public void addrating (Long id, int rating, String date)
  {
    Optional<Movie> movies = Optional.fromNullable(moviesIndex.get(id));
    if (movies.isPresent())
    {
    	movies.get().rate.add(new Rating(rating, date));
    }
  }
  
  private Serializer serializer;

  public MovieRecAPI(Serializer serializer)
  {
    this.serializer = serializer;
  }

  @SuppressWarnings("unchecked")
  public void load() throws Exception
  {
    serializer.read();
    moviesIndex = (Map<Long, Movie>) serializer.pop();
    emailIndex      = (Map<String, User>)   serializer.pop();
    userIndex       = (Map<Long, User>)     serializer.pop();
  }

  void store() throws Exception
  {
    serializer.push(userIndex);
    serializer.push(emailIndex);
    serializer.push(moviesIndex);
    serializer.write(); 
  }
  //...
  
  Optional<User> currentUser;
//simplified login method
 public boolean login(String email, String password) {
   Optional<User> user = Optional.fromNullable(emailIndex.get(email));
   if (user.isPresent() && user.get().password.equals(password)) {
     currentUser = user;
     FileLogger.getLogger().log(currentUser.get().email + " logged in...");
     return true;
   }
   return false;
 }
 
 // simplified and generalized version of my logout method
 public void logout() {
   Optional<User> user = currentUser;
   if (user.isPresent()) {
     FileLogger.getLogger().log(currentUser.get().firstName + " logged out...");
     currentUser = Optional.absent();
   }
 }
}