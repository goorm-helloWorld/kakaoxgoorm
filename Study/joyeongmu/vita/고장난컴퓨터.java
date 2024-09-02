import java.io.*;
class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		

		/**
		* 코드 수와 공백 관련
		*/
		String[] input = br.readLine().split(" "); // 입력할 코드의 수와 유지 시간이 공백을 통하여 입력됨
		
		int N = Integer.parseInt(input[0]); // 혜지가 입력할 코드의 수
		
		int c = Integer.parseInt(input[1]); // 유지 시간
		
		/**
		* 코드 관련
		* 1 2 3 4 5 6 7 공백으로 주어 짐
		* 공백을 나누어 배열에 저장해야된다.
		*/
		String[] input2 = br.readLine().split(" ");
		int[] codes = new int[N]; // 혜지가 입력할 코드 생성
		
		// 입력 숫자에 따른 혜지의 코드 입력
		for (int i = 0; i < codes.length; i++) {
			codes[i] = Integer.parseInt(input2[i]); // 공백을 기준으로 나눈 배열을 코드에 넣어준다.
		}
		
		// 입력 종료
		br.close();
		
		// 혜지의 남아 있는 코드 카운트 -> 1개의 입력 1초일 경우 카운트 1로 초기화 
		int count = 1; 
		
		for (int i = 0; i < codes.length - 1; i++) {
			if(codes[i + 1] - codes[i] > c) { // n 과 n+1 의 차이가 c 보다 크면 기존 코드를 없앤다.
				count = 1; // 코드가 사라지고 마지막 문자만 남아야 하기 때문에
			} else {
				count++;
			}
		}
		System.out.print(count);
	}
}