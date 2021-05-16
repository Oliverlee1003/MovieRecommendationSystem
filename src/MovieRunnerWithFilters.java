import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerWithFilters {
    public static void printAverageRatings() {
        String ratingsFileName = "ratings.csv";
        String movieFileName = "ratedmoviesfull.csv";
        ThirdRatings thirdRatings = new ThirdRatings(ratingsFileName);

        System.out.println("number of raters " + thirdRatings.getRaterSize());
        MovieDatabase.initialize(movieFileName);
        System.out.println("number of movies " + MovieDatabase.size());

        ArrayList<Rating> ratingArrayList = thirdRatings.getAverageRatings(35);

        ArrayList<Rating> ratingArrayListFiltered = new ArrayList<>();
        for (Rating rating: ratingArrayList) {

            if (rating.getValue() > 0.0 ) {
                ratingArrayListFiltered.add(rating);
            }
        }
        Collections.sort(ratingArrayListFiltered);

        for (Rating rating: ratingArrayListFiltered) {
            System.out.println(MovieDatabase.getTitle(rating.getItem()));
            System.out.print("   ");
            System.out.println(rating.getValue());
        }

    }

    public static void printAverageRatingsByYear() {
        Filter yearAfterFilter = new YearAfterFilter(2000);

        String ratingsFileName = "ratings.csv";
        String movieFileName = "ratedmoviesfull.csv";
        ThirdRatings thirdRatings = new ThirdRatings(ratingsFileName);

        System.out.println("number of raters " + thirdRatings.getRaterSize());
        MovieDatabase.initialize(movieFileName);
        System.out.println("number of movies " + MovieDatabase.size());

        ArrayList<Rating> ratingArrayList = thirdRatings.getAverageRatingsByFilter(20, yearAfterFilter);

        ArrayList<Rating> ratingArrayListFiltered = new ArrayList<>();
        for (Rating rating: ratingArrayList) {

            if (rating.getValue() > 0.0 ) {
                ratingArrayListFiltered.add(rating);
            }
        }
        Collections.sort(ratingArrayListFiltered);

        for (Rating rating: ratingArrayListFiltered) {
            System.out.println(MovieDatabase.getTitle(rating.getItem()));
            System.out.print("   ");
            System.out.println(rating.getValue());
        }
    }

    public static void printAverageRatingsByGenre() {
        Filter genreFilter = new GenreFilter("Comedy");

        String ratingsFileName = "ratings.csv";
        String movieFileName = "ratedmoviesfull.csv";
        ThirdRatings thirdRatings = new ThirdRatings(ratingsFileName);

        System.out.println("number of raters " + thirdRatings.getRaterSize());
        MovieDatabase.initialize(movieFileName);
        System.out.println("number of movies " + MovieDatabase.size());

        ArrayList<Rating> ratingArrayList = thirdRatings.getAverageRatingsByFilter(20, genreFilter);

        ArrayList<Rating> ratingArrayListFiltered = new ArrayList<>();
        for (Rating rating: ratingArrayList) {

            if (rating.getValue() > 0.0 ) {
                ratingArrayListFiltered.add(rating);
            }
        }
        Collections.sort(ratingArrayListFiltered);

        for (Rating rating: ratingArrayListFiltered) {
            System.out.println(MovieDatabase.getTitle(rating.getItem()));
            System.out.print("   ");
            System.out.println(rating.getValue());
        }
    }

    public static void printAverageRatingsByMinutes() {
        Filter minutesFilter = new MinutesFilter(105, 135);

        String ratingsFileName = "ratings.csv";
        String movieFileName = "ratedmoviesfull.csv";
        ThirdRatings thirdRatings = new ThirdRatings(ratingsFileName);

        System.out.println("number of raters " + thirdRatings.getRaterSize());
        MovieDatabase.initialize(movieFileName);
        System.out.println("number of movies " + MovieDatabase.size());

        ArrayList<Rating> ratingArrayList = thirdRatings.getAverageRatingsByFilter(5, minutesFilter);

        ArrayList<Rating> ratingArrayListFiltered = new ArrayList<>();
        for (Rating rating: ratingArrayList) {

            if (rating.getValue() > 0.0 ) {
                ratingArrayListFiltered.add(rating);
            }
        }
        Collections.sort(ratingArrayListFiltered);

        for (Rating rating: ratingArrayListFiltered) {
            System.out.println(MovieDatabase.getTitle(rating.getItem()));
            System.out.print("   ");
            System.out.println(rating.getValue());
        }
    }

    public static void printAverageRatingsByDirectors() {
        Filter directorsFilter = new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");

        String ratingsFileName = "ratings.csv";
        String movieFileName = "ratedmoviesfull.csv";
        ThirdRatings thirdRatings = new ThirdRatings(ratingsFileName);

        System.out.println("number of raters " + thirdRatings.getRaterSize());
        MovieDatabase.initialize(movieFileName);
        System.out.println("number of movies " + MovieDatabase.size());

        ArrayList<Rating> ratingArrayList = thirdRatings.getAverageRatingsByFilter(4, directorsFilter);

        ArrayList<Rating> ratingArrayListFiltered = new ArrayList<>();
        for (Rating rating: ratingArrayList) {

            if (rating.getValue() > 0.0 ) {
                ratingArrayListFiltered.add(rating);
            }
        }
        Collections.sort(ratingArrayListFiltered);

        for (Rating rating: ratingArrayListFiltered) {
            System.out.println(MovieDatabase.getTitle(rating.getItem()));
            System.out.print("   ");
            System.out.println(rating.getValue());
        }
    }

    public static void printAverageRatingsByYearAfterAndGenre() {
        Filter yearAfterFilter = new YearAfterFilter(1990);
        Filter genreFilter = new GenreFilter("Drama");
        AllFilters allFilter = new AllFilters();
        allFilter.addFilter(yearAfterFilter);
        allFilter.addFilter(genreFilter);

        String ratingsFileName = "ratings.csv";
        String movieFileName = "ratedmoviesfull.csv";
        ThirdRatings thirdRatings = new ThirdRatings(ratingsFileName);

        System.out.println("number of raters " + thirdRatings.getRaterSize());
        MovieDatabase.initialize(movieFileName);
        System.out.println("number of movies " + MovieDatabase.size());

        ArrayList<Rating> ratingArrayList = thirdRatings.getAverageRatingsByFilter(8, allFilter);

        ArrayList<Rating> ratingArrayListFiltered = new ArrayList<>();
        for (Rating rating: ratingArrayList) {

            if (rating.getValue() > 0.0 ) {
                ratingArrayListFiltered.add(rating);
            }
        }
        Collections.sort(ratingArrayListFiltered);

        for (Rating rating: ratingArrayListFiltered) {
            System.out.println(MovieDatabase.getTitle(rating.getItem()));
            System.out.print("   ");
            System.out.println(rating.getValue());
        }
    }

    public static void printAverageRatingsByDirectorsAndMinutes() {
        Filter minutesFilter = new MinutesFilter(90, 180);
        Filter directorsFilter = new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack");
        AllFilters allFilter = new AllFilters();
        allFilter.addFilter(minutesFilter);
        allFilter.addFilter(directorsFilter);

        String ratingsFileName = "ratings.csv";
        String movieFileName = "ratedmoviesfull.csv";
        ThirdRatings thirdRatings = new ThirdRatings(ratingsFileName);

        System.out.println("number of raters " + thirdRatings.getRaterSize());
        MovieDatabase.initialize(movieFileName);
        System.out.println("number of movies " + MovieDatabase.size());

        ArrayList<Rating> ratingArrayList = thirdRatings.getAverageRatingsByFilter(3, allFilter);

        ArrayList<Rating> ratingArrayListFiltered = new ArrayList<>();
        for (Rating rating: ratingArrayList) {

            if (rating.getValue() > 0.0 ) {
                ratingArrayListFiltered.add(rating);
            }
        }
        Collections.sort(ratingArrayListFiltered);

        for (Rating rating: ratingArrayListFiltered) {
            System.out.println(MovieDatabase.getTitle(rating.getItem()));
            System.out.print("   ");
            System.out.println(rating.getValue());
        }
    }



}
