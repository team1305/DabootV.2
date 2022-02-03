// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Subsystem_Drivebase;


public class Command_Drive_With_Joystick extends CommandBase {
  /** Creates a new Command_Drive_With_Joystick. */

  private final Subsystem_Drivebase driveSub;

  public Command_Drive_With_Joystick(Subsystem_Drivebase drive) {
    // Use addRequirements() here to declare subsystem dependencies.
    driveSub = drive;
    addRequirements(driveSub);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    driveSub.driveWithJoystick(RobotContainer.getJoystickDriver());
    //SmartDashboard.putNumber("Gyro Angle", RobotContainer.drive.gyroGetAngle());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
