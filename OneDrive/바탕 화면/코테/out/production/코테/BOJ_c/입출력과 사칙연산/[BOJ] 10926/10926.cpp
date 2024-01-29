#include <stdio.h>
#include <string.h>

int main(void){

    char nick[50]="";
    char str[4]="??!";

    scanf("%s",&nick);
    strcat(nick,str);
    printf("%s",&nick);
    return 0;
}