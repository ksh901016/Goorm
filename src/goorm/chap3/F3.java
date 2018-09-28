package goorm.chap3;

import java.util.Scanner;

public class F3 {
	// 연속한 영역의 숫자들을 더하는 계산은 반복문을 통해 간단히 수행할 수 있다.
	// 하지만 일반적인 계산과 배열의 차이점은 비슷한 계산이 반복될 때 성능이 다르다는 것이다.
	// 비슷한 계산을 반복해야 할 때 배열을 통해 간소화하는 방법을 고민해보자 (누적합 배열을 만든다!)
	public static final Scanner scanner = new Scanner(System.in);
	
	public static Range getBestRange(int n, int m, int[] cards, Range[] ranges) {
		Range answer = ranges[0];
		
		// 누적 합 배열
		long[] rangeSum = new long[n+1];
		for(int i=1; i<=n; i++){
			rangeSum[i] = rangeSum[i-1] + cards[i];
		}
		
		long maxScore = 0;
		
		for(Range r : ranges){
			long rangeScore = rangeSum[r.right] - rangeSum[r.left-1];
			if(maxScore < rangeScore){
				maxScore = rangeScore;
				r.totalPoint = rangeScore;
				answer = r;
			}
		}
		
		return answer;
	}

	public static void main(String[] args) throws Exception {
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		int[] cards = new int[n+1];
		Range[] ranges = new Range[m];

		// 각 카드의 정보를 입력받는다.
		for(int i = 1 ; i <= n ; i ++)
		{
			cards[i] = scanner.nextInt();
		}

		// 팬들의 정보를 입력받는다.
		for(int i = 0 ; i < m; i ++)
		{
			int l = scanner.nextInt();
			int r = scanner.nextInt();
			ranges[i] = new Range(i + 1, l, r);
		}

		//범위의 합이 가장 큰 범위를 계산한다.
		Range answer = getBestRange(n, m, cards, ranges);

		System.out.printf("%d %d\n", answer.index, answer.totalPoint);
	}

}

class Range{
	int index;
	int left;
	int right;
	long totalPoint;
	Range(int index, int left, int right)
	{
		this.index = index;
		this.left = left;
		this.right = right;
		this.totalPoint = 0;
	}
}