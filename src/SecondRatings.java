
/**
 * Write a description of SecondRatings here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;

    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");

    }

    public SecondRatings(String moviefile, String ratingsfile) {
        // default constructor
        FirstRatings firstRatings = new FirstRatings();
        this.myMovies = firstRatings.loadMovies(moviefile);
        this.myRaters = firstRatings.loadRaters(ratingsfile);
    }

    public ArrayList<Movie> getMyMovies() {
        return myMovies;
    }

    public int getMovieSize() {
        return this.myMovies.size();
    }

    public int getRaterSize() {
        return this.myRaters.size();
    }

    private double getAverageByID(String id, int minimalRaters) {

        int sumRatings = 0;
        int sumNumbers = 0;
        for (Rater rater: this.myRaters) {
            for (String ratingItem: rater.getItemsRated()){
                if (ratingItem.equals(id)) {
                    sumNumbers++;
                    sumRatings += rater.getRating(id);
                }
            }
        }
        if (sumNumbers < minimalRaters) {
            return 0;
        } else {
            return (double) sumRatings / (double)sumNumbers;
        }
    }

    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> ratingArrayList = new ArrayList<>();

        HashSet<String> movieSet = new HashSet<>();
        for (Rater rater: this.myRaters) {
            for (String ratingItem: rater.getItemsRated()){
                if (! movieSet.contains(ratingItem)) {
                    double avgRating = getAverageByID(ratingItem, minimalRaters);
                    Rating rating = new Rating(ratingItem, avgRating);
                    ratingArrayList.add(rating);
                    movieSet.add(ratingItem);
                }
            }
        }
        return ratingArrayList;
    }

    public String getTitle(String id) {
        for (Movie movie: this.myMovies) {
            if (movie.getID().equals(id)) {
                return movie.getTitle();
            }
        }
        return "the ID was not found";
    }

    public String getID(String title) {
        for (Movie movie: this.myMovies) {
            if (movie.getTitle().equals(title)) {
                return movie.getID();
            }
        }
        return "NO SUCH TITLE.";
    }



    // named MovieRunnerAverage. In this class,
    // create a void method named printAverageRatings that has no parameters.
    // This method should:



}