// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Subsystem_Elevator extends SubsystemBase {
  /** Creates a new Subsystem_Intake. */


  private final static WPI_TalonFX mtElevator = Constants.mtElevator;


  public boolean bintakeOn;

  public Subsystem_Elevator() {

   // Trying to get brake mode working
   mtElevator.setNeutralMode(NeutralMode.Brake);

   mtElevator.configStatorCurrentLimit(Constants.currentLimitConfig, 40);



  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setElevator(double speed) {
    mtElevator.set(speed);
  }


  public void stopElevator() {
    mtElevator.set(0);
  }




}
