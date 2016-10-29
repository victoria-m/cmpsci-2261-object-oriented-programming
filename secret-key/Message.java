import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Message {
   private StringBuilder text = new StringBuilder("");
   private StringBuilder secretKey = new StringBuilder("  -   -  ");

   Message() {
   };

   Message(StringBuilder text, StringBuilder secretKey) {
      this.text = text;
      this.secretKey = secretKey;
   }

   void setSecretKey(StringBuilder secretKey) {
      this.secretKey = secretKey;
   }

   StringBuilder getSecretKey() {
      return this.secretKey;
   }

   void setText(StringBuilder text) {
      this.text = text;
   }

   StringBuilder getText() {
      return this.text;
   }

   void readText() {
      Scanner scan = new Scanner(System.in);

      this.setText(new StringBuilder(scan.nextLine()));

      scan.close();
   }

   void encryptText() {

      // Step 1: remove all punctuation except for very end ? or .

      ArrayList<Character> punctuation = new ArrayList<>(
            Arrays.asList('\'', ':', ',', '-', '-', '.', '!', '(', ')', '?', '\"', ';'));

      for (int i = 0; i < text.length(); ++i) {

         if (punctuation.contains(this.text.charAt(i))) {

            // only remove punctuation if NOT ? OR . at the very end
            if (!((i == text.length() - 1) && (this.text.charAt(i) == '?' || this.text.charAt(i) == '.'))) {
               this.text.deleteCharAt(i);

               // go back to previous position since current char was deleted,
               // meaning the length of the string is now 1 less than before
               --i;
            }
         }
      }

      // Step 2: convert phrase to lowerCase

      this.setText(new StringBuilder(this.text.toString().toLowerCase()));

      // Step 3: the english phrase should be split up into words
      // and each word encrypted separately (using the secret key)

      // split phrase up into words
      ArrayList<String> words = new ArrayList<>(Arrays.asList(this.text.toString().split(" ")));

      // Step 3.1:

      ArrayList<Character> vowels = new ArrayList<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));

      for (int i = 0; i < words.size(); ++i) {

         // if first char is vowel, keep first char in place but add
         // y1x3x4 to the end of word
         if (vowels.contains(words.get(i).charAt(0)))
            words.set(i, words.get(i).substring(0, words.get(i).length()) + this.secretKey.substring(3, 6));

         // otherwise, move first char of the word to end then of the word
         // then add x1x2 to the end of word
         else
            words.set(i, words.get(i).substring(1, words.get(i).length()) + words.get(i).charAt(0)
                  + this.secretKey.substring(0, 2));
      }

      StringBuilder temp = new StringBuilder("");

      for (String word : words)
         temp.append(word + " ");

      this.setText(temp);

      // Step 3.2:

      // create new StringBuilder object from encrypted words
      // to keep track of position relative to entire phrase

      // go through entire phrase
      for (int i = 0; i < this.getText().length(); ++i) {

         // if position is multiple of z, insert the special char y2
         if ((i % Character.getNumericValue(this.secretKey.charAt(7)) == 0) && (i != 0)) {
            this.getText().replace(0, this.getText().length(), this.getText().substring(0, i) + this.secretKey.charAt(8)
                  + this.getText().substring(i, this.getText().length()));
         }
      }

   }

   void decryptCode() {
      // decrypt the code according to the secret key
      // decrypted code is lower-case and punctuation-free
      // except for end ? or .

      // Step 1: remove the special char y2 from positions that are
      // multiples of z

      // used to store new string
      StringBuilder temp = new StringBuilder("");

      // go through entire phrase
      for (int i = 0; i < this.getText().length(); ++i) {

         // append char to temp if char at i is not a multiple of z
         if (!(i % Character.getNumericValue(this.secretKey.charAt(7)) == 0) || i == 0)
            temp.append(this.getText().charAt(i));
      }

      this.setText(temp);

      // Step 2:

      // split phrase up into words
      ArrayList<String> words = new ArrayList<>(Arrays.asList(this.text.toString().split(" ")));

      for (int i = 0; i < words.size(); ++i) {

         // if y1x3x4 is at the end of the word, remove it from word
         
         if (words.get(i).substring(words.get(i).length() - 3, words.get(i).length())
               .equals(this.getSecretKey().substring(3, 6)))
            words.set(i, words.get(i).substring(0, words.get(i).length() - 3));

         // if x1x2 is at the end of the word
         else {
            // remove x1x2 from end
            words.set(i, words.get(i).substring(0, words.get(i).length() - 2));
            
            // move last char to beginning
            words.set(i, words.get(i).charAt(words.get(i).length() - 1) + words.get(i).substring(0, words.get(i).length() - 1));
         }
      }

      temp = new StringBuilder("");

      for (String word : words)
         temp.append(word + " ");

      this.setText(temp);

   }

   void generateSecretKey() {

      /*
       * sets secret key to format: x1x2-y1x3x4-zy2 where: - x1, x2, x3, and x4
       * are randomly generated upper case alphabet chars - y1 and y2 are
       * randomly generated special chars - z is a randomly generated int
       * between 2 and 5 (inclusive)
       */

      Random rand = new Random();

      // CHECK ALL212 GENERATORS

      // randomly generate alphabet char
      this.secretKey.setCharAt(0, (char) (rand.nextInt(26) + 'a'));
      this.secretKey.setCharAt(1, (char) (rand.nextInt(26) + 'a'));
      this.secretKey.setCharAt(4, (char) (rand.nextInt(26) + 'a'));
      this.secretKey.setCharAt(5, (char) (rand.nextInt(26) + 'a'));

      // randomly generate special chars
      this.secretKey.setCharAt(3, (char) (rand.nextInt(15) + '!'));
      this.secretKey.setCharAt(8, (char) (rand.nextInt(15) + '!'));

      // randomly generate int between 2 and 5
      this.secretKey.setCharAt(7, (char) (rand.nextInt(4) + '2'));

   }

   // verify that the secret key matches the format x1x2-y1x3x4-zy2
   boolean verifySecretKey(StringBuilder secretKey) {
      return secretKey.toString().matches("[a-zA-Z][a-zA-Z]-[^a-zA-Z0-9][a-zA-Z][a-zA-Z]-[2-5][^a-zA-Z0-9]");
   }

}
