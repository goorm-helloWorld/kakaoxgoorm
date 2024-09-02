import java.io.*;
class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");		
		int N = Integer.parseInt(input[0]);
		String T = input[1];
		
		br.close();
		
		for (int r = 2; r <= 16; r++) {
			try {
				int result = Integer.parseInt(T, r);
				if (result == N) {
					System.out.print(r);
					return; // 7의 테스트에서 문제가 되기에 출력값이 정해지면 return 진행
				}
			} catch(NumberFormatException e) {
				// parseInt 포맷에 대한 Exception이 테스트에서 실행되었음 -> try catch 진행
			}
		}
	}
}