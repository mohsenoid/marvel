package com.mirhoseini.marvel;

import com.mirhoseini.marvel.character.search.CharacterSearch;

import java.util.Scanner;

public class MainClass {

    public static void main(String[] args) {
        ConsoleComponent component = createComponent();
        Scanner scanner = new Scanner(System.in);
        CharacterSearch characterSearch = new CharacterSearch(component);

        while (true) {
            System.out.println("Please enter character name or q to exit:");
            String query = scanner.nextLine();
            if (query.equalsIgnoreCase("q"))
                break;

            characterSearch.doSearch(query);
        }
    }

    private static ConsoleComponent createComponent() {
        return DaggerConsoleComponent.builder()
                .build();
    }

}
