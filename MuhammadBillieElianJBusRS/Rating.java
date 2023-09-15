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
        this.total=this.total+rating;
        this.count=this.count+1;
    }   
    
    public double getAverage(){
        return this.total/this.count;
    }
    
    public long getCount(){
        return this.count;
    }
    
    public long getTotal(){
        return this.total;
    }
}
