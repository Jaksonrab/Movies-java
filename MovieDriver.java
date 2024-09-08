
package comp249_2;

import java.io.*;
import java.util.Scanner;

import ExceptionPackage.*;

/** Driver class containing parts 1, 2 and 3:
 * - Part 1: reads movie records from csv files, validates and organizes them by genre into
 *   separate CSV files, and identifies records with syntax or semantic errors 
 * - Part 2: serializes sorted movie records into binary files for each genre and creates a 
 * 	 manifest for these serialized files
 * - Part 3: Deserializes the binary files back into movie objects organized within a 2D array,
 *   enabling user interactive navigation through movie genres and their respective records
 **/
public class MovieDriver {

	public static void main(String[] args) throws BadDurationException, BadGenreException, BadNameException, BadRatingException, 
	BadScoreException, BadTitleException, BadYearException, MissingQuotesException, ExcessFieldsException, 
	MissingFieldsException, IOException, FileNotFoundException, NumberFormatException  {

		
		String part1_manifest = "part1_manifest.txt";
		String part2_manifest = do_part1(part1_manifest);
		try {
            do_part2("part2_manifest.txt"); 
        } catch (IOException e) {
            System.err.println("IOException in main");
            e.printStackTrace();
        }
		try {
            do_part3("part3_manifest.txt"); 
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error in main");
            e.printStackTrace();
        }
	}	
		//methods to be used in parts
		
		
	//returns true if there's less than nine commas in the line. It takes into account if the comma is in a title, actor, or director name. 
			public static boolean nineCommas(String line) {
			    int count = 0;
			    boolean insideQuotes = false;

			    for (int i = 0; i < line.length(); i++) {
			        char currentChar = line.charAt(i);

			        if (currentChar == '"') {
			            insideQuotes = !insideQuotes; 
			        } 
			        if (currentChar == ',' && insideQuotes == false) {
			            count++; 
			        }
			    }
			    return count == 9; 
			}
			
			
			//counting commas to see if fields are missing or in excess
			public static int countCommas(String line) {
			    int count = 0;
			    boolean insideQuotes = false;

			    for (int i = 0; i < line.length(); i++) {
			        char currentChar = line.charAt(i);

			        if (currentChar == '"') {
			            insideQuotes = !insideQuotes; 
			        } 
			        if (currentChar == ',' && insideQuotes == false) {
			            count++; 
			        }
			    }
			    return count; 
			}
			
			
			//returns true if there is even amount of double quotations
			public static boolean enoughQuotes(String line) {
				int count = 0;
				
				for(int i=0; i<line.length(); i++) {
					if(line.charAt(i) == '"')
						count++;
				}
				return count%2 == 0;
			}
			
			
			//read from the manifest file and put it in a string
			public static String[] manifestArray(String fileName, int amountFiles) {
			String[] manifestArray = new String[amountFiles];

			    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			        String line;
			        int index = 0;
			        
			        
			        while ((line = br.readLine()) != null && index < amountFiles) {
			            manifestArray[index++] = line;
			        }
			    } catch (IOException e) {
			        e.printStackTrace();
			    }
			    return manifestArray;
			}
			
			//how to display the badMovies
			public static String badMoviesDisplay(String errorType, String specificError, String line, String list, int lineNum) {
				return "(a) " + errorType + ": " + specificError + 
						"\n(b) " + line +
						"\n(c) from file: " +  list +
						"\n(d) at line: " + lineNum + "\n";			
			}
		
	
		
			//Creating a file containing all the movie genre.csvs which stores each array
			public static String arrayToFile(String[] genre, String fileNumber) {
				
			
				try {
				PrintWriter output = new PrintWriter(new FileOutputStream(fileNumber));
				
					for(int i=0; i<genre.length; i++) {
						if (genre[i] == null) {
		                       break;} 
						output.println(genre[i]);
						
						}
					output.close();
					
					//System.out.println(fileNumber + " file has been created");
				}
				
				catch(FileNotFoundException e){
					System.err.println("FileNotFoundException caught!");
					e.printStackTrace();
					
				}
				catch(IOException e) {
					System.err.println("IOException caught!");
					e.printStackTrace();
				}
				
				return fileNumber + " created";
			}
		
		public static String do_part1(String part1_manifest)throws BadDurationException, BadGenreException, BadNameException, BadRatingException, BadScoreException, BadTitleException, BadYearException, MissingQuotesException, ExcessFieldsException, MissingFieldsException{
			
			//String for each genre and bad movies
			String[] musical = new String[200];
		    String[] comedy = new String[200];
		    String[] animation = new String[200];
		    String[] adventure = new String[200];
		    String[] drama = new String[200];
		    String[] crime = new String[200];
		    String[] biography = new String[200];
		    String[] horror = new String[200];
		    String[] action = new String[200];
		    String[] documentary = new String[200];
	        String[] fantasy = new String[200];
	        String[] mystery = new String[200];
	        String[] sciFi = new String[200];
		    String[] family = new String[200];	
		    String[] romance = new String[200];
		    String[] thriller = new String[200];
		    String[] western = new String[200];
		    String[] badMovie = new String[200];
		   
		    //Count for each genre and bad movies
		    int musicalCount = 0;
		    int comedyCount = 0;
		    int animationCount = 0;
		    int adventureCount = 0;
		    int dramaCount = 0;
		    int crimeCount = 0;
		    int biographyCount = 0;
		    int horrorCount = 0;
		    int actionCount = 0;
		    int documentaryCount = 0;
		    int fantasyCount = 0;
		    int mysteryCount = 0;
		    int sciFiCount = 0;
		    int familyCount = 0;
		    int romanceCount = 0;
		    int thrillerCount = 0;
		    int westernCount = 0;
		    int badMovieCount =0; 
		   

		    String[] movieList = manifestArray("part1_manifest.txt", 10);
		    
		     //To display that the file was turned into an array
		       for (String movie : movieList) { 		
	             System.out.println(movie);}
		    
		    for(int i =0; i< movieList.length; i++ ) {
		    String line;
		    int lineNum = 1;
		    
		    //reading the line is 
		    try {
		    	
		    	
		    	BufferedReader read = new BufferedReader(new FileReader(movieList[i]));
		    	
		    	while ((line = read.readLine()) != null) {
		    		
		    		String[] info = line.split("(?<!\\\\),(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);	
		    
		    		boolean error = false;
		    		
		    		
		    
		    //testing for syntax errors (got to them first)
		    try {
		    	if (!enoughQuotes(line)) {
		    	    error = true;
		    	    badMovie[badMovieCount++] = badMoviesDisplay("Syntax", "Missing Quotes", line, movieList[i], lineNum);
		    		throw new MissingQuotesException();
		    	} else if (countCommas(line) < 9) {
		    	    error = true;
		    	    badMovie[badMovieCount++] = badMoviesDisplay("Syntax", "Missing Fields", line, movieList[i], lineNum);
		    			throw new MissingFieldsException();
		    	} else if (countCommas(line) > 9) {
		    	    error = true;
		    	    badMovie[badMovieCount++] = badMoviesDisplay("Syntax", "Excess Fields", line, movieList[i], lineNum);
		    			throw new ExcessFieldsException();
		    	}
		    		}
		    
		    catch(MissingQuotesException e) {
		    	System.err.println("MissingQuotesException thrown");	
		    	
		    }
		    
		    catch (MissingFieldsException e) {
		    	System.err.println("MissingFieldsException thrown");
		    
		    }
		    
		    catch(ExcessFieldsException e) {
		    	System.err.println("ExcessFieldsException thrown");	
		    
		    }

		    	
		    	//semantic errors
		    	if(error==false) {
		    	
		    	//giving each member of the array a variable (that are strings)
		    	String title = info[1];
		    	String genre = info[3].trim();
		    	String rating = info[4].trim();
		    	String director = info[6], actor1 = info[7], actor2 = info[8], actor3 =info[9];
		    	
		    	//testing year parse
		    	int year = 0;
		    	try {
		    		year = Integer.parseInt(info[0].trim()); 
		    	}
		    	catch (NumberFormatException e) {
		 		    System.err.println("NumberException thrown");
		 		    error = true;
		 		    badMovie[badMovieCount++] = badMoviesDisplay("Semantic", "Invalid Year", line, movieList[i], lineNum);	    
		    	}
		    		
		    	//testing duration parse
		 		int duration = 0;
		 		try {
		 			duration = Integer.parseInt(info[2]);
		 		
		 		}
		 		catch (NumberFormatException e) {
		 		    System.err.println("NumberException thrown");
			        error = true;
	    	    	badMovie[badMovieCount++] = badMoviesDisplay("Semantic", "Invalid Duration", line, movieList[i], lineNum);
			    }
		 		
		 		//testing score parse
		 		double score = 0;
		 		try {
		 			score = Double.parseDouble(info[5]);
		 		}
		 		catch (NumberFormatException e) {
		 		    System.err.println("NumberException thrown");		    
				        error = true;
			    		badMovie[badMovieCount++] = badMoviesDisplay("Semantic", "Invalid Score", line, movieList[i], lineNum);
				    }
		 		
		 		try {
		    		//year is invalid
		    	    if(info[0].trim().isEmpty()) {
		    	    	error = true;
		    	    	badMovie[badMovieCount++] = badMoviesDisplay("Semantic", "Missing Year", line, movieList[i], lineNum);
		    	    	throw new BadYearException();
		    	    }
		    	    
		    	    if(year<1990|| year>1999) {
		    	    	error = true;
		    	    	badMovie[badMovieCount++] = badMoviesDisplay("Semantic", "Invalid Year", line, movieList[i], lineNum);	
		    	    	throw new BadYearException();
		    	    }
		    	    
		    	    // actors and directors are invalid
		    	    if(director.trim().isEmpty()|| actor1.trim().isEmpty()  || actor2.trim().isEmpty() || actor3.trim().isEmpty()) {
		    	    	error = true;
		    	    	badMovie[badMovieCount++] =badMoviesDisplay("Semantic", "Missing Name(s)", line, movieList[i], lineNum);
		    	    	throw new BadNameException();
		    	    }
		    	    
		    	    //titles are invalid
		    	    if(title.trim().isEmpty()) {
		    	    	error = true;
		    	    	badMovie[badMovieCount++] = badMoviesDisplay("Semantic", "Missing Title", line, movieList[i], lineNum);
		    	    	throw new BadTitleException();
		    	    }
		    	    
		    	    //duration is invalid
		    	    if(info[2].trim().isEmpty()) {
		    	    	error = true;
		    	    	badMovie[badMovieCount++] = badMoviesDisplay("Semantic", "Missing Duration", line, movieList[i], lineNum);
		    	    	throw new BadDurationException();
		    	    }
		    	    if(duration<30 || duration>300) {
		    	    	error = true;
		    	    	badMovie[badMovieCount++] = badMoviesDisplay("Semantic", "Invalid Duration", line, movieList[i], lineNum);
		    	    	throw new BadDurationException();
		    	    }
		    	    
		    	  //genre is invalid
		    	  if(genre.isEmpty()) {
		    		  error = true;
		    		  badMovie[badMovieCount++] = badMoviesDisplay("Semantic", "Missing Genre", line, movieList[i], lineNum);
		    		  throw new BadGenreException();
		    	  }
		    	  
		    	  if(!genre.equals("Musical") && !genre.equals("Comedy") && !genre.equals("Animation") && !genre.equals("Adventure") 
		    			  && !genre.equals("Drama") && !genre.equals("Crime") && !genre.equals("Biography") && !genre.equals("Horror") 
		       		   && !genre.equals("Action") && !genre.equals("Documentary") && !genre.equals("Fantasy")  && !genre.equals("Mystery") 
		    		   && !genre.equals("Sci-fi") && !genre.equals("Family") && !genre.equals("Romance") && !genre.equals("Thriller") 
		    		   && !genre.equals("Western")) {
		    		  error = true;
		    		  badMovie[badMovieCount++] = badMoviesDisplay("Semantic", "Invalid Genre", line, movieList[i], lineNum);
		    		  throw new BadGenreException();
		    	  }
		    	  
		    	  //score is invalid 
		    	  if(info[5].trim().isEmpty()) {
		    		  error = true;
		    		  badMovie[badMovieCount++] = badMoviesDisplay("Semantic", "Missing Score", line, movieList[i], lineNum);
		    		  throw new BadScoreException();
		    	  }
		    	  
		    	  if(score<0 || score>10) {
		    		  error = true;
		    		  badMovie[badMovieCount++] = badMoviesDisplay("Semantic", "Invalid Score", line, movieList[i], lineNum);
		    		  throw new BadScoreException();
		    	  }
		    	  
		    	  //rating is invalid
		    	  if(rating.isEmpty()) {
		    		  error = true;
		    		  badMovie[badMovieCount++] = badMoviesDisplay("Semantic", "Missing Rating", line, movieList[i], lineNum);
		    		  throw new BadRatingException();
		    	  }
		    	 
		    	  if(!rating.equals("PG") && !rating.equals("Unrated") && !rating.equals("G") && !rating.equals("R") 
		    			  && !rating.equals("PG-13") && !rating.equals("NC-17")) {
		    		  error = true;
		    		  badMovie[badMovieCount++] = badMoviesDisplay("Semantic", "Invalid Rating", line, movieList[i], lineNum);
		    		  throw new BadRatingException();
		    	  }
		    	
		    
		    }
		    
		   
		    catch (BadYearException e) {
		        System.err.println("BadYearException thrown");
		        
		    }
		    
		    catch (BadDurationException e) {
		        System.err.println("BadDurationException thrown");
		        
		    }
		    
		    catch (BadGenreException e) {
		        System.err.println("BadGenreException thrown");
		        
		    }
		    
		    catch (BadNameException e) {
		        System.err.println("BadNameException thrown");
		        
		    }
		    
		    catch (BadRatingException e) {
		        System.err.println("BadRatingException thrown");
		     
		    }
		   
		    catch (BadScoreException e) {
		        System.err.println("BadScoreException thrown");		    
		       
		    }
		    
		    catch (BadTitleException e) {
		        System.err.println("BadTitleException thrown");     
		       
		    }
		    
		    }
		    //there's no semantic and syntax errors
		   
		    if(error == false){
		    	
		    	//giving each member of the array a variable (doesn't recognize it from the other if statement but it should).
		    	
		    	String genre = info[3];
		    	
		    	
		    	
		    	if (genre.equalsIgnoreCase("musical")) {
		    	    musical[musicalCount++] = line;
		    	} else if (genre.equalsIgnoreCase("comedy")) {
		    	    comedy[comedyCount++] = line;
		    	} else if (genre.equalsIgnoreCase("animation")) {
		    	    animation[animationCount++] = line;
		    	} else if (genre.equalsIgnoreCase("adventure")) {
		    	    adventure[adventureCount++] = line;
		    	} else if (genre.equalsIgnoreCase("drama")) {
		    	    drama[dramaCount++] = line;
		    	} else if (genre.equalsIgnoreCase("crime")) {
		    	    crime[crimeCount++] = line;
		    	} else if (genre.equalsIgnoreCase("biography")) {
		    	    biography[biographyCount++] = line;
		    	} else if (genre.equalsIgnoreCase("horror")) {
		    	    horror[horrorCount++] = line;
		    	} else if (genre.equalsIgnoreCase("action")) {
		    	    action[actionCount++] = line;
		    	} else if (genre.equalsIgnoreCase("documentary")) {
		    	    documentary[documentaryCount++] = line;
		    	} else if (genre.equalsIgnoreCase("fantasy")) {
		    	    fantasy[fantasyCount++] = line;
		    	} else if (genre.equalsIgnoreCase("mystery")) {
		    	    mystery[mysteryCount++] = line;
		    	} else if (genre.equalsIgnoreCase("sci-fi")) {
		    	    sciFi[sciFiCount++] = line;
		    	} else if (genre.equalsIgnoreCase("family")) {
		    	    family[familyCount++] = line;
		    	} else if (genre.equalsIgnoreCase("romance")) {
		    	    romance[romanceCount++] = line;
		    	} else if (genre.equalsIgnoreCase("thriller")) {
		    	    thriller[thrillerCount++] = line;
		    	} else if (genre.equalsIgnoreCase("western")) {
		    	    western[westernCount++] = line;
		    	}	
		    }//for the if
		    
		   
		    //Incrementing the counts and line numbers
		    lineNum++;
		    
		    if(error==true) {
		    	badMovieCount++;
		    	}
		    	
		    	
		    	}//the while
		    		
		    read.close();
		    }//for the try
		    	
		    catch (FileNotFoundException e) {
				System.err.println("File not found: " + e.getMessage());
			} catch (IOException e) {
				System.err.println("Error reading file: " + e.getMessage());
			} catch (NumberFormatException e) {
				System.err.println("Invalid number format on line ");
			} 

		    
		    } // the for
		    
		    try (PrintWriter out = new PrintWriter(new FileOutputStream("bad_movie_records.txt"))) {
		        for (int i = 0; i < badMovieCount; i++) {
		            if (badMovie[i] != null) {
		                out.println(badMovie[i]);
		            }
		        }
		    } catch (FileNotFoundException e) {
		        System.err.println("Error while writing to the bad movies list file: " + e.getMessage());
		        e.printStackTrace();
		    }
			
			//creating the csv for each genre and bad movie
			String[] allGenresandBad = {"musical.csv","comedy.csv","animation.csv","adventure.csv","drama.csv", 
					"crime.csv", "biography.csv","horror.csv", "action.csv", "documentary.csv", 	
					"fantasy.csv", "mystery.csv","sci-fi.csv", "family.csv", "western.csv", "romance.csv", "thriller.csv"};
			
			
			
			//storing all the genre arrays and bad movie array, into one array
			String[][] genreAndBadArray = {
				    musical, comedy, animation, adventure, drama, crime, biography, horror, 
				    action, documentary, fantasy, mystery, sciFi, family, romance, thriller, western
				};

				//storing all the arrays into files
				for (int i = 0; i < genreAndBadArray.length; i++) {
				    arrayToFile(genreAndBadArray[i], allGenresandBad[i]);
				}
			
				
				//making the print manifest file for part 2
				try {
					PrintWriter output = new PrintWriter(new FileOutputStream("part2_manifest.txt"));
					
						for(int i=0; i<allGenresandBad.length-1; i++) {
							output.println(allGenresandBad[i]);
							}
						output.close();
						
						System.out.println("part2_manifest file has been created");
					}
					
					catch(FileNotFoundException e){
						System.err.println("FileNotFoundException caught!");
						e.printStackTrace();
						
					}
					catch(IOException e) {
						System.err.println("IOException caught!");
						e.printStackTrace();
					}
				
					
				
		
		return "part2_manifest.txt";
		
		} //the method
		public static void do_part2(String part2_manifest) throws IOException {
		    final int MAX_MOVIES = 200; 
		    PrintWriter manifestWriter = new PrintWriter(new FileOutputStream("part3_manifest.txt"));

		    try (BufferedReader br = new BufferedReader(new FileReader(part2_manifest))) {
		        String line;
		        while ((line = br.readLine()) != null) {
		            String csvFileName = line.trim();
		            String serFileName = csvFileName.replace(".csv", ".ser");
		            Movie[] movies = new Movie[MAX_MOVIES]; 
		            int count = 0;

		            try (BufferedReader csvReader = new BufferedReader(new FileReader(csvFileName))) {
		                String csvLine;
		                while ((csvLine = csvReader.readLine()) != null && count < MAX_MOVIES) {
		                    try {
		                        movies[count] = new Movie(csvLine); 
		                        count++;
		                    } catch (Exception e) {
		                        System.err.println("Error processing line: " + csvLine);
		                        e.printStackTrace();
		                    }
		                }
		            } catch (FileNotFoundException e) {
		                System.err.println("Could not find file: " + csvFileName);
		            }

		            if (count > 0) {
		                //copying array
		            	Movie[] exactMovies = new Movie[count];
		                for (int i = 0; i < count; i++) {
		                    exactMovies[i] = movies[i];
		                }
		                
		                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(serFileName))) {
		                    oos.writeObject(exactMovies);
		                }
		                manifestWriter.println(serFileName);
		            }
		        }
		    } catch (FileNotFoundException e) {
		        System.err.println("FileNotFoundException: " + part2_manifest + " not found.");
		    } finally {
		        manifestWriter.close();
		    }
		}

		 private static Movie[][] all_movies;
		    private static int currentGenre = 0; 
		    private static int currentMovie = 0; 
		    private static final String[] GENRE_NAMES = {"Musical", "Comedy", "Animation", "Adventure", "Drama", "Crime", "Biography", "Horror", "Action", "Documentary", "Fantasy", "Mystery", "Sci-Fi", "Family", "Western", "Romance", "Thriller"}; 
		    
		    public static void do_part3(String part3_manifest) throws IOException, ClassNotFoundException {
		        final int MAX_GENRES = 20; 
		        all_movies = new Movie[MAX_GENRES][]; 
		        
		        try (BufferedReader br = new BufferedReader(new FileReader(part3_manifest))) {
		            String serFileName;
		            int genreIndex = 0;

		            while ((serFileName = br.readLine()) != null && genreIndex < MAX_GENRES) {
		                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(serFileName))) {
		                    //directly assign the deserialized array to an element of the 2D array
		                    all_movies[genreIndex] = (Movie[]) ois.readObject();
		                    genreIndex++;
		                } catch (FileNotFoundException e) {
		                    System.err.println("Could not find file: " + serFileName);
		                } catch (IOException | ClassNotFoundException e) {
		                    System.err.println("Error processing file: " + serFileName);
		                    e.printStackTrace();
		                }
		            }
		        }
		        navigateMovies();
		    }


		    private static void navigateMovies() {
		        Scanner scanner = new Scanner(System.in);
		        String input;
		        do {
		            System.out.println("----------------------------");
		            System.out.println("Main Menu");
		            System.out.println("----------------------------");
		            System.out.println("s: Select a movie genre to navigate");
		            System.out.println("n: Navigate within the current genre (" + GENRE_NAMES[currentGenre] + ")");
		            System.out.println("x: Exit");
		            System.out.println("----------------------------");
		            System.out.print("Enter Your Choice: ");
		            input = scanner.nextLine().trim();

		            switch (input.toLowerCase()) {
		                case "s":
		                    selectGenre(scanner);
		                    break;
		                case "n":
		                    navigateCurrentGenre(scanner);
		                    break;
		                case "x":
		                    System.out.println("Exited.");
		                    break;
		                default:
		                    System.out.println("Invalid option, please try again.");
		                    break;
		            }
		        } while (!input.equalsIgnoreCase("x"));
		        scanner.close();
		    }
		 // Method to count the records in a CSV file
		    private static int countRecordsInCsv(String fileName) {
		        int count = 0;
		        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
		            while (br.readLine() != null) {
		                count++;
		            }
		        } catch (IOException e) {
		            System.err.println("Error reading file: " + fileName);
		        }
		        return count;
		    }

		    private static void selectGenre(Scanner scanner) {
		        System.out.println("\n----------------------------");
		        System.out.println("Genre Sub-Menu");
		        for (int i = 0; i < GENRE_NAMES.length; i++) {
		            // Construct the filename for the genre CSV
		            String genreFileName = GENRE_NAMES[i].toLowerCase().replaceAll("\\s+","") + ".csv";
		            int movieCount = countRecordsInCsv(genreFileName);
		            System.out.println((i + 1) + ": " + GENRE_NAMES[i] + " (" + movieCount + " movies)");
		        }
		        System.out.println("----------------------------");
		        System.out.print("Select a genre by number: ");
		        int selection = scanner.nextInt() - 1;
		        if (selection >= 0 && selection < GENRE_NAMES.length) {
		            currentGenre = selection;
		            currentMovie = 0; // Reset movie navigation to the start of the new genre
		        } else {
		            System.out.println("Invalid selection. Please select a valid genre number.");
		        }
		        scanner.nextLine(); // Consume the newline left-over
		    }


		    private static void navigateCurrentGenre(Scanner scanner) {
		        System.out.println("\nNavigating " + GENRE_NAMES[currentGenre] + " Movies");
		    
		        
		       
		        int n;
		        do {
		            System.out.print("Enter number of movies to navigate (0 to return to main menu): ");
		            n = scanner.nextInt();
		            scanner.nextLine(); 
		            System.out.println("----------------------------");
		            System.out.println("\nCurrent Movie: " + all_movies[currentGenre][currentMovie]);
		            System.out.println("----------------------------");
		            

		            if (n == 0) {
		                break; // return to the main menu
		            } else if (n < 0) {
		                // Navigate backwards
		                int newPos = Math.max(currentMovie + n, 0);
		                if (newPos < currentMovie) {
		                    for (int i = newPos; i < currentMovie; i++) {
		                        System.out.println("\nMovie: " + all_movies[currentGenre][i]);
		                    }
		                    currentMovie = newPos;
		                } else {
		                    System.out.println("\nBOF has been reached.");
		                }
		            } else {
		                // Navigate forwards
		                int newPos = Math.min(currentMovie + n, all_movies[currentGenre].length - 1);
		                if (newPos > currentMovie) {
		                    for (int i = currentMovie + 1; i <= newPos && all_movies[currentGenre][i] != null; i++) {
		                    	System.out.println("----------------------------");
		                        System.out.println("Movie: " + all_movies[currentGenre][i]);
		                        System.out.println("----------------------------");
		                    }
		                    currentMovie = newPos;
		                } else {
		                    System.out.println("\nEOF has been reached.");
		                }
		            }

		        } while (n != 0);
		    }
		        } //the class