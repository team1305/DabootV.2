// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Subsystem_Elevator;

public class Command_Elevator extends CommandBase {
  /** Creates a new Command_Intake. */
  private final Subsystem_Elevator elevatorSub;
  private double bspeed;
  
  public Command_Elevator(Subsystem_Elevator elevator, double bspeed) {
    // Use addRequirements() here to declare subsystem dependencies.
    elevatorSub = elevator;
    addRequirements(elevatorSub);
    this.bspeed = bspeed;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //RobotContainer.shooter.setShooter(-0.2);
    RobotContainer.intake.setIntake(0.5);;
    RobotContainer.elevator.setElevator(bspeed);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.intake.setIntake(0);
    RobotContainer.elevator.stopElevator();
    //RobotContainer.shooter.setShooter(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
