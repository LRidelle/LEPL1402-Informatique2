public class StringUtils {


    /*
     * Split the input string 'str' w.r.t the character 'marker' in an array of String
     * for example split("test-test", '-') => {"test", "test"}
     * Must return null if there is no occurrence of 'marker' in 'str'
     */
    public static String [] split(String str, char marker){
        int count = 0;
        for(int i=0; i<str.length(); i++){
            if(str.charAt(i) == marker){
                count ++;
            }
        }
        String[] nstr = new String[count + 1];
        int index = 0;
        for(int i=0; i<str.length(); i++){
            if(str.charAt(i)==marker){
                nstr[index] = str.substring(,i-1);
            }
        }
    }


    /*
     * Returns the index of the first occurrence of sub in str
     * or -1 if there is no occurrence of sub in str at all.
     * Be careful, we ask you to make CASE SENSITIVE comparison between str and sub.
     */
    public static int indexOf(String str, String sub){
        int l = sub.length();
        for(int i=0; i<str.length() - l; i++){
            if(str.substring(i,i+l).equals(sub)){
                return i;
            }
        }
        return -1;
    }


    public static String toLowerCase(String str){
        String nStr = "";
        for(int i=0; i<str.length(); i++){
            Character.toLowerCase(str.charAt(i));
        }
    }


    /*
     * Returns true if the string 'str' is a palindrome (a string that reads the same from
     * left to right AND from right to left).
     */
    public static boolean palindrome(String str){
        for(int i=0; i<str.length();i++){
            if(str.charAt(i)!=str.charAt(str.length()-1-i)){
                return false;
            }
        }
        return true;
    }

}
