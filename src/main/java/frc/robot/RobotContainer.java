// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.Subsystem_Drivebase;
import frc.robot.subsystems.Subsystem_Intake;
import frc.robot.subsystems.Subsystem_Compressor_Power;
import frc.robot.commands.Command_Drive_With_Joystick;
import frc.robot.commands.Command_Intake;
import frc.robot.commands.Command_Compressor_Off;
import frc.robot.commands.Command_Compressor_On;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  // Xbox Controllers
  final static XboxController PRIMARY = new XboxController(0); // Primary Driver Stick


  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  public final static Subsystem_Drivebase drive = new Subsystem_Drivebase();
  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  public final static Subsystem_Intake intake = new Subsystem_Intake();
  public static Subsystem_Compressor_Power compressor = new Subsystem_Compressor_Power();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    new JoystickButton(PRIMARY, Constants.A_BUTTON).whileHeld(new Command_Intake(intake));
    new JoystickButton(PRIMARY, Constants.BACK).whenPressed(new Command_Compressor_Off(compressor));
    new JoystickButton(PRIMARY, Constants.START).whenPressed(new Command_Compressor_On(compressor));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }

  // returns joyxbox1 whenever getJoystickDriver is called
  public static XboxController getJoystickDriver() {
    return PRIMARY;
  }
/*
  //returns joyxbox2 whenever getJoystickOperator is called
  public static XboxController getJoystickOperator() {
    return SECONDARY;
  }
*/

}
