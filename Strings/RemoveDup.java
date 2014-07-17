/*
Given a string, return recursively a "cleaned" string where adjacent chars that are the same have been reduced to a single char. So "yyzzza" yields "yza". 

stringClean("yyzzza") → "yza"
stringClean("abbbcdd") → "abcd"
stringClean("Hello") → "Helo"
*/

public String stringClean(String str) {
   
 if(str == "" || str.length() == 1)
    return str;

  if(str.charAt(0) == str.charAt(1)){
     return stringClean(str.substring(1));
   }

   return Character.toString(str.charAt(0))+stringClean(str.substring(1));

}
