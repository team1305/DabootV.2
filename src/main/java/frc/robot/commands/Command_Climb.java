// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Subsystem_Climb;

public class Command_Climb extends CommandBase {
  /** Creates a new Command_Climb. */
  private final Subsystem_Climb climbSub;
  private double bspeed;

  public Command_Climb(Subsystem_Climb Climb, double bspeed) {
    // Use addRequirements() here to declare subsystem dependencies.
    climbSub = Climb;
    addRequirements(climbSub);
    this.bspeed = bspeed;
  }


  // Called when the command is initially scheduled.
  @Override
  public void initialize() { 
    if(bspeed > 0) {
      RobotContainer.Climb.HighGear();
    } else {
      RobotContainer.Climb.LowGear();
    }
    
    RobotContainer.Climb.startClimb(bspeed);
  
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.Climb.stopCLimb(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
