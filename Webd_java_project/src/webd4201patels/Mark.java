package webd4201patels;
import java.text.DecimalFormat;
/**
 * Mark - This is the mark.java class file entends from User class.
 * @author Shan Patel  
 * @version 1.0 
 * @since 1.0
 */


public class Mark extends User{

    /** Constant for minimum GPA */
    public static final float MINIMUM_GPA = 0.0f;
    /** Constant for maximum GPA */
    public static final float MAXIMUM_GPA = 5.0f;
    /** Constant for GPA format */
    public static final DecimalFormat GPA_FORMAT = new DecimalFormat("0.00");

    /** Instance variable for course code */
    private String courseCode;

    /** Instance variable for course name */
    private String courseName;

    /** Instance variable for result */
    private int result;

    /** Instance variable for GPA weighting */
    private float gpaWeighting;

    /**
     * Getter for the course code
     * @return course code for the mark */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * setter for the course code
     * @param courseCode for the mark */
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    /**
     * getter for the course name
     * @return course name for the mark */
    public String getCourseName() {
        return courseName;
    }

    /**
     * setter for the course name
     * @param courseName for the mark */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * getter for the result
     * @return result for the mark */
    public int getResult() {
        return result;
    }

    /**
     * setter for the marks
     * @param result for the mark */
    public void setResult(int result) {
        this.result = result;
    }

    /**
     * getter for the GPA
     * @return GPA weighting for the mark */
    public float getGpaWeighting() {
        return gpaWeighting;
    }

    /**
     * setter for the GPA
     * @param gpaWeighting for the mark */
    public void setGpaWeighting(float gpaWeighting) {
        this.gpaWeighting = gpaWeighting;
    }


    /**
     * Default constructor
     * @param courseCode for the associated student
     * @param courseName for the associated student
     * @param result for the associated student
     * @param gpaWeighting for the associated student
     * @throws InvalidUserDataException when user data invalid
     */
    public Mark(String courseCode, String courseName, int result, float gpaWeighting) throws InvalidUserDataException{

    	this.setCourseCode(courseCode);

    	this.setCourseName(courseName);

    	this.setResult(result);

    	this.setGpaWeighting(gpaWeighting);

    }

   
//    public Mark(long id, String password, String firstName, String lastName, String emailAddress, Date lastAccess, Date enrolDate, boolean enabled, char type, String courseCode, String courseName, int result, float gpaWeighting) throws InvalidUserDataException {
//        super(id, password, firstName, lastName, emailAddress, lastAccess, enrolDate, enabled, type);
//        this.courseCode = courseCode;
//        this.courseName = courseName;
//        this.result = result;
//        this.gpaWeighting = gpaWeighting;
//    }


    /**
     * This method is used to display the mark details
     * @return student's mark details
     */
    public String toString() {
        return courseCode + "\t" +
                String.format("%-35s", getCourseName()) +
                getResult() + "\t\t" +
                GPA_FORMAT.format(getGpaWeighting());
    }




}
