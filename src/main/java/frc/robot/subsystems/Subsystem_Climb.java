// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class Subsystem_Climb extends SubsystemBase {
 private final static WPI_TalonFX mtClimb1 = Constants.mtClimb1;
 private final static WPI_TalonFX mtClimb2 = Constants.mtClimb2;
 private final static Solenoid slndClimb1 = Constants.slndClimb;

 

  /** Creates a new Subsystem_Climb. */
  public Subsystem_Climb() {

    mtClimb1.setNeutralMode(NeutralMode.Brake);
    mtClimb2.setNeutralMode(NeutralMode.Brake);

    mtClimb1.configStatorCurrentLimit(Constants.currentLimitConfig, 40);
    mtClimb1.configStatorCurrentLimit(Constants.currentLimitConfig, 40);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void HighGear() {
    slndClimb1.set(true);

  }

  public void LowGear() {
    slndClimb1.set(false);

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
