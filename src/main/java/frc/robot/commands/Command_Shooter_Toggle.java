// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Subsystem_Shooter;

public class Command_Shooter_Toggle extends CommandBase {
  /** Creates a new Command_Intake. */
  private final Subsystem_Shooter shooterSub;

  
  public Command_Shooter_Toggle(Subsystem_Shooter shooter) {
    shooterSub = shooter;
    addRequirements(shooterSub);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
       RobotContainer.shooter.ShooterUp();
  }
    
      
  

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.shooter.ShooterDown();

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
