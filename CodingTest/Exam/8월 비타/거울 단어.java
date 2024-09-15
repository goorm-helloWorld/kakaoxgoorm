import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		
		String[] arr = new String[t];
		for(int i=0; i<t; i++) {
			arr[i] = br.readLine();
		}
		br.close();
		
		HashMap<Character, Character> map = new HashMap<> ();
		map.put('b', 'd');
		map.put('i', 'i');
		map.put('l', 'l');
		map.put('m', 'm');
		map.put('n', 'n');
		map.put('o', 'o');
		map.put('p', 'q');
		map.put('s', 'z');
		map.put('u', 'u');
		map.put('v', 'v');
		map.put('w', 'w');
		map.put('x', 'x');
		
		map.put('d', 'b');
		map.put('q', 'p');
		map.put('z', 's');
		
		for(String s : arr) {
			if(checkMirror(s, map)) {
				System.out.println("Mirror");
			} else {
				System.out.println("Normal");
			}
		}
	}
	
	public static boolean checkMirror(String word, HashMap<Character, Character> map) {
		int len = word.length();
		for(int i=0; i<len/2; i++) {
			char leftChar = word.charAt(i);
			char rightChar = word.charAt(len - 1 - i);
			if(!map.containsKey(leftChar) || map.get(leftChar) != rightChar) {
				return false;
			}
		}
		return true;
	}
}