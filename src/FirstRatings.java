import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings{
    private ArrayList<Movie> movieList;
    private ArrayList<Rater> raterList;


    public FirstRatings() {
        this.movieList = new ArrayList<Movie>();
    }
 
    public ArrayList<Movie> loadMovies(String filename) {
        ArrayList<Movie> movieListTemp = new ArrayList<Movie>();

        FileResource fr = new FileResource(filename);
        for (CSVRecord record : fr.getCSVParser()) {
            String id = record.get("id");
            String title = record.get("title");
            String year = record.get("year");
            String country = record.get("country");
            String genre = record.get("genre");
            String director = record.get("director");
            int mins = Integer.parseInt(record.get("minutes"));
            String poster = record.get("poster");
            Movie movie = new Movie(id, title, year, genre, director, country, poster, mins);
            movieListTemp.add(movie);
        }

        return movieListTemp;
    }
    
    public void testloadMovies() {
        this.movieList = loadMovies("data/ratedmovies_short.csv");
        System.out.println("comedy number " + getNumOfMoviesWithGenre("Comedy"));
        System.out.println("getNumOfMoviesGreaterThanMin 150 " + getNumOfMoviesGreaterThanMin(150));
        System.out.println("map of director + " + getMapOfDirectors());
    }
    
    public ArrayList<Rater> loadRaters(String filename) {
        ArrayList<Rater> raterListTemp = new ArrayList<>();
        FileResource fr = new FileResource(filename);
        outLoop:
        for (CSVRecord record : fr.getCSVParser()) {
            String rater_id = record.get("rater_id");
            String movie_id = record.get("movie_id");
            String ratingScore = record.get("rating");
            int ratingScoreInt = Integer.parseInt(ratingScore);
            double ratingScoreDouble = (double) ratingScoreInt;

            for (Rater rater:raterListTemp) {
                // if we have the rater, then we add rating and continue to next record
                if (rater.getID().equals(rater_id)){
                    rater.addRating(movie_id,ratingScoreDouble);
                    continue outLoop;
                }
            }
            // if we do not have the rater, then we create the rater then add the rating and continue to next record
            Rater rater = new Rater(rater_id);
            rater.addRating(movie_id,ratingScoreDouble);
            raterListTemp.add(rater);
        }

        return raterListTemp;
    }

    public void testLoadRaters() {
        this.raterList = loadRaters("data/ratings_short.csv");
        System.out.println("raterList size " + this.raterList.size());

        System.out.println("getNumOfRatings of 2 " + getNumOfRatings("2"));

        System.out.println("getMaxOfRatings, what is maximum number " + getMaxOfRatings()[0]);
        System.out.println("getMaxOfRatings, how many raters with this max num " + getMaxOfRatings()[1]);

        System.out.println("getNumOfRatingsForAMovie with 1798709 " + getNumOfRatingsForAMovie("1798709"));

        System.out.println("getNumOfMoviesRated " + getNumOfMoviesRated());

    }

    private int getNumOfRatings(String rater_id) {
        for (Rater rater: this.raterList) {
            if (rater.getID().equals(rater_id)) {
                return rater.numRatings();
            }
        }
        return 0;
    }

    //  find the maximum number of ratings by any rater
    private int[] getMaxOfRatings() {
        int max = 0;
        int numOfRatersWithMaxRatings = 0;

        for (Rater rater: this.raterList) {
            if (rater.numRatings() > max) {
                max = rater.numRatings();
            }
        }

        for (Rater rater: this.raterList) {
            if (rater.numRatings() == max) {
                numOfRatersWithMaxRatings++;
            }
        }
        return new int[]{max, numOfRatersWithMaxRatings};
    }

    private int getNumOfRatingsForAMovie(String movie_id) {
        int res = 0;
        for (Rater rater: this.raterList) {
            for (String ratingItem: rater.getItemsRated()){
                if (ratingItem.equals(movie_id)) res++;
            }
        }
        return res;
    }

    private int getNumOfMoviesRated() {
        HashSet<String> movieSet = new HashSet<>();
        for (Rater rater: this.raterList) {
            for (String ratingItem: rater.getItemsRated()){
                movieSet.add(ratingItem);
            }
        }
        return movieSet.size();
    }

    private int getNumOfMoviesWithGenre(String genre) {
        int res = 0;
        for (Movie movie: this.movieList) {
            if (movie.getGenres().equals(genre)) res ++;
        }
        return res;
    }

    private int getNumOfMoviesGreaterThanMin(int min) {
        int res = 0;
        for (Movie movie: this.movieList) {
            if (movie.getMinutes() > min) res ++;
        }
        return res;
    }

    private Map<String, Integer> getMapOfDirectors() {
        HashMap<String, Integer> map = new HashMap<>();
        for (Movie movie: this.movieList) {
            String director = movie.getDirector();
            String[] directorArray = director.split(",");
            for(String dir: directorArray) {
                map.put(dir, map.getOrDefault(dir,0) + 1);
            }
        }
        Map<String, Integer> sortedMapDesc = SortHashMap.sortByValue(map, false);
        return sortedMapDesc;
    }
}


