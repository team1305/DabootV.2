// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.ExampleCommand;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import frc.robot.subsystems.*;

import frc.robot.commands.Command_Elevator;
import frc.robot.commands.Command_Intake;
import frc.robot.commands.Command_Shooter;
import frc.robot.commands.Command_Compressor_Off;
import frc.robot.commands.Command_Compressor_On;
import frc.robot.commands.Command_Climb;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  // Xbox Controllers
  final static XboxController PRIMARY = new XboxController(0);
  final static XboxController SECONDARY = new XboxController(1);


  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  public final static Subsystem_Drivebase drive = new Subsystem_Drivebase();
  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  public final static Subsystem_Intake intake = new Subsystem_Intake();
  public final static Subsystem_Elevator elevator = new Subsystem_Elevator();
  public final static Subsystem_Shooter shooter = new Subsystem_Shooter();
  public static Subsystem_Compressor compressor = new Subsystem_Compressor();
  public static Subsystem_Climb Climb = new Subsystem_Climb();
  public static Subsystem_LED Led = new Subsystem_LED();
  public static Subsystem_Limelight Limelight = new Subsystem_Limelight();

  

 
  
 
  

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
    
    /* Climb uses DPad 
    AI loop uses Right Bmper */
    new JoystickButton(SECONDARY, Constants.LEFT_BUMPER).whileHeld(new Command_Intake(intake));
    new JoystickButton(SECONDARY, Constants.B_BUTTON).whileHeld(new Command_Elevator(elevator, 0.4));
    // new JoystickButton(SECONDARY, Constants.X_BUTTON).whileHeld(new Command_Shooter(shooter, 0.7, false));
    // new JoystickButton(SECONDARY, Constants.BACK).whenPressed(new Command_Compressor_Off(compressor));
    // new JoystickButton(SECONDARY, Constants.START).whenPressed(new Command_Compressor_On(compressor));
    // new JoystickButton(SECONDARY, Constants.RIGHT_BUMPER).whenPressed(new Command_Shooter_Toggle(shooter));
    new JoystickButton(SECONDARY, Constants.Y_BUTTON).whileHeld(new Command_Elevator(elevator, -0.2));
    

    // new JoystickButton(PRIMARY, Constants.LEFT_BUMPER).whileHeld(new Command_Intake(intake));
    // new JoystickButton(PRIMARY, Constants.B_BUTTON).whileHeld(new Command_Elevator(elevator, 0.4));
    new JoystickButton(PRIMARY, Constants.X_BUTTON).whileHeld(new Command_Shooter(shooter, 0.7, false));
    new JoystickButton(PRIMARY, Constants.BACK).whenPressed(new Command_Compressor_Off(compressor));
    new JoystickButton(PRIMARY, Constants.START).whenPressed(new Command_Compressor_On(compressor));
    // new JoystickButton(PRIMARY, Constants.A_BUTTON).whenHeld(new Command_Shooter_Toggle(shooter));
    // new JoystickButton(PRIMARY, Constants.Y_BUTTON).whileHeld(new Command_Elevator(elevator, -0.2));
    // new JoystickButton(PRIMARY, Constants.A_BUTTON).whileHeld(new Command_Climb(Climb, 0.75)); // Climb
    // new JoystickButton(PRIMARY, Constants.X_BUTTON).whileHeld(new Command_Climb(Climb, -0.75)); // Extend
    
    
    //new JoystickButton(SECONDARY, Constants.X_BUTTON).whileHeld(new Command_Climb(Climb, 0.5));
    //new JoystickButton(SECONDARY, Constants.A_BUTTON).whileHeld(new Command_Climb(Climb, -0.5));
  }

  /**    new JoystickButton(PRIMARY, Constants.Y_BUTTON).whenPressed(new Command_Intake_Toggle(intake));

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

  //returns joyxbox2 whenever getJoystickOperator is called
  public static XboxController getJoystickOperator() {
    return SECONDARY;
  }



}
