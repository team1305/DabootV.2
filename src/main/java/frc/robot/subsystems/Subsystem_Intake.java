// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Subsystem_Intake extends SubsystemBase {
  /** Creates a new Subsystem_Intake. */


  private final static WPI_TalonFX mtIntake = Constants.mtIntake;

private final static Solenoid slndIntake1 = Constants.slndIntake1;
//private final static Solenoid slndIntake2 = Constants.slndIntake2;
  public boolean bintakeOn;

  public Subsystem_Intake() {

   // Trying to get brake mode working
   mtIntake.setNeutralMode(NeutralMode.Brake);

   mtIntake.configStatorCurrentLimit(Constants.currentLimitConfig, 40);

   SmartDashboard.putBoolean("Intake Solenoid Set Value", false);
   bintakeOn = false;


  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void setIntake(double speed) {
    //mtIntake.set(ControlMode.PercentOutput, speed);
    mtIntake.set(0.6);
    slndIntake1.set(true);  //added bjk at FLL
    //slndIntake2.set(true);  //added bjk at FLL
    bintakeOn = true;
    SmartDashboard.putBoolean("Intake Solenoid Set Value", true);
  }


  public void stopIntake() {
    //mtIntake.set(ControlMode.PercentOutput, 0);
    mtIntake.set(0);
    slndIntake1.set(false);  //added bjk at fll
    //slndIntake2.set(false);  //added bjk at fll
    SmartDashboard.putBoolean("Intake Solenoid Set Value", false);
    bintakeOn = false;
  }


  public void intakeExtension(boolean extension) {
    if (extension = true){
    slndIntake1.set(true);
    //slndIntake2.set(true);
    bintakeOn = true;

    }
    
    else if (extension = false){
      slndIntake1.set(false);
      //slndIntake2.set(false);
      bintakeOn = false;
    }
    else{

    }

  }


  public boolean isIntakeOn(){
    if (bintakeOn) {
      return true;
    }else {
      return false;
    }
    //return bintakeOn;
    //return slndIntake.get();
  }










}
