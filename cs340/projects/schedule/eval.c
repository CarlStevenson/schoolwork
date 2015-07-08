/************************************************  file f1.c  ****/

// eval.c
// written by Carl Stevenson
// evaluation function written for the Genesis genetic algorithm software
// Requires a structure length of 1280 bits.


    
double eval(str, length, vect, genes)
char str[];	/* string representation			*/
int length;	/* length of bit string				*/
double vect[];	/* floating point representation		*/
int genes;	/* number of elements in vect			*/
{



	double sum;
	sum = 0.0;
	//   [worker][slot][preference]
    int sched[4][8][5] = {{{10,10,10,10,10},{9,9,9,9,9},{8,8,8,8,8},{1,1,1,1,1},{1,1,1,1,1},{1,1,1,1,1},{1,1,1,1,1},{1,1,1,1,1}},{{10,9,8,7,6},{10,9,8,7,6},{10,9,8,7,6},{10,3,3,3,3},{1,1,1,1,1},{1,2,3,4,5},{1,2,3,4,5},{1,2,3,4,5}},{{10,10,10,10,10},{9,9,9,9,9},{8,8,8,8,8},{0,0,0,0,0},{1,1,1,1,1},{1,1,1,1,1},{1,1,1,1,1},{1,1,1,1,1}},{{10,9,8,7,6},{10,9,8,7,6},{10,9,8,7,6},{10,3,3,3,3},{1,1,1,1,1},{1,2,3,4,5},{1,2,3,4,5},{1,2,3,4,5}}};
    // done setting up the schedule
    // start doing the actual work
    // set up the assigned schedule based on the gene
    // 0 means person does not work, anything else means they do
    int assigned[4][8][5];
    int count;
    int i;
    int j;
    int k;
    // conse is used to measure consequent hours worked
    int conse;
    conse = 0;
    count = 0;
    for(i=0; i<4; i++){
        for(j=0; j<8; j++){

            for(k=0; k<5; k++){

                if(sched[i][j][k] == 0){
                    assigned[i][j][k] = 0;
                    count++;
                }
                else if(sched[i][j][k] ==1 && str[count]%10 ==0){
                    
                    assigned[i][j][k] = 1;
                    count++;
                }
                else{
                    
                    if(conse=0){
                        conse++;
                        assigned[i][j][k] = str[count]%sched[i][j][k];
                        count++;
                    }
                    if(conse=1){
                        conse++;
                        assigned[i][j][k] = str[count]%sched[i][j][k];
                        count++;
                        }
                    if(conse=2){
                        
                        conse = 0;
                        count++;
                    }
                    if(str[count]%sched[i][j][k] == 0){
                        conse = 0;
                    }
                }
            }
        }
    }
    // now that we have the assigned schedule, calculate its goodness.
    // hpw keeps track of hours per week
    int hpw[] = {0,0,0,0};
    // conse is used to measure consequent hours worked
    conse = 0;
    // slot1 and slot2 checks to see if the person has at least slot 4 or 5
    // off for lunch
    int slot1,slot2;
    // atl1 makes sure there is always one person working each shift
    int atl1[8][5] = {{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0}};
    
    // measure the hours for the week, and the coverage
    // meaure how well each employee was scheduled based on their preferences
    for(i=0; i<4; i++){
        for(j=0; j<8; j++){

            for(k=0; k<5; k++){
    
                if(assigned[i][j][k] >0){
                
                    hpw[i]++;
                    atl1[j][k]++;
                    sum += sched[i][j][k];
                    
                }
                
            }
        }
    }
    for(i=0; i<4; i++){
    
        if(hpw[i]>20){
            sum -= 100;
        }
    }    
    for(j=0;j<8;j++){
    
        for(k=0;k<5;k++){
            if(atl1[j][k]-1 !=0){
                sum -= 5*(abs(atl1[j][k]-1));
                
            }
        }
    }
    // check for more than 2 slots in a row worked, penalize accordingly
    for(i=0; i<4;i++){
        conse = 0;
        for(j=0; j<5; j++){
            conse = 0;
            for(k=0; k<8;k++){
            
                if(assigned[i][k][j]>0){
                    if(conse=0){conse++;}
                    if(conse=1){conse++;}
                    if(conse=2){
                        sum -= 10;
                        conse = 0;
                        
                    }
                }
                if(assigned[i][k][j]==0){
                    conse =0;
                }
            }
        }
    } 
    // check to see if the employee has at least 4 or 5 off for work
    for(i=0; i<4; i++){
    
        for(j=0;j<5;j++){
        
            slot1=0;
            slot2=0;
            
            if(assigned[i][4][j]>0){
                slot1=1;
            }
            if(assigned[i][5][j]>0){
                slot2 = 1;
            }
            if(!(slot1 || slot2)){
                sum-=10;
                
            }
        }
    }
    
   
	return (sum*-1);
}

/************************************************ end of file ****/
