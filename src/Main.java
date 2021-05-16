public class Main {

    public static void main(String[] args) {
//        System.out.println("Hello World!");
//
//        FirstRatings firstRatings = new FirstRatings();
//
//        firstRatings.testloadMovies();
//
//        firstRatings.testLoadRaters();
          // MovieRunnerAverage.getHowManyMoviesHaveRatingsMoreThanFifty();
       //  System.out.println(MovieRunnerAverage.getHowManyMoviesHaveRatingsMoreThan(20));
//        MovieRunnerAverage.MovieRunnerAverage();
//
//        MovieRunnerAverage.getAverageRatingOneMovie();
       // MovieRunnerWithFilters.printAverageRatings();
        IRater me = new PlainRater("15");
        me.addRating("2354", 10);
        me.addRating("3285", 6);
        me.addRating("1297", 2);
        me.addRating("5804", 8);

        IRater r = new PlainRater("20");
        r.addRating("3285", 4);
        r.addRating("1297", 7);
        r.addRating("6574", 10);
        r.addRating("2354", 9);

        MovieRunnerSimilarRatings.printSimilarRatingsByYearAfterAndMinutes();

    }
}
