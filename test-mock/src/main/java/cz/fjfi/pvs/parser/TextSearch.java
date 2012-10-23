package cz.fjfi.pvs.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * http://www.dreamincode.net/code/snippet1761.htm
 * 
 * found here
 * 
 * @author Jan Doubek
 *
 */

public class TextSearch {

    static void search(String fileName,String str){
        try{
            File file = new File(fileName);
            String temp = "";
            FileReader ins = new FileReader(file);
            BufferedReader bf = new BufferedReader(ins);
            Pattern pattern = Pattern.compile(str);
            while((str = bf.readLine())!= null) {
                temp += str;
            }

            Matcher matcher = pattern.matcher(temp);

            while(matcher.find()) {
               System.out.println("The pattern starts at " + (matcher.start()+1) + " and ends at " + (matcher.end()+1));
            }
        } catch (Exception e) {}
    }

    public static void main(String[] args) {
        search("e:\\sample","bc");
    }
}