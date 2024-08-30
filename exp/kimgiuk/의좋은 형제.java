import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n1 = Integer.parseInt(st.nextToken());
		int n2 = Integer.parseInt(st.nextToken());
		
		int d = Integer.parseInt(br.readLine());
		br.close();
		
		boolean check = true;
		
		for(int i=0; i<d; i++) {
			if(check) {
				if(n1%2 == 1) {
					n1 = n1/2;
					n2 = n2 + n1 + 1;
					check = false;
				} else {
					n1 = n1/2;
					n2 = n2 + n1;
					check = false;
				}
			}
			else if(!check) {
				if(n2%2 == 1) {
					n2 = n2/2;
					n1 = n1 + n2 + 1;
					check = true;
				} else {
					n2 = n2/2;
					n1 = n1 + n2;
					check = true;
				}
			}
		}
		
		System.out.print(n1 + " " + n2);
	}
}