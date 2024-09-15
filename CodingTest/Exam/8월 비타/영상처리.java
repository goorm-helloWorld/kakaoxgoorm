import java.io.*;
import java.util.*;

class Main {
	static int n, m;
	static char[][] photo;	// 사진을 char 2차원 배열로
	static boolean[][] visited;	// # 발견후 탐색 시 방문 여부
	
	// 상하좌우
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		photo = new char[m][n];
		visited = new boolean[m][n];
		
		for(int i=0; i<m; i++) {
			photo[i] = br.readLine().toCharArray();
		}
		br.close();
		
		int objectCount = 0;
		int maxSize = 0;
		
		for(int i=0; i<m; i++) {
			for(int j=0; j<n; j++) {
				// # 발견시 상하좌우 탐색
				if( photo[i][j] == '#' && !visited[i][j] ) {
					int size = dfs(i, j);
					objectCount++;
					maxSize = Math.max(maxSize, size);
				}
			}
		}
		
		System.out.println(objectCount);
		System.out.println(maxSize);
	}
	
	public static int dfs(int x, int y) {
		visited[x][y] = true;
		int size = 1;
		
		for(int i=0; i<4; i++) {
			// 상 -> 하 -> 좌 -> 우 순서로 재귀 탐색
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			// 2차원 배열 내의 범위 안에 있고, 해당 위치가 # 이며, 방문한 적이 없는 좌표로 탐색
			if( nx >= 0 && nx < m && ny >= 0 && ny < n
			  && photo[nx][ny] == '#' && !visited[nx][ny] ) {
				size += dfs(nx, ny);
			}
		}
		
		return size;
	}
}