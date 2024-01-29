#include <stdio.h>

int main(void){
    int num1,num2;
    scanf("%d",&num1);
    scanf("%d",&num2);

    int subnum[3];
    int bnum = num2;

    subnum[0] = bnum%10;
    bnum=bnum/10;
    subnum[1] = bnum%10;
    bnum=bnum/10;
    subnum[2] = bnum%10;

    printf("%d\n",subnum[0]*num1);
    printf("%d\n",subnum[1]*num1);
    printf("%d\n",subnum[2]*num1);
    printf("%d\n",num1*num2);    
    return 0;
}
