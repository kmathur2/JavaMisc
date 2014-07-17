
/*This class puts star between to consecutive characters in a string
hello -> hel*l0 

*/
public String pairStar(String str) {
  if(str == "" || str.length() == 1)
    return str;

  if(str.charAt(0) == str.charAt(1)){
     return Character.toString(str.charAt(0))+"*"+pairStar(str.substring(1));
   }
   
   return Character.toString(str.charAt(0))+pairStar(str.substring(1));
   
}

