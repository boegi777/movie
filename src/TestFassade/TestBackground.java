/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestFassade;

/**
 *
 * @author Mustermann
 */
public class TestBackground {
    static String filterAttribut = "";
    static String[][] FILM_LIST_TEST = {{"Terminator_Favorite", "01.01.1990", filterAttribut, "Arnold", "Schwarzenegger", "1"}, {"Eis am Stiel _ Favorite", "01.01.1970", filterAttribut, "Tommy", "Thompson", "2"}};
    static String[][] FILM_LIST_TEST2 = {{"TitleMan", "01", filterAttribut, "Arnold Bnold", "Schwarzenegger", "3"}, {"B", "02", filterAttribut, "Tommy", "Thompson", "4"}};
    static String[][] FILM_LIST_TEST3 = {{"DateMan", "01", filterAttribut, "Arnold", "Schwarzenegger", "5"}, {"B", "02", filterAttribut, "Tommy", "Thompson", "6"}};
    static String[][] FILM_LIST_TEST4 = {{"GenreMan", "01", filterAttribut, "Arnold", "Schwarzenegger", "7"}, {"B", "02", filterAttribut, "Tommy", "Thompson", "8"}};
    static String[][] FILM_LIST_TEST5 = {{"DirectorMan", "01", filterAttribut, "Arnold", "Schwarzenegger", "9"}, {"B", "02", filterAttribut, "Tommy", "Thompson", "10"}};
    static String[][] FILM_LIST_TEST6 = {{"ActorMan", "01", filterAttribut, "Arnold", "Schwarzenegger", "11"}, {"B", "02", filterAttribut, "Tommy", "Thompson", "12"}};
    static String[][] FILM_LIST_TEST_ALL = {{"Terminator_Favorite", "01.01.1990", filterAttribut, "Arnold", "Schwarzenegger", "1"}, {"Eis am Stiel _ Favorite", "01.01.1970", filterAttribut, "Tommy", "Thompson", "2"}, {"TitleMan", "01", filterAttribut, "Arnold Bnold", "Schwarzenegger", "3"}, {"B", "02", filterAttribut, "Tommy", "Thompson", "4"}, {"DateMan", "01", filterAttribut, "Arnold", "Schwarzenegger", "5"}, {"B", "02", filterAttribut, "Tommy", "Thompson", "6"}, {"GenreMan", "01", filterAttribut, "Arnold", "Schwarzenegger", "7"}, {"B", "02", filterAttribut, "Tommy", "Thompson", "8"}, {"DirectorMan", "01", filterAttribut, "Arnold", "Schwarzenegger", "9"}, {"B", "02", filterAttribut, "Tommy", "Thompson", "10"}, {"ActorMan", "01", filterAttribut, "Arnold", "Schwarzenegger", "11"}, {"B", "02", filterAttribut, "Tommy", "Thompson", "12"}};
    
    public static boolean logIn(String name, String password){
        if(name.equals("hans")&password.equals("meier")){
            return true;
        }else{
            return false;
        }
    }
    public static String[][] getList(String filter_Attribut, String filterValue){
        filterAttribut = filter_Attribut;
        String[][] filmList;
        switch(filterAttribut){
            case "0":
                filmList = FILM_LIST_TEST2;
                break;
            case "2":
                filmList = FILM_LIST_TEST3;
                break;
            case "3":
                filmList = FILM_LIST_TEST4;
                break;
            case "1":
                filmList = FILM_LIST_TEST5;
                break;
            case "4":
                filmList = FILM_LIST_TEST6;
                break;
            default:
                filmList = FILM_LIST_TEST;
        }
        return filmList;
    }
    public static String[][] getListOfFavorite(){
        return FILM_LIST_TEST;
    }
    public static String[][] getListAll(){
        return FILM_LIST_TEST_ALL;
    }
    public static String createMovie(String[] newMovieArray){
        return "F-A-S-S-A-D-E: createMovieUI AUSGELÖST: "+newMovieArray;
    }
    public static String saveFavorite(String id){
        return "F-A-S-S-A-D-E: saveFavorite AUSGELÖST.  "+id;
    }
}
