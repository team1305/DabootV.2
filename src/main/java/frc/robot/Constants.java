// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.motorcontrol.Spark;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */

 //:)
public final class Constants {


    //Motor ID for the drive base
	public static WPI_TalonFX mtDriveRight2 = new WPI_TalonFX(11);
	public static WPI_TalonFX mtDriveRight1 = new WPI_TalonFX(12);
	public static WPI_TalonFX mtDriveLeft1 = new WPI_TalonFX(13);
	public static WPI_TalonFX mtDriveLeft2 = new WPI_TalonFX(14);
    public final static StatorCurrentLimitConfiguration currentLimitConfig = new StatorCurrentLimitConfiguration(true, 40, 39.95, 1);
	 public final static StatorCurrentLimitConfiguration currentLimitConfig30 = new StatorCurrentLimitConfiguration(true, 30, 29.95, 1);

    // Led spark
	 public static Spark mtLed = new Spark(9);

	 //motor ID for intake
	public static WPI_TalonFX mtIntake = new WPI_TalonFX(15); //set the motor to the right ID
	

	// Motor ID for Elevator
	public static WPI_TalonFX mtElevator = new WPI_TalonFX(16); //set the motor to the right ID
	
	// Motor ID for Shooter
	public static WPI_TalonFX mtShooter1 = new WPI_TalonFX(17); //set the motor to the right ID
	public static WPI_TalonFX mtShooter2 = new WPI_TalonFX(18); //set the motor to the right ID

	// Motor ID for Climb
	public static WPI_TalonFX mtClimb1 = new WPI_TalonFX(20); //set the motor to the right ID
	public static WPI_TalonFX mtClimb2 = new WPI_TalonFX(21); //set the motor to the right ID



// 0 - Climb
// 1 - Shooter Angle
// 6 - Intake

	//solenoids for Intake   
	 public static Solenoid slndIntake =  new Solenoid(PneumaticsModuleType.CTREPCM, 6); //6 set the solenoid to right ID, and add the moduler type
	
	//solenoids for Shooter
	public static Solenoid slndShooter =  new Solenoid(PneumaticsModuleType.CTREPCM, 1); //set the solenoid to right ID, and add the moduler type
	//solenoids for Climb
	public static Solenoid slndClimb =  new Solenoid(PneumaticsModuleType.CTREPCM, 0); //set the solenoid to right ID, and add the moduler type
	//solenoids for Climb arm   
	public static Solenoid slndArm =  new Solenoid(PneumaticsModuleType.CTREPCM, 7); // set the solenoid to right ID, and add the moduler type

	//solenoids for Blue LEDs
	public static Solenoid slndLeds =  new Solenoid(PneumaticsModuleType.CTREPCM, 3); //set the solenoid to right ID, and add the moduler type
	//declares compressor port
	public static Compressor cmpRobotCompressor = new Compressor(0, PneumaticsModuleType.CTREPCM);
	 	


//BUTTON NUMBER CONSTANTS
public static final int A_BUTTON = 1;  //xbox "A" Button 1
public static final int B_BUTTON=  2; //xbox "B" Button 2
public static final int X_BUTTON=  3; //xbox "X" Button 3
public static final int Y_BUTTON=  4; //xbox "Y" Button 4
public static final int LEFT_BUMPER =  5; //xbox "LB" Button 5
public static final int RIGHT_BUMPER =  6; //xbox "RB" Button 6
public static final int BACK=  7; //xbox "Back" Button 7
public static final int START =  8;  //xbox "Start" Button 8
public static final int LEFT_STICK_CLICK =  9; //xbox "Left Stick Click" Button 9
public static final int RIGHT_STICK_CLICK =  10;  //xbox "Right Stick Click" Button 10

	 //Limelight PID and Constants
	 public static final double LIMELIGHT_KP = -0.03f;
	 public static final double LIMELIGHT_KI = 0.012f; // 0.006
	 public static final double LIMELIGHT_KD = 0; // 0.006
	 public static final double LIMELIGHT_KF = 0.05f;  //feedforward - minimum command signal
 
	 public static final double HORIZONTAL_PIXEL_TO_ANGLE = 59.6/320; //   59.6/320
	 public static final double VERTICAL_PIXEL_TO_ANGLE = 49.7/240; //   49.7/240
	 public static final double HALF_VERTICAL_FOV_DEGREES = 24.85;
	 public static final double HALF_HORIZONTAL_FOV_DEGREES = 29.9;
	 public static final double ANGLE_OUTER_TRIANGLE_DEGREES = 45.418; //Degrees
	 public static final double DIAGONAL_LENGTH_FROM_OUTER_TO_INNER= 24.219; //Inches
 
	 public static final double HORIZONTAL_PIXEL_TO_RADIANS = (59.6 * (Math.PI/180))/320; //   (59.6)*(Math.PI/180)/320
	 public static final double VERTICAL_PIXEL_TO_RADIANS = (49.7 * (Math.PI/180))/240; //   49.7/240
	 public static final double HALF_VERTICAL_FOV_RADIANS = 24.85 * (Math.PI/180);
	 public static final double HALF_HORIZONTAL_FOV_RADIANS = 29.9 * (Math.PI/180);
	 public static final double ANGLE_OUTER_TRIANGLE_RADIANS = (45.418) * (Math.PI/180); //Radians
 
	 public static final double VISION_LEFT_KP = 0; //THIS VALUE MUST BE FOUND
	 public static final double VISION_LEFT_KI = 0;//THIS VALUE MUST BE FOUND
	 public static final double VISION_LEFT_KD = 0;//THIS VALUE MUST BE FOUND
	 public static final double VISION_RIGHT_KP = 0;//THIS VALUE MUST BE FOUND
	 public static final double VISION_RIGHT_KI = 0;//THIS VALUE MUST BE FOUND
	 public static final double VISION_RIGHT_KD = 0;//THIS VALUE MUST BE FOUND

	 	//Shooter PID values
	public static final double kP_SHOOTER_INFRONT_OF_LINE = 0.2; // 0.1; //0.095;  //0.085
	public static final double kI_SHOOTER_INFRONT_OF_LINE = 0;
	public static final double kD_SHOOTER_INFRONT_OF_LINE = 0;
	public static final double kF_SHOOTER_INFRONT_OF_LINE = 0.0512;

	public static final double kP_SHOOTER_INITIATION_LINE = 0.09;
	public static final double kI_SHOOTER_INITIATION_LINE = 0;
	public static final double kD_SHOOTER_INITIATION_LINE = 0;
	public static final double kF_SHOOTER_INITIATION_LINE = 0.0512;

	public static final double kP_SHOOTER_TRENCH = 0.1;
	public static final double kI_SHOOTER_TRENCH = 0;
	public static final double kD_SHOOTER_TRENCH = 0;
	public static final double kF_SHOOTER_TRENCH = 0.0512;

	public static final double kP_SHOOTER_TRENCH_BACK = 0.1;
	public static final double kI_SHOOTER_TRENCH_BACK = 0;
	public static final double kD_SHOOTER_TRENCH_BACK = 0;
	public static final double kF_SHOOTER_TRENCH_BACK = 0.0512;



	public static final int kIZone_SHOOTER = 200;
	public static final double SHOOTER_OUTPUT_TO_ENCODER_RATIO = 1; //0.77; //Previous 3.0 Because 3 revolutions of the encoder was one revolution of the wheels, 24.0/36.0
	public static final double TICKS_PER_ROTATION = 2048.0;
	public static final int kLongCANTimeOutMs = 100;
	public static final double kFlywheelTicksPerRevolution = 0;




}
