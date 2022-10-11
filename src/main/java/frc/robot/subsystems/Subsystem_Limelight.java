/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class Subsystem_Limelight extends SubsystemBase {
  /**
   * Creates a new ExampleSubsystem.
   */
  public NetworkTable table;
  public NetworkTableEntry tx;

  private double[] defaultValue = {0.1,0.1,0.1,0.1};
  private int topRight = 0; //NEEDS TO BE CHANGED TO ACTUAL TOP LEFT VALUE
  /*private double left_command;
  private double right_command;
  private double x, thor; //Limelight Values
  private double Kp = Constants.LIMELIGHT_KP;
  private double Ki = Constants.LIMELIGHT_KI; // 0.006
  private double Kd = 0; // 0.006
  private double Kf = Constants.LIMELIGHT_KF;  //feedforward - minimum command signal
*/
  double height_of_limelight = 26.5; //Inches
  double height_of_target = 98; //Inches
  double angle_of_limelight_degrees = 25; // Degrees
  double angle_of_limelight_radians = 25 * (Math.PI/180); //Radians

  //public PIDController limelightpid = new PIDController(Kp,Ki,Kd,Kf);



  public Subsystem_Limelight() {
    table = getLimelightValues();
  }

  public double get_Tx(){
    return table.getEntry("tx").getDouble(0.0);
  }



  //NEW VISION TRACKING WITH 3D CALCULATIONS

  /*This vision code has been written to target the inner goal of the infinite recharge target. There are some constants that this
  relies on to target. Basically, a triangle is constructed using the two gathered measurements from the limelight.
  The first measurement is the angle from the center of the robot the to center of the target. The second angle is 
  from the center of the robot to the rightmost edge of the target. By gathering these angles, as well as the distance to the center of
  the target, we can calculate both the true angle to the target and the distance using primary trig, cosine law, and sine law. */

  /*NOTE: Java naturally uses radians for trig. Due to this, all angle presented are commented with what measurement they are 
  recorded in (Radians or Degrees). */

 

  //This function gathers the top target position (in pixels)                    NEED TO CONFIRM WHAT IS TH ACTUAL MEASUREMENT
  public double get_Right_Top_Tx(){
    double[] array = table.getEntry("tcornx").getDoubleArray(defaultValue);
    return array[topRight];
  }

 
 
  //THIS FIGURES OUT WHERE THE INNER GOAL IS AND THE DISTANCE TO THE INNER GOAL
  public double getOffSetAngle(){
    double angleToTurn = 0; //degrees
    
    /*This gathers the pixel point the right corner is on, and converts it to radians. Then, it subtracts the angle from the center of 
    the robot to the center of the target (also converted to radians)*/
    double angleA = ((get_Right_Top_Tx()* Constants.HORIZONTAL_PIXEL_TO_RADIANS) - (get_Tx()* (Math.PI/180))); //Radians

    double boardAngle = getBoardAngle(angleA);
    double trueDistance = getTrueDistance(angleA);
    
    double innerAngle = Math.asin((Math.sin(boardAngle + Constants.ANGLE_OUTER_TRIANGLE_RADIANS)/trueDistance)*(Constants.DIAGONAL_LENGTH_FROM_OUTER_TO_INNER));
    
    //If the robot needs to turn right, the innerAngle will be smaller than angleA
    if (angleA - innerAngle > 0){
      angleToTurn = -(angleA - innerAngle);
      angleToTurn = angleToTurn * (180/Math.PI);

    }

    //If the robot needs to turn left, the innerAngle will be larger than angleA, resulting in a negative number
    else if (angleA - innerAngle < 0){
      angleToTurn = innerAngle - angleA;
      angleToTurn = angleToTurn * (180/Math.PI);

    }
      

    //The angle that is being returned is used by the limelight to place the center of the target an offset distance
    //These values need to be readjusted in order to use it on the navX
    return angleToTurn; 
  }

  public double getAngleA(){
    double angleA = ((get_Right_Top_Tx() - get_Tx()) * Constants.HORIZONTAL_PIXEL_TO_RADIANS);
    return angleA;
  }


  //Calculates the true distance from the robot to the inner goal
  public double getTrueDistance(double angleA){  
    double middleDistance = getDistance(); //Inches
  
  
    double boardLength = 39.25; // Inches
    double boardAngle = Math.asin((Math.sin(angleA)/boardLength)*(middleDistance));
    
    double b =  Math.PI - angleA - boardAngle; 
    double rightDistance = Math.asin((boardLength/Math.sin(angleA))*(Math.sin(b)));

    double trueDistance = Math.sqrt((rightDistance*rightDistance) + 
                                    (Constants.DIAGONAL_LENGTH_FROM_OUTER_TO_INNER*Constants.DIAGONAL_LENGTH_FROM_OUTER_TO_INNER)
                                    - (2*rightDistance*Constants.DIAGONAL_LENGTH_FROM_OUTER_TO_INNER*(Math.cos(boardAngle + Constants.ANGLE_OUTER_TRIANGLE_RADIANS)))
                                    );
    
      return Math.abs(trueDistance);

  }


  //Returns angle from target to right side of triangle. refer to tracking explanation doc if confused.
  public double getBoardAngle(double a){
    double middleDistance = getDistance(); //Inches
    double boardLength = 39.25; // Inches
    double boardAngle = Math.asin((Math.sin(a)/boardLength)*(middleDistance));
    return boardAngle;

  }

  //END OF NEW VISION CODE

  


  public boolean is_Target(){
    if (table.getEntry("tv").getDouble(0) == 1) {
      //if (RobotContainer.getdebug()) {
      //   SmartDashboard.putString("AI_HAS_TARGET", "YES" );
     // }
  
      return true;
   } else {
    //if (RobotContainer.getdebug()) {
    //  SmartDashboard.putString("AI_HAS_TARGET", "NO" );
    //}
    return false;
   }
  }

  public double get_Thor(){
    return table.getEntry("thor").getDouble(0.0);
  }

  public double get_Ty(){
    return table.getEntry("ty").getDouble(0.0);
  }

  public double getOffsetRatio(){
    double tshort = table.getEntry("tshort").getDouble(0.0);
    double tlong = table.getEntry("tlong").getDouble(0.0);
    double tresult = tlong-tshort;
    double tdistance = RobotContainer.Limelight.getDistance();
    double result = 0;
    if ((tdistance >= 192) && (tdistance <= 242)){
      if(Math.abs(tresult) >= 2){
        if (RobotContainer.drive.gyroGetAngle() >= 2){
          result = 0 - tresult;
        }

        else if (RobotContainer.drive.gyroGetAngle() <= -2){
          result = tresult;
        }

        else{
          result = tresult;
        }
      }
    }
    return result;   
  }




  public double getDistance(){
    double distance = 0;
    double height_of_limelight = 26.5; //Inches
    double height_of_target = 98; //Inches
    double angle_of_limelight = 25; // Degrees

    if (is_Target()){
      double tanValue = Math.tan((angle_of_limelight + get_Ty())*(Math.PI/180));
      //if (RobotContainer.getdebug()) {
      //   SmartDashboard.putNumber("Tan Value", tanValue);
      //   SmartDashboard.putNumber("The angle", angle_of_limelight + get_Ty());
     // }
      distance = (height_of_target - height_of_limelight) / Math.tan((angle_of_limelight + get_Ty())*(Math.PI/180));
      distance = Math.abs(distance);
    }
    return distance;
  }

  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }


  public NetworkTable getLimelightValues() {
 
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");

    //SmartDashboard.putNumber("Limelight TX", table.getEntry("tx").getDouble(0));
 
    //double x = tx.getDouble();


    //read values periodically

    //post to smart dashboard periodically
    //SmartDashboard.putNumber("LimelightX", tx);
    //SmartDashboard.putNumber("LimelightY", ty);

    return table;
  }

  public void limelightOff(){
    //NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1);
  }

  public void limelightOn(){
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(3);
  }

  public void limelightstream(int ivalue){ // 0 - standard double stream, 1 - PiP secondary small, 2 - PiP secondary big
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("stream").setNumber(ivalue);
  }

  public boolean isLimelightOn(){
    int isOn = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").getNumber(1).intValue();
    if (isOn == 3){
      return true;
    } 
    else{
      return false;
    }
  }

  /*
  public void turnRobotToAngle(double x){

    if (Robot.limelight.is_Target()) {
      double left_command;
      double right_command;

      left_command = Robot.drive.getLeftSide();
      right_command = Robot.drive.getRightSide();

      double heading_error = -x;
      double steering_adjust = 0.0f;
           if (x > 1)
           {
                   steering_adjust = Kp*heading_error + Kf;
           }
           else if (x < -1)
           {
                   steering_adjust = Kp*heading_error - Kf;
           }
           else{
             steering_adjust = 0;
           }


      left_command += steering_adjust;
      right_command -= steering_adjust;

      SmartDashboard.putNumber("Left Command", left_command);
      SmartDashboard.putNumber("Right Command", right_command);
      SmartDashboard.putNumber("Steering Adjust", steering_adjust);
      SmartDashboard.putNumber("X", x);


      if (left_command > 0.75){
        left_command = 0.75;
      }

      if (right_command > 0.75){
        right_command = 0.75;
      }

      Robot.drive.setLeftSide(-left_command);
      Robot.drive.setRightSide(right_command);
    }
  }
  */




}
