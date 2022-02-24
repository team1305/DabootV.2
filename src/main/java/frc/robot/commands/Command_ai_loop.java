/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Subsystem_Intake;
import frc.robot.subsystems.Subsystem_LED;
import frc.robot.subsystems.Subsystem_Limelight;
import frc.robot.subsystems.Subsystem_Shooter;

public class Command_ai_loop extends CommandBase {
  /**
   * Creates a new Command_ai_loop.
   */
  private final Subsystem_Shooter shooterSub;
  private final Subsystem_Limelight limelightSub ;
  private final Subsystem_Intake intakeSub ;
  private final Subsystem_LED ledSub;

  private String cstate;
  
  private double x;
  private int izone, irpm;
  private boolean btarget;
  private double zone1threshold = 50; //  pixel width
  public double distance;
  private int iloops = 0;
  
  private int isuccess = 0; 

  public Command_ai_loop(Subsystem_Shooter shooter, Subsystem_Limelight limelight,Subsystem_Intake intake,Subsystem_LED led) {
    // Use addRequirements() here to declare subsystem dependencies.
    shooterSub = shooter;
    limelightSub = limelight;
    intakeSub = intake;
    ledSub = led;
    addRequirements(shooterSub, limelightSub, intakeSub, ledSub);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    cstate = "HUNT";
    izone = 1;
    irpm = 0;
    btarget = false;
    isuccess = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // AI Loop, States HUNT, SHOOT

     // Colour Loop
     set_colors();
/*
     //Vision tracking code, activates when right bumper is pressed
     if (RobotContainer.getJoystickDriver().getRawButton(6)) { // Driver RB

      if (RobotContainer.Limelight.isLimelightOn() == false){
        RobotContainer.Limelight.limelightOn();
      }

       //Limelight turn to target
       switch (cstate) {  
       case "HUNT" : 
         // Use Limelight to move to target
         //RobotContainer.drive.LowGear();
         RobotContainer.shooter.setShooterRPM(4000); // ramp up to 4000 rpm as base so shoot will be faster

         // display target distance when in hunt state
         //SmartDashboard.putNumber("thedistance", Robot.limelight.getDistance());
         
         x = RobotContainer.Limelight.get_Tx();
         //x = x + Robot.limelight.getOffsetRatio();
         SmartDashboard.putNumber("x ai loop", x);
         
         RobotContainer.drive.turnRobotToAngle(x);

         if (Math.abs(x) <= 1){
          // Calc Distance away so we know zone 1 or zone 2

          isuccess = isuccess + 1;
         
          izone = 1; // default
        
          distance = RobotContainer.Limelight.getDistance();
          SmartDashboard.putNumber("thedistance", distance );

          if (distance > 0){
            if (isuccess >= 5) {
            cstate = "SHOOT";
           
            }
           SmartDashboard.putNumber("isuccess", isuccess);
          }
        }

         
         //Check hood angle, and shooter speed based on zone
         break;
        case "SHOOT" : 
           

           isuccess = 0;

           if (distance <=200){ //1205
              RobotContainer.shooter.hoodDown();
             irpm = 4000;
             RobotContainer.shooter.setShooterPIDInfrontOfLine();

           }

           else if ((distance > 200) && (distance <= 300)){ //120, 259
             irpm = 4000;

             RobotContainer.shooter.hoodDown();
             RobotContainer.shooter.setShooterPIDInitiationLine();
             
           }

           else if ((distance > 300) && (distance <= 350)){//259, 450
             irpm = 5000;
             RobotContainer.shooter.hoodUp();
             RobotContainer.shooter.setShooterPIDTrench();
           }

           else if ((distance > 350) && (distance <= 400)){//259, 450
            irpm = 5500;
            RobotContainer.shooter.hoodUp();
            RobotContainer.shooter.setShooterPIDTrench();
          }

           else if ((distance > 400) && (distance <= 500)){//259, 450
            irpm = 5750;
            RobotContainer.shooter.hoodUp();
            RobotContainer.shooter.setShooterPIDTrench();
          }

          else if ((distance > 500) && (distance <= 600)){//259, 450
            irpm = 5750;
            RobotContainer.shooter.hoodDown();
            RobotContainer.shooter.setShooterPIDTrench();
          }

           else{
             irpm = 6000;
             RobotContainer.shooter.hoodUp();
             RobotContainer.shooter.setShooterPIDTrenchBack();
           }

           // Fire up the Shooter
           RobotContainer.shooter.setShooterRPM(irpm);
           SmartDashboard.putNumber("irpm", irpm);
           
           SmartDashboard.putNumber("shooterRPM", RobotContainer.shooter.getShooterRPM() );
           SmartDashboard.putNumber("thedistance", distance );
           double droppedIrpm = irpm - 50;

           if (RobotContainer.shooter.getShooterRPM() >= droppedIrpm) {
              // We are at speed, Turn on feeders 
              RobotContainer.elevator.setElevator(0.5);
              RobotContainer.hopper.setHopper(-0.4);
              RobotContainer.intake.setIntake(0.4);
          }
        break;
      }
      
     } 
     else { // button not pressed
        if (cstate == "SHOOT") { // RECOVERY STATE
           // Button no longer pressed but last state was shoot
           // Turn off Shooter and Feeders
           RobotContainer.shooter.setShooterRPM(0);
           RobotContainer.elevator.stopElevator();
           RobotContainer.hopper.stopHopper();
           RobotContainer.intake.stopIntake();
           
           RobotContainer.shooter.hoodDown();

           RobotContainer.drive.HighGear();

           RobotContainer.led.setBlack();
           RobotContainer.Limelight.limelightOff();

           cstate = "HUNT";
           izone = 1;
           irpm = 0;

        } else { // Reset back to HUNT State
           cstate = "HUNT";
           izone = 1;
           irpm = 0;
           if (RobotContainer.Limelight.isLimelightOn()){
            RobotContainer.Limelight.limelightOff();
           }
        }

     }
*/
  }

