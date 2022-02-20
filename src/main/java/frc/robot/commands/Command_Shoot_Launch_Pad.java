// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Subsystem_Shooter;

public class Command_Shoot_Launch_Pad extends CommandBase {
  /** Creates a new Command_Intake. */
  private final Subsystem_Shooter shooterSub;
  private double bspeed;
  //private boolean shooterup;
  //, boolean shooterup
  public Command_Shoot_Launch_Pad(Subsystem_Shooter shooter, double bspeed) {
    // Use addRequirements() here to declare subsystem dependencies.
    shooterSub = shooter;
    this.bspeed = bspeed;
    //this.shooterup = shooterup;
    addRequirements(shooterSub);
  }


  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //RobotContainer.intake.intakeExtension(true);
    /*    
    if (shooterup) {
      RobotContainer.shooter.ShooterUp();
    }
    else {
      RobotContainer.shooter.ShooterDown();
    }
    */
    RobotContainer.shooter.ShooterUp();
    RobotContainer.shooter.setShooter(bspeed);

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.shooter.ShooterDown();
    RobotContainer.shooter.stopShooter();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
