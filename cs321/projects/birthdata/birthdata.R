daFile = file(description = "./birthData.csv")
daTable = read.csv(daFile)

birthTime = with(daTable, Minutes.after.midnight)
sex = with(daTable, Sex)



# initialize the needed components to get the needed data
previous = birthTime[1]
current = birthTime[2]
betweenBirths = current-previous

birthsAHour = c(1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0)

girls = 2
boys = 0

#get the needed data
for(i in 3:length(birthTime)){

      #print(i)
      #print(birthTime[i])
      previous = birthTime[i-1]
      current = birthTime[i]
      betweenBirths = c(betweenBirths, current-previous)
      
      birthsAHour[floor(current/60)] = birthsAHour[floor(current/60)]+1
      if(sex[i] ==1){
      		girls = girls +1
      }
      else{
		boys = boys +1
      }
      

}



