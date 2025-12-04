public abstract class AbstractDAO<E extends AbstractEntity> {

//Adding this method to the AbstractDAO class
   public void setTestDB(){
     this.Database ="music_db_test"; //Adding _test to DB Name
}
