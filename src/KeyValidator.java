// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP102 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP102 - 2024T1, Assignment 2
 * Name: Jack Scrivener
 * Username: scrivejack
 * ID: 300658748
 */

 import ecs100.*;
 import java.awt.Color;


 /**
  * Asks user for a key word and checks whether it meets a set of rules or not.
  */
 
 public class KeyValidator {
 
     /**
      * Asks user for key word and then checks if it is a valid key word.
      */
     public void checkKey(){
         UI.clearText();
         String key = UI.askString("Key:   ");
         UI.println();
         this.validateKey(key);
     }
 
     /**
      * Report that the key is valid or report ALL the rules that the key failed.
      */
     public void validateKey(String key){

         String error_list_errors[] = {"Password too short","Password too long","Password starts with !",
         "Password starts with ?","Password contains @","Password needs to contain capital",
         "Password needs to contain lower case"};
         boolean error_list_numbers[] = {false,false,false,false,false,true,true};

         if(key.length() < 6){
            error_list_numbers[0] = true;
         }
         if( key.length() > 12 ){
            error_list_numbers[1] = true;
         }
         if(key.startsWith("!")){
            error_list_numbers[2] = true;
         }
         if(key.startsWith("?")){
            error_list_numbers[3] = true;
         }
         for(int i =0; i < key.length(); i+=1){
            char current_char = key.charAt(i);
            if(current_char == '@'){
                error_list_numbers[4] = true;
            }
            if(Character.isUpperCase(current_char)){
                error_list_numbers[5] = false;
            }
            if(Character.isLowerCase(current_char)){
                error_list_numbers[6] = false;
            }
         }


         boolean no_errors = true;
         for(int k = 0; k < error_list_numbers.length; k +=1){
            if(error_list_numbers[k]){
                UI.println(error_list_errors[k]);
                no_errors = false;
            }
         }
         if(no_errors){
            UI.print("No issues detected with pass word");
         }
 
     }

     public void checkKeyChallange(){
        UI.clearText();
        String name = UI.askString("name:   ");
        String key = UI.askString("Key:   ");
        UI.println();
        this.validateKeyChallange(key,name);
    }

    public void validateKeyChallange(String key,String name){

        String error_list_errors[] = {"Password too short","Password too long","Password starts with !",
        "Password starts with &","Password contains @","Password needs to contain capital",
        "Password needs to contain lower case","Password needs to contain both a letter and number",
        "Password can not contain your name in senquince"};
        boolean error_list_numbers[] = {false,false,false,false,false,true,true,true,false};
        boolean number = false;
        boolean letter = false;

        if(key.length() < 6){
           error_list_numbers[0] = true;
        }
        if( key.length() > 12 ){
           error_list_numbers[1] = true;
        }
        if(key.startsWith("!")){
           error_list_numbers[2] = true;
        }
        if(key.startsWith("&")){
           error_list_numbers[3] = true;
        }
        for(int i =0; i < key.length(); i+=1){
           char current_char = key.charAt(i);
           if(current_char == '@'){
               error_list_numbers[4] = true;
           }
           if(Character.isUpperCase(current_char)){
               error_list_numbers[5] = false;
           }
           if(Character.isLowerCase(current_char)){
               error_list_numbers[6] = false;
           }
           if(Character.isDigit(current_char)){
               number = true;
           }
           if(Character.isAlphabetic(current_char)){
               letter = true;
           }
        }
        //Checks if there the letter and number varables are true and if so it stops the letter and number error message
        if(number && letter){
           error_list_numbers[7] = false;
        }

        boolean failed = false;
        String key_with_blanks = key;
        int char_counter[] = new int[name.length()];
 
        //This checks if the name is inside the string 
        for(int char_in_name =0; char_in_name < name.length(); char_in_name +=1){
           for(int char_in_key=0; char_in_key < key.length(); char_in_key +=1){
               if(key_with_blanks.charAt(char_in_key) == name.charAt(char_in_name)){
                   char_counter[char_in_name] += 1;

                   char[] key_with_blanks_char_list = key_with_blanks.toCharArray();
                   for(int k = 0; k < char_in_key; k+=1){
                       key_with_blanks_char_list[k] = ' ';
                   }
                   key_with_blanks = String.valueOf(key_with_blanks_char_list);
                   break;
               }else{
                   failed = true;
               }
           }

        }

        //sets failed back to false if not all the charicters of the name are present in the key 
        for(int unique_char = 0; unique_char < name.length(); unique_char +=1){
           if(char_counter[unique_char] == 0){
               failed = false;
           }
        }

        if(failed){
           error_list_numbers[8] = true;
        }

        boolean no_errors = true;
        for(int k = 0; k < error_list_numbers.length; k +=1){
           if(error_list_numbers[k]){
               UI.println(error_list_errors[k]);
               no_errors = false;
           }
        }
        if(no_errors){
           UI.print("No issues detected with pass word");
        }

    }
 
 
     /**
      * Setup GUI and buttons
      */
     public void setupGUI(){
         UI.initialise();
         UI.addButton("Clear", UI::clearText );
         UI.addButton("Check Key", this::checkKey );
         UI.addButton("Check Key (challange)", this::checkKeyChallange );
         UI.addButton("Quit", UI::quit );
         UI.setDivider(1);       // Expand the text area
     }
 
     /**
      * Create object and call setupGUI on it
      */ 
     public static void main(String[] args){
         KeyValidator kv = new KeyValidator();
         kv.setupGUI();
     }
 }
 