import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerSimilarRatings {
    public static void printAverageRatings() {
        String ratingsFileName = "ratings.csv";
        String movieFileName = "ratedmoviesfull.csv";
        FourthRatings fourthRatings = new FourthRatings(ratingsFileName);

        System.out.println("number of raters " + RaterDatabase.size());
        MovieDatabase.initialize(movieFileName);
        System.out.println("number of movies " + MovieDatabase.size());

        ArrayList<Rating> ratingArrayList = fourthRatings.getAverageRatings(35);

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
        FourthRatings fourthRatings = new FourthRatings(ratingsFileName);

        System.out.println("number of raters " + RaterDatabase.size());
        MovieDatabase.initialize(movieFileName);
        System.out.println("number of movies " + MovieDatabase.size());

        ArrayList<Rating> ratingArrayList = fourthRatings.getAverageRatingsByFilter(8, allFilter);

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

    public static void printSimilarRatings() {
        String ratingsFileName = "ratings.csv";
        String movieFileName = "ratedmoviesfull.csv";
        FourthRatings fourthRatings = new FourthRatings(ratingsFileName);

        System.out.println("number of raters " + RaterDatabase.size());
        MovieDatabase.initialize(movieFileName);
        System.out.println("number of movies " + MovieDatabase.size());

        ArrayList<Rating> ratingArrayList = fourthRatings.getSimilarRatings("71", 20, 5);

        System.out.println("77 " + ratingArrayList.size());
        ArrayList<Rating> ratingArrayListFiltered = new ArrayList<>();
        for (Rating rating: ratingArrayList) {

            if (rating.getValue() > 0.0 ) {
                ratingArrayListFiltered.add(rating);
            }
        }
        // Collections.sort(ratingArrayListFiltered);

        for (Rating rating: ratingArrayListFiltered) {
            System.out.println(MovieDatabase.getTitle(rating.getItem()));
            System.out.print("   ");
            System.out.println(rating.getValue());
        }

    }

    public static void printSimilarRatingsByGenre() {
        String ratingsFileName = "ratings.csv";
        String movieFileName = "ratedmoviesfull.csv";
        FourthRatings fourthRatings = new FourthRatings(ratingsFileName);

        System.out.println("number of raters " + RaterDatabase.size());
        MovieDatabase.initialize(movieFileName);
        System.out.println("number of movies " + MovieDatabase.size());


        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(new GenreFilter("Mystery"));

        ArrayList<Rating> ratingArrayList = fourthRatings.getSimilarRatingsByFilter("964", 20, 5, allFilters);

        System.out.println("77 " + ratingArrayList.size());
        ArrayList<Rating> ratingArrayListFiltered = new ArrayList<>();
        for (Rating rating: ratingArrayList) {

            if (rating.getValue() > 0.0 ) {
                ratingArrayListFiltered.add(rating);
            }
        }
        // Collections.sort(ratingArrayListFiltered);

        for (Rating rating: ratingArrayListFiltered) {
            System.out.println(MovieDatabase.getTitle(rating.getItem()));
            System.out.print("   ");
            System.out.println(rating.getValue());
        }

    }

    public static void printSimilarRatingsByDirector() {
        String ratingsFileName = "ratings.csv";
        String movieFileName = "ratedmoviesfull.csv";
        FourthRatings fourthRatings = new FourthRatings(ratingsFileName);

        System.out.println("number of raters " + RaterDatabase.size());
        MovieDatabase.initialize(movieFileName);
        System.out.println("number of movies " + MovieDatabase.size());


        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh"));

        ArrayList<Rating> ratingArrayList = fourthRatings.getSimilarRatingsByFilter("120", 10, 2, allFilters);

        System.out.println("77 " + ratingArrayList.size());
        ArrayList<Rating> ratingArrayListFiltered = new ArrayList<>();
        for (Rating rating: ratingArrayList) {

            if (rating.getValue() > 0.0 ) {
                ratingArrayListFiltered.add(rating);
            }
        }
        // Collections.sort(ratingArrayListFiltered);

        for (Rating rating: ratingArrayListFiltered) {
            System.out.println(MovieDatabase.getTitle(rating.getItem()));
            System.out.print("   ");
            System.out.println(rating.getValue());
        }

    }

    public static void printSimilarRatingsByGenreAndMinutes() {
        String ratingsFileName = "ratings.csv";
        String movieFileName = "ratedmoviesfull.csv";
        FourthRatings fourthRatings = new FourthRatings(ratingsFileName);

        System.out.println("number of raters " + RaterDatabase.size());
        MovieDatabase.initialize(movieFileName);
        System.out.println("number of movies " + MovieDatabase.size());


        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(new GenreFilter("Drama"));
        allFilters.addFilter(new MinutesFilter(80, 160));
        ArrayList<Rating> ratingArrayList = fourthRatings.getSimilarRatingsByFilter("168", 10, 3, allFilters);

        System.out.println("77 " + ratingArrayList.size());
        ArrayList<Rating> ratingArrayListFiltered = new ArrayList<>();
        for (Rating rating: ratingArrayList) {

            if (rating.getValue() > 0.0 ) {
                ratingArrayListFiltered.add(rating);
            }
        }
        // Collections.sort(ratingArrayListFiltered);

        for (Rating rating: ratingArrayListFiltered) {
            System.out.println(MovieDatabase.getTitle(rating.getItem()));
            System.out.print("   ");
            System.out.println(rating.getValue());
        }

    }

    public static void printSimilarRatingsByYearAfterAndMinutes() {
        String ratingsFileName = "ratings.csv";
        String movieFileName = "ratedmoviesfull.csv";
        FourthRatings fourthRatings = new FourthRatings(ratingsFileName);

        System.out.println("number of raters " + RaterDatabase.size());
        MovieDatabase.initialize(movieFileName);
        System.out.println("number of movies " + MovieDatabase.size());


        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(new YearAfterFilter(1975));
        allFilters.addFilter(new MinutesFilter(70, 200));
        ArrayList<Rating> ratingArrayList = fourthRatings.getSimilarRatingsByFilter("314", 10, 5, allFilters);

        System.out.println("77 " + ratingArrayList.size());
        ArrayList<Rating> ratingArrayListFiltered = new ArrayList<>();
        for (Rating rating: ratingArrayList) {

            if (rating.getValue() > 0.0 ) {
                ratingArrayListFiltered.add(rating);
            }
        }
        // Collections.sort(ratingArrayListFiltered);

        for (Rating rating: ratingArrayListFiltered) {
            System.out.println(MovieDatabase.getTitle(rating.getItem()));
            System.out.print("   ");
            System.out.println(rating.getValue());
        }

    }

    public static double dotProduct(IRater me, IRater r) {
        double sumOfDotProduct = 0.0;
        ArrayList<String> itemsRatedByMe = me.getItemsRated();
        for (String movieId: itemsRatedByMe) {
            if (r.hasRating(movieId)) {
                double dotProduct = (me.getRating(movieId) - 5.0) * (r.getRating(movieId) - 5.0);
                System.out.println(dotProduct);
                sumOfDotProduct += dotProduct;
            }
        }
        System.out.println("sumOfDotProduct " + sumOfDotProduct);
        return sumOfDotProduct;
    }

}
