// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.math.filter.SlewRateLimiter;
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


  private DifferentialDrive drRobotDrive;


  public double dDeadband = 0.1;
  public double dSquareFactor = 1.0;


  private double Kp = Constants.LIMELIGHT_KP;
  private double Ki = Constants.LIMELIGHT_KI; // 0.006
  private double Kd = 0; // 0.006
  private double Kf = Constants.LIMELIGHT_KF; // feedforward - minimum command signal


  // Lower number means more delay, higher is less delay
  // 0.8 is a good number
  public SlewRateLimiter rotationFilter = new SlewRateLimiter(0.8);
  public SlewRateLimiter accelerationFilter = new SlewRateLimiter(0.8);

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
 
    mtLeft1.configOpenloopRamp(0.1); //0.2
    mtLeft2.configOpenloopRamp(0.1); // 0.2
    mtRight1.configOpenloopRamp(0.1); // 0.2
    mtRight2.configOpenloopRamp(0.1); // 0.2
    drRobotDrive.setDeadband(0.06); // By default, the Differential Drive class applies an input deadband of .02
    

    drRobotDrive.setSafetyEnabled(false);


    //drRobotDrive.setDeadband(0.06);
 
    mtLeft1.configStatorCurrentLimit(Constants.currentLimitConfig, 40);
    mtLeft2.configStatorCurrentLimit(Constants.currentLimitConfig, 40);
    mtRight1.configStatorCurrentLimit(Constants.currentLimitConfig, 40);
    mtRight2.configStatorCurrentLimit(Constants.currentLimitConfig, 40);
    

    //SlewRateLimiter filter = new SlewRateLimiter(0.5);

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


  // creates a driving function using specified joystick
  public void driveWithJoystick(XboxController xboxController) {
    //mtLeft1.set(ControlMode.PercentOutput, xboxController.getLeftY());
    //mtLeft2.set(ControlMode.PercentOutput, xboxController.getLeftY());
    //mtRight1.set(ControlMode.PercentOutput, xboxController.getRightY());
    //mtRight2.set(ControlMode.PercentOutput, xboxController.getRightY() );
    
   
   
    // creates variables for joystick x and y values
    double zRotation = xboxController.getRawAxis(4);
    double xSpeed = xboxController.getLeftY(); 
    double dist = getDistance();
    // uses joystick to do driving thing

    
    
   
    SmartDashboard.putNumber("drive stick  speed", xSpeed);
    SmartDashboard.putNumber("drive stick  rotation", zRotation);
    SmartDashboard.putNumber("RAW Encoder Right",dist);
    curvaturedrive(rotationFilter.calculate(xSpeed), -1 * accelerationFilter.calculate(zRotation));
    //curvaturedrive(filter.calculate(xSpeed), zRotation);
    
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
    // True means it can turn in place, false requires fwd/bk to turn
    drRobotDrive.curvatureDrive(xspeed, zrotation, true);   
  }

  public void tankdrive(double lspeed, double rspeed) {
    // True means it can turn in place, false requires fwd/bk to turn
    drRobotDrive.tankDrive(lspeed, rspeed);   
  }

  public void DriveStop() {
      tankdrive(0, 0);    
  }

  public double getLeftSide() {
    double currentSpeed = mtLeft1.get();
    return currentSpeed;
  }

  public double getRightSide() {
    double currentSpeed = mtRight1.get();
    return currentSpeed;
  }

  public void turnRobotToAngleAuto(double x) {

    if (RobotContainer.Limelight.is_Target()) {
      double left_command;
      double right_command;

      left_command = RobotContainer.drive.getLeftSide();
      right_command = RobotContainer.drive.getRightSide();

      double heading_error = -x;
      double steering_adjust = 0.0f;
      if (x > 1) {
        steering_adjust = Kp * heading_error + Kf;
      } else if (x < -1) {
        steering_adjust = Kp * heading_error - Kf;
      } else {
        steering_adjust = 0;
      }

      left_command += steering_adjust;
      right_command -= steering_adjust;

      SmartDashboard.putNumber("Left Command", left_command);
      SmartDashboard.putNumber("Right Command", right_command);
      SmartDashboard.putNumber("Steering Adjust", steering_adjust);
      SmartDashboard.putNumber("X", x);

      if (left_command > 0.15) {
        left_command = 0.15;
      }

      if (right_command > 0.15) {
        right_command = 0.15;
      }

      RobotContainer.drive.tankdrive(-left_command, right_command);
  
    }
  }


  public void gyroReset() {
    navx.reset();
  }

  public void resetEncoder() {
   

   mtRight1.setSelectedSensorPosition(0);
   mtLeft1.setSelectedSensorPosition(0);

 }

 public double getgearratio() {
  // Convert encoder ticks to 1 inch
  double encoder_ticks = 2048;
  double gearratio = 10.75;
  
  double wheel_circumfrance = 6 * Math.PI;
  return Math.round((encoder_ticks * gearratio) / wheel_circumfrance);
}

}
