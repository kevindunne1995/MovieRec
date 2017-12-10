package controllers;

import java.util.Collection;

import com.google.common.base.Optional;

import asg.cliche.Command;
import asg.cliche.Param;
import models.Movie;
import models.User;

public class AdminMenu {

  private String name;
  private MovieRecAPI movApi;

  public AdminMenu(MovieRecAPI movApi, String userName) {

    this.movApi = movApi;
    this.setName(userName);
  }

  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  
  @Command(description = "Create a new User")
  public void createUser(@Param(name = "first name") String firstName, @Param(name = "last name") String lastName,
      @Param(name = "email") String email, @Param(name = "password") String password, @Param(name = "role") String role) {

	  movApi.createUser(firstName, lastName, email, password, role);
  }
  
  @Command(description = "Get all users details")
  public void getUsers() {

    Collection<User> users = movApi.getUsers();
    System.out.println(users);
  }
  
  @Command(description = "Get all users details")
  public void getmovies() {

    Collection<Movie> movies = movApi.getMovies();
    System.out.println(movies);
  }
  
  @Command(description = "Get a Users detail")
  public void getUser(@Param(name = "email") String email) {

    User user = movApi.getUserByEmail(email);
    System.out.println(user);

  }
  
  @Command(description = "Get a Movies detail by title")
  public void searchmovie(@Param(name = "title") String title) {

    Movie movies = movApi.getMoviebytitle(title);
    System.out.println(movies);

  }
  
  @Command(description = "Get a Movies detail by release")
  public void searchmovierelease(@Param(name = "release") String release) {

    Movie movies = movApi.getMoviebyrelease(release);
    System.out.println(movies);

  }
  
  @Command(description = "Delete a User")
  public void deleteUser(@Param(name = "email") String email) {

    Optional<User> user = Optional.fromNullable(movApi.getUserByEmail(email));
    if (user.isPresent()) {
      movApi.deleteUser(user.get().id);
    }
  }
  
  @Command(description = "Add A Movie")
  public void addMovie(@Param(name = "title") String title, @Param(name = "release") String release,
      @Param(name = "imdb") String imdb) {
	  movApi.createMovieListing(title, release, imdb);
  }
}