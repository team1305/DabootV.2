// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import javax.lang.model.util.ElementScanner6;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Subsystem_Intake;

public class Command_Intake_Toggle extends CommandBase {
  /** Creates a new Command_Intake. */
  private final Subsystem_Intake intakeSub;
   boolean bisdone;
  
  public Command_Intake_Toggle(Subsystem_Intake intake) {
    // Use addRequirements() here to declare subsystem dependencies.
    intakeSub = intake;
    addRequirements(intakeSub);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    RobotContainer.intake.Intake_Toggle();
    bisdone = false;
    }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    bisdone = false;
    bisdone = true;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  
  }
}
