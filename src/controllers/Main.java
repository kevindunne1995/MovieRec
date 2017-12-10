package controllers;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import com.google.common.base.Optional;

import utils.FileLogger;
import utils.Serializer;
import utils.XMLSerializer;
import asg.cliche.Command;
import asg.cliche.Param;
import asg.cliche.Shell;
import asg.cliche.ShellDependent;
import asg.cliche.ShellFactory;
import models.Movie;
import models.User;


public class Main implements ShellDependent {
	  private static final String ADMIN = "admin";
	  public MovieRecAPI movApi;
	  private Shell theShell;

	  public Main() throws Exception {
	    File datastore = new File("datastore.xml");
	    Serializer serializer = new XMLSerializer(datastore);
	    movApi = new MovieRecAPI(serializer);
	    if (datastore.isFile()) {
	    	movApi.load();
	    }
	  }

	  public void cliSetShell(Shell theShell) {
	    this.theShell = theShell;
	  }

	  @Command(description = "Log in")
	  public void logIn(@Param(name = "user name") String userName, @Param(name = "password") String pass)
	      throws IOException {

	    if (movApi.login(userName, pass) && movApi.currentUser.isPresent()) {
	      User user = movApi.currentUser.get();
	      System.out.println("You are logged in as " + user.email);
	      if (user.role!=null && user.role.equals(ADMIN)) {
	        AdminMenu adminMenu = new AdminMenu(movApi, user.firstName);
	        ShellFactory.createSubshell(user.firstName, theShell, "Admin", adminMenu).commandLoop();
	      } else {
	        DefaultMenu defaultMenu = new DefaultMenu(movApi, user);
	        ShellFactory.createSubshell(user.firstName, theShell, "Default", defaultMenu).commandLoop();
	      }
	    } else
	      System.out.println("Unknown username/password.");
	  }

	  public static void main(String[] args) throws Exception {
	    Main main = new Main();
	    Shell shell = ShellFactory.createConsoleShell("pm", "Welcome to movie listings?list - ?help for instructions",
	        main);
	    shell.commandLoop();
	    main.movApi.store();
	  }
	}