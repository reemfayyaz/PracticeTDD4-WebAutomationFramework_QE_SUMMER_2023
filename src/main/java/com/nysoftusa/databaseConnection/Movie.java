package com.nysoftusa.databaseConnection;

public class Movie {
    private int movieId;
    private String movieTitle;
    private int movieReleaseYear;
    private String movieGenre;
    private String movieRating;


    public Movie() {
    }

    public Movie(int movieId) {
        this.movieId = movieId;
    }

    public Movie(int movieId, String movieTitle) {
        this.movieId = movieId;
        this.movieTitle = movieTitle;
    }

    public Movie(int movieId, String movieTitle, int movieReleaseYear) {
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.movieReleaseYear = movieReleaseYear;
    }

    public Movie(int movieId, String movieTitle, int movieReleaseYear, String movieGenre, String movieRating) {
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.movieReleaseYear = movieReleaseYear;
        this.movieGenre = movieGenre;
        this.movieRating = movieRating;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public int getMovieReleaseYear() {
        return movieReleaseYear;
    }

    public void setMovieReleaseYear(int movieReleaseYear) {
        this.movieReleaseYear = movieReleaseYear;
    }

    public String getMovieGenre() {
        return movieGenre;
    }

    public void setMovieGenre(String movieGenre) {
        this.movieGenre = movieGenre;
    }

    public String getMovieRating() {
        return movieRating;
    }

    public void setMovieRating(String movieRating) {
        this.movieRating = movieRating;
    }


    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", movieTitle='" + movieTitle + '\'' +
                ", movieReleaseYear=" + movieReleaseYear +
                ", movieGenre='" + movieGenre + '\'' +
                ", movieRating='" + movieRating + '\'' +
                '}';
    }

//    public static void main(String[] args) {
//        Movie movie=new Movie();
//        System.out.println(movie.toString());
//    }
}
