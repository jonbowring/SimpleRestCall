package controller;

import model.SimplePost;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Driver {
	
	public static void main(String[] args) {
		
		// Variable declaration
		String restUrl = "", restParams = "", restType = "", outFile = "", response = "";
		boolean print = false;
		SimplePost simplePost = null;
		File file;
		FileWriter fw;
		BufferedWriter bw;
		
		try {
			
			// Assign the arguments
			for(int a = 0; a < args.length; a++) {
				
				// If -url option assign the next arg to the restUrl
				if(args[a].equals("-url")) {
					if((a + 1) <= args.length) {
						a++;
						restUrl = args[a];
					}
				}
				// Else if -params option assign the next arg to the restParams
				else if(args[a].equals("-params")) {
					if((a + 1) <= args.length) {
						a++;
						restParams = args[a];
					}
				}
				// Else if -type option assign the next arg to the restType
				else if(args[a].equals("-type")) {
					if((a + 1) <= args.length) {
						a++;
						restType = args[a];
					}
				}
				// Else if -outfile option assign the next arg to the outfile
				else if(args[a].equals("-outfile")) {
					if((a + 1) <= args.length) {
						a++;
						outFile = args[a];
					}
				}
				// Else if -print option assign the next arg to the print
				else if(args[a].equals("-print")) {
					print = true;
				}
			}
			
			// Check the usage
			if(restUrl.equals("") || restType.equals("")) {
				System.out.println("Usage:\njava -jar SimpleRestCall.jar -url [REST url <mandatory>] -type [type of call (POST) <mandatory>] -params [REST parameters <optional>] -outfile [response file path <optional>] -print <optional>");
				System.exit(0);
			}
			
			// Create the post
			if(restType.toLowerCase().equals("post")) {
				if(!restUrl.equals("") && !restParams.equals("")) {
					// Create the post
					simplePost = new SimplePost(restUrl,restParams);
				}
				else {
					// Create the post
					simplePost = new SimplePost(restUrl);
				}
			}
			
			// Get the response
			response = simplePost.getResponse();
			
			// If the print option was included print the response
			if(print == true) {
				System.out.println(response);
			}
			
			// Write the response to the outfile
			if(!outFile.equals("")) {
				
				// Initialise the file
				file = new File(outFile);
				
				// If the file doesn't exist create a new one
				if(!file.exists()) {
					file.createNewFile();
				}
				
				// Initialise the file and buffer writers
				fw = new FileWriter(file.getAbsoluteFile());
				bw = new BufferedWriter(fw);
				
				// Write the response and then close the file
				bw.write(response);
				bw.close();
			}
		}
		catch(IOException e) {
			System.err.println(e.getMessage());
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}

	}

}
