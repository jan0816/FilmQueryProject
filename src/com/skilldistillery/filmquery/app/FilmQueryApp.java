package com.skilldistillery.filmquery.app;

import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.*;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
		// app.test();
		app.launch();
		app.displayMenu();
	}

	private void test() {
		Film film = db.findFilmById(1);
		Actor actor = db.findActorById(1);
		List<Film> films = db.findFilmByKeyword("thrilling");
		for (Film film2 : films) {
			System.out.println(film2);

		}
//		List<Actor> actors = db.findActorsByFilmId(1);
//		
//		for (Actor actor2 : actors) {
//			System.out.println(actor2);
//		}
		if (film != null) {
			System.out.println(film);
		} else {
			System.err.println();
		}
		if (actor != null) {
			System.out.println(actor);
		} else {
			System.err.println();
		}
	}

	private void launch() {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) {
		boolean quit = true;
		System.out.println("**********************************");
		System.out.println("*                                *");
		System.out.println("*  Welcome to the film database. *");
		System.out.println("*                                *");
		System.out.println("**********************************");
		while (quit) {
			displayMenu();

			int userChoice = input.nextInt();
			switch (userChoice) {
			case 1:
				chooseFilmByAnId(input);
				break;
			case 2:
				chooseFilmByKeyword(input);
				break;
			case 3:
				System.out.println("Have a great day. Thank you for inquiring about the films.");
				quit = false;
				break;
			}

		}

	}

	public void displayMenu() {
		System.out.println("\nChoose a Film Query menu option (1-3): ");
		System.out.println("--------------------------------------");
		System.out.println("1. Look up a film by an ID");
		System.out.println("2. Look up a film by search KEYWORD");
		System.out.println("3. EXIT the application");

	}

	public void chooseFilmByAnId(Scanner input) {
		System.out.println("Enter the film's id: ");

		try {
			int filmId = input.nextInt();
			Film selectFilm = db.findFilmById(filmId);
			if (selectFilm != null) {
				System.out.println(selectFilm);
			} else {
				System.err.print("Sorry. That film ID does not exist. ");
			}
		} catch (Exception e) {
			System.err.println("Sorry. You have inputted an unrecognizable selection.");
			input.next();
			startUserInterface(input);
		}

	}

	public void chooseFilmByKeyword(Scanner input) {
		System.out.println("Enter a keyword to search films by: ");

		try {
			String userInputKeyword = input.next();
			List<Film> listOfFilms = db.findFilmByKeyword(userInputKeyword);
			if (listOfFilms.size() > 0) {
				for (Film film3 : listOfFilms) {
					System.out.println(film3);

				}
			} else {
				System.err.println("Sorry. There is no film that exists in the database with that keyword.");
			}
		} catch (Exception e) {
			System.err.println("Sorry. You have inputted an unrecognizable selection.");
			input.next();
			startUserInterface(input);
		}
	}
}
