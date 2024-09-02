import java.util.*;
class Main {
	public static void main(String[] args) throws Exception {
		// Scanner 사용. int 입력값이 크기 때문에 for을 조심해야됨
		Scanner sc = new Scanner(System.in);
		
		/*추가로 찾아온 사람의 수 와 밴치의 수*/
		int N = sc.nextInt(); // 밴치의수
		int M = sc.nextInt(); // 추가로 찾아온 사람의 수
		
		
		/* 이미 앉아있는 사람의 수 */
		int[] seats = new int[N];
		for(int i = 0; i < N; i++) {
			seats[i] = sc.nextInt();
		}
		
		sc.close();
		
		int max = seats[0];
		int sum = 0;
		
		// 이미 많이 앉아 있는 벤치에 사람을 모두 몰아 넣으면 최대가 됨
		// 최소는 모두 더한다음 벤치에 균등하게 나눌 예정
		for (int i = 0; i < N; i++) {
			if (seats[i] > max) { 
				max = seats[i]; // 더 큰 벤치로 바꿔가면서 가장 많이 앉은 벤치 찾기
			}
			sum += seats[i]; //
		}
		
		int maxSeat = max + M; // 찾아 온사람을 다 넣음
		
		int minSeat = (sum + M) / N; // 벤치에 균등하게 나눔
		if ((sum + M) % N != 0) { // 0이 아니면 나머지 인원이 있기 때문에 인원 추가 진행
			minSeat++;
		}
		System.out.print(minSeat + " " + maxSeat);
	
	}
}