  protected void set_colors() {
/*
    SmartDashboard.putString("Game Data", getGamedata());
    SmartDashboard.putBoolean("Intake On", RobotContainer.intake.isIntakeOn());
    
    if (RobotContainer.intake.isIntakeOn() && (getGamedata() != ""))  { // Intake On, Have Game Data
      if (iloops <= 50){
        RobotContainer.Led.setWhite();
        iloops = iloops +1;    
      } else {
        setColorWheel();
        iloops = iloops +1;
    
        if (iloops == 100){
          iloops = 0;
        }
      }
    } else if (RobotContainer.intake.isIntakeOn() && (getGamedata() == "")) { // Intake On, no Game Data  
      RobotContainer.Led.setWhite(); 
    } else if (RobotContainer.Limelight.is_Target() && (getGamedata() != "")){ // Have Target, Have Game Data
      if (iloops <= 50){
        RobotContainer.Led.setViolet();
        iloops = iloops +1;    
      } else {
        setColorWheel();
        iloops = iloops +1;
        if (iloops == 100){
          iloops = 0;
        }
      }
          
    } else if (RobotContainer.Limelight.is_Target() && (getGamedata() == "")){ // Have Target, No Game Data
      RobotContainer.Led.setViolet();
    } else { // Lights Off
      if (RobotContainer.drive.IsLow()) {
        RobotContainer.Led.setLavaWave();
      } else {   
        RobotContainer.Led.setBlack();
      }
    }
  */
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  
  public String getGamedata(){
    return DriverStation.getGameSpecificMessage();
  }


  public void setColorWheel(){
    String gameData;
    gameData = DriverStation.getGameSpecificMessage();
    if(gameData.length() > 0)
    {
      switch (gameData.charAt(0))
      {
        case 'B' :
        RobotContainer.Led.setBlue();
          break;
        case 'G' :
        RobotContainer.Led.setGreen();
          break;
        case 'R' :
        RobotContainer.Led.setRed();
          break;
        case 'Y' :
        RobotContainer.Led.setYellow();
          break;
        default :
        RobotContainer.Led.setBlack();
          break;
      }
    } else {
      //Code for no data received yet
    }
  }
}
