// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;

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
	 public static Solenoid slndIntake =  new Solenoid(PneumaticsModuleType.CTREPCM, 6); //set the solenoid to right ID, and add the moduler type
	
	//solenoids for Shooter
	public static Solenoid slndShooter =  new Solenoid(PneumaticsModuleType.CTREPCM, 1); //set the solenoid to right ID, and add the moduler type
	//solenoids for Clim
	public static Solenoid slndClimb =  new Solenoid(PneumaticsModuleType.CTREPCM, 0); //set the solenoid to right ID, and add the moduler type

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

}
