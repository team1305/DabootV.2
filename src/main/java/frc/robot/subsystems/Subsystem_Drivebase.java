// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.commands.Command_Drive_With_Joystick;


public class Subsystem_Drivebase extends SubsystemBase {
  /** Creates a new Subsystem_Drivebase. */
  AHRS navx;

 
 
  // variable for deadband
  public double dDeadband = 0.1;
  public double dSquareFactor = 1.0;
  public double dThrottleFactor = 0.5;

  private DifferentialDrive drRobotDrive;


    // grabs drive motor information from RobotMap
    private final static WPI_TalonFX mtLeft1 = Constants.mtDriveLeft1;
    private final static WPI_TalonFX mtLeft2 = Constants.mtDriveLeft2;
    private final static WPI_TalonFX mtRight1 = Constants.mtDriveRight1;
    private final static WPI_TalonFX mtRight2 = Constants.mtDriveRight2;

    
  public Subsystem_Drivebase() {
 
    navx = new AHRS(SPI.Port.kMXP);
    navx.reset();

    // creates motor controller groups for left and right motors
    final  MotorControllerGroup mcgLeft = new MotorControllerGroup(mtLeft1, mtLeft2);
    final  MotorControllerGroup mcgRight = new MotorControllerGroup(mtRight1, mtRight2);

    drRobotDrive = new DifferentialDrive(mcgLeft, mcgRight);

  

    // Trying to get brake mode working
    mtLeft1.configFactoryDefault();
    mtLeft2.configFactoryDefault();
    mtRight1.configFactoryDefault();
    mtRight2.configFactoryDefault();

    mtLeft1.setNeutralMode(NeutralMode.Brake);
    mtLeft2.setNeutralMode(NeutralMode.Brake);
    mtRight1.setNeutralMode(NeutralMode.Brake);
    mtRight2.setNeutralMode(NeutralMode.Brake);
    
    mtLeft1.setInverted(true);
    mtLeft2.setInverted(true);

  
    mtLeft1.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
    mtRight1.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
 
    mtLeft1.configOpenloopRamp(0.2);
    mtLeft2.configOpenloopRamp(0.2);
    mtRight1.configOpenloopRamp(0.2);
    mtRight2.configOpenloopRamp(0.2);
    drRobotDrive.setDeadband(0.09); // By default, the Differential Drive class applies an input deadband of .02
    

    drRobotDrive.setSafetyEnabled(false);
 
    mtLeft1.configStatorCurrentLimit(Constants.currentLimitConfig, 40);
    mtLeft2.configStatorCurrentLimit(Constants.currentLimitConfig, 40);
    mtRight1.configStatorCurrentLimit(Constants.currentLimitConfig, 40);
    mtRight2.configStatorCurrentLimit(Constants.currentLimitConfig, 40);
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    //drRobotDrive.tankDrive(xboxcontroller.getLeftY(), xboxcontroller.getRightY());
  
   //if(drRobotDrive == null) {
   //  SmartDashboard.putString("DriveTrain is NULL", "YES");
   //} else {
   // SmartDashboard.putString("DriveTrain is NULL", "NO");
   //}  

    setDefaultCommand(new Command_Drive_With_Joystick(RobotContainer.drive));

  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  private double ThrottleScale(double throttle, double input) {
    return (JoystickDeadBand(input) * (1 - (throttle * dThrottleFactor)));
  }
  private double JoystickDeadBand(double input) {
    if (Math.abs(input) < dDeadband)
      return 0;
    else if (input > 0)
      return Math.pow(((input - dDeadband) * (1 / (1 - dDeadband))), dSquareFactor);
    else if (input < 0)
      return ((Math.pow(((Math.abs(input) - dDeadband) * (1 / (1 - dDeadband))), dSquareFactor)) * -1);
    else
      return 0;
  }

  // creates a driving function using specified joystick
  public void driveWithJoystick(XboxController xboxController) {
    //mtLeft1.set(ControlMode.PercentOutput, xboxController.getLeftY());
    //mtLeft2.set(ControlMode.PercentOutput, xboxController.getLeftY());
    //mtRight1.set(ControlMode.PercentOutput, xboxController.getRightY());
    //mtRight2.set(ControlMode.PercentOutput, xboxController.getRightY() );
    
   
   
    // creates variables for joystick x and y values
    double zRotation = ThrottleScale(Math.abs(xboxController.getLeftY() * 0.5), xboxController.getRawAxis(4) * -0.5);
    double xSpeed = JoystickDeadBand(xboxController.getLeftY() * 0.5);
    double dist = getDistance();
    double angle = gyroGetAngle();
    // uses joystick to do driving thing

   
    SmartDashboard.putNumber("drive stick  speed", xSpeed);
    SmartDashboard.putNumber("drive stick  rotation", zRotation);
    SmartDashboard.putNumber("RAW Gyro Angle", angle);
    SmartDashboard.putNumber("RAW Encoder Right",dist);
    curvaturedrive(xSpeed, zRotation);
    
  }


  public static double getDistance() {
    double left_wheel_rot = -1 * getEncLeftSide();
    double right_wheel_rot = getEncRightSide();
    SmartDashboard.putNumber("Left Encoder", left_wheel_rot);
    SmartDashboard.putNumber("Right Encoder", right_wheel_rot);
    double average_wheel_rot = right_wheel_rot; // (left_wheel_rot + right_wheel_rot) / 2;
    return average_wheel_rot;

  }

  public static double getEncRightSide() {
    return mtRight1.getSelectedSensorPosition();
  }

  public static double getEncLeftSide() {
    return mtLeft1.getSelectedSensorPosition();
  }

  public double gyroGetAngle() {
    return navx.getYaw();
  }
   
  public void curvaturedrive(double xspeed, double zrotation) {
    drRobotDrive.curvatureDrive(xspeed, zrotation, true);
    
    
  }
}
