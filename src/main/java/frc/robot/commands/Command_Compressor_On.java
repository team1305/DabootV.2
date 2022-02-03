// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Subsystem_Compressor_Power;

public class Command_Compressor_On extends CommandBase {
  /** Creates a new Command_Compressor_On. */

  private final Subsystem_Compressor_Power compressorSub;

  public Command_Compressor_On(Subsystem_Compressor_Power compressor) {
    // Use addRequirements() here to declare subsystem dependencies.
    compressorSub = compressor;
    addRequirements(compressorSub);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    RobotContainer.compressor.CompressorON();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
