// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Subsystem_Shooter extends SubsystemBase {
  /** Creates a new Subsystem_Intake. */


  private final static WPI_TalonFX mtShooter1 = Constants.mtShooter1;
  private final static WPI_TalonFX mtShooter2 = Constants.mtShooter2;

private final static Solenoid slndShooter = Constants.slndShooter;

  public boolean bShooterUp;

  public Subsystem_Shooter() {
    bShooterUp = false;
    mtShooter1.setInverted(true);

   // Trying to get brake mode working
   mtShooter1.setNeutralMode(NeutralMode.Coast);
   mtShooter2.setNeutralMode(NeutralMode.Coast);


   mtShooter1.configStatorCurrentLimit(Constants.currentLimitConfig, 30);
   mtShooter2.configStatorCurrentLimit(Constants.currentLimitConfig, 30);


  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void setShooter(double speed) {
    //mtIntake.set(ControlMode.PercentOutput, speed);
    mtShooter1.set(speed);
    mtShooter2.set(speed);
  }


  public void stopShooter() {
    //mtIntake.set(ControlMode.PercentOutput, 0);
    mtShooter1.set(0);
    mtShooter2.set(0);
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
