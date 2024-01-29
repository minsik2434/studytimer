/*
문제
n이 주어졌을 때, 1부터 n까지 합을 구하는 프로그램을 작성하시오.

입력
첫째 줄에 n (1 ≤ n ≤ 10,000)이 주어진다.

출력
1부터 n까지 합을 출력한다.
*/

#include <stdio.h>

int main(void){
    int num;
    scanf("%d",&num);
    int sum=0;

    for(int i=1; i<=num; i++){
        sum = sum +i;
    }
    printf("%d",sum);
    return 0;
}