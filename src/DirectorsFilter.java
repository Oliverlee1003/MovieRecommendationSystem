import java.util.*;
public class DirectorsFilter implements Filter {
    private String directors;

    public DirectorsFilter(String directors) {
        this.directors = directors;
    }

    @Override
    public boolean satisfies(String id) {
        //  return true if a movie has at least one of these directors as one of its directors. Note that each movie may have several directors.
        String currentDirectors = MovieDatabase.getDirector(id);
        String[] directorArray = directors.split(",");

        for(String dir: directorArray) {
            System.out.print(dir + "  ");
            if (currentDirectors.contains(dir)) {
                return true;
            }
        }

        return false;
    }
}
