import java.io.*;                                                                           
import java.util.*;                                                                          
class Main {                                                                               
  public static void main(String[] args) {      
    String word = pickWord();                     
    //Prompt user input and set it to a variable
    Scanner input = new Scanner(System.in);                                         
    String reply = "";                                                                         
    System.out.println("\nWordle: Get the five letter word in six tries.\n\n[_, _, _, _, _]"); 
    //Give the user 6 attempts
    for (int i = 0;i<6;i++) {                                                                  
      int checker = 0;                                                                        
      System.out.print("\nType your five letter word => ");                                   
      reply = input.nextLine();       
      //Check if the user's input is 5 letters
      if (reply.length()<5 || reply.length()>5){                                              
        i--;                          
        System.out.println("Letter Count Insufficient");
      }
      //Check the solve using the checkSolve Function and print out return value
      else{
        String[] wordle = checkSolve(reply,word);
        for (int x = 0; x<5;x++){
          if (wordle[x].contains("_")){
            System.out.println("Incorrect\n" + Arrays.toString(wordle));
            break;
          }
          else{
            checker++;
            continue;
          }
        }
        //Win Condition
        if (checker==5){
          System.out.println("\nYou Win!\n" + Arrays.toString(wordle));
          break;
        }
      }
    }
    input.close();
    System.out.println("The word was: " + word);
    System.out.println("Game Over");
  }
  //Function: picks and returns a word at random from the list provided
  static String pickWord(){
    //List of all possible target words. Adding more words can be done easily
    String[] wordList = {"eight","dream","guard","adult","sight","force","wound","drive","close","eagle","joker","equip","jolly","chump","froze","chunk","climb","champ","dozen","crack","black","green","dazed","fixed","equal","ninja","brick"};
    int wordIndex = (int)(Math.random() * (wordList.length));
    return wordList[wordIndex];
  }
  //Function: checks if the word matches the target and returns an array of correct letter indexes
  static String[] checkSolve(String answer, String target){
    String containedLetters="";
    String[] wordle = {"_","_","_","_","_"};
    //Iterate through each letter the word to check if the letter is matched in the word
    for (int i = 0;i<5;i++){
      String letter = ""+answer.charAt(i);
      //Checks if the word contains the letters 
      if (target.contains(letter)){
        //Checks if the matched letter is in the correct position
        if (target.indexOf(letter)==answer.indexOf(letter)){
          wordle[target.indexOf(letter)]=letter;
          answer = answer.substring(0,i)+ " " + answer.substring(i+1);
          target = target.substring(0,i)+ " " + target.substring(i+1);
        }
        //If it not in the correct position, add it to the containedLetters list
        else{
          answer = answer.substring(0,i)+ " " + answer.substring(i+1);
          if (!containedLetters.contains(letter)){
            containedLetters+=letter;
          }
          else{
            continue;
          }
        }
      }
      else{
        continue;
      }
    }
    //Print out containedLetters and return the array
    System.out.println("\nThe Word Contains the Letters: "+ containedLetters);
    return wordle;
  }
}