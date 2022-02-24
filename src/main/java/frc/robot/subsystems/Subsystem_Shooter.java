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
import frc.robot.commands.Command_ai_loop;

public class Subsystem_Shooter extends SubsystemBase {
  /** Creates a new Subsystem_Intake. */


  private final static WPI_TalonFX mtShooter1 = Constants.mtShooter1;
  private final static WPI_TalonFX mtShooter2 = Constants.mtShooter2;

  private double kP_Shooter;
  private double kI_Shooter;
  private double kD_Shooter;
  private double kF_Shooter;

private final static Solenoid slndShooter = Constants.slndShooter;

  public boolean bShooterUp;

  public Subsystem_Shooter() {
    kP_Shooter = Constants.kP_SHOOTER_INFRONT_OF_LINE;
    kI_Shooter = Constants.kI_SHOOTER_INFRONT_OF_LINE;
    kD_Shooter = Constants.kD_SHOOTER_INFRONT_OF_LINE;
    kF_Shooter = Constants.kF_SHOOTER_INFRONT_OF_LINE;

    
    bShooterUp = false;
    mtShooter1.configFactoryDefault();
    mtShooter2.configFactoryDefault();
    mtShooter1.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
    mtShooter1.setInverted(false);
    mtShooter2.setSensorPhase(true);

    mtShooter1.setInverted(true);
    mtShooter1.setSensorPhase(true);

   // Trying to get brake mode working
   mtShooter1.setNeutralMode(NeutralMode.Coast);
   mtShooter2.setNeutralMode(NeutralMode.Coast);

   mtShooter1.config_kP(0, Constants.kP_SHOOTER_INFRONT_OF_LINE);
   mtShooter1.config_kI(0, Constants.kI_SHOOTER_INFRONT_OF_LINE);
   mtShooter1.config_kD(0, Constants.kD_SHOOTER_INFRONT_OF_LINE);
   mtShooter1.config_kF(0, Constants.kF_SHOOTER_INFRONT_OF_LINE);
   mtShooter1.config_IntegralZone(0, Constants.kIZone_SHOOTER);


   mtShooter1.clearStickyFaults();
   mtShooter2.clearStickyFaults();

   mtShooter2.follow(mtShooter1);

   mtShooter1.configStatorCurrentLimit(Constants.currentLimitConfig, 30);
   mtShooter2.configStatorCurrentLimit(Constants.currentLimitConfig, 30);


  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Shooter Rotations", getShooterRotations());
    SmartDashboard.putNumber("Shooter RPM", getShooterRPM());
    SmartDashboard.putNumber("Shooter Output Percent", mtShooter1.getMotorOutputPercent());

    //setDefaultCommand(new Command_ai_loop(RobotContainer.shooter, RobotContainer.Limelight, RobotContainer.intake, RobotContainer.Led));

  }

  public double getShooterRotations() {
    return mtShooter1.getSelectedSensorPosition() / Constants.SHOOTER_OUTPUT_TO_ENCODER_RATIO / Constants.TICKS_PER_ROTATION;
    // sensor / 1/ 2048
  }

  public void setShooter(double speed) {
    //mtIntake.set(ControlMode.PercentOutput, speed);
    mtShooter1.set(speed);
    //mtShooter2.set(speed);

    
  }

  public double getShooterRPM() {
    return mtShooter1.getSelectedSensorVelocity() / Constants.SHOOTER_OUTPUT_TO_ENCODER_RATIO / Constants.TICKS_PER_ROTATION * 10.0 * 60.0;
}

public void setShooterRPM(double rpm) {
    // double kF = (shooterA.getMotorOutputPercent() * 1023) / shooterA.getSelectedSensorVelocity();
    // shooterA.config_kF(0, kF);
    mtShooter1.set(ControlMode.Velocity, shooterRPMToNativeUnits(rpm));
}

public double shooterRPMToNativeUnits(double rpm) {
  return rpm * Constants.SHOOTER_OUTPUT_TO_ENCODER_RATIO * Constants.TICKS_PER_ROTATION / 10.0 / 60.0;
}


  public void stopShooter() {
    //mtIntake.set(ControlMode.PercentOutput, 0);
    mtShooter1.set(0);
    //mtShooter2.set(0);
  }  
  
  public void ShooterUp() {
    
    slndShooter.set(true);
    //bShooterUp = true;
  }

  public void ShooterDown() {
    slndShooter.set(false);

    //bShooterUp = false;  
  }
/*
 public boolean isShooterUp() {
  if (bShooterUp) {
    return true;
  } else {
    return false;
  }
 }
 

 public void ShooterToggle(){
slndShooter.toggle();


 }

 public void ShooterDefault(){
slndShooter.set(true);

 }
 */
 
 


}
