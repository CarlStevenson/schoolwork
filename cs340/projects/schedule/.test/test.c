    
#include <stdio.h>
void main(){    
    
    //   [worker][slot][preference]
    int sched[][8][5] = {{{10,10,10,10,10},{9,9,9,9,9},{8,8,8,8,8},{1,1,1,1,1},{1,1,1,1,1},{1,1,1,1,1},{1,1,1,1,1},{1,1,1,1,1}},{{10,9,8,7,6},{10,9,8,7,6},{10,9,8,7,6},{10,3,3,3,3},{1,1,1,1,1},{1,2,3,4,5},{1,2,3,4,5},{1,2,3,4,5}},{{10,10,10,10,10},{9,9,9,9,9},{8,8,8,8,8},{0,0,0,0,0},{1,1,1,1,1},{1,1,1,1,1},{1,1,1,1,1},{1,1,1,1,1}},{{10,9,8,7,6},{10,9,8,7,6},{10,9,8,7,6},{10,3,3,3,3},{1,1,1,1,1},{1,2,3,4,5},{1,2,3,4,5},{1,2,3,4,5}}};
    
    // done setting up the schedule
    // start doing the actual work
    // set up the assigned schedule based on the gene
    int assigned[4][8][5];
    int count,i,j,k;
    count = 0;
    for(i=0; i<4; i++){
        for(j=0; j<8; j++){

            for(k=0; k<5; k++){

                //assigned[i][j][k] = str[count]%sched[i][j][k];
                printf("%d", sched[i][j][k]);
            }
        printf("\n");
        }
    }
    return;


}
