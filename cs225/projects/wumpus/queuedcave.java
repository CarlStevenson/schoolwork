// queuestuff.java
// queuestuff.java provides a means to keep track of the source direction 
// when using the queue in the pathfinding algorithm.
// idea suggested to me by Troy Wynn
// Written by: Carl Stevenson


class queuedcave extends cave{

    // cave is the queued cave
    cave daCave;
    // source is the direction it came from
    int source;
    // idnumber from cave
    int idnumber;
    

    queuedcave(cave inCave, int inSource){
    
        daCave = inCave;
        source = inSource;
        idnumber = inCave.idnumber;
    
    }

    public cave getCave(){
    
        return daCave;
    }
    
    public int getSource(){
    
        return source;
    }

}
