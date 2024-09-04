class Solution
{
    public int solution(int n, int a, int b)
    {
        // 시간 복잡도 Onlogn 적정

        int answer = 0; // 라운드 수 반환
        // 인원 짝수명
        while (a != b) {
            a = (a + 1) / 2;
            b = (b + 1) / 2;
            answer++;
        }


        return answer;
    }
}