// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Subsystem_Drivebase;
import frc.robot.subsystems.Subsystem_Elevator;
import frc.robot.subsystems.Subsystem_Intake;
import frc.robot.subsystems.Subsystem_Limelight;
import frc.robot.subsystems.Subsystem_Shooter;

public class Command_AI_Shooter extends CommandBase {
  /** Creates a new Command_Intake. */
  private final Subsystem_Shooter shooterSub;
  private final Subsystem_Elevator elevatorSub;
  private final Subsystem_Intake intakeSub;
  private final Subsystem_Drivebase driveSub;
  private final Subsystem_Limelight limelightSub;
  private double bspeed;
  //private boolean shooterup;
  private Integer aiLoop;
  private boolean hasFoundtarget;
  
  public Command_AI_Shooter(Subsystem_Shooter shooter, 
                            Subsystem_Elevator Elevator, 
                            Subsystem_Intake intake, 
                            Subsystem_Drivebase drivebase, 
                            Subsystem_Limelight limelight, 
                            double bspeed, boolean shooterup ) {
    // Use addRequirements() here to declare subsystem dependencies.
    shooterSub = shooter;
    elevatorSub = Elevator;
    intakeSub = intake;
    driveSub = drivebase;
    limelightSub = limelight;
    this.bspeed = bspeed;
    //this.shooterup = shooterup;
    addRequirements(shooterSub);
    addRequirements(elevatorSub);
    addRequirements(intakeSub);
    addRequirements(driveSub);
    addRequirements(limelightSub);
  }


  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    shooterSub.setShooter(bspeed);
    aiLoop = 0;
    hasFoundtarget = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    if (aiLoop < 2) {
         // roll back ball
    } else if ( aiLoop < 10 && !hasFoundtarget) {
        // seek target
        // try for x number of loops (example 10) while there is no target
    } else if (hasFoundtarget) {
        // found target will shoot
    } else {
        // if all else fails back to start
        aiLoop = -1;
        hasFoundtarget = false;
    }
      aiLoop++;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

    shooterSub.stopShooter();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
