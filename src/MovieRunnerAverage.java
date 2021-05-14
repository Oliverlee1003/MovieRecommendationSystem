import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.*;


public class MovieRunnerAverage {

    public static void MovieRunnerAverage() {
        SecondRatings secondRatings = new SecondRatings("ratedmovies_short.csv", "ratings_short.csv");

        System.out.println("number of movies " + secondRatings.getMovieSize());
        System.out.println("number of raters " + secondRatings.getRaterSize());

        ArrayList<Rating> ratingArrayList = secondRatings.getAverageRatings(3);

        ArrayList<Rating> ratingArrayListFiltered = new ArrayList<>();
        for (Rating rating: ratingArrayList) {

            if (rating.getValue() > 0.0 ) {
                ratingArrayListFiltered.add(rating);
            }
        }
        Collections.sort(ratingArrayListFiltered);

        for (Rating rating: ratingArrayListFiltered) {
            System.out.print(secondRatings.getTitle(rating.getItem()));
            System.out.print("   ");
            System.out.println(rating.getValue());
        }

    }

    public static void getAverageRatingOneMovie() {
        SecondRatings secondRatings = new SecondRatings("ratedmoviesfull.csv", "ratings.csv");

        ArrayList<Rating> ratingArrayList = secondRatings.getAverageRatings(3);
        for (Rating rating: ratingArrayList) {
            if (rating.getItem().equals(secondRatings.getID("Vacation"))) System.out.println("avg score of The Godfather is " + rating.getValue());
        }
    }

//    public static int getHowManyMoviesHaveRatingsMoreThan(int howMany) {
//        FirstRatings firstRatings = new FirstRatings("ratedmoviesfull.csv", "ratings.csv");
//        ArrayList<Movie> arrayList = firstRatings.getMovieList();
//        int sum = 0;
//        for (Movie movie: arrayList) {
//            int num = firstRatings.getNumOfRatingsForAMovie(movie.getID());
//            if (num >= howMany) {
//                sum++;
//                double min = 9999;
//                String tempID = ""
//                for (PlainRater rater: firstRatings.getPlainRaterList()) {
//                    for (String ratingItem: rater.getItemsRated()){
//                        if (ratingItem.equals(movie.getID())) {
//                            if (rater.getRating(movie.getID()) < min) {
//                                tempID = ratingItem;
//                            }
//                            min = Math.min(min, rater.getRating(movie.getID()));
//                        }
//                    }
//                }
//                System.out.println(min);
//            }
//        }
//        return sum;
//    }
}


