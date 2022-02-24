// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.commands.Command_Climb_Loop;


public class Subsystem_Climb extends SubsystemBase {
 private final static WPI_TalonFX mtClimb1 = Constants.mtClimb1;
 private final static WPI_TalonFX mtClimb2 = Constants.mtClimb2;
 private final static Solenoid slndClimb1 = Constants.slndClimb;

 private Integer idpad;

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
    
    setDefaultCommand(new Command_Climb_Loop(RobotContainer.Climb));

  }

  public void Climb_Loop() {
     idpad = RobotContainer.getJoystickOperator().getPOV();

    if (idpad == 0) {
      // Climb Up
      ClimbUp(0.75);
    } else if (idpad == 180) {
      // Climb Down
      ClimbDown(-0.75);
    } else {
      // anything else
      stopCLimb(0);
    }

  }

  public void HighGear() {
    slndClimb1.set(true);

  }

  public void LowGear() {
    slndClimb1.set(false);

  }

  public void ClimbUp(double speed) {
    HighGear();
    mtClimb1.set(speed);
    mtClimb2.set(speed);
  }
  
  public void ClimbDown(double speed) {
    LowGear();
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
