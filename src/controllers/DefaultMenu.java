package controllers;

import com.google.common.base.Optional;

import asg.cliche.Command;
import asg.cliche.Param;
import models.User;
import models.Movie;

public class DefaultMenu {

  private String name;
  private User user;
  private MovieRecAPI movApi;

  public DefaultMenu(MovieRecAPI movApi, User user) {
    this.movApi = movApi;
    this.setName(user.firstName);
    this.user = user;
  }
  @Command(description = "Get a Users detail")
  public void getUser(@Param(name = "email") String email) {
    User user = movApi.getUserByEmail(email);
    System.out.println(user);
  }
  @Command(description = "Add a movie")
  public void addmovie(@Param(name = "title") String title, @Param(name = "release") String release,
      @Param(name = "imdb") String imdb) {
    movApi.addMovie(user.id, title, release, imdb);
  }
  @Command(description = "Add a rating to movie")
  public void addrating(@Param(name = "movie-id") Long id, @Param(name = "rating") int rating,
      @Param(name = "Date") String date) {
    Optional<Movie> movies = Optional.fromNullable(movApi.getMovie(id));
    if (movies.isPresent()) {
      movApi.addrating(movies.get().id, rating, date);
    }
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
}