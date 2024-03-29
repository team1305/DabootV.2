// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.AutoCommands;



/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  Command autonomousCommand;
  SendableChooser<SequentialCommandGroup> autonomousModes;

  private double Kp = -0.03f;
  private double Ki = 0.012f; // 0.006
  private double Kf = 0.05f;  //feedforward - minimum command signal
  
  private double left_command;
  private double right_command;
  
  private double x, thor;
  private int izone, irpm;
  private boolean btarget;
  private double zone1threshold = 50; //  pixel width
  private int iloops = 0;
  
  private int isuccess = 0; 

  private String cstate = "HUNT";

  private int caseMove = 0;
  

  public double distance;

  public static UsbCamera camera;

 

  SendableChooser<Integer> autoChooser = new SendableChooser<>();
  private RobotContainer m_robotContainer;

  

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();

    
   
    autonomousModes = new SendableChooser<SequentialCommandGroup >();
    autonomousModes.setDefaultOption("4 Ball Auto", new AutoCommands(2));
    autonomousModes.addOption("2 Ball Auto", new AutoCommands(1));
    autonomousModes.addOption("3 Ball Auto", new AutoCommands(3));
    //autonomousModes.addOption("5 Ball Auto", new AutoCommands(4));
    SmartDashboard.putData("AUTO Modes", autonomousModes);
  
    // Set Default States
    RobotContainer.compressor.CompressorON();

    RobotContainer.Climb.ArmRetract();

    if (DriverStation.getAlliance() == Alliance.Red) {
      RobotContainer.Led.setRed();
    } else {
      RobotContainer.Led.setBlue();
    }

    RobotContainer.drive.resetEncoder();
    RobotContainer.drive.gyroReset();
    RobotContainer.Climb.resetEncoder();

    RobotContainer.Limelight.limelightOn();

    RobotContainer.Led.base_led(true);
      
    //starts camera stream if camera is available
    try {
      camera = CameraServer.startAutomaticCapture();
    }
    catch(Exception ex) {

      System.out.println("ERROR: setting camera: " + ex.getMessage()) ;
    } 

    RobotContainer.Limelight.limelightstream(2);
    
    
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();


  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
   // m_autonomousCommand = m_robotContainer.getAutonomousCommand();
    m_autonomousCommand = autonomousModes.getSelected();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }

    
    RobotContainer.drive.gyroReset();
    RobotContainer.drive.resetEncoder();

  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
  
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
        }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {}

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}
