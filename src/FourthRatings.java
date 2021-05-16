import java.lang.reflect.Array;
import java.util.*;

public class FourthRatings {
    public FourthRatings() {
        // default constructor
        this("ratings.csv");
    }

    public FourthRatings(String ratingsfile) {
        RaterDatabase.initialize(ratingsfile);
    }

    private double getAverageByID(String id, int minimalRaters) {
        int sumRatings = 0;
        int sumNumbers = 0;

        for (IRater plainRater : RaterDatabase.getRaters()) {
            if (plainRater.hasRating(id)) {
                sumNumbers++;
                sumRatings += plainRater.getRating(id);
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

    private double dotProduct(IRater me, IRater r) {
        double sumOfDotProduct = 0.0;
        ArrayList<String> itemsRatedByMe = me.getItemsRated();
        for (String movieId: itemsRatedByMe) {
            if (r.hasRating(movieId)) {
                double dotProduct = (me.getRating(movieId) - 5.0) * (r.getRating(movieId) - 5.0);
                sumOfDotProduct += dotProduct;
            }
        }
        return sumOfDotProduct;
    }

    // give a rater ID, return a list of rating
    // for each rating, Rater ID (other rater) is the anItem field
    //                  the dot product (how similar two raters are) is the value field
    // list of rating is sorted from largest to smallest
    private ArrayList<Rating> getSimilarities(String raterID) {
        ArrayList<Rating> list = new ArrayList<Rating>();
        IRater me = RaterDatabase.getRater(raterID);

        for(IRater rater: RaterDatabase.getRaters()) {
            if (!rater.getID().equals(raterID)) {
                double dp = dotProduct(me, rater);
                Rating similarRating = new Rating(rater.getID(), dp);
                list.add(similarRating);
            }
        }
        Collections.sort(list, Collections.reverseOrder());
        return list;
    }

    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {
        return getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, new TrueFilter());
    }

    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria ) {
        ArrayList<Rating> movieSimRatings = new ArrayList<>();

        // similarMap String is the other Rater ID (instead of the given Rater ID)
        //            Double is the dot product btw this rater id and given rater id
        HashMap<String, Double> raterDotProductMap = new HashMap<>();
        int mapSize = getSimilarities(id).size();
        mapSize = Math.min(mapSize, numSimilarRaters);

        ArrayList<String> movieListFiltered = MovieDatabase.filterBy(filterCriteria);
        ArrayList<Rating> ratingArrayList = getSimilarities(id);

        for (Rating similarRating: ratingArrayList.subList(0, mapSize)) {
            if (similarRating.getValue() > 0) {
                raterDotProductMap.put(similarRating.getItem(), similarRating.getValue());
            }
        }

        for(String movieID: movieListFiltered) {
            int count = 0;
            double sum = 0;

            // for each movie ID
            for (IRater rater : RaterDatabase.getRaters()) {
                // if a rater is included in the similar map
                // and this rater has rating of this movie
                // then we added the rating
                double rating = -1;
                if (raterDotProductMap.containsKey(rater.getID()) && rater.hasRating(movieID)) {

                    // the rating of this movie from this rater
                    // multiplies
                    // the dotProductValue of this rater to the given rater
                    // ( which is considered as weight, how close/similar this rater with the given rater)
                    rating = rater.getRating(movieID) * raterDotProductMap.get(rater.getID());
                }
                if (rating != -1) {
                    count++;
                    sum += rating;
                }
            }

            // now we have the count how many ratings does the specific movie have

            if (count < minimalRaters || count == 0) {
                // do nothing
            } else {
                // sum/count is the average weighted ratings
                movieSimRatings.add(new Rating(movieID, sum / count));
            }
        }

            //Collections.sort(movieSimRatings, Collections.reverseOrder());
            Collections.sort(movieSimRatings, Collections.reverseOrder());
            return movieSimRatings;
        }
}
