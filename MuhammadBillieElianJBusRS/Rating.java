package MuhammadBillieElianJBusRS;


/**
 * Write a description of class Rating here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
class Rating {
    private long count;
    private long total;

    public Rating(){
        this.total = 0;
        this.count = 0;
    }
    
    public void insert(int rating){
        //long temp_total;
        //temp_total=this.total+rating;
        total=total+rating;
        count=count+1;
    }   
    
    public double getAverage(){
        return total/count;
    }
    
    public long getCount(){
        return count;
    }
    
    public long getTotal(){
        return total;
    }
}
