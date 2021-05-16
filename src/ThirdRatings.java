import java.util.ArrayList;
import java.util.HashSet;
import java.util.*;

public class ThirdRatings {
    // private ArrayList<Movie> myMovies;
    private ArrayList<IRater> myEfficientRaters;

    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }

    public ThirdRatings(String ratingsfile) {
        // default constructor
        FirstRatings firstRatings = new FirstRatings();
        // this.myMovies = firstRatings.loadMovies(moviefile);
        this.myEfficientRaters = firstRatings.loadRaters(ratingsfile);
    }

    public int getRaterSize() {
        return this.myEfficientRaters.size();
    }

    private double getAverageByID(String id, int minimalRaters) {

        int sumRatings = 0;
        int sumNumbers = 0;
        for (IRater plainRater : this.myEfficientRaters) {
            for (String ratingItem: plainRater.getItemsRated()){
                if (ratingItem.equals(id)) {
                    sumNumbers++;
                    sumRatings += plainRater.getRating(id);
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
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> ratingArrayList = new ArrayList<>();
        Set<String> set = new HashSet<>();

        for (String id: movies) {
            double avgRating = getAverageByID(id, minimalRaters);
            if(avgRating > 0.0) {
                set.add(id);
                Rating rating = new Rating(id, avgRating);
                ratingArrayList.add(rating);
            }
        }
        System.out.println("set.size() " + set.size());
        System.out.println("ratingArrayList " + ratingArrayList.size());
        return ratingArrayList;
    }

    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        ArrayList<Rating> ratingArrayList = new ArrayList<>();
        Set<String> set = new HashSet<>();

        for (String id: movies) {
            double avgRating = getAverageByID(id, minimalRaters);
            if(avgRating > 0.0) {
                set.add(id);
                Rating rating = new Rating(id, avgRating);
                ratingArrayList.add(rating);
            }
        }
        System.out.println("set.size() " + set.size());
        System.out.println("ratingArrayList " + ratingArrayList.size());
        return ratingArrayList;
    }

}
