package comp249_2;


import java.io.*;
/**
 * Represents a movie with properties like year, title, duration, genre, rating, score, 
 * director, and actors. Provides methods for creating a movie from individual attributes or
 * a CSV line, accessing and modifying its properties, and comparing movies for equality. 
 * Designed for use in movie database applications.
 */
public class Movie implements Serializable {

	private int year;
	private String title;
	private int duration;
	private String genre;
	private String rating;
	private double score;
	private String director;
	private String actor1;
	private String actor2;
	private String actor3;
	
	public Movie() {
        
    }
	public Movie(int year, String title, int duration, String genre, String rating, double score, String director, String actor1, String actor2, String actor3) {
        this.year = year;
        this.title = title;
        this.duration = duration;
        this.genre = genre;
        this.rating = rating;
        this.score = score;
        this.director = director;
        this.actor1 = actor1;
        this.actor2 = actor2;
        this.actor3 = actor3;
    }
	public Movie(String csvLine) throws Exception {
        
        String[] fields = csvLine.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);

        if (fields.length != 10) {
            throw new IllegalArgumentException("Incorrect number of fields in CSV line: " + csvLine);
        }

        try {
            this.year = Integer.parseInt(fields[0].trim());
            this.title = fields[1].trim().replaceAll("^\"|\"$", ""); 
            this.duration = Integer.parseInt(fields[2].trim());
            this.genre = fields[3].trim();
            this.rating = fields[4].trim();
            this.score = Double.parseDouble(fields[5].trim());
            this.director = fields[6].trim();
            this.actor1 = fields[7].trim();
            this.actor2 = fields[8].trim();
            this.actor3 = fields[9].trim();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Error parsing numeric field in CSV line: " + csvLine, e);
        }
    }
    

    //Mutator methods
    public void setYear(int year) {
        this.year = year;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setActor1(String actor1) {
        this.actor1 = actor1;
    }

    public void setActor2(String actor2) {
        this.actor2 = actor2;
    }

    public void setActor3(String actor3) {
        this.actor3 = actor3;
    }

    //Accessor methods
    public int getYear() {
        return year;
    }

    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }

    public String getGenre() {
        return genre;
    }

    public String getRating() {
        return rating;
    }

    public double getScore() {
        return score;
    }

    public String getDirector() {
        return director;
    }

    public String getActor1() {
        return actor1;
    }

    public String getActor2() {
        return actor2;
    }

    public String getActor3() {
        return actor3;
    }
    
    
    //equals method
    public boolean equals(Object other) {
    	  
    	if (this == other) {
    	        return true;
    	    }
    	    if (other == null || getClass() != other.getClass()) {
    	        return false;
    	    }
    	    
    	    Movie i = (Movie) other;

    	    return year == i.getYear() &&
    	            title == i.getTitle() &&
    	            duration == i.getDuration() &&
    	            genre == i.getGenre() &&
    	            rating == i.getRating() &&
    	            score == i.getScore()&&
    	            director == i.getDirector() &&
    	            actor1 == i.getActor1() &&
    	            actor2 == i.getActor2() &&
    	            actor3 == i.getActor3();
    	}
    
    //toString method
    public String toString() {
        return 
        		"\nTitle: " + title +
        		"\nYear: " + year +
                "\nDuration: " + duration +
                "\nGenre: " + genre  +
                "\nRating: " + rating +
                "\nScore: " + score +
                "\nDirector: " + director +
                "\nActor 1: " + actor1 +
                "\nActor 2: " + actor2  +
                "\nActor 3: " + actor3;
    		}
    

    		   
    }
    
    