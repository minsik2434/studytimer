#include <stdio.h>

int main(void){
    
    double a,b;
    double result;
    scanf("%lf",&a);
    scanf("%lf",&b);
    result = a/b;
    printf("%.10lf",result);

    return 0;
}