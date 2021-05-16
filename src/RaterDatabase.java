/**
 * Write a description of RaterDatabase here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class RaterDatabase {
    private static HashMap<String,IRater> ourRaters;

    private static void initialize() {
        // this method is only called from addRatings
        if (ourRaters == null) {
            ourRaters = new HashMap<String,IRater>();
        }
    }

    public static void initialize(String filename) {
        if (ourRaters == null) {
            ourRaters= new HashMap<String,IRater>();
            addRatings("data/" + filename);
        }
    }

    public static void addRatings(String filename) {
        initialize();
        FileResource fr = new FileResource(filename);
        CSVParser csvp = fr.getCSVParser();
        for(CSVRecord rec : csvp) {
            String id = rec.get("rater_id");
            String item = rec.get("movie_id");
            String rating = rec.get("rating");
            addRaterRating(id,item,Double.parseDouble(rating));
        }
    }

    public static void addRaterRating(String raterID, String movieID, double rating) {
        initialize();
        IRater rater =  null;
        if (ourRaters.containsKey(raterID)) {
            rater = ourRaters.get(raterID);
        }
        else {
            rater = new EfficientRater(raterID);
            ourRaters.put(raterID,rater);
        }
        rater.addRating(movieID,rating);
    }

    public static IRater getRater(String id) {
        initialize();

        return ourRaters.get(id);
    }

    public static ArrayList<IRater> getRaters() {
        initialize();
        ArrayList<IRater> list = new ArrayList<IRater>(ourRaters.values());

        return list;
    }

    public static int size() {
        return ourRaters.size();
    }



}