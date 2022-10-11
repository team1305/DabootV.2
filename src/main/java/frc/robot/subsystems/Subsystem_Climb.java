// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.commands.Command_Climb_Loop;


public class Subsystem_Climb extends SubsystemBase {
 private final static WPI_TalonFX mtClimb1 = Constants.mtClimb1;
 private final static WPI_TalonFX mtClimb2 = Constants.mtClimb2;
 private final static Solenoid slndClimb1 = Constants.slndClimb;
 private final static Solenoid slndArm = Constants.slndArm;

 private Integer idpad;
 private boolean bhighgear;

 private double climb1encoder;
  /** Creates a new Subsystem_Climb. */
  public Subsystem_Climb() {

    mtClimb1.setNeutralMode(NeutralMode.Brake);
    mtClimb2.setNeutralMode(NeutralMode.Brake);

    mtClimb1.configStatorCurrentLimit(Constants.currentLimitConfig, 40);
    mtClimb2.configStatorCurrentLimit(Constants.currentLimitConfig, 40);

    mtClimb1.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
    mtClimb2.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);

    mtClimb1.config_kP(0, Constants.kP_SHOOTER_INFRONT_OF_LINE);
    mtClimb1.config_kI(0, Constants.kI_SHOOTER_INFRONT_OF_LINE);
    mtClimb1.config_kD(0, Constants.kD_SHOOTER_INFRONT_OF_LINE);
    mtClimb1.config_kF(0, Constants.kF_SHOOTER_INFRONT_OF_LINE);
    mtClimb1.config_IntegralZone(0, Constants.kIZone_SHOOTER);

    mtClimb2.config_kP(0, Constants.kP_SHOOTER_INFRONT_OF_LINE);
    mtClimb2.config_kI(0, Constants.kI_SHOOTER_INFRONT_OF_LINE);
    mtClimb2.config_kD(0, Constants.kD_SHOOTER_INFRONT_OF_LINE);
    mtClimb2.config_kF(0, Constants.kF_SHOOTER_INFRONT_OF_LINE);
    mtClimb2.config_IntegralZone(0, Constants.kIZone_SHOOTER);

    mtClimb1.clearStickyFaults();
    mtClimb2.clearStickyFaults();
 

    resetEncoder();
    bhighgear = true;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    
    setDefaultCommand(new Command_Climb_Loop(RobotContainer.Climb));

  }

  public void Climb_Loop() {
    // Send Encoder Info to the Smart Dash Board
    
    climb1encoder = getEncoder1();

    if (RobotContainer.getdebug()) {
       SmartDashboard.putNumber("Climb Encoder1", getEncoder1()); 
       //SmartDashboard.putNumber("Climb Encoder2", getEncoder2());
    }

    // Observed encoder values
    // Start at 0
    // Go up to 193000 to 206000
    // Going back down to start posisiton
      // goes to -260000

/*
    idpad = RobotContainer.getJoystickOperator().getPOV();

    if ( ( (idpad >= 0) && (idpad <= 15)) || ( (idpad >= 345) && (idpad <= 360)) ) {
         // Climb Up
         if (climb1encoder <= 430000) {
          ClimbUp(-0.85);   
        
         } else if ((climb1encoder > 430000) && (climb1encoder <= 470000)) {
            ClimbUp(-0.4); 
         } else {
          //if ( RobotContainer.getJoystickOperator().getAButton()) {
             ClimbUp(-0.4);
          //} else {
          //   stopCLimb(0);
          //} 
         }
    } else if ( (idpad >= 165) && (idpad <= 195) ) {
         // Climb Down
         if (climb1encoder >= 20000) {
            ClimbDown(0.85);
         } else if ((climb1encoder >= 10000) && (climb1encoder < 20000)) {
          ClimbDown(0.4); // Slow down as we approach bottom
         } else {
            //if ( RobotContainer.getJoystickOperator().getAButton()) {
               ClimbDown(0.4); // Slow Decend, Manual
            //} else { // Stop climbing
            //   stopCLimb(0);
            //} 
         }
    } else if (idpad == 90) { // Go to Full Up position
      //SmartDashboard.putNumber("DPAD", idpad);
      stopCLimb(0);
      //ClimbTo(460000);

    } else if (idpad == 270) { // go to almost full down position
      //SmartDashboard.putNumber("DPAD", idpad);
        //ClimbTo(20000);
        stopCLimb(0);
    } else  { // Any other D-PAD value
      // anything else
      stopCLimb(0);
    }
*/
  }

  public void ClimbTo(double theposition) {
    mtClimb1.set(ControlMode.Position, theposition);
    mtClimb2.set(ControlMode.Position, theposition);
  }

  public static double getEncoder1() {
    double climb1enc = -mtClimb1.getSelectedSensorPosition();
    return climb1enc;

  }

  public static double getEncoder2() {
    double climb2enc = -mtClimb2.getSelectedSensorPosition();
    return climb2enc;

  }

  public void resetEncoder() {
   

    mtClimb1.setSelectedSensorPosition(0);
    mtClimb2.setSelectedSensorPosition(0);
 
  }

  public void HighGear() {
    slndClimb1.set(true);
    bhighgear = true;

  }

  public void ArmExtend() {
    slndArm.set(true);
    
  }

  public void ArmRetract() {
    slndArm.set(false);
    
  }

  public void LowGear() {
    slndClimb1.set(false);
    bhighgear = false;

  }

  public boolean ishighgear() {
    return bhighgear;
  }

  public void ClimbDown(double speed) {
    LowGear();
    mtClimb1.set(speed);
    mtClimb2.set(speed);
  }
  
  public void ClimbUp(double speed) {
    LowGear(); // dont use high gear as we want to use encoder values as soft limits
    mtClimb1.set(speed);
    mtClimb2.set(speed);
  }

  public void startClimb(double speed) {
  mtClimb1.set(speed);
  mtClimb2.set(speed);
  }


  public void stopCLimb(double speed) {
  mtClimb1.set(speed);
  mtClimb2.set(speed);
  }
}
