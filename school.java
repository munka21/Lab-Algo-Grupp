import java.util.Scanner;
import java.util.HashMap;
import java.util.Arrays;

class school{
    public static void main(String [] args) {

        Scanner scanner = new Scanner(System.in);
        HashMap<String, String> map = new HashMap<String, String>();
        int n = scanner.nextInt();
        String g = scanner.nextLine();
        String s = scanner.nextLine();

        String [] names = s.split(" ");
        int erg = 0;
        for (int i = 0; i < n; i++){
            char [] arr = names[i].toCharArray();
            Arrays.sort(arr);
            String str = String.valueOf(arr);^^^
            if(!(map.containsKey(str))){
                map.put(str, str);
                erg++;
            }
        }
        System.out.println(erg);
    }

